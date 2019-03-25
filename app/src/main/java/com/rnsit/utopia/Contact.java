package com.rnsit.utopia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import com.rnsit.utopia.AdapterObjects.CoordinatorObject;
import com.rnsit.utopia.Adapters.CoordinatorAdapter;

import java.util.ArrayList;
import java.util.Objects;

public class Contact extends AppCompatActivity{

    private Context context;
    private RecyclerView rec_coordinators;

    public CoordinatorAdapter mCoordinatorAdapter;
    private LinearLayoutManager linearLayoutManager1;
    private ArrayList<CoordinatorObject> mCoordinatorObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        context= this;

        rec_coordinators = (RecyclerView)findViewById(R.id.rec_coordinators);
        mCoordinatorObject = new ArrayList<CoordinatorObject>();
        linearLayoutManager1 = new LinearLayoutManager(context);
        linearLayoutManager1.setOrientation(RecyclerView.VERTICAL);
        rec_coordinators.setLayoutManager(linearLayoutManager1);
        mCoordinatorAdapter = new CoordinatorAdapter(context, mCoordinatorObject);
        rec_coordinators.setAdapter(mCoordinatorAdapter);
        mCoordinatorObject.add(new CoordinatorObject("Cultural",R.drawable.cultboy,"Rahul Shetkar","8880877635",R.drawable.my_photo,"",""));
        mCoordinatorObject.add(new CoordinatorObject("Fun",R.drawable.funboy,"Sharan Chhibber","7044142526",R.drawable.my_photo,"",""));
        mCoordinatorObject.add(new CoordinatorObject("Literature",R.drawable.my_photo,"","",R.drawable.litgirl,"Meghana V P ","9900207255"));
        mCoordinatorObject.add(new CoordinatorObject("Sports",R.drawable.sportsboy,"Eekshith","9490453239",R.drawable.my_photo,"",""));
        mCoordinatorObject.add(new CoordinatorObject("Technical",R.drawable.techboy,"Varun Harish","9986780267",R.drawable.techgirl,"Sheetal Vernekar","9632660327"));
        mCoordinatorAdapter.notifyDataSetChanged();
    }
}