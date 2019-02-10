package ehi.ehiapplication.ui.main;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import ehi.ehiapplication.R;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    OnViewReposClickListener mCallback;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

    public interface OnViewReposClickListener {
        void onViewReposClick();
    }

    public void setViewReposClickListener(Activity activity) {
        mCallback = activity;
    }

    @OnClick(R.id.viewReposBtn)
    protected void onViewReposClickCall() {
        mCallback.onViewReposClick();
    }
}
