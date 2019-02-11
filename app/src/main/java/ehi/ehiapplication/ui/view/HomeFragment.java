package ehi.ehiapplication.ui.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ehi.ehiapplication.R;
import ehi.ehiapplication.viewmodels.HomeViewModel;

public class HomeFragment extends Fragment {

    @BindView(R.id.viewReposBtn)
    Button viewReposBtn;

    private HomeViewModel homeViewModel;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        homeViewModel.setViewReposClickListener(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, v);

        return v;
    }

    @OnClick(R.id.viewReposBtn)
    protected void onViewReposClickCall() {
        homeViewModel.onViewReposClick();
    }
}
