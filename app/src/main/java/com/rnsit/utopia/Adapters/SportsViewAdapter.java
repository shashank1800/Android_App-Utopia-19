package com.rnsit.utopia.Adapters;

import com.rnsit.utopia.AdapterObjects.SportsObject;
import com.rnsit.utopia.R;

import android.content.Context;

import androidx.annotation.NonNull;
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

public class SportsViewAdapter extends RecyclerView.Adapter<SportsViewAdapter.ViewHolder> {
    private ArrayList<SportsObject> mSports;
    private Context context;

    public SportsViewAdapter(Context context, ArrayList<SportsObject> sports) {
        this.context = context;
        mSports = sports;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView eventName,subHead,winnerName;
        public ImageView teamImage1,teamImage2;

        public ViewHolder(@Nullable View itemView) {
            super(itemView);
            eventName = (TextView) itemView.findViewById(R.id.eventName);
            subHead = (TextView) itemView.findViewById(R.id.subHead);
            winnerName = (TextView) itemView.findViewById(R.id.winnerName);
            teamImage1 = (ImageView)itemView.findViewById(R.id.teamImage1);
            teamImage2 = (ImageView)itemView.findViewById(R.id.teamImage2);
        }
    }

    @Override
    public SportsViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_layout_sports, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SportsViewAdapter.ViewHolder viewHolder, int i) {
        Map<String,Integer> teams = new TreeMap<String,Integer>();
        teams.put("Elegant Egyptians",R.drawable.venus);
        teams.put("Radiant Romans",R.drawable.earth);
        teams.put("Shielding Spartans",R.drawable.mercury);
        teams.put("Vigorous Vikings",R.drawable.mars);
        viewHolder.eventName.setTag(mSports.get(i));
        viewHolder.eventName.setText(mSports.get(i).getEventName());
        viewHolder.subHead.setText(mSports.get(i).getSubHead());
        viewHolder.teamImage1.setImageResource(teams.get(mSports.get(i).getTeamName1()));
        viewHolder.teamImage2.setImageResource(teams.get(mSports.get(i).getTeamName2()));
        viewHolder.winnerName.setText(mSports.get(i).getWinnerName()+" Won");
    }

    @Override
    public int getItemCount() {
        return mSports.size();
    }

    public void clear(){
        mSports.clear();
        notifyDataSetChanged();
    }
}

