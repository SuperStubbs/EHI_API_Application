package ehi.ehiapplication.ui.view;

import android.support.v4.app.Fragment;

import ehi.ehiapplication.viewmodels.RepoViewModel;

public class RepoDetailsFragment extends Fragment {

    RepoViewModel repoViewModel;

    public static RepoListFragment newInstance() {
        return new RepoListFragment();
    }
}
