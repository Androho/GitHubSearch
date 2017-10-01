package ua.com.superdeal.githubsearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GitHubService {
    @GET("search/users")
    Call<Repo> getData(@Query ("q") String name);
}