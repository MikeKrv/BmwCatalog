package com.example.mike.bmwcatalog.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.mike.bmwcatalog.CarDetailsActivity;
import com.example.mike.bmwcatalog.R;
import com.example.mike.bmwcatalog.adapters.CarsAdapter;
import com.example.mike.bmwcatalog.model.BmwModel;
import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mike on 30.07.2017.
 */

public class CarsRVFragment extends Fragment implements CarsAdapter.ItemClickListener{
    private final static String TAG = CarsRVFragment.class.getSimpleName();
    private DatabaseReference modelDatabaseReference;
    private static final String CATEGORY = "category";
    private static final String BODY_STYLE = "bodyStyle";
    private static final String NODE_MODELS = "models";
    private static final boolean GRID_LAYOUT = false;
    private CarsAdapter adapter;
    private List<BmwModel> modelsList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private String category;
    private String bodyStyle;
    private ProgressBar progressBar;
    private int orientation;

    public static CarsRVFragment newInstance(String category, String bodyStyle) {
        Bundle args = new Bundle();
        args.putString(CATEGORY, category);
        args.putString(BODY_STYLE, bodyStyle);
        CarsRVFragment carsRVFragment = new CarsRVFragment();
        carsRVFragment.setArguments(args);
        return carsRVFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        orientation = context.getResources().getConfiguration().orientation;
        Log.d(TAG, "onAttach: orientationLand = " + orientation);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreateView: line 64_orienationLand = " + orientation);
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        category = getArguments().getString(CATEGORY);
        bodyStyle = getArguments().getString(BODY_STYLE);
        modelDatabaseReference = FirebaseDatabase.getInstance().getReference().child(NODE_MODELS).child(category).child(bodyStyle);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);

        modelDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               // Log.d(TAG, "Count: " + dataSnapshot.getChildrenCount());

                for (DataSnapshot modelSnapshot : dataSnapshot.getChildren()) {
                    BmwModel model = modelSnapshot.getValue(BmwModel.class);
                    modelsList.add(model);
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: " + databaseError.getMessage());
            }
        });



        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }

        mRecyclerView.setHasFixedSize(true);
        adapter = new CarsAdapter(getContext(), modelsList, orientation, this);
        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(adapter);

    }


    @Override
    public void onItemClicked(CarsAdapter.ModelViewHolder viewHolder) {
       BmwModel model = modelsList.get(viewHolder.getAdapterPosition());
       Intent intent = new Intent(getContext(), CarDetailsActivity.class);
       intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
       startActivity(intent);
    }
}

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//
//        if (mRecyclerView != null) {
//            mRecyclerView.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    mRecyclerView.smoothScrollToPosition(0);
//                    mRecyclerView.
//                }
//            }, 100);
//        }
//    }

