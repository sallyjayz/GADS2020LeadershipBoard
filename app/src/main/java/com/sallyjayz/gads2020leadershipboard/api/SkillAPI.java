package com.sallyjayz.gads2020leadershipboard.api;

import com.sallyjayz.gads2020leadershipboard.model.LearnerSkill;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SkillAPI {

    @GET("api/skilliq")
    Call<List<LearnerSkill>> getLearnerSkill();

}
