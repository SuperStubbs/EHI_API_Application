package ehi.ehiapplication.viewmodels;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import ehi.ehiapplication.Data.RepoFactory;
import ehi.ehiapplication.Data.RepoService;
import ehi.ehiapplication.models.Repo;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RepoListViewModel extends ViewModel {

    private MutableLiveData<List<Repo>> repoList;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    public MutableLiveData<List<Repo>> getRepoList() {
        if(repoList == null) {
            repoList = new MutableLiveData<>();
        }
        return repoList;
    }

    public void fetchRepos(){
        RepoService repoService = RepoFactory.create();

        Disposable disposable = repoService.fetchRepos(RepoFactory.EXTENSION_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repoResponse -> repoList.setValue(repoResponse), throwable -> {
                });
        compositeDisposable.add(disposable);
    }

    public int getSizeOfRepos() {
        if(repoList.getValue() != null) {
            return repoList.getValue().size();
        } else {
            return 0;
        }
    }

    public Repo getRepoAtPosition(int position) {
        if(repoList.getValue() != null && position <= repoList.getValue().size()){
            return repoList.getValue().get(position);
        } else {
            return null;
        }
    }

    public void onRepoClicked(Repo selectedRepo) {
        String s = selectedRepo.getRepoName();
    }
}
