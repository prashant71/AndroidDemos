package com.android.demo.prashant;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.demo.prashant.camera.CameraActivity;
import com.android.demo.prashant.gcm.GCMActivity;
import com.android.demo.prashant.recyclerview.adaptor.NotificationClickListener;
import com.android.demo.prashant.recyclerview.MyRecyclerView;
import com.android.demo.prashant.share.ShareActivity;
import com.android.demo.prashant.viewpager.WelcomeActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.android.demo.prashant.gcm.RegistrationIntentService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<String> demoList=new ArrayList<>();
    {
        demoList.add("ViewPager(App Intro) Demo");
        demoList.add("GCM Demo");
        demoList.add("RecyclerView and CardView Demo");
        demoList.add("Share Image/Video Demo");
        demoList.add("Camera2 API Demo");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (checkPlayServices()) {
            Intent serviceIntent = new Intent(this, RegistrationIntentService.class);
            this.startService(serviceIntent);
        }


        recyclerView=(android.support.v7.widget.RecyclerView) findViewById(R.id.demolist);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        DemoListAdaptor demoListAdaptor=new DemoListAdaptor(MainActivity.this,demoList);
        recyclerView.setAdapter(demoListAdaptor);

        recyclerView.addOnItemTouchListener(new NotificationClickListener(getApplicationContext(), recyclerView,new NotificationClickListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Toast.makeText(getApplicationContext(), demoList.get(position).toString() + " is selected!", Toast.LENGTH_SHORT).show();
                callDemo(position);
            }


            @Override
            public void onLongClick(View view, int position) {
//                String notification = demoList.get(position).toString();
//                Toast.makeText(getApplicationContext(), notification + " is long pressed!", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private void callDemo(int position) {
        switch (position) {
            case 0:
                /** ViewPager Demo */
                Intent intent0 = new Intent(this, WelcomeActivity.class);
                this.startActivity(intent0);
                break;
            case 1:
                /** GCM Demo */
                Intent intent1= new Intent(this, GCMActivity.class);
                this.startActivity(intent1);
                break;
            case 2:
                /** RecyclerView Demo */
                Intent intent2 = new Intent(this, MyRecyclerView.class);
                this.startActivity(intent2);
                break;
            case 3:
                /** Share Image/Video Demo */
                Intent intent3 = new Intent(this, ShareActivity.class);
                this.startActivity(intent3);
                break;
            case 4:
                /** Glide Image Download Demo */
                Intent intent4 = new Intent(this, CameraActivity.class);
                this.startActivity(intent4);
                break;
        }


    }


    private class DemoListAdaptor extends RecyclerView.Adapter<DemoListAdaptor.ItemViewHolder> {

        List<String> demoList;
        Context context;
        public DemoListAdaptor(Context context,List<String> demoList) {
            this.demoList = demoList;
            this.context=context;
        }

        @Override
        public DemoListAdaptor.ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.demo_cardview, viewGroup, false);
            DemoListAdaptor.ItemViewHolder itemViewHolder=new DemoListAdaptor.ItemViewHolder(v);
            return itemViewHolder;
        }

        @Override
        public void onBindViewHolder(final DemoListAdaptor.ItemViewHolder holder, int position) {
            holder.textView.setText(demoList.get(position).toString());
        }

        @Override
        public int getItemCount() {
            return demoList.size();
        }

        public class ItemViewHolder extends RecyclerView.ViewHolder {
            CardView cv;
            TextView textView;

            ItemViewHolder(View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.democv);
                textView = (TextView)itemView.findViewById(R.id.demo_title);
            }
        }
    }
















    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        9000).show();
            } else {
                finish();
            }
            return false;
        }
        return true;
    }

}
