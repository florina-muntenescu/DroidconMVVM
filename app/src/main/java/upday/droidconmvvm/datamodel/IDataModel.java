package upday.droidconmvvm.datamodel;

import android.support.annotation.NonNull;

import java.util.List;

import rx.Observable;
import upday.droidconmvvm.model.Language;

import static upday.droidconmvvm.model.Language.LanguageCode;

public interface IDataModel {

    @NonNull
    Observable<String> getGreeting();

}
