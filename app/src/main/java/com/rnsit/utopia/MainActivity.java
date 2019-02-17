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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private Context context;

    private NavigationView navigationView;

    private RecyclerView mRecyclerViewPost;
    private PostViewAdapter mPostViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<PostViewObject> PostViewObject;
    private FirebaseFirestore db;
    private Query query;
    private static final int TOTAL_ITEM_EACH_LOAD = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        PostViewObject = new ArrayList<PostViewObject>();

        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mRecyclerViewPost = (RecyclerView) findViewById(R.id.main_posts);
        mRecyclerViewPost.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(context, RecyclerView.VERTICAL, false);
        mRecyclerViewPost.setLayoutManager(linearLayoutManager);
        mPostViewAdapter = new PostViewAdapter(this,PostViewObject);
        mRecyclerViewPost.setAdapter(mPostViewAdapter);

        loadData();

        mRecyclerViewPost.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {

            @Override
            public void onLoadMore() {
                loadData();
            }

        });
    }

    private void loadData() {
        db = FirebaseFirestore.getInstance();
        query = db.collection("Posts")
                .orderBy("timeStamp",Query.Direction.DESCENDING)
                .limit(TOTAL_ITEM_EACH_LOAD);
        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (DocumentSnapshot posts : queryDocumentSnapshots.getDocuments()) {
                            PostViewObject mPostView = posts.toObject(PostViewObject.class);
                            PostViewObject.add(mPostView);
                        }
                        mPostViewAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.nav_teams) {
            Intent intent = new Intent(context,Teams.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.nav_events) {
            Intent intent = new Intent(context,Events.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.nav_results) {
            Intent intent = new Intent(context,Results.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.nav_contact) {
            Intent intent = new Intent(context,Contact.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.nav_about_utopia) {
            Intent intent = new Intent(context,AboutApp.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.nav_feedback_text) {
            Intent intent = new Intent(context,feedback.class);
            startActivity(intent);
            return true;
        }else if(id == R.id.nav_shareapp) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,"Hey, download this app!");
            intent.setType("text/plain");
            startActivity(intent);
        }else if(id == R.id.nav_aboutdev) {
            Intent intent = new Intent(context,AboutDev.class);
            startActivity(intent);
            return true;
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
