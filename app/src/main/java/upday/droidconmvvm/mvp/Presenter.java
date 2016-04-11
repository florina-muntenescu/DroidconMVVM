package upday.droidconmvvm.mvp;

import android.support.annotation.NonNull;

import rx.subscriptions.CompositeSubscription;
import upday.droidconmvvm.datamodel.IDataModel;

/**
 * Implementation class for the Presenter in the MVP model.
 */
public class Presenter implements IPresenter {

    @NonNull
    private final IDataModel mDataModel;

    @NonNull
    private final IView mView;

    private CompositeSubscription mSubscription;

    public Presenter(@NonNull final IDataModel dataModel,
                     @NonNull final IView view) {
        mDataModel = dataModel;
        mView = view;
    }

    @Override
    public void bind() {
        mSubscription = new CompositeSubscription();

        mSubscription.add(mDataModel.getGreeting()
                                    .subscribe(this::setGreeting));
    }

    @Override
    public void unBind() {
        mSubscription.unsubscribe();
    }

    private void setGreeting(@NonNull final String greeting) {
        mView.setGreeting(greeting);
    }
}
