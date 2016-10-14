package com.android.demo.prashant.recyclerview.adaptor;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.demo.prashant.R;
import com.android.demo.prashant.Utils.CircleTransform;
import com.android.demo.prashant.recyclerview.model.ModelClass;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;


/**
 * Created by ADMIN on 5/23/2016.
 */
public class RecycleViewAdaptor extends RecyclerView.Adapter<RecycleViewAdaptor.ItemViewHolder> {

    List<ModelClass> modelList;
    Context context;
    public RecycleViewAdaptor(Context context,List<ModelClass> modelList) {
            this.modelList = modelList;
            this.context=context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mycardview, viewGroup, false);
        ItemViewHolder itemViewHolder=new ItemViewHolder(v);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder.name.setText(modelList.get(position).getName());
        holder.tagline.setText(modelList.get(position).getTagline());
//        holder.profile.setImageResource(modelList.get(position).getPhotoId());
        Glide.with(context).load(modelList.get(position).getPhotoId())
                .placeholder(R.drawable.profile)
                .error(R.drawable.profile)
                .skipMemoryCache(false)
                .transform(new CircleTransform(context))
                //.signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.profile);

       /* holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopupMenu(holder.overflow);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public void removeNotificationItem(int position){
        modelList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, modelList.size());
    }

    public void addNotificationtItem(ModelClass model){
        modelList.add(model);
        notifyItemInserted(modelList.size());
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView name;
        TextView tagline;
        ImageView profile;
        ImageView overflow;

        ItemViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            name = (TextView)itemView.findViewById(R.id.person_name);
            tagline = (TextView)itemView.findViewById(R.id.person_age);
            profile = (ImageView)itemView.findViewById(R.id.person_photo);
//            overflow = (ImageView)itemView.findViewById(R.id.overflow);
        }
    }

    private void showPopupMenu(View view) {
        // inflate menu
        PopupMenu popup = new PopupMenu(context, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menuitem, popup.getMenu());
        popup.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popup.show();
    }

    class MyMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

        public MyMenuItemClickListener() {
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.action_add_favourite:
                    Toast.makeText(context, "Add to favourite", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.action_play_next:
                    Toast.makeText(context, "Play next", Toast.LENGTH_SHORT).show();
                    return true;
                default:
            }
            return false;
        }
    }
}
