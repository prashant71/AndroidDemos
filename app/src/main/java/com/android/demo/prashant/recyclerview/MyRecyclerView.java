package com.android.demo.prashant.recyclerview;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.demo.prashant.R;
import com.android.demo.prashant.recyclerview.adaptor.NotificationClickListener;
import com.android.demo.prashant.recyclerview.adaptor.NotificationItemTouchHelper;
import com.android.demo.prashant.recyclerview.adaptor.RecycleViewAdaptor;
import com.android.demo.prashant.recyclerview.model.ModelClass;

import java.io.File;
import java.util.ArrayList;

public class MyRecyclerView extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<ModelClass> modelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initCollapsingToolbar();
        recyclerView=(android.support.v7.widget.RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        /*RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        recyclerView.setLayoutManager(layoutManager);*/

        /*StaggeredGridLayoutManager stgaggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(stgaggeredGridLayoutManager);*/

        //set view to recyclerview
        modelList=(ArrayList<ModelClass>) new ModelClass().initializeData();
        RecycleViewAdaptor myRecyclerView=new RecycleViewAdaptor(MyRecyclerView.this,modelList);
        recyclerView.setAdapter(myRecyclerView);

        //for swip and drag-drop actionz

        ItemTouchHelper.Callback callback = new NotificationItemTouchHelper(myRecyclerView,modelList);
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(recyclerView);

        //onrecyclerview item touch event
        recyclerView.addOnItemTouchListener(new NotificationClickListener(getApplicationContext(), recyclerView,new NotificationClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                String notification = modelList.get(position).getName();
                Toast.makeText(getApplicationContext(), notification + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                String notification = modelList.get(position).getName();
//                Toast.makeText(getApplicationContext(), notification + " is long pressed!", Toast.LENGTH_SHORT).show();
                Intent share = new Intent(Intent.ACTION_SEND);

                // If you want to share a png image only, you can do:
                // setType("image/png"); OR for jpeg: setType("image/jpeg");
                share.setType("image/*");

                // Make sure you put example png image named myImage.png in your
                // directory
                String imagePath = Environment.getExternalStorageDirectory()
                        + "/android.png";

                File imageFileToShare = new File(imagePath);

                Uri uri = Uri.fromFile(imageFileToShare);
                share.putExtra(Intent.EXTRA_STREAM, uri);

                startActivity(Intent.createChooser(share, "Share Image!"));
            }
        }));

    }

    private void initCollapsingToolbar() {

        final CollapsingToolbarLayout collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.app_bar_layout);
        appBarLayout.setExpanded(true);

        setPalette(collapsingToolbar);
        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

    private void setPalette(final CollapsingToolbarLayout collapsingToolbar) {
        ImageView image=(ImageView) findViewById(R.id.cover);
//        Picasso.with(this).load("http://imgur.com/gallery/9dcMf").into(image);
        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                int primaryDark = getResources().getColor(R.color.colorPrimaryDark);
                int primary = getResources().getColor(R.color.colorPrimary);
                collapsingToolbar.setContentScrimColor(palette.getMutedColor(primary));
                collapsingToolbar.setStatusBarScrimColor(palette.getDarkVibrantColor(primaryDark));
            }
        });
    }
}
