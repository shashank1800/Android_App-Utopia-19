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
import android.view.View;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.rnsit.utopia.AdapterObjects.CultObject;
import com.rnsit.utopia.AdapterObjects.FunObject;
import com.rnsit.utopia.AdapterObjects.LitObject;
import com.rnsit.utopia.AdapterObjects.SportsObject;
import com.rnsit.utopia.AdapterObjects.TechObject;
import com.rnsit.utopia.Adapters.CultViewAdapter;
import com.rnsit.utopia.Adapters.FunViewAdapter;
import com.rnsit.utopia.Adapters.LitViewAdapter;
import com.rnsit.utopia.Adapters.SportsViewAdapter;
import com.rnsit.utopia.Adapters.TechViewAdapter;

import java.util.ArrayList;

public class Results extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    public BottomNavigationView bottomNavigationView;
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private Context context;
    private FirebaseFirestore db;
    private Query query;
    private static final int TOTAL_ITEM_EACH_LOAD = 7;
    public static DocumentSnapshot lastVisible;

    private RecyclerView mRecyclerFun,mRecyclerSports,mRecyclerCult,mRecyclerTech,mRecyclerLit;

    private FunViewAdapter mFunViewAdapter;
    private SportsViewAdapter mSportsViewAdapter;
    private CultViewAdapter mCultViewAdapter;
    private LitViewAdapter mLitViewAdapter;
    private TechViewAdapter mTechViewAdapter;

    private LinearLayoutManager linearLayoutManager1,linearLayoutManager2,linearLayoutManager3,linearLayoutManager4,linearLayoutManager5;

    private ArrayList<FunObject> funs;
    private ArrayList<SportsObject> sports;
    private ArrayList<CultObject> cults;
    private ArrayList<LitObject> lits;
    private ArrayList<TechObject> techs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        funs = new ArrayList<FunObject>();
        sports = new ArrayList<SportsObject>();
        cults = new ArrayList<CultObject>();
        lits = new ArrayList<LitObject>();
        techs = new ArrayList<TechObject>();

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        mRecyclerFun = (RecyclerView) findViewById(R.id.rec_fun);
        mRecyclerCult = (RecyclerView) findViewById(R.id.rec_cult);
        mRecyclerSports = (RecyclerView) findViewById(R.id.rec_sports);
        mRecyclerLit = (RecyclerView) findViewById(R.id.rec_lit);
        mRecyclerTech = (RecyclerView) findViewById(R.id.rec_tech);

        mRecyclerFun.setHasFixedSize(true);
        mRecyclerCult.setHasFixedSize(true);
        mRecyclerSports.setHasFixedSize(true);
        mRecyclerLit.setHasFixedSize(true);
        mRecyclerTech.setHasFixedSize(true);

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
        mRecyclerCult.setLayoutManager(linearLayoutManager2);
        mRecyclerSports.setLayoutManager(linearLayoutManager3);
        mRecyclerLit.setLayoutManager(linearLayoutManager4);
        mRecyclerTech.setLayoutManager(linearLayoutManager5);

        mFunViewAdapter = new FunViewAdapter(context,funs);
        mCultViewAdapter = new CultViewAdapter(context,cults);
        mSportsViewAdapter = new SportsViewAdapter(context,sports);
        mLitViewAdapter = new LitViewAdapter(context,lits);
        mTechViewAdapter = new TechViewAdapter(context,techs);

        mRecyclerFun.setAdapter(mFunViewAdapter);
        mRecyclerCult.setAdapter(mCultViewAdapter);
        mRecyclerSports.setAdapter(mSportsViewAdapter);
        mRecyclerLit.setAdapter(mLitViewAdapter);
        mRecyclerTech.setAdapter(mTechViewAdapter);

        bottomNavigationView.setSelectedItemId(R.id.bot_sports);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.bot_sports:ClearAdapter();
                ClearVisibility();
                mRecyclerSports.setVisibility(View.VISIBLE);
                sports.add(new SportsObject("Event Name","Finals","Team Name1","teamName2","Team Name1"));
                sports.add(new SportsObject("Event Name","Semi Finals","teamName1","teamName2","Team Name1"));
                sports.add(new SportsObject("Event Name","Finals","teamName1","teamName2","Team Name1"));
                sports.add(new SportsObject("Event Name","","teamName1","teamName2","Team Name1"));
                sports.add(new SportsObject("Event Name","","teamName1","teamName2","Team Name1"));
                mSportsViewAdapter.notifyDataSetChanged();
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
                ClearVisibility();
                mRecyclerFun.setVisibility(View.VISIBLE);
                funs.add(new FunObject("Event Name","First Name","Second Name","1","2"));
                funs.add(new FunObject("Event Name","First Name","Second Name","1","2"));
                funs.add(new FunObject("Event Name","First Name","Second Name","1","2"));
                funs.add(new FunObject("Event Name","First Name","Second Name","1","2"));
                funs.add(new FunObject("Event Name","First Name","Second Name","1","2"));
                mFunViewAdapter.notifyDataSetChanged();
                break;

            case R.id.bot_cultural:ClearAdapter();
                ClearVisibility();
                mRecyclerCult.setVisibility(View.VISIBLE);
                cults.add(new CultObject("Event Name","First Name","Second Name","1","2"));
                cults.add(new CultObject("Event Name","First Name","Second Name","1","2"));
                cults.add(new CultObject("Event Name","First Name","Second Name","1","2"));
                cults.add(new CultObject("Event Name","First Name","Second Name","1","2"));
                cults.add(new CultObject("Event Name","First Name","Second Name","1","2"));
                mCultViewAdapter.notifyDataSetChanged();
                break;
            case R.id.bot_literature:ClearAdapter();
                ClearVisibility();
                mRecyclerLit.setVisibility(View.VISIBLE);
                lits.add(new LitObject("Event Name","First Name","Second Name","1","2"));
                lits.add(new LitObject("Event Name","First Name","Second Name","1","2"));
                lits.add(new LitObject("Event Name","First Name","Second Name","1","2"));
                lits.add(new LitObject("Event Name","First Name","Second Name","1","2"));
                lits.add(new LitObject("Event Name","First Name","Second Name","1","2"));
                mLitViewAdapter.notifyDataSetChanged();
                break;
            case R.id.bot_technical:ClearAdapter();
                ClearVisibility();
                mRecyclerTech.setVisibility(View.VISIBLE);
                techs.add(new TechObject("Event Name","Winner Name","1"));
                techs.add(new TechObject("Event Name","Winner Name","1"));
                techs.add(new TechObject("Event Name","Winner Name","1"));
                techs.add(new TechObject("Event Name","Winner Name","1"));
                techs.add(new TechObject("Event Name","Winner Name","1"));
                mTechViewAdapter.notifyDataSetChanged();
                break;
        }
        return true;
    }

    private void ClearVisibility() {
        mRecyclerFun.setVisibility(View.GONE);
        mRecyclerCult.setVisibility(View.GONE);
        mRecyclerSports.setVisibility(View.GONE);
        mRecyclerLit.setVisibility(View.GONE);
        mRecyclerTech.setVisibility(View.GONE);
    }

    private void ClearAdapter() {
        mFunViewAdapter.clear();
        mCultViewAdapter.clear();
        mSportsViewAdapter.clear();
        mLitViewAdapter.clear();
        mTechViewAdapter.clear();
    }
}
