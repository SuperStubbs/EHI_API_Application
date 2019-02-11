package ehi.ehiapplication.viewmodels;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private OnViewReposClickListener mCallback;

    public void onViewReposClick() {

        mCallback.onViewReposClick();
    }

    public interface OnViewReposClickListener {
        void onViewReposClick();
    }

    public void setViewReposClickListener(Activity activity) {
        mCallback = (OnViewReposClickListener) activity;
    }
}
