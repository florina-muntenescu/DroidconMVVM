package upday.droidconmvvm.datamodel;

import android.support.annotation.NonNull;

import java.util.List;

import io.reactivex.Observable;
import upday.droidconmvvm.model.Language;

import static upday.droidconmvvm.model.Language.LanguageCode;

public interface IDataModel {

    @NonNull
    Observable<List<Language>> getSupportedLanguages();

    @NonNull
    Observable<String> getGreetingByLanguageCode(@NonNull final LanguageCode code);
}
