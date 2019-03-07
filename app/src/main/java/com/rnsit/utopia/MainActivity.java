package com.rnsit.utopia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.rnsit.utopia.AdapterObjects.PostViewObject;
import com.rnsit.utopia.Adapters.EndlessRecyclerOnScrollListener;
import com.rnsit.utopia.Adapters.PostViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private Context context;

    private NavigationView navigationView;

    private RecyclerView mRecyclerViewPost;
    public static PostViewAdapter mPostViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    public static ArrayList<PostViewObject> PostViewObject;
    private FirebaseFirestore db;
    private Query query;

    private static final int TOTAL_ITEM_EACH_LOAD = 7;
    public static DocumentSnapshot lastVisible;

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

        PostViewObject = new ArrayList<PostViewObject>();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mRecyclerViewPost = (RecyclerView) findViewById(R.id.main_posts);
        mRecyclerViewPost.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        mRecyclerViewPost.setLayoutManager(linearLayoutManager);
        mPostViewAdapter = new PostViewAdapter(this, PostViewObject);
        mRecyclerViewPost.setAdapter(mPostViewAdapter);

        /*sharedPreferences = this.getSharedPreferences(getString(R.string.shared_preference_key), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        drawerSwitch = (Switch) navigationView.getMenu().findItem(R.id.saveData).getActionView();
        drawerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    editor.putString("switch","1").apply();
                }
                else
                    editor.putString("switch","0").apply();
            }
        });

        if(!sharedPreferences.getString("switch","").matches("")){
            String is = sharedPreferences.getString("switch","");
            if(is.matches("0")) drawerSwitch.setChecked(false);
            else drawerSwitch.setChecked(true);
        }*/

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
                        PostViewObject.add(mPostView);
                    }
                    mPostViewAdapter.notifyDataSetChanged();
                    if (!(task.getResult().size() == 0))
                        lastVisible = task.getResult().getDocuments().get(task.getResult().size() - 1);

                    mRecyclerViewPost.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager) {
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
        if (id == R.id.nav_teams) {
            Intent intent = new Intent(context, Teams.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_events) {
            Intent intent = new Intent(context, Events.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_results) {
            Intent intent = new Intent(context, Results.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_contact) {
            Intent intent = new Intent(context, Contact.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_about_utopia) {
            Intent intent = new Intent(context, AboutApp.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_feedback_text) {
            Intent intent = new Intent(context, feedback.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.nav_shareapp) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app!");
            intent.setType("text/plain");
            startActivity(intent);
        } else if (id == R.id.nav_aboutdev) {
            Intent intent = new Intent(context, AboutDev.class);
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
