package com.rnsit.utopia.Adapters;

import com.rnsit.utopia.AdapterObjects.CoordinatorObject;
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


public class CoordinatorAdapter extends RecyclerView.Adapter<CoordinatorAdapter.ViewHolder> {
    private ArrayList<CoordinatorObject> mCoordinatorObject;
    private Context context;

    public CoordinatorAdapter(Context context, ArrayList<CoordinatorObject> coordinatorObject) {
        this.context = context;
        mCoordinatorObject= coordinatorObject;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView eventName,boyName,boyPhone,girlName,girlPhone;
        ImageView boyPhoto,girlPhoto;

        public ViewHolder(@Nullable View itemView) {
            super(itemView);
            eventName = (TextView) itemView.findViewById(R.id.eventName);
            boyPhoto = (ImageView)itemView.findViewById(R.id.boyPhoto);
            girlPhoto = (ImageView)itemView.findViewById(R.id.girlPhoto);
            boyName = (TextView) itemView.findViewById(R.id.boyName);
            boyPhone = (TextView) itemView.findViewById(R.id.boyPhone);
            girlName = (TextView) itemView.findViewById(R.id.girlName);
            girlPhone = (TextView) itemView.findViewById(R.id.girlPhone);
        }
    }

    @Override
    public CoordinatorAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_layout_event_coordinator, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CoordinatorAdapter.ViewHolder viewHolder, int i) {

        viewHolder.eventName.setText(mCoordinatorObject.get(i).getEventName());
        viewHolder.boyName.setText(mCoordinatorObject.get(i).getBoyName());
        viewHolder.boyPhone.setText(mCoordinatorObject.get(i).getBoyPhone());
        viewHolder.girlName.setText(mCoordinatorObject.get(i).getGirlName());
        viewHolder.girlPhone.setText(mCoordinatorObject.get(i).getGirlPhone());
        viewHolder.boyPhoto.setImageResource(mCoordinatorObject.get(i).getBoyPhoto());
        viewHolder.girlPhoto.setImageResource(mCoordinatorObject.get(i).getGirlPhoto());

    }

    @Override
    public int getItemCount() {
        return mCoordinatorObject.size();
    }
}
