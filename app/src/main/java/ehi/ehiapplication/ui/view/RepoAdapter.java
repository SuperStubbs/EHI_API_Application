package ehi.ehiapplication.ui.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import ehi.ehiapplication.viewmodels.RepoViewModel;
import ehi.ehiapplication.viewmodels.RepoListViewModel;
import ehi.ehiapplication.databinding.RepoListItemBinding;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoHolder> {

    private LayoutInflater layoutInflater;
    private RepoListViewModel repoListViewModel;

    public void setData(RepoListViewModel repos) {
        repoListViewModel = repos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RepoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        RepoListItemBinding binding = RepoListItemBinding.inflate(layoutInflater, parent, false);
        return new RepoHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoHolder holder, int position) {
        holder.bind(repoListViewModel.getRepoAtPosition(position));
    }

    @Override
    public int getItemCount() {
        if(repoListViewModel == null) {
            return 0;
        }
        return repoListViewModel.getSizeOfRepos();
    }

    class RepoHolder extends RecyclerView.ViewHolder {
        private final RepoListItemBinding binding;

        RepoHolder(final RepoListItemBinding itemBinding){
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }

        void bind(RepoViewModel repoViewModel) {
            binding.setRepoViewModel(repoViewModel);
            binding.executePendingBindings();
        }
    }
}
