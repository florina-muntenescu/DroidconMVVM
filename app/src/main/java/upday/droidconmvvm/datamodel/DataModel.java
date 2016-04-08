package upday.droidconmvvm.datamodel;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import upday.droidconmvvm.Language;

import static upday.droidconmvvm.Language.LanguageCode;

public class DataModel implements IDataModel {

    @Override
    public Observable<String> getGreetingStream() {
        return Observable.just("Hello there");
    }

    @NonNull
    @Override
    public Observable<List<Language>> getSupportedLanguages() {
        List<Language> languages = Arrays
                .asList(new Language("English", LanguageCode.EN),
                        new Language("German", LanguageCode.DE));
        return Observable.just(languages);
    }

    @NonNull
    @Override
    public Observable<String> getGreetingByLanguageCode(final LanguageCode code) {
        switch (code) {
            case DE:
                return Observable.just("Guten Tag!");
            case EN:
                return Observable.just("Hello!");
            case FR:
                return Observable.just("Bonjour!");
            default:
                return Observable.never();
        }
    }
}
