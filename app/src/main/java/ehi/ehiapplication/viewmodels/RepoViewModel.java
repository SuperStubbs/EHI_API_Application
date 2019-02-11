package ehi.ehiapplication.viewmodels;

import android.app.Activity;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import ehi.ehiapplication.models.License;
import ehi.ehiapplication.models.Owner;
import ehi.ehiapplication.models.Repo;

public class RepoViewModel extends ViewModel {

    private MutableLiveData<Repo> repo;

    public MutableLiveData<Repo> getRepo() {
        if(repo == null) {
            repo = new MutableLiveData<>();
        }
        return repo;
    }

    public void setRepo(Repo newRepo) {
        if(repo == null) {
            repo = new MutableLiveData<>();
        }
        repo.setValue(newRepo);
    }

    public String getRepoName() {
        String name = "";
        if(repo.getValue() != null){
            name = repo.getValue().getRepoName();
        }
        return name;
    }

    public String getLastUpdatedDate() {
        String date = "";
        if(repo.getValue() != null){
            date = repo.getValue().getLastUpdatedDate();
        }
        return date;
    }

    public Owner getOwner() {
        Owner owner = new Owner();
        if(repo.getValue() != null){
            owner = repo.getValue().getOwner();
        }
        return owner;
    }

    public String getDescription() {
        String desc = "";
        if(repo.getValue() != null){
            desc = repo.getValue().getDescription();
        }
        return desc;
    }

    public String getCreatedDate() {
        String date = "";
        if(repo.getValue() != null){
            date = repo.getValue().getCreatedDate();
        }
        return date;
    }

    public String getRepoLink() {
        String link = "";
        if(repo.getValue() != null){
            link = repo.getValue().getRepoLink();
        }
        return link;
    }

    public License getLicence() {
        License license = new License();
        if(repo.getValue() != null) {
            license = repo.getValue().getLicense();
        }
        return license;
    }
}
