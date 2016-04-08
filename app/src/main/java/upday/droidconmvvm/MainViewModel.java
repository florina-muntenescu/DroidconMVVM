package upday.droidconmvvm;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;
import rx.subjects.BehaviorSubject;
import upday.droidconmvvm.datamodel.IDataModel;

/**
 * View model for the main activity.
 */
public class MainViewModel {

    @NonNull
    private final IDataModel mDataModel;

    @NonNull
    private final BehaviorSubject<Language> mSelectedLanguage = BehaviorSubject.create();

    public MainViewModel(@NonNull final IDataModel dataModel) {
        mDataModel = dataModel;
    }

    @NonNull
    public Observable<String> getGreeting() {
        return mSelectedLanguage
                .map(Language::getCode)
                .flatMap(mDataModel::getGreetingByLanguageCode);
    }

    @NonNull
    public Observable<List<Language>> getSupportedLanguages() {
        return mDataModel.getSupportedLanguages();
    }

    public void languageSelected(@NonNull final Language language) {
        mSelectedLanguage.onNext(language);
    }

}
