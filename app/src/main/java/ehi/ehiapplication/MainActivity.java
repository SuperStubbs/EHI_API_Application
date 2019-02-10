package ehi.ehiapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ehi.ehiapplication.ui.main.MainFragment;
import ehi.ehiapplication.ui.main.ViewReposFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.OnViewReposClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, MainFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof MainFragment) {
            MainFragment mainFragment = (MainFragment) fragment;
            mainFragment.setViewReposClickListener(this);
        }
    }

    @Override
    public void onViewReposClick() {
        ViewReposFragment newFragment = new ViewReposFragment();
        Bundle args = new Bundle();
        newFragment.setArguments(args);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.container, newFragment);
        fragmentTransaction.addToBackStack(null);

        fragmentTransaction.commit();
    }
}
