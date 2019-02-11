package ehi.ehiapplication.viewmodels;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import ehi.ehiapplication.data.RepoFactory;
import ehi.ehiapplication.data.RepoService;
import ehi.ehiapplication.models.Repo;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class RepoListViewModel extends ViewModel {

    private MutableLiveData<List<Repo>> repoList;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<Boolean> isLoading;

    public MutableLiveData<List<Repo>> getRepoList() {
        if(repoList == null) {
            repoList = new MutableLiveData<>();
        }
        return repoList;
    }

    public void fetchRepos(){
        isLoading.setValue(Boolean.TRUE);
        RepoService repoService = RepoFactory.create();

        Disposable disposable = repoService.fetchRepos(RepoFactory.EXTENSION_URL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repoResponse -> repoList.setValue(repoResponse), throwable -> {
                });
        compositeDisposable.add(disposable);
    }

    public MutableLiveData<Boolean> getIsLoading() {
        if(isLoading == null){
            isLoading = new MutableLiveData<>();
        }
        return isLoading;
    }

    public void setIsLoading(Boolean isLoading) {
        this.isLoading.setValue(isLoading);
    }

    public int getSizeOfRepos() {
        if(repoList.getValue() != null) {
            return repoList.getValue().size();
        } else {
            return 0;
        }
    }

    public RepoViewModel getRepoViewModelAtPosition(int position) {
        if(repoList.getValue() != null && position <= repoList.getValue().size()){
            RepoViewModel repoViewModel = new RepoViewModel();
            repoViewModel.setRepo(repoList.getValue().get(position));
            return repoViewModel;
        } else {
            return null;
        }
    }

    public Repo getRepoAtPosition(int position) {
        if(repoList.getValue() != null && position <= repoList.getValue().size()){
            return repoList.getValue().get(position);
        } else {
            return null;
        }
    }

    private static OnDetailsClickListeners mCallback;

    public interface OnDetailsClickListeners {
        void onViewDetailsClick();
    }

    public void setViewDetailsClickListener(Activity activity) {
        mCallback = (OnDetailsClickListeners) activity;
    }

    public void onRepoClicked(int position) {
        getRepoViewModelAtPosition(position);
        mCallback.onViewDetailsClick();
    }
}
