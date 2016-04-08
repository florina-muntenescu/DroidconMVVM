package upday.droidconmvvm;

import android.app.Application;
import android.support.annotation.NonNull;

import upday.droidconmvvm.datamodel.DataModel;
import upday.droidconmvvm.datamodel.IDataModel;

public class DroidconApplication extends Application {

    @NonNull
    private IDataModel mDataModel;

    @Override
    public void onCreate() {
        super.onCreate();
        initInstance();
    }

    private void initInstance() {
        mDataModel = new DataModel();
    }

    @NonNull
    public IDataModel getDataModel() {
        return mDataModel;
    }
}
