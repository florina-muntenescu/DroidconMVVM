package upday.droidconmvvm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import upday.droidconmvvm.datamodel.IDataModel;

public class MainActivity extends AppCompatActivity {

    @NonNull
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @NonNull
    private MainViewModel mViewModel;

    @Nullable
    private TextView mGreetingView;

    @Nullable
    private Spinner mLanguagesSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewModel = new MainViewModel(getDataModel());

        mGreetingView = (TextView) findViewById(R.id.greeting);
        mLanguagesSpinner = (Spinner) findViewById(R.id.languages);
    }

    @Override
    protected void onResume() {
        super.onResume();
        bind();
    }

    @Override
    protected void onPause() {
        super.onPause();
        unBind();
    }

    private void bind() {
        mSubscription.add(mViewModel.getGreeting()
                                    .subscribeOn(Schedulers.computation())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(this::setGreeting));

        mSubscription.add(mViewModel.getSupportedLanguages()
                                    .subscribeOn(Schedulers.computation())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(this::setLanguages));
    }

    private void unBind() {
        mSubscription.unsubscribe();
    }

    private void setGreeting(@NonNull final String greeting) {
        assert mGreetingView != null;

        mGreetingView.setText(greeting);
    }

    private void setLanguages(@NonNull final List<Language> languages) {
        assert mLanguagesSpinner != null;

        LanguageSpinnerAdapter adapter = new LanguageSpinnerAdapter(this,
                                                                    R.layout.language_item,
                                                                    languages);
//        adapter.setDropDownViewResource(R.layout.language_item);
        mLanguagesSpinner.setAdapter(adapter);

    }

    @NonNull
    private IDataModel getDataModel() {
        return ((DroidconApplication) getApplication()).getDataModel();
    }
}
