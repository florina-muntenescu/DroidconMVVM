package upday.droidconmvvm.datamodel;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;
import upday.droidconmvvm.Language;

import static upday.droidconmvvm.Language.LanguageCode;

public interface IDataModel {

    @NonNull
    Observable<List<Language>> getSupportedLanguages();

    @NonNull
    Observable<String> getGreetingStream();

    @NonNull
    Observable<String> getGreetingByLanguageCode(LanguageCode code);
}
