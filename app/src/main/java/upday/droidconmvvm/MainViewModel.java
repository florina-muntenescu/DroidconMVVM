package upday.droidconmvvm;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;
import upday.droidconmvvm.datamodel.IDataModel;

/**
 * View model for the main activity.
 */
public class MainViewModel {

    @NonNull
    private final IDataModel mDataModel;

    public MainViewModel(@NonNull final IDataModel dataModel) {
        mDataModel = dataModel;
    }

    @NonNull
    public Observable<String> getGreeting() {
        return mDataModel.getGreetingStream();
    }

    @NonNull
    public Observable<List<Language>> getSupportedLanguages() {
        return mDataModel.getSupportedLanguages();
    }

}
