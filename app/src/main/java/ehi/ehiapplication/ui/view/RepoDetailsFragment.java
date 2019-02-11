package ehi.ehiapplication.ui.view;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import ehi.ehiapplication.R;
import ehi.ehiapplication.databinding.RepoDetailBinding;
import ehi.ehiapplication.viewmodels.RepoViewModel;

public class RepoDetailsFragment extends Fragment {

    RepoDetailBinding binding;

    public static RepoDetailsFragment newInstance() {
        return new RepoDetailsFragment();
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        RepoViewModel repoViewModel = ViewModelProviders.of(getActivity()).get(RepoViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.repo_detail, container, false);
        binding.setLifecycleOwner(this);

        ButterKnife.bind(this, binding.getRoot());

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
