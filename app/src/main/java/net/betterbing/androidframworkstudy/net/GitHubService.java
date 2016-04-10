package net.betterbing.androidframworkstudy.net;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by aibb on 16/4/9.
 */
public class GitHubService {

    public static class Contributor {
        public final String login;
        public final int contributions;

        public Contributor(String login, int contributions) {
            this.login = login;
            this.contributions = contributions;
        }
    }

    public interface GitHub {
        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> contributors(@Path("owner") String owner, @Path("repo") String repo);

        @GET("/repos/{owner}/{repo}/contributors")
        Call<List<Contributor>> getContributors(@Path("owner") String owner, @Path("repo") String repo, Callback<List<Contributor>> callback);
    }

}
