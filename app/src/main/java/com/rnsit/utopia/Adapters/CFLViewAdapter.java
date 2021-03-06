package com.rnsit.utopia.Adapters;

import com.rnsit.utopia.AdapterObjects.CFLObject;
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


public class CFLViewAdapter extends RecyclerView.Adapter<CFLViewAdapter.ViewHolder> {
    private ArrayList<CFLObject> mCFLObject;
    private Context context;

    public CFLViewAdapter(Context context, ArrayList<CFLObject> cflObjects) {
        this.context = context;
        mCFLObject= cflObjects;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView eventName,firstName,secondName;
        ImageView teamImage1,teamImage2;

        public ViewHolder(@Nullable View itemView) {
            super(itemView);
            eventName = (TextView) itemView.findViewById(R.id.eventName);
            firstName = (TextView) itemView.findViewById(R.id.firstName);
            secondName = (TextView) itemView.findViewById(R.id.secondName);
            teamImage1 = (ImageView)itemView.findViewById(R.id.teamImage1);
            teamImage2 = (ImageView)itemView.findViewById(R.id.teamImage2);
        }
    }

    @Override
    public CFLViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_layout_cfl, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CFLViewAdapter.ViewHolder viewHolder, int i) {

        Map<String,Integer> teams = new TreeMap<String,Integer>();
        teams.put("Elegant Egyptians",R.drawable.ee_icon);
        teams.put("Radiant Romans",R.drawable.rr_icon);
        teams.put("Shielding Spartans",R.drawable.ss_icon);
        teams.put("Vigorous Vikings",R.drawable.vv_icon);

        viewHolder.eventName.setText(mCFLObject.get(i).getEventName());
        viewHolder.firstName.setText(mCFLObject.get(i).getFirstName());
        viewHolder.secondName.setText(mCFLObject.get(i).getSecondName());
        viewHolder.teamImage1.setImageResource(teams.get(mCFLObject.get(i).getFirstTeam()));
        viewHolder.teamImage2.setImageResource(teams.get(mCFLObject.get(i).getSecondTeam()));

    }

    @Override
    public int getItemCount() {
        return mCFLObject.size();
    }

    public void clear(){
        mCFLObject.clear();
        notifyDataSetChanged();
    }
}
