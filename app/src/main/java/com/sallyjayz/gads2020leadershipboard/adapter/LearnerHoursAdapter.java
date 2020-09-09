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
import com.sallyjayz.gads2020leadershipboard.model.LearnerHours;

import java.util.List;

public class LearnerHoursAdapter extends RecyclerView.Adapter<LearnerHoursAdapter.LearnerHourViewHolder> {

    private List<LearnerHours> mLearnerHours;
    private Context mContext;

    public LearnerHoursAdapter(List<LearnerHours> learnerHours, Context context) {
        this.mLearnerHours = learnerHours;
        mContext = context;
    }

    @NonNull
    @Override
    public LearnerHourViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.list_item, parent, false);

        LearnerHourViewHolder viewHolder = new LearnerHourViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LearnerHourViewHolder holder, int position) {

        LearnerHours learnerHours = mLearnerHours.get(position);
        String url = learnerHours.getUrl();

        Glide.with(mContext)
                .load(url)
                .fitCenter()
                .into(holder.mBadgeImage);

        holder.mLearnerName.setText(learnerHours.getName());
        holder.mLearnerDetails.setText(learnerHours.getHours()
                + " learning hours, " + learnerHours.getCountry());


    }

    @Override
    public int getItemCount() {
        return mLearnerHours.size();
    }

    public class LearnerHourViewHolder extends RecyclerView.ViewHolder {

        private ImageView mBadgeImage;
        private TextView mLearnerName, mLearnerDetails;

        public LearnerHourViewHolder(@NonNull View itemView) {
            super(itemView);

            mBadgeImage = itemView.findViewById(R.id.leaders_badge);
            mLearnerName = itemView.findViewById(R.id.learner_name);
            mLearnerDetails = itemView.findViewById(R.id.learner_details);
        }
    }
}
