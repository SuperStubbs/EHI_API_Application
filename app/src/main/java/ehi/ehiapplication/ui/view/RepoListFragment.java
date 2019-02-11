package ehi.ehiapplication.ui.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
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
import ehi.ehiapplication.models.Repo;
import ehi.ehiapplication.viewmodels.RepoViewModel;
import ehi.ehiapplication.R;
import ehi.ehiapplication.viewmodels.RepoListViewModel;

public class RepoListFragment extends Fragment {

    @BindView(R.id.tvLoading)
    TextView tvLoading;
    @BindView(R.id.repo_recycler_view)
    RecyclerView repo_recycler_view;
    @BindView(R.id.swipe_container)
    SwipeRefreshLayout swipe_container;

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
        repoListViewModel.setViewDetailsClickListener(getActivity());

        final Observer<List<Repo>> repoListObserver = newRepoList -> {
            if(newRepoList != null) {
                repoListViewModel.setIsLoading(false);
                tvLoading.setVisibility(View.GONE);
                repo_recycler_view.setVisibility(View.VISIBLE);
                repoAdapter.setData(repoListViewModel);
            }
        };

        final Observer<Boolean> isLoadingObserver = isLoading -> {
            if(isLoading != null) {
                swipe_container.setRefreshing(isLoading);
            }
        };

        repoListViewModel.getRepoList().observe(this, repoListObserver);
        repoListViewModel.getIsLoading().observe(this, isLoadingObserver);
    }

    @Override
    public void onStart() {
        super.onStart();
        if(repoListViewModel.getRepoList().getValue() == null) {
            repoListViewModel.fetchRepos();
            tvLoading.setVisibility(View.VISIBLE);
            repo_recycler_view.setVisibility(View.GONE);
        } else {
            repoAdapter.setData(repoListViewModel);
            tvLoading.setVisibility(View.GONE);
            repo_recycler_view.setVisibility(View.VISIBLE);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.view_repo_list, container, false);
        binding.setLifecycleOwner(this);
        repoAdapter = new RepoAdapter(ViewModelProviders.of(getActivity()).get(RepoViewModel.class));
        binding.repoRecyclerView.setAdapter(repoAdapter);
        binding.repoRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        ButterKnife.bind(this, binding.getRoot());

        swipe_container.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                repoListViewModel.fetchRepos();
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
