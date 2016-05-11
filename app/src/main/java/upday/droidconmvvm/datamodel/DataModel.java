package upday.droidconmvvm.datamodel;

import android.support.annotation.NonNull;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import upday.droidconmvvm.model.Language;

import static upday.droidconmvvm.model.Language.LanguageCode;

public class DataModel implements IDataModel {

    @NonNull
    @Override
    public Observable<List<Language>> getSupportedLanguages() {
        List<Language> languages = Arrays
                .asList(new Language("English", LanguageCode.EN),
                        new Language("German", LanguageCode.DE),
                        new Language("Slovakian", LanguageCode.HR));
        return Observable.just(languages);
    }

    @NonNull
    @Override
    public Observable<String> getGreetingByLanguageCode(@NonNull final LanguageCode code) {
        switch (code) {
            case DE:
                return Observable.just("Guten Tag!");
            case EN:
                return Observable.just("Hello!");
            case HR:
                return Observable.just("Zdravo!");
            default:
                return Observable.empty();
        }
    }
}
