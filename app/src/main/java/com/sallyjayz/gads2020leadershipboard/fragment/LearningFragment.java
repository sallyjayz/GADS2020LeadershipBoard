package com.sallyjayz.gads2020leadershipboard.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sallyjayz.gads2020leadershipboard.R;
import com.sallyjayz.gads2020leadershipboard.adapter.LearnerHoursAdapter;
import com.sallyjayz.gads2020leadershipboard.model.LearnerHours;
import com.sallyjayz.gads2020leadershipboard.client.LearnersAPIClient;
import com.sallyjayz.gads2020leadershipboard.api.HoursAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<LearnerHours> mHoursList;
    private RecyclerView mRecyclerView;
    private LearnerHoursAdapter mLearnerHoursAdapter;
    private HoursAPI mHoursAPI;
    private ProgressBar mProgressBar;
    private TextView emptyView;
    private SwipeRefreshLayout mSwipeRefreshLayout;


    public LearningFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_learning, container, false);

        mRecyclerView = view.findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);

        mProgressBar = view.findViewById(R.id.progress_bar);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefresh);

        emptyView = view.findViewById(R.id.empty_view);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mHoursList = new ArrayList<>();

        mLearnerHoursAdapter = new LearnerHoursAdapter(mHoursList, getContext());
        mRecyclerView.setAdapter(mLearnerHoursAdapter);

        mHoursAPI = LearnersAPIClient.getClient(getContext()).create(HoursAPI.class);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        getHours();

        return view;
    }

    public void getHours() {

        Call<List<LearnerHours>> callHours = mHoursAPI.getLearnerHours();

        callHours.enqueue(new Callback<List<LearnerHours>>() {
            @Override
            public void onResponse(Call<List<LearnerHours>> call, Response<List<LearnerHours>> response) {
                if (!response.isSuccessful()) {
                    emptyView.setVisibility(View.VISIBLE);
                    mProgressBar.setVisibility(View.GONE);
                    return;
                }

                List<LearnerHours> retrofitLearners = response.body();

                for (LearnerHours learners : retrofitLearners) {
                    mHoursList.add(learners);
                }

                mProgressBar.setVisibility(View.GONE);
                mLearnerHoursAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<LearnerHours>> call, Throwable t) {
                emptyView.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }



    @Override
    public void onResume() {
        super.onResume();
        mLearnerHoursAdapter.notifyDataSetChanged();
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onRefresh() {
        mRecyclerView.setAdapter(mLearnerHoursAdapter);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}