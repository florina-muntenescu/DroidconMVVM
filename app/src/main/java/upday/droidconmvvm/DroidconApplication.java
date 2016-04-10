package upday.droidconmvvm;

import android.app.Application;
import android.support.annotation.NonNull;

import upday.droidconmvvm.datamodel.DataModel;
import upday.droidconmvvm.datamodel.IDataModel;

public class DroidconApplication extends Application {

    @NonNull
    private final IDataModel mDataModel;

    public DroidconApplication() {
        mDataModel = new DataModel();
    }

    @NonNull
    public IDataModel getDataModel() {
        return mDataModel;
    }
}
