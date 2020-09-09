package com.sallyjayz.gads2020leadershipboard.api;

import com.sallyjayz.gads2020leadershipboard.model.LearnerHours;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface HoursAPI {

    @GET("api/hours")
    Call<List<LearnerHours>> getLearnerHours();

}
