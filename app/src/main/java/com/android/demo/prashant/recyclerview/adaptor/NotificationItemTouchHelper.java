package com.android.demo.prashant.recyclerview.adaptor;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;


import com.android.demo.prashant.recyclerview.model.ModelClass;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by PRASHANT KOLI on 8/22/2016.
 */
public class NotificationItemTouchHelper extends ItemTouchHelper.SimpleCallback {
    RecycleViewAdaptor recyclerViewAdaptor;
    ArrayList<ModelClass> modelList;

    public NotificationItemTouchHelper(RecycleViewAdaptor recyclerViewAdaptor, ArrayList<ModelClass> modelList) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.recyclerViewAdaptor=recyclerViewAdaptor;
        this.modelList=modelList;
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//        return false;

        //following code is written for drag and drop the list item

        final int fromPosition = viewHolder.getAdapterPosition();
        final int toPosition = target.getAdapterPosition();
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(modelList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(modelList, i, i - 1);
            }
        }
        recyclerViewAdaptor.notifyItemMoved(fromPosition, toPosition);
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        recyclerViewAdaptor.removeNotificationItem(viewHolder.getAdapterPosition());
    }
}
