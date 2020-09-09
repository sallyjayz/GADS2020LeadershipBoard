package com.sallyjayz.gads2020leadershipboard.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.sallyjayz.gads2020leadershipboard.R;
import com.sallyjayz.gads2020leadershipboard.adapter.LearnerSkillAdapter;
import com.sallyjayz.gads2020leadershipboard.client.LearnersAPIClient;
import com.sallyjayz.gads2020leadershipboard.api.SkillAPI;
import com.sallyjayz.gads2020leadershipboard.model.LearnerSkill;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private List<LearnerSkill> mLearnerSkills;
    private RecyclerView mRecyclerView;
    private LearnerSkillAdapter mLearnerSkillAdapter;
    private SkillAPI mSkillAPI;
    private ProgressBar mProgressBar;
    private TextView emptyView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public SkillsFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skills, container, false);

        mRecyclerView = view.findViewById(R.id.recycler);
        mRecyclerView.setHasFixedSize(true);

        mProgressBar = view.findViewById(R.id.progress_bar);
        emptyView = view.findViewById(R.id.empty_view);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefresh);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                RecyclerView.VERTICAL, false);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        mLearnerSkills = new ArrayList<>();

        mLearnerSkillAdapter = new LearnerSkillAdapter(mLearnerSkills, getContext());
        mRecyclerView.setAdapter(mLearnerSkillAdapter);

        mSkillAPI = LearnersAPIClient.getClient(getContext()).create(SkillAPI.class);

        mSwipeRefreshLayout.setOnRefreshListener(this);

        getSkill();

        return view;
    }

    public void getSkill() {

        Call<List<LearnerSkill>> callSkills = mSkillAPI.getLearnerSkill();

        callSkills.enqueue(new Callback<List<LearnerSkill>>() {
            @Override
            public void onResponse(Call<List<LearnerSkill>> call, Response<List<LearnerSkill>> response) {
                if (!response.isSuccessful()) {
                    emptyView.setVisibility(View.VISIBLE);
                    mProgressBar.setVisibility(View.GONE);
                    return;
                }

                List<LearnerSkill> retrofitLearners = response.body();

                Log.d("LearningFragment", "onResponse: " + retrofitLearners);

                for (LearnerSkill learners : retrofitLearners) {
                    Log.d("LearningFragment", "onResponse: " + learners);
                    mLearnerSkills.add(learners);
                }
                mProgressBar.setVisibility(View.GONE);
                mLearnerSkillAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<LearnerSkill>> call, Throwable t) {
                emptyView.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }



    @Override
    public void onResume() {
        super.onResume();
        mLearnerSkillAdapter.notifyDataSetChanged();
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void onRefresh() {
        mRecyclerView.setAdapter(mLearnerSkillAdapter);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}