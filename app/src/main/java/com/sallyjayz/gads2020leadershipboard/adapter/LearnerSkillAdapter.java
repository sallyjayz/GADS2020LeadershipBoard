package com.sallyjayz.gads2020leadershipboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.sallyjayz.gads2020leadershipboard.R;
import com.sallyjayz.gads2020leadershipboard.model.LearnerSkill;

import java.util.List;

public class LearnerSkillAdapter extends RecyclerView.Adapter<LearnerSkillAdapter.LearnerSkillViewHolder> {

    private List<LearnerSkill> mLearnerSkills;
    private Context mContext;

    public LearnerSkillAdapter(List<LearnerSkill> learnerSkills, Context context) {
        this.mLearnerSkills = learnerSkills;
        mContext = context;
    }

    @NonNull
    @Override
    public LearnerSkillAdapter.LearnerSkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_item, parent, false);

        LearnerSkillViewHolder viewHolder = new LearnerSkillViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearnerSkillAdapter.LearnerSkillViewHolder holder, int position) {

        LearnerSkill learnerSkill = mLearnerSkills.get(position);
        String url = learnerSkill.getUrl();

        Glide.with(mContext)
                .load(url)
                .fitCenter()
                .into(holder.mBadgeImage);

        holder.mLearnerName.setText(learnerSkill.getName());
        holder.mLearnerDetails.setText(learnerSkill.getScore()
                + " skill IQ Score, " + learnerSkill.getCountry());


    }

    @Override
    public int getItemCount() {
        return mLearnerSkills.size();
    }

    public class LearnerSkillViewHolder extends RecyclerView.ViewHolder {

        private ImageView mBadgeImage;
        private TextView mLearnerName, mLearnerDetails;

        public LearnerSkillViewHolder(@NonNull View itemView) {
            super(itemView);

            mBadgeImage = itemView.findViewById(R.id.leaders_badge);
            mLearnerName = itemView.findViewById(R.id.learner_name);
            mLearnerDetails = itemView.findViewById(R.id.learner_details);

        }
    }
}
