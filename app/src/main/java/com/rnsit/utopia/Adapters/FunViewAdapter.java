package com.rnsit.utopia.Adapters;
import com.rnsit.utopia.AdapterObjects.FunObject;
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

public class FunViewAdapter extends RecyclerView.Adapter<FunViewAdapter.ViewHolder> {
    private ArrayList<FunObject> mFun;
    private Context context;

    public FunViewAdapter(Context context, ArrayList<FunObject> funs) {
        this.context = context;
        mFun= funs;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView eventName,firstName,secondName,firstTeam,secondTeam;
        public ImageView teamImage1,teamImage2;;

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
    public FunViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_layout_fun, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FunViewAdapter.ViewHolder viewHolder, int i) {
        Map<String,Integer> m = new TreeMap<>();
        m.put("Venu Venus",R.drawable.venus);
        m.put("Eart Earth",R.drawable.earth);
        m.put("Merc Mercury",R.drawable.earth);
        m.put("Mars Mars",R.drawable.mars);

        viewHolder.eventName.setTag(mFun.get(i));
        viewHolder.eventName.setText(mFun.get(i).getEventName());
        viewHolder.firstName.setText(mFun.get(i).getFirstName());
        viewHolder.secondName.setText(mFun.get(i).getSecondName());
        viewHolder.teamImage1.setImageResource(m.get(mFun.get(i).getFirstTeam()));
        viewHolder.teamImage2.setImageResource(m.get(mFun.get(i).getSecondTeam()));

    }

    @Override
    public int getItemCount() {
        return mFun.size();
    }

    public void clear(){
        mFun.clear();
        notifyDataSetChanged();
    }

}
