package upday.droidconmvvm.mvp;

import android.support.annotation.NonNull;

/**
 * Interface for the view classes in the MVP pattern.
 */
public interface IView {

    void setGreeting(@NonNull final String greeting);
}
