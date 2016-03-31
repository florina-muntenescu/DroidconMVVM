package upday.droidconmvvm.datamodel;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import upday.droidconmvvm.Language;

public class DataModel implements IDataModel {

    @NonNull
    private static IDataModel mInstance;

    public static IDataModel getInstance() {
        if (mInstance == null) {
            mInstance = new DataModel();
        }
        return mInstance;
    }

    private DataModel() {
    }

    @Override
    public Observable<String> getGreetingStream() {
        return Observable.just("Hello there");
    }

    @NonNull
    @Override
    public Observable<List<Language>> getSupportedLanguages() {
        List<Language> languages = Arrays
                .asList(new Language("English", "EN"), new Language("German", "DE"));
        return Observable.just(languages);
    }
}
