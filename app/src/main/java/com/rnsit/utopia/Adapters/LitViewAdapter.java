package com.rnsit.utopia.Adapters;
import com.rnsit.utopia.AdapterObjects.LitObject;
import com.rnsit.utopia.R;

import android.content.Context;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;


public class LitViewAdapter extends RecyclerView.Adapter<LitViewAdapter.ViewHolder> {
    private ArrayList<LitObject> mLit;
    private Context context;

    public LitViewAdapter(Context context, ArrayList<LitObject> lits) {
        this.context = context;
        mLit = lits;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView eventName,firstName,secondName,firstTeam,secondTeam;

        public ViewHolder(@Nullable View itemView) {
            super(itemView);
            eventName = (TextView) itemView.findViewById(R.id.eventName);
            firstName = (TextView) itemView.findViewById(R.id.firstName);
            secondName = (TextView) itemView.findViewById(R.id.secondName);
        }
    }

    @Override
    public LitViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.my_layout_fun, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(LitViewAdapter.ViewHolder viewHolder, int i) {
        viewHolder.eventName.setTag(mLit.get(i));
        viewHolder.eventName.setText(mLit.get(i).getEventName());
        viewHolder.firstName.setText(mLit.get(i).getFirstName());
        viewHolder.secondName.setText(mLit.get(i).getSecondName());
    }

    @Override
    public int getItemCount() {
        return mLit.size();
    }

    public void clear(){
        mLit.clear();
        notifyDataSetChanged();
    }
}
