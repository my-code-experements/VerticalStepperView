package com.liefery.android.vertical_stepper_view;

import ohos.agp.components.BaseItemProvider;
import ohos.agp.components.Component;
import ohos.agp.components.ComponentContainer;
import ohos.agp.components.ComponentParent;
import ohos.app.Context;
import ohos.utils.PlainArray;

import java.util.Optional;

import static com.liefery.android.vertical_stepper_view.VerticalStepperItemView.*;


public abstract class VerticalStepperAdapter extends BaseItemProvider {
    private int focus = 0;


    private PlainArray<Component> contentViews = new PlainArray<Component>(getCount());

    public VerticalStepperAdapter(Context context) {
        for (int i = 0; i < getCount(); i++) {
            getContentView(context, i);
        }
    }

    public int getState(int position) {
        if (position == focus)
            return STATE_ACTIVE;
        else if (position < focus)
            return STATE_COMPLETE;
        else
            return STATE_INACTIVE;
    }


    private int getCircleNumber(int position) {
        return position + 1;
    }

    private boolean showConnectorLine(int position) {
        return position < getCount() - 1;
    }

    private Component getContentView(Context context, int position) {
        int id = (int) getItemId(position);
        Optional<Component> contentView = contentViews.get(id);

        if (!contentView.isPresent()) {
            Component contentView2 = onCreateContentView(context, position);
            contentViews.put(id, contentView2);
            return contentView2;
        }

        return contentView.get();
    }

    public void invalidateContentView(int position) {
        int id = (int) getItemId(position);
        contentViews.remove(id);
        notifyDataSetItemChanged(position);
    }

    private void applyData(
            Context context,
            VerticalStepperItemView itemView,
            int position) {
        Component currentContentView = itemView.getContentView();
        Component contentView = getContentView(context, position);

        if (currentContentView != contentView) {
            // Make sure the content view isn't attached to a foreign parent
            ComponentParent parent = (ComponentParent) contentView.getComponentParent();
            if (parent != null)
                parent.removeComponent(contentView);

            itemView.setContentView(contentView);
        }

        itemView.setState(getState(position));
        itemView.setCircleNumber(getCircleNumber(position));
        itemView.setTitle(getTitle(position));
        itemView.setSummary(getSummary(position));
        itemView.setEditable(isEditable(position));
        itemView.setShowConnectorLine(showConnectorLine(position));
    }


    public int getFocus() {
        return focus;
    }

    public void jumpTo(int position) {
        if (focus != position) {
            focus = position;
            notifyDataSetItemChanged(position);
        }
    }

    public boolean hasPrevious() {
        return focus > 0;
    }


    public void previous() {
        if (hasPrevious()) {
            jumpTo(focus - 1);
        }
    }

    public boolean hasNext() {
        return focus < getCount() - 1;
    }


    public void next() {
        if (hasNext()) {
            jumpTo(focus + 1);
        }
    }


    //#region abstract
    public abstract Component onCreateContentView(Context context, int position);

    public abstract CharSequence getTitle(int position);

    public abstract CharSequence getSummary(int position);

    public abstract boolean isEditable(int position);
    //#endregion abstract
    //#region overrides


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Component getComponent(int position, Component convertComponent, ComponentContainer parent) {
        Context context = parent.getContext();
        VerticalStepperItemView itemView;
        if (convertComponent == null) {
            itemView = new VerticalStepperItemView(context);
        }else{
            itemView = (VerticalStepperItemView) convertComponent;
        }
        applyData(context,itemView,position);
        return itemView;
    }
    //#endregion overrides

}
