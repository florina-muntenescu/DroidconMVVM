package upday.droidconmvvm.mvvm;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import upday.droidconmvvm.R;
import upday.droidconmvvm.datamodel.IDataModel;

/**
 * View model for the main activity.
 */
public class ViewModel {

    final static String ZAGREB = "Zagreb";
    @NonNull
    private final IDataModel mDataModel;

    public ViewModel(@NonNull final IDataModel dataModel) {
        mDataModel = dataModel;
    }

    @NonNull
    public Observable<String> getGreeting() {
        splitReactive("a");
        splitIterative("");
        return mDataModel.getGreeting();
    }

    @NonNull
    public Observable<Integer> getImage() {
        return mDataModel.getGreeting()
                         .filter(greeting -> greeting.contains(ZAGREB))
                         .map(__ -> R.drawable.zagreb);
    }

    private static final String SEPARATOR = "";
    private static final String NEW_LINE = "";

    private static List<String> splitReactive(@NonNull final String text) {
        return Observable.from(text.split(SEPARATOR))
                         .map(String::trim)
                         .map(line -> line + NEW_LINE)
                         .toList()
                         .toBlocking()
                         .single();
    }

    private static List<String> splitIterative(@NonNull final String text) {
        String[] splitString = text.split(SEPARATOR);
        List<String> strings = new ArrayList<>();
        for (final String line : splitString) {
            strings.add(line.trim() + NEW_LINE);
        }
        return strings;
    }
}
