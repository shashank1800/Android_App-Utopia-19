package com.rnsit.utopia;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.rnsit.utopia.AdapterObjects.PostViewObject;
import com.rnsit.utopia.EndlessRecycler.EndlessRecyclerOnScrollListener;
import com.rnsit.utopia.Adapters.PostViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;
    private Context context;
    private NavigationView navigationView;

    private RecyclerView mRecyclerViewPost;
    @SuppressLint("StaticFieldLeak")
    public static PostViewAdapter mPostViewAdapter;
    private LinearLayoutManager linearLayoutManager1;
    public static ArrayList<PostViewObject> mPostViewObject;

    private FirebaseFirestore db;

    private static final int TOTAL_ITEM_EACH_LOAD = 4;
    public static DocumentSnapshot lastVisible;

    private ShimmerFrameLayout mShimmerViewContainer;
    private SwipeRefreshLayout mySwipeRefreshLayout;
    public static boolean isScrollListenerEnabled = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        toolbar = findViewById(R.id.toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mySwipeRefreshLayout = findViewById(R.id.swiperefresh);
        mShimmerViewContainer = findViewById(R.id.shimmer_view_container);
        mShimmerViewContainer.startShimmer();

        mPostViewObject = new ArrayList<>();
        mRecyclerViewPost = findViewById(R.id.main_posts);

        linearLayoutManager1 = new LinearLayoutManager(this);
        linearLayoutManager1.setOrientation(RecyclerView.VERTICAL);
        mRecyclerViewPost.setLayoutManager(linearLayoutManager1);
        mPostViewAdapter = new PostViewAdapter(this, mPostViewObject);
        mRecyclerViewPost.setAdapter(mPostViewAdapter);
        mRecyclerViewPost.setItemAnimator(new DefaultItemAnimator());
        mRecyclerViewPost.getRecycledViewPool().setMaxRecycledViews(1,0);

        updatePost();
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        mySwipeRefreshLayout.setRefreshing(false);
                        mShimmerViewContainer.setVisibility(View.VISIBLE);
                        mShimmerViewContainer.startShimmer();
                        mPostViewAdapter.clear();
                        mPostViewObject.clear();
                        updatePost();
                    }
                }
        );
    }

    public void updatePost(){

        if(!isNetworkAvailable())
            Snackbar.make(findViewById(R.id.linearLayout), "No internet Connection", Snackbar.LENGTH_SHORT).show();

        db = FirebaseFirestore.getInstance();
        Query query = db.collection("Posts")
                .orderBy("timeStamp", Query.Direction.DESCENDING)
                .limit(TOTAL_ITEM_EACH_LOAD);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    mPostViewObject.clear();
                    for (DocumentSnapshot documentSnapshot : Objects.requireNonNull(task.getResult())) {
                        PostViewObject mPostView = documentSnapshot.toObject(PostViewObject.class);
                        mPostViewObject.add(mPostView);
                    }
                    mPostViewAdapter.notifyDataSetChanged();
                    mShimmerViewContainer.stopShimmer();
                    mShimmerViewContainer.setVisibility(View.GONE);

                    if (!(task.getResult().size() == 0))
                        lastVisible = task.getResult().getDocuments().get(task.getResult().size() - 1);

                    mRecyclerViewPost.addOnScrollListener(new EndlessRecyclerOnScrollListener(linearLayoutManager1) {
                        @Override
                        public void onLoadMore() {
                        }
                    });
                }
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
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
                showFeedbackDialog();
                break;
            case R.id.nav_shareapp:
                intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, "Hey, download this app!");
                intent.setType("text/plain");
                final String appPackageName = getPackageName();
                try {
                    intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=" + appPackageName);
                } catch (ActivityNotFoundException ignored) { }
                startActivity(intent);
                break;
            case R.id.nav_aboutdev:
                showAboutDevDialog();
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
            finish();
        }
    }

    private void showFeedbackDialog() {
        final ViewGroup viewGroup = findViewById(android.R.id.content);
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.activity_feedback, viewGroup, false);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Feedback");
        builder.setPositiveButton(R.string.submit, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText feedback_text = dialogView.findViewById(R.id.feedback_text);
                if(feedback_text.getText().toString().compareTo("")==0)
                    Snackbar.make(findViewById(R.id.linearLayout), "Please enter feedback text", Snackbar.LENGTH_SHORT).show();
                else {
                    Snackbar.make(findViewById(R.id.linearLayout), "Thanks for your Feedback!", Snackbar.LENGTH_SHORT).show();
                    String uniqueID = UUID.randomUUID().toString();
                    String feedbackText = feedback_text.getText().toString();

                    Map<String, Object> feedback = new HashMap<>();
                    feedback.put("feedbackText", feedbackText);

                    db = FirebaseFirestore.getInstance();
                    db.collection("Feedback").document(uniqueID).set(feedback);

                    InputMethodManager keyboard = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
                    keyboard.hideSoftInputFromWindow(viewGroup.getWindowToken(), 0);
                }
            }
        });

        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showAboutDevDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.activity_about_dev, viewGroup, false);

        ImageView dev_photo = dialogView.findViewById(R.id.photo);
        Glide.with(this)
                .load(R.drawable.my_photo)
                .apply(RequestOptions.circleCropTransform())
                .into(dev_photo);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        dialogView.findViewById(R.id.fb).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlFb="https://www.facebook.com/shashankbhat114";
                String appFb="fb://facewebmodal/f?href="+urlFb;

                try {
                    if (isAppInstalled(context, "com.facebook.orca") || isAppInstalled(context, "com.facebook.katana")
                            || isAppInstalled(context, "com.example.facebook") || isAppInstalled(context, "com.facebook.android")) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(appFb)));
                    } else {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlFb)));
                    }
                }catch (Exception e){e.printStackTrace();}
            }
        });

        dialogView.findViewById(R.id.ln).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlLn="https://www.linkedin.com/in/shashank-bhat-924b1bb9/";
                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(urlLn));
                    intent.setPackage("com.linkedin.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException anfe)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlLn)));
                }

            }
        });

        dialogView.findViewById(R.id.insta).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String appIs="http://instagram.com/shashank_bhat__";
                String urlIs="http://instagram.com/_u/shashank_bhat__";

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(appIs));
                    intent.setPackage("com.instagram.android");
                    startActivity(intent);
                }
                catch (ActivityNotFoundException anfe)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlIs)));
                }
            }
        });
    }
    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
