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

import java.util.ArrayList;

public class Results extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    public BottomNavigationView navigationView;
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private Context context;

    private RecyclerView mRecyclerViewPost;
    protected static PostViewAdapter mPostViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    protected static ArrayList<PostViewObject> PostViewObject;
    private FirebaseFirestore db;
    private Query query;
    private static final int TOTAL_ITEM_EACH_LOAD = 7;
    public static DocumentSnapshot lastVisible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        navigationView.setOnNavigationItemSelectedListener(this);

        PostViewObject = new ArrayList<PostViewObject>();

        mRecyclerViewPost = (RecyclerView) findViewById(R.id.main_posts);
        mRecyclerViewPost.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerViewPost.setLayoutManager(linearLayoutManager);
        mPostViewAdapter = new PostViewAdapter(this,PostViewObject);
        mRecyclerViewPost.setAdapter(mPostViewAdapter);
        navigationView.setSelectedItemId(R.id.sports);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.sports:
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
                });
                break;
            case R.id.fun:mPostViewAdapter.clear();
                break;
        }
        return true;
    }
}
