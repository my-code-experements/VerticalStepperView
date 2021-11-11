package com.liefery.android.vertical_stepper_view;

import ohos.agp.components.*;
import ohos.agp.render.Canvas;
import ohos.agp.utils.Color;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import static ohos.agp.components.ComponentContainer.LayoutConfig.MATCH_CONTENT;
import static ohos.agp.components.ComponentContainer.LayoutConfig.MATCH_PARENT;

public class VerticalStepperItemView extends StackLayout implements Component.DrawTask, Component.LayoutRefreshedListener {
    private static final HiLogLabel LABEL_LOG = new HiLogLabel(HiLog.LOG_APP
            , 0x00201, "-MainAbility-");
    //#region static variables
    public static int STATE_ACTIVE = 1;
    public static int STATE_COMPLETE = 2;
    public static int STATE_INACTIVE = 0;
    //#endregion static variables

    //#region internal state
    private boolean showConnectorLine = true;
    private boolean editable = false;
    private int state = STATE_INACTIVE;
    private int number;
    //#endregion internal state

    //#region components
    private VerticalStepperItemCircleView circle;
    private DirectionalLayout wrapper;
    private Text title;
    private Text summary;
    private StackLayout contentWrapper;
    private ConnectorLineDrawer connector;
    //#endregion components

    //#region constructor

    public VerticalStepperItemView(Context context) {
        super(context);
        initialize(context);
    }

    public VerticalStepperItemView(Context context, AttrSet attrSet) {
        super(context, attrSet);
        initialize(context);
    }

    public VerticalStepperItemView(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
        initialize(context);
    }

    //#endregion constructor

    private void initialize(Context context) {
        setClipEnabled(false);
        addDrawTask(this);
        setLayoutRefreshedListener(this);


        int padding = Util.dpToPx(context, 8);
        setPadding(padding, padding, padding, 0);

        LayoutScatter.getInstance(context).parse(ResourceTable.Layout_vertical_stepper_view_item, this, true);

        circle = (VerticalStepperItemCircleView) findComponentById(ResourceTable.Id_vertical_stepper_view_item_circle);
        wrapper = (DirectionalLayout) findComponentById(ResourceTable.Id_vertical_stepper_view_item_wrapper);
        title = (Text) findComponentById(ResourceTable.Id_vertical_stepper_view_item_title);
        summary = (Text) findComponentById(ResourceTable.Id_vertical_stepper_view_item_summary);
        contentWrapper = (StackLayout) findComponentById(ResourceTable.Id_vertical_stepper_view_item_content_wrapper);

        connector = new ConnectorLineDrawer(context);

    }

    public boolean getShowConnectorLine() {
        return showConnectorLine;
    }

    public void setShowConnectorLine(boolean show) {
        showConnectorLine = show;
        setMarginBottom(state == STATE_ACTIVE);
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;

        if (state == STATE_COMPLETE)
            if (isEditable())
                circle.setIconEdit();
            else
                circle.setIconCheck();
    }

    public void setCircleNumber(int number) {
        this.number = number;

        if (state != STATE_COMPLETE)
            circle.setNumber(number);
    }

    public void setTitle(CharSequence title) {
        this.title.setText(title.toString());
    }

    public void setSummary(CharSequence summary) {
        this.summary.setText(summary.toString());

        if (state == STATE_COMPLETE)
            this.summary.setVisibility(VISIBLE);
    }

    public Component getContentView() {
        return contentWrapper.getComponentAt(0);
    }

    public void setContentView(Component view) {
        contentWrapper.removeAllComponents();
        ;
        contentWrapper.addComponent(view, MATCH_PARENT, MATCH_CONTENT);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;

        if (state == STATE_INACTIVE) {
            setStateInactive();
        } else if (state == STATE_ACTIVE) {
            setStateActive();
        } else {
            setStateComplete();
        }
    }

    private void setStateInactive() {
        circle.setIconEdit();
        setMarginBottom(false);
        circle.setNumber(number);
        circle.setBackgroundInactive();


        title.setTextColor(new Color(getContext().getColor(ResourceTable.Color_vertical_stepper_view_black_38)));
//        title.setTypeface(title.getTypeface(), Typeface.NORMAL);
        summary.setVisibility(INVISIBLE);
        contentWrapper.setVisibility(HIDE);
    }


    private void setStateActive() {
        circle.setIconEdit();
        setMarginBottom(true);
        circle.setNumber(number);
        circle.setBackgroundActive();
        title.setTextColor(new Color(getContext().getColor(ResourceTable.Color_vertical_stepper_view_black_87)));
//        title.setTypeface( title.getTypeface(), Typeface.BOLD );
        summary.setVisibility(HIDE);
        contentWrapper.setVisibility(VISIBLE);
    }

    private void setStateComplete() {
        setMarginBottom(false);
        circle.setBackgroundActive();

        if (isEditable())
            circle.setIconEdit();
        else
            circle.setIconCheck();

        title.setTextColor(new Color(getContext().getColor(ResourceTable.Color_vertical_stepper_view_black_87)));
//        title.setTypeface( title.getTypeface(), Typeface.BOLD );
        summary.setVisibility(summary.getText().isEmpty() ? HIDE : VISIBLE);
        contentWrapper.setVisibility(HIDE);
    }

    private void setMarginBottom(boolean active) {
        ComponentContainer.LayoutConfig params = wrapper.getLayoutConfig();
        if (!getShowConnectorLine())
            params.setMarginBottom(0);
        else if (active)
            params.setMarginBottom((int) Util.dpToPx(getContext(), 48));
        else
            params.setMarginBottom(Util.dpToPx(getContext(), 40));
    }


    @Override
    public void onDraw(Component component, Canvas canvas) {
        HiLog.warn(LABEL_LOG, "VerticalStepperItemView: onDraw");
        if (showConnectorLine) connector.draw(canvas);
    }

    @Override
    public void onRefreshed(Component component) {
        HiLog.warn(LABEL_LOG, "VerticalStepperItemView: onRefreshed");
        connector.adjust(getContext(), component.getWidth(), component.getHeight(), number);
        component.invalidate();
    }
}
