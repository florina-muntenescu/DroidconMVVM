package upday.droidconmvvm;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import rx.Observable;
import upday.droidconmvvm.datamodel.DataModel;
import upday.droidconmvvm.datamodel.IDataModel;

/**
 * View model for the main activity
 */
public class MainViewModel {

    @Nullable
    private static MainViewModel mInstance;

    @NonNull
    private final IDataModel mDataModel;

    public static MainViewModel getInstance() {
        if (mInstance == null) {
            mInstance = new MainViewModel();
        }

        return mInstance;
    }

    private MainViewModel() {
        mDataModel = DataModel.getInstance();
    }

    @NonNull
    public Observable<String> getGreeting() {
        return mDataModel.getGreetingStream();
    }
}
