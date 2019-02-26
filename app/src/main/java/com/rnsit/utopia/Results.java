package com.rnsit.utopia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.rnsit.utopia.AdapterObjects.FunObject;
import com.rnsit.utopia.AdapterObjects.PostViewObject;
import com.rnsit.utopia.AdapterObjects.SportsObject;
import com.rnsit.utopia.AdapterObjects.TechObject;
import com.rnsit.utopia.Adapters.FunViewAdapter;

import java.util.ArrayList;

public class Results extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    public BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private Context context;

    private RecyclerView mRecyclerFun,mRecyclerSports,mRecyclerTech;
    private FunViewAdapter mFunViewAdapter;
    private LinearLayoutManager linearLayoutManager1,linearLayoutManager2,linearLayoutManager3,linearLayoutManager4,linearLayoutManager5;
    private FirebaseFirestore db;
    private Query query;
    private static final int TOTAL_ITEM_EACH_LOAD = 7;
    public static DocumentSnapshot lastVisible;
    private ArrayList<FunObject> funs;
    private ArrayList<SportsObject> sports;
    private ArrayList<TechObject> tech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        funs = new ArrayList<FunObject>();
        sports = new ArrayList<SportsObject>();
        tech = new ArrayList<TechObject>();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        mRecyclerFun = (RecyclerView) findViewById(R.id.results);

        mRecyclerFun.setHasFixedSize(true);

        linearLayoutManager1 = new LinearLayoutManager(context);
        linearLayoutManager1.setOrientation(RecyclerView.VERTICAL);

        linearLayoutManager2 = new LinearLayoutManager(context);
        linearLayoutManager2.setOrientation(RecyclerView.VERTICAL);

        linearLayoutManager3 = new LinearLayoutManager(context);
        linearLayoutManager3.setOrientation(RecyclerView.VERTICAL);

        linearLayoutManager4 = new LinearLayoutManager(context);
        linearLayoutManager4.setOrientation(RecyclerView.VERTICAL);

        linearLayoutManager4 = new LinearLayoutManager(context);
        linearLayoutManager4.setOrientation(RecyclerView.VERTICAL);

        linearLayoutManager5 = new LinearLayoutManager(context);
        linearLayoutManager5.setOrientation(RecyclerView.VERTICAL);

        mRecyclerFun.setLayoutManager(linearLayoutManager1);

        mFunViewAdapter = new FunViewAdapter(this,funs);
        mRecyclerFun.setAdapter(mFunViewAdapter);

        bottomNavigationView.setSelectedItemId(R.id.bot_sports);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.bot_sports:mFunViewAdapter.clear();
                /*mPostViewAdapter = new PostViewAdapter(this,PostViewObject);
                mRecyclerViewPost.setAdapter(mPostViewAdapter);
                mPostViewAdapter.clear();
                db = FirebaseFirestore.getInstance();
                query = db.collection("Posts")
                        .orderBy("timeStamp", Query.Direction.DESCENDING)
                        .limit(TOTAL_ITEM_EACH_LOAD);
                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                PostViewObject mPostView = document.toObject(PostViewObject.class);
                                PostViewObject.add(mPostView);
                            }
                            mPostViewAdapter.notifyDataSetChanged();
                        }
                    }
                });*/
                break;
            case R.id.bot_fun: ClearAdapter();
                funs.add(new FunObject("Event Name","First Name","Second Name","1","2"));
                funs.add(new FunObject("Event Name","First Name","Second Name","1","2"));
                funs.add(new FunObject("Event Name","First Name","Second Name","1","2"));
                funs.add(new FunObject("Event Name","First Name","Second Name","1","2"));
                funs.add(new FunObject("Event Name","First Name","Second Name","1","2"));
                mFunViewAdapter.notifyDataSetChanged();
                break;

            case R.id.bot_cultural:ClearAdapter();
                break;
            case R.id.bot_literature:ClearAdapter();
                break;
            case R.id.bot_technical:ClearAdapter();
                break;
        }
        return true;
    }

    private void ClearAdapter() {
        mFunViewAdapter.clear();
    }
}
