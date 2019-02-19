package com.rnsit.utopia;

import android.widget.AbsListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
public abstract class EndlessRecyclerOnScrollListener extends RecyclerView.OnScrollListener{

    private static final int TOTAL_ITEM_EACH_LOAD = 5;
    private boolean isScrolling = false;
    private boolean isLastItemReached = false;
    private LinearLayoutManager mLinearLayoutManager;
    private FirebaseFirestore db;

    public EndlessRecyclerOnScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
            isScrolling = true;
        }
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int firstVisibleItemPosition = mLinearLayoutManager.findFirstVisibleItemPosition();
        int visibleItemCount = mLinearLayoutManager.getChildCount();
        int totalItemCount = mLinearLayoutManager.getItemCount();

        if (isScrolling && (firstVisibleItemPosition + visibleItemCount == totalItemCount) && !isLastItemReached) {
            isScrolling = false;
            Query nextQuery = db.collection("Posts")
                    .orderBy("timeStamp",Query.Direction.DESCENDING)
                    .startAfter(MainActivity.lastVisible)
                    .limit(TOTAL_ITEM_EACH_LOAD);
            nextQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> t) {
                    if (t.isSuccessful()) {
                        for (DocumentSnapshot d : t.getResult()) {
                            PostViewObject mPostView = d.toObject(PostViewObject.class);
                            MainActivity.PostViewObject.add(mPostView);
                        }
                        MainActivity.mPostViewAdapter.notifyDataSetChanged();
                        if(!(t.getResult().size()==0))
                            MainActivity.lastVisible = t.getResult().getDocuments().get(t.getResult().size() - 1);

                        if (t.getResult().size()+1< TOTAL_ITEM_EACH_LOAD) {
                            isLastItemReached = true;
                        }
                    }
                }
            });
        }
    }
    public abstract void onLoadMore();
}