package ehi.ehiapplication.data;

import java.util.List;

import ehi.ehiapplication.viewmodels.RepoViewModel;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RepoService {

    @GET
    Observable<List<RepoViewModel>> fetchRepos(@Url String url);
}
