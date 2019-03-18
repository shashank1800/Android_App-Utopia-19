package com.rnsit.utopia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.rnsit.utopia.AdapterObjects.PostViewObject;
import com.rnsit.utopia.AdapterObjects.RecentPostObject;
import com.rnsit.utopia.EndlessRecycler.EndlessRecyclerOnScrollListener;
import com.rnsit.utopia.Adapters.PostViewAdapter;
import com.rnsit.utopia.Adapters.RecentPostViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private Context context;
    private NavigationView navigationView;

    private RecyclerView mRecyclerViewPost,mRecyclerViewRecentPost;
    public static PostViewAdapter mPostViewAdapter;
    private RecentPostViewAdapter mRecentPostViewAdapter;
    private LinearLayoutManager linearLayoutManager1,linearLayoutManager2;
    public static ArrayList<PostViewObject> mPostViewObject;
    private ArrayList<RecentPostObject> mRecentPostObject;

    private FirebaseFirestore db;
    private Query query;

    private static final int TOTAL_ITEM_EACH_LOAD = 4;
    public static DocumentSnapshot lastVisible;

    private ShimmerFrameLayout mShimmerViewContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        mShimmerViewContainer.startShimmer();

        mRecentPostObject = new ArrayList<RecentPostObject>();
        mPostViewObject = new ArrayList<PostViewObject>();

        mRecyclerViewRecentPost = (RecyclerView) findViewById(R.id.main_recent_post);
        mRecyclerViewPost = (RecyclerView) findViewById(R.id.main_posts);
        mRecyclerViewPost.setNestedScrollingEnabled(false);

        linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        mRecyclerViewRecentPost.setLayoutManager(linearLayoutManager1);
        mRecentPostViewAdapter = new RecentPostViewAdapter(this, mRecentPostObject);
        mRecyclerViewRecentPost.setAdapter(mRecentPostViewAdapter);
        mRecentPostObject.add(new RecentPostObject(1,"aads"));
        mRecentPostObject.add(new RecentPostObject(1,"aads"));
        mRecentPostObject.add(new RecentPostObject(1,"aads"));
        mRecentPostObject.add(new RecentPostObject(1,"aads"));
        mRecentPostObject.add(new RecentPostObject(1,"aads"));
        mRecentPostObject.add(new RecentPostObject(1,"aads"));
        mRecentPostViewAdapter.notifyDataSetChanged();

        linearLayoutManager2 = new LinearLayoutManager(this);
        linearLayoutManager2.setOrientation(RecyclerView.VERTICAL);
        mRecyclerViewPost.setLayoutManager(linearLayoutManager2);
        mPostViewAdapter = new PostViewAdapter(this, mPostViewObject);
        mRecyclerViewPost.setAdapter(mPostViewAdapter);

        db = FirebaseFirestore.getInstance();
        query = db.collection("Posts")
                .orderBy("timeStamp", Query.Direction.DESCENDING)
                .limit(TOTAL_ITEM_EACH_LOAD);
        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (DocumentSnapshot documentSnapshot : task.getResult()) {
                        PostViewObject mPostView = documentSnapshot.toObject(PostViewObject.class);
                        mPostViewObject.add(mPostView);
                    }
                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);
                    mPostViewAdapter.notifyDataSetChanged();
                    if (!(task.getResult().size() == 0))
                        lastVisible = task.getResult().getDocuments().get(task.getResult().size() - 1);

                    mRecyclerViewPost.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager2) {
                        @Override
                        public void onLoadMore() {
                        }
                    });
                }
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.nav_teams:
                Intent intent = new Intent(context, Teams.class);
                startActivity(intent);
                break;
            case R.id.nav_events:
                intent = new Intent(context, Events.class);
                startActivity(intent);
                break;
            case R.id.nav_results:
                intent = new Intent(context, Results.class);
                startActivity(intent);
                break;
            case R.id.nav_contact:
                intent = new Intent(context, Contact.class);
                startActivity(intent);
                break;

            case R.id.nav_about_utopia:
                intent = new Intent(context, AboutApp.class);
                startActivity(intent);
                break;
            case R.id.nav_feedback_text:
                intent = new Intent(context, feedback.class);
                startActivity(intent);
                break;
            case R.id.nav_shareapp:
                intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app!");
                intent.setType("text/plain");
                startActivity(intent);
                break;
            case R.id.nav_aboutdev:
                intent = new Intent(context, AboutDev.class);
                startActivity(intent);
                break;
        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {

        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
