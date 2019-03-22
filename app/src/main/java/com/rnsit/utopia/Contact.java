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
        linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(RecyclerView.VERTICAL);
        rec_coordinators.setLayoutManager(linearLayoutManager1);
        mCoordinatorAdapter = new CoordinatorAdapter(this, mCoordinatorObject);
        rec_coordinators.setAdapter(mCoordinatorAdapter);
        mCoordinatorObject.add(new CoordinatorObject("Cultural",R.drawable.my_photo,"Shashank Bhat","+91 77655555211",R.drawable.my_photo,"Hjdfjdjddj ","+91 7760254155"));
        mCoordinatorObject.add(new CoordinatorObject("Fun",R.drawable.my_photo,"Shashank Bhat","+91 77655555211",R.drawable.my_photo,"Hjdfjdjddj ","+91 7760254155"));
        mCoordinatorObject.add(new CoordinatorObject("Literature",R.drawable.my_photo,"Shashank Bhat","+91 77655555211",R.drawable.my_photo,"Hjdfjdjddj ","+91 7760254155"));
        mCoordinatorObject.add(new CoordinatorObject("Sports",R.drawable.my_photo,"Shashank Bhat","+91 77655555211",R.drawable.my_photo,"Hjdfjdjddj ","+91 7760254155"));
        mCoordinatorObject.add(new CoordinatorObject("Technical",R.drawable.my_photo,"Shashank Bhat","+91 77655555211",R.drawable.my_photo,"Hjdfjdjddj ","+91 7760254155"));
        mCoordinatorAdapter.notifyDataSetChanged();

    }
}

