package ua.com.superdeal.githubsearch;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface GitHubService {
    @GET("search/users")
    Observable<Repo> getData(@Query ("q") String name);
}