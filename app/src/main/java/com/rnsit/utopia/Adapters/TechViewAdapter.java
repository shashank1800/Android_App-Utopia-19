package com.rnsit.utopia.Adapters;

import com.rnsit.utopia.AdapterObjects.TechObject;
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
import java.util.Map;
import java.util.TreeMap;


public class TechViewAdapter extends RecyclerView.Adapter<TechViewAdapter.ViewHolder> {
    private ArrayList<TechObject> mTech;
    private Context context;

    public TechViewAdapter(Context context, ArrayList<TechObject> tech) {
        this.context = context;
        mTech = tech;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView eventName,partName;
        public ImageView teamImage1;

        public ViewHolder(@Nullable View itemView) {
            super(itemView);
            eventName = (TextView) itemView.findViewById(R.id.eventName);
            partName = (TextView) itemView.findViewById(R.id.partName);
            teamImage1 = (ImageView)itemView.findViewById(R.id.teamImage1);
        }
    }

    @Override
    public TechViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_layout_tech, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TechViewAdapter.ViewHolder viewHolder, int i) {
        Map<String,Integer> m = new TreeMap<>();
        m.put("Venu Venus",R.drawable.venus);
        m.put("Eart Earth",R.drawable.earth);
        m.put("Merc Mercury",R.drawable.mercury);
        m.put("Mars Mars",R.drawable.mars);

        viewHolder.eventName.setTag(mTech.get(i));
        viewHolder.eventName.setText(mTech.get(i).getEventName());
        viewHolder.partName.setText(mTech.get(i).getPartName());
        viewHolder.teamImage1.setImageResource(m.get(mTech.get(i).getTeamName()));
    }

    @Override
    public int getItemCount() {
        return mTech.size();
    }

    public void clear(){
        mTech.clear();
        notifyDataSetChanged();
    }
}
