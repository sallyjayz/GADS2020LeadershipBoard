package com.sallyjayz.gads2020leadershipboard.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.sallyjayz.gads2020leadershipboard.R;
import com.sallyjayz.gads2020leadershipboard.fragment.LearningFragment;
import com.sallyjayz.gads2020leadershipboard.fragment.SkillsFragment;

public class LeadershipFragmentPagerAdapter extends FragmentStateAdapter {

    final int PAGE_COUNT = 2;
    @StringRes
    private static final int[] TAB_TITLES = new int[] { R.string.learning_leaders, R.string.skill_leaders};


    public LeadershipFragmentPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new LearningFragment();
                break;
            case 1:
                fragment = new SkillsFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return PAGE_COUNT;
    }

    /*public LeadershipFragmentPagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.context = context;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new LearningFragment();
                break;
            case 1:
                fragment = new SkillsFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return context.getResources().getString(TAB_TITLES[position]);
    }*/
}
