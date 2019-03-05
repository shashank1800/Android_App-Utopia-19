package com.rnsit.utopia.Adapters;
import com.rnsit.utopia.AdapterObjects.CultObject;
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


public class CultViewAdapter extends RecyclerView.Adapter<CultViewAdapter.ViewHolder> {
    private ArrayList<CultObject> mCult;
    private Context context;

    public CultViewAdapter(Context context, ArrayList<CultObject> cults) {
        this.context = context;
        mCult= cults;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView eventName,firstName,secondName,firstTeam,secondTeam;
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
    public CultViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_layout_fun, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CultViewAdapter.ViewHolder viewHolder, int i) {

        Map<String,Integer> m = new TreeMap<>();
        m.put("Venu Venus",R.drawable.venus);
        m.put("Eart Earth",R.drawable.earth);
        m.put("Merc Mercury",R.drawable.earth);
        m.put("Mars Mars",R.drawable.mars);

        viewHolder.eventName.setTag(mCult.get(i));
        viewHolder.eventName.setText(mCult.get(i).getEventName());
        viewHolder.firstName.setText(mCult.get(i).getFirstName());
        viewHolder.secondName.setText(mCult.get(i).getSecondName());
        viewHolder.teamImage1.setImageResource(m.get(mCult.get(i).getFirstTeam()));
        viewHolder.teamImage2.setImageResource(m.get(mCult.get(i).getSecondTeam()));

    }

    @Override
    public int getItemCount() {
        return mCult.size();
    }

    public void clear(){
        mCult.clear();
        notifyDataSetChanged();
    }
}