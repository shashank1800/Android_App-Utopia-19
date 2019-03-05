package com.rnsit.utopia.Adapters;

import com.rnsit.utopia.AdapterObjects.CFLObject;
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


public class CFLViewAdapter extends RecyclerView.Adapter<CFLViewAdapter.ViewHolder> {
    private ArrayList<CFLObject> mCFLObject;
    private Context context;

    public CFLViewAdapter(Context context, ArrayList<CFLObject> cflObjects) {
        this.context = context;
        mCFLObject= cflObjects;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView eventName,firstName,secondName;
        public ImageView teamImage1,teamImage2;

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
    public CFLViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_layout_cfl, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CFLViewAdapter.ViewHolder viewHolder, int i) {

        Map<String,Integer> m = new TreeMap<>();
        m.put("Venu Venus",R.drawable.venus);
        m.put("Eart Earth",R.drawable.earth);
        m.put("Merc Mercury",R.drawable.earth);
        m.put("Mars Mars",R.drawable.mars);

        viewHolder.eventName.setTag(mCFLObject.get(i));
        viewHolder.eventName.setText(mCFLObject.get(i).getEventName());
        viewHolder.firstName.setText(mCFLObject.get(i).getFirstName());
        viewHolder.secondName.setText(mCFLObject.get(i).getSecondName());
        viewHolder.teamImage1.setImageResource(m.get(mCFLObject.get(i).getFirstTeam()));
        viewHolder.teamImage2.setImageResource(m.get(mCFLObject.get(i).getSecondTeam()));

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
