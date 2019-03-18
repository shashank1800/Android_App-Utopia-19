package com.rnsit.utopia.Adapters;

import com.bumptech.glide.Glide;
import com.rnsit.utopia.AdapterObjects.PostViewObject;
import com.rnsit.utopia.AdapterObjects.RecentPostObject;
import com.rnsit.utopia.R;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;


public class RecentPostViewAdapter extends RecyclerView.Adapter<RecentPostViewAdapter.ViewHolder> {
    private ArrayList<RecentPostObject> mRecentPosts;
    private Context context;

    public RecentPostViewAdapter(Context context, ArrayList<RecentPostObject> RecentPosts) {
        this.context = context;
        mRecentPosts = RecentPosts;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageRecentPostIV;

        public ViewHolder(@Nullable View itemView) {
            super(itemView);
            imageRecentPostIV = (ImageView) itemView.findViewById(R.id.imageRecentPostIV);
        }
    }

    @Override
    public RecentPostViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_layout_recent_post, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecentPostViewAdapter.ViewHolder viewHolder, int i) {

        /*Glide.with(context)
                .load(mRecentPosts.get(i).getImagePostURL())
                .into(viewHolder.imageRecentPostIV);*/
    }

    @Override
    public int getItemCount() {
        return mRecentPosts.size();
    }

    public void clear(){
        mRecentPosts.clear();
        notifyDataSetChanged();
    }

}