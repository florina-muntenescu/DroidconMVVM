package upday.droidconmvvm.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import upday.droidconmvvm.DroidconApplication;
import upday.droidconmvvm.R;
import upday.droidconmvvm.datamodel.IDataModel;

/**
 * Implements the view class of the MVP pattern.
 */
public class MVPActivity extends AppCompatActivity implements IView {

    @NonNull
    private IPresenter mPresenter;

    @Nullable
    private TextView mGreetingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPresenter = new Presenter(getDataModel(), this);
        setupViews();
    }

    private void setupViews() {
        mGreetingView = (TextView) findViewById(R.id.greeting);
    }

    @Override
    public void setGreeting(@NonNull final String greeting) {
        assert mGreetingView != null;
        mGreetingView.setText(greeting);
    }

    @NonNull
    private IDataModel getDataModel() {
        return ((DroidconApplication) getApplication()).getDataModel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.unBind();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.bind();
    }
}
