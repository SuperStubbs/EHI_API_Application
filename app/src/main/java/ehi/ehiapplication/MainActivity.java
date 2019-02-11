package ehi.ehiapplication;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ehi.ehiapplication.ui.view.RepoDetailsFragment;
import ehi.ehiapplication.viewmodels.HomeViewModel;
import ehi.ehiapplication.viewmodels.RepoListViewModel;
import ehi.ehiapplication.ui.view.HomeFragment;
import ehi.ehiapplication.ui.view.RepoListFragment;
import ehi.ehiapplication.viewmodels.RepoViewModel;

public class MainActivity extends AppCompatActivity implements HomeViewModel.OnClickListeners, RepoViewModel.OnDetailsClickListeners {

    private RepoListViewModel repoListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, HomeFragment.newInstance())
                    .commitNow();
        }

        repoListViewModel = ViewModelProviders.of(this).get(RepoListViewModel.class);

    }

    public void replaceFragment(Fragment fragmentToShow) {
        Bundle args = new Bundle();
        fragmentToShow.setArguments(args);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragmentToShow)
                .addToBackStack(null);

        fragmentTransaction.commit();
    }

    public void addFragment(Fragment fragmentToAdd) {
        Bundle args = new Bundle();
        fragmentToAdd.setArguments(args);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction()
                .add(R.id.container, fragmentToAdd)
                .addToBackStack(null);

        fragmentTransaction.commit();
    }

    @Override
    public void onViewReposClick() {
        replaceFragment(RepoListFragment.newInstance());
    }

    @Override
    public void onViewDetailsClick() {
        replaceFragment(RepoDetailsFragment.newInstance());
    }
}
