package com.sallyjayz.gads2020leadershipboard.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SubmitAPI {

    String id = "1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";

    @FormUrlEncoded
    @POST(id)
    Call<Void> createSubmitProject(
            @Field("entry.1824927963") String firstName,
            @Field("entry.1877115667") String lastName,
            @Field("entry.2006916086") String emailAddress,
            @Field("entry.284483984") String githubLink

    );

}
