package ehi.ehiapplication.ui.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ehi.ehiapplication.R;
import ehi.ehiapplication.databinding.RepoDetailBinding;
import ehi.ehiapplication.models.Repo;
import ehi.ehiapplication.viewmodels.RepoViewModel;

public class RepoDetailsFragment extends Fragment {

    @BindView(R.id.textView)
    TextView tv;

    RepoDetailBinding binding;
    RepoViewModel repoViewModel;

    public static RepoDetailsFragment newInstance() {
        return new RepoDetailsFragment();
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        repoViewModel = ViewModelProviders.of(getActivity()).get(RepoViewModel.class);

        final Observer<Repo> repoObserver = newRepo -> {
            if(newRepo != null) {
                tv.setText(repoViewModel.getLastUpdatedDate());
            }
        };

        repoViewModel.getRepo().observe(this, repoObserver);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.repo_detail, container, false);
        binding.setLifecycleOwner(this);
        binding.setRepoViewModel(repoViewModel);

        ButterKnife.bind(this, binding.getRoot());

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
