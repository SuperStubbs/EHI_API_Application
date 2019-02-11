package ehi.ehiapplication.viewmodels;

import android.app.Activity;
import android.arch.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private OnClickListeners mCallback;

    public void onViewReposClick() {
        mCallback.onViewReposClick();
    }

    public interface OnClickListeners {
        void onViewReposClick();
    }

    public void setViewReposClickListener(Activity activity) {
        mCallback = (OnClickListeners) activity;
    }
}
