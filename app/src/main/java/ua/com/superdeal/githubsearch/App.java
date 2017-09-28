package ua.com.superdeal.githubsearch;


import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application{
    private Retrofit retrofit;
    private static GitHubService gitHubService;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofit=new Retrofit.Builder()
                .baseUrl("http://github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        GitHubService service = retrofit.create(GitHubService.class);
    }

    public static GitHubService getApi() {
        return gitHubService;
    }
}