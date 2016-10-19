package upday.droidconmvvm.datamodel;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import upday.droidconmvvm.model.Language;

import static android.os.Looper.getMainLooper;
import static java.lang.Thread.currentThread;
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
        boolean main = getMainLooper().getThread().equals(currentThread());
        Log.d("flo", " is main " + main);
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
