package com.rnsit.utopia.Adapters;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.rnsit.utopia.AdapterObjects.PostViewObject;
import com.rnsit.utopia.R;

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

    public PostViewAdapter(Context context, ArrayList<PostViewObject> posts) {
        this.context = context;
        mPosts = posts;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView postDetail,timeStamp;
        ImageView imagePostIV;

        ViewHolder(@Nullable View itemView) {
            super(itemView);
            postDetail = (TextView) itemView.findViewById(R.id.postDetail);
            timeStamp = (TextView) itemView.findViewById(R.id.timeStamp);
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

        String time = String.valueOf(mPosts.get(i).getTimeStamp());
        String hr = time.substring(8,10);
        String min = time.substring(10,12);

        RequestOptions myOptions = new RequestOptions()
                .override(1300,730)
                .fitCenter();

        if(Integer.parseInt(hr)==12){
            int h = Integer.parseInt(hr);
            time = String.valueOf(h)+":"+min+" PM";
        }
        else if(Integer.parseInt(hr)>12){
            int h = Integer.parseInt(hr)-12;
            time = String.valueOf(h)+":"+min+" PM";
        }else {
            time = hr+":"+min+" AM";
        }

        viewHolder.postDetail.setTag(mPosts.get(i));
        viewHolder.postDetail.setText(mPosts.get(i).getPostDetail());
        Glide.with(context)
                .load(mPosts.get(i).getImagePostURL())
                .apply(myOptions)
                .into(viewHolder.imagePostIV);
        viewHolder.timeStamp.setText(time);
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }

    public void clear(){
        mPosts.clear();
        notifyDataSetChanged();
    }

    public void addAll(ArrayList<PostViewObject> posts){
        mPosts.addAll(posts);
    }

}