package com.rnsit.utopia;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;


public class PostViewAdapter extends RecyclerView.Adapter<PostViewAdapter.ViewHolder> {
    private ArrayList<PostViewObject> mPosts;
    private Context context;

    public PostViewAdapter(Context context, ArrayList<PostViewObject> Department) {
        this.context = context;
        mPosts = Department;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView postDetail;
        public ImageView imagePostIV;

        public ViewHolder(@Nullable View itemView) {
            super(itemView);
            postDetail = (TextView) itemView.findViewById(R.id.postDetail);
            imagePostIV = (ImageView) itemView.findViewById(R.id.imagePostIV);
        }
    }

    @Override
    public PostViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_layout_postview, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PostViewAdapter.ViewHolder viewHolder, int i) {
        RequestOptions myOptions = new RequestOptions()
                .override(500, 500)
                .fitCenter();

        viewHolder.postDetail.setTag(mPosts.get(i));
        viewHolder.postDetail.setText(mPosts.get(i).getPostDetail());
        Glide.with(context).load(mPosts.get(i).getImagePostURL()).apply(myOptions).into(viewHolder.imagePostIV);
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

}