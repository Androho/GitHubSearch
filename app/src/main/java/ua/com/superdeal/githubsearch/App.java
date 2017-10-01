package ua.com.superdeal.githubsearch;


import android.app.Application;

import java.util.Queue;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

public class App extends Application{
    private Retrofit retrofit;
    private static GitHubService gitHubService;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofit=new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gitHubService = retrofit.create(GitHubService.class);
    }

    public static GitHubService getApi() {
        return gitHubService;
    }
}