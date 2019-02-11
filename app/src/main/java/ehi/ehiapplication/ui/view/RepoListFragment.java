package ehi.ehiapplication.ui.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ehi.ehiapplication.databinding.ViewRepoListBinding;
import ehi.ehiapplication.viewmodels.RepoViewModel;
import ehi.ehiapplication.R;
import ehi.ehiapplication.viewmodels.RepoListViewModel;

public class RepoListFragment extends Fragment {

    @BindView(R.id.tvLoading)
    TextView tvLoading;
    @BindView(R.id.repo_recycler_view)
    RecyclerView repo_recycler_view;

    RepoListViewModel repoListViewModel;
    ViewRepoListBinding binding;
    RepoAdapter repoAdapter;

    public static RepoListFragment newInstance() {
        return new RepoListFragment();
    }

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);

        repoListViewModel = ViewModelProviders.of(getActivity()).get(RepoListViewModel.class);

        final Observer<List<RepoViewModel>> repoListObserver = newRepoList -> {
            if(newRepoList != null) {
                tvLoading.setVisibility(View.GONE);
                repo_recycler_view.setVisibility(View.VISIBLE);
                repoAdapter.setData(repoListViewModel);
            }
        };

        repoListViewModel.getRepoList().observe(this, repoListObserver);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.view_repo_list, container, false);
        binding.setLifecycleOwner(this);
        repoAdapter = new RepoAdapter();
        binding.repoRecyclerView.setAdapter(repoAdapter);
        binding.repoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ButterKnife.bind(this, binding.getRoot());

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
