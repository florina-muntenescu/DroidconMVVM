package upday.droidconmvvm;

import android.support.annotation.NonNull;

public class DataModel implements IDataModel {

    @NonNull
    private static IDataModel mInstance;

    public static IDataModel getInstance() {
        if (mInstance == null) {
            mInstance = new DataModel();
        }
        return mInstance;
    }

    private DataModel() {
    }

    @Override
    public String getGreeting() {
        return "Hello there";
    }
}
