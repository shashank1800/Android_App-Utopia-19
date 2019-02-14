package com.rnsit.utopia;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private Context context;

    private RecyclerView mRecyclerViewPost;
    private PostViewAdapter mPostViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ArrayList<PostViewObject> PostViewObject;

    private DatabaseReference mDatabaseRef;

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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);

        mRecyclerViewPost = (RecyclerView) findViewById(R.id.main_posts);
        mRecyclerViewPost.setHasFixedSize(true);

        linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, true);

        mRecyclerViewPost.setLayoutManager(linearLayoutManager);


        PostViewObject = new ArrayList<PostViewObject>();
        mPostViewAdapter = new PostViewAdapter(this,PostViewObject);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        Query myTopPostsQuery = mDatabaseRef.child("Posts");
        myTopPostsQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                PostViewObject.clear();
                for(DataSnapshot posts:dataSnapshot.getChildren()) {
                    PostViewObject mPostView = posts.getValue(PostViewObject.class);
                    PostViewObject.add(mPostView);
                }
                mPostViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        mRecyclerViewPost.setAdapter(mPostViewAdapter);
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
        }else if(id == R.id.nav_shareapp) {
            return true;
        }else if(id == R.id.nav_feedback_text) {
            Intent intent = new Intent(context,feedback.class);
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
