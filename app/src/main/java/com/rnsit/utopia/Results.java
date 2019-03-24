package com.rnsit.utopia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.rnsit.utopia.AdapterObjects.CFLObject;
import com.rnsit.utopia.AdapterObjects.SportsObject;
import com.rnsit.utopia.AdapterObjects.TechObject;
import com.rnsit.utopia.Adapters.CFLViewAdapter;
import com.rnsit.utopia.Adapters.SportsViewAdapter;
import com.rnsit.utopia.Adapters.TechViewAdapter;
import com.rnsit.utopia.EndlessRecycler.CultEndlessRecyclerOnScrollListener;
import com.rnsit.utopia.EndlessRecycler.FunEndlessRecyclerOnScrollListner;
import com.rnsit.utopia.EndlessRecycler.LitEndlessRecyclerOnScrollListner;
import com.rnsit.utopia.EndlessRecycler.SportsEndlessRecyclerOnScrollListner;
import com.rnsit.utopia.EndlessRecycler.TechEndlessRecyclerOnScrollListener;

import java.util.ArrayList;
import java.util.Objects;

public class Results extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    public BottomNavigationView bottomNavigationView;
    private Context context;
    private FirebaseFirestore db;
    private Query query;
    private static final int TOTAL_ITEM_EACH_LOAD = 10;
    public static DocumentSnapshot lastVisible;

    public static RecyclerView mRecyclerFun,mRecyclerSports,mRecyclerCult,mRecyclerTech,mRecyclerLit;
    public static SportsViewAdapter mSportsViewAdapter;
    public static TechViewAdapter mTechViewAdapter;
    public static CFLViewAdapter mCFLViewAdapter;

    private LinearLayoutManager linearLayoutManager1,linearLayoutManager2,linearLayoutManager3,linearLayoutManager4,linearLayoutManager5;

    public static ArrayList<SportsObject> sports;
    public static ArrayList<TechObject> techs;
    public static ArrayList<CFLObject> cflObjects;

    private ShimmerFrameLayout mShimmerViewContainer;
    private LinearLayout not_updated;
    public static boolean isScrollListenerEnabled = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        context = this;

        sports = new ArrayList<SportsObject>();
        techs = new ArrayList<TechObject>();
        cflObjects = new ArrayList<CFLObject>();

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        not_updated = (LinearLayout)findViewById(R.id.not_updated);

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

        mSportsViewAdapter = new SportsViewAdapter(context,sports);
        mTechViewAdapter = new TechViewAdapter(context,techs);
        mCFLViewAdapter = new CFLViewAdapter(context,cflObjects);

        mRecyclerFun.setAdapter(mCFLViewAdapter);
        mRecyclerCult.setAdapter(mCFLViewAdapter);
        mRecyclerSports.setAdapter(mSportsViewAdapter);
        mRecyclerLit.setAdapter(mCFLViewAdapter);
        mRecyclerTech.setAdapter(mTechViewAdapter);

        bottomNavigationView.setSelectedItemId(R.id.bot_sports);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        mShimmerViewContainer.setVisibility(View.VISIBLE);
        mShimmerViewContainer.startShimmer();
        int id = item.getItemId();
        switch (id){
            case R.id.bot_sports:
                ClearAdapter();
                ClearVisibility();
                mRecyclerSports.setVisibility(View.VISIBLE);

                db = FirebaseFirestore.getInstance();
                query = db.collection("Sports")
                        .orderBy("timeStamp", Query.Direction.DESCENDING)
                        .limit(TOTAL_ITEM_EACH_LOAD);
                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        sports.clear();
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                SportsObject  mSportsObject= document.toObject(SportsObject.class);
                                sports.add(mSportsObject);
                            }
                            if(sports.isEmpty())
                                not_updated.setVisibility(View.VISIBLE);
                            mShimmerViewContainer.stopShimmer();
                            mShimmerViewContainer.setVisibility(View.GONE);
                            mSportsViewAdapter.notifyDataSetChanged();

                            if (!(task.getResult().size() == 0))
                                lastVisible = task.getResult().getDocuments().get(task.getResult().size() - 1);

                            mRecyclerSports.addOnScrollListener(new SportsEndlessRecyclerOnScrollListner(linearLayoutManager3) {
                                @Override
                                public void onLoadMore() {
                                }
                            });
                        }
                    }
                });

                break;
            case R.id.bot_fun:
                ClearAdapter();
                ClearVisibility();
                mRecyclerFun.setVisibility(View.VISIBLE);
                db = FirebaseFirestore.getInstance();
                query = db.collection("Fun")
                        .orderBy("timeStamp", Query.Direction.DESCENDING)
                        .limit(TOTAL_ITEM_EACH_LOAD);
                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        cflObjects.clear();
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                CFLObject mCFLObject = document.toObject(CFLObject.class);
                                cflObjects.add(mCFLObject);
                            }
                            if(cflObjects.isEmpty())
                                not_updated.setVisibility(View.VISIBLE);
                            mShimmerViewContainer.startShimmer();
                            mShimmerViewContainer.setVisibility(View.GONE);
                            mCFLViewAdapter.notifyDataSetChanged();
                            if (!(task.getResult().size() == 0))
                                lastVisible = task.getResult().getDocuments().get(task.getResult().size() - 1);

                            mRecyclerFun.addOnScrollListener(new FunEndlessRecyclerOnScrollListner(linearLayoutManager1) {
                                @Override
                                public void onLoadMore() {
                                }
                            });
                        }
                    }
                });
                break;

            case R.id.bot_cultural:
                ClearAdapter();
                ClearVisibility();
                mRecyclerCult.setVisibility(View.VISIBLE);

                db = FirebaseFirestore.getInstance();
                query = db.collection("Cultural")
                        .orderBy("timeStamp", Query.Direction.DESCENDING)
                        .limit(TOTAL_ITEM_EACH_LOAD);
                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        cflObjects.clear();
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                CFLObject mCFLObject = document.toObject(CFLObject.class);
                                cflObjects.add(mCFLObject);
                            }
                            if(cflObjects.isEmpty())
                                not_updated.setVisibility(View.VISIBLE);
                            mShimmerViewContainer.stopShimmer();
                            mShimmerViewContainer.setVisibility(View.GONE);
                            mCFLViewAdapter.notifyDataSetChanged();
                            if (!(task.getResult().size() == 0))
                                lastVisible = task.getResult().getDocuments().get(task.getResult().size() - 1);

                            mRecyclerCult.addOnScrollListener(new CultEndlessRecyclerOnScrollListener(linearLayoutManager2) {
                                @Override
                                public void onLoadMore() {
                                }
                            });
                        }
                    }
                });
                break;

            case R.id.bot_literature:
                ClearAdapter();
                ClearVisibility();
                mRecyclerLit.setVisibility(View.VISIBLE);

                db = FirebaseFirestore.getInstance();
                query = db.collection("Literature")
                        .orderBy("timeStamp", Query.Direction.DESCENDING)
                        .limit(TOTAL_ITEM_EACH_LOAD);
                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        cflObjects.clear();
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                CFLObject mCFLObject = document.toObject(CFLObject.class);
                                cflObjects.add(mCFLObject);
                            }
                            mShimmerViewContainer.stopShimmer();
                            mShimmerViewContainer.setVisibility(View.GONE);
                            mCFLViewAdapter.notifyDataSetChanged();
                            if (!(task.getResult().size() == 0))
                                lastVisible = task.getResult().getDocuments().get(task.getResult().size() - 1);

                            mRecyclerLit.addOnScrollListener(new LitEndlessRecyclerOnScrollListner(linearLayoutManager4) {
                                @Override
                                public void onLoadMore() {
                                }
                            });
                        }
                    }
                });
                break;
            case R.id.bot_technical:
                ClearAdapter();
                ClearVisibility();
                mRecyclerTech.setVisibility(View.VISIBLE);

                db = FirebaseFirestore.getInstance();
                query = db.collection("Technical")
                        .orderBy("timeStamp", Query.Direction.DESCENDING)
                        .limit(TOTAL_ITEM_EACH_LOAD);
                query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        techs.clear();
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                                TechObject mTechObject = document.toObject(TechObject.class);
                                techs.add(mTechObject);
                            }
                            if(techs.isEmpty())
                                not_updated.setVisibility(View.VISIBLE);
                            mShimmerViewContainer.stopShimmer();
                            mShimmerViewContainer.setVisibility(View.GONE);
                            mTechViewAdapter.notifyDataSetChanged();

                            if (!(task.getResult().size() == 0))
                                lastVisible = task.getResult().getDocuments().get(task.getResult().size() - 1);

                            mRecyclerTech.addOnScrollListener(new TechEndlessRecyclerOnScrollListener(linearLayoutManager5) {
                                @Override
                                public void onLoadMore() {
                                }
                            });
                        }
                    }
                });
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

        not_updated.setVisibility(View.GONE);
    }

    private void ClearAdapter() {
        mSportsViewAdapter.clear();
        mTechViewAdapter.clear();
        mCFLViewAdapter.clear();
    }
}
