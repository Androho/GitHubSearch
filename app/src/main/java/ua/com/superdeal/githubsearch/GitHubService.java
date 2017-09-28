package ua.com.superdeal.githubsearch;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("users/list")
    Call<List<Repo>> listRepos(@Path("user") String user);
}