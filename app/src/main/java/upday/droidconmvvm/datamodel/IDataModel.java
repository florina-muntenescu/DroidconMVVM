package upday.droidconmvvm.datamodel;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;
import upday.droidconmvvm.Language;

public interface IDataModel {

    @NonNull
    Observable<List<Language>> getSupportedLanguages();

    @NonNull
    Observable<String> getGreetingStream();
}
