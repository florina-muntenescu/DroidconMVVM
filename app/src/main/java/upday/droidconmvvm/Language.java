package upday.droidconmvvm;

import android.support.annotation.NonNull;

/**
 * Language class containing the name of the language and the code.
 */
public class Language {

    @NonNull
    private final String mName;

    @NonNull
    private final String mCode;

    public Language(@NonNull final String name, @NonNull final String code) {
        mName = name;
        mCode = code;
    }

    @NonNull
    public String getName() {
        return mName;
    }

    @NonNull
    public String getCode() {
        return mCode;
    }
}
