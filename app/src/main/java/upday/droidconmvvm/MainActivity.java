package upday.droidconmvvm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import upday.droidconmvvm.datamodel.IDataModel;

public class MainActivity extends AppCompatActivity {

    @NonNull
    private CompositeSubscription mSubscription;

    @NonNull
    private MainViewModel mViewModel;

    @Nullable
    private TextView mGreetingView;

    @Nullable
    private Spinner mLanguagesSpinner;

    @Nullable
    private LanguageSpinnerAdapter mLanguageSpinnerAdapter;

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
        mSubscription = new CompositeSubscription();

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

        mLanguageSpinnerAdapter = new LanguageSpinnerAdapter(this,
                                                             R.layout.language_item,
                                                             languages);
        mLanguagesSpinner.setAdapter(mLanguageSpinnerAdapter);
        mLanguagesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view,
                                       final int position, final long id) {
                Log.d("flo", "item selected " + position);
                Language languageSelected = mLanguageSpinnerAdapter.getItem(position);
                mViewModel.languageSelected(languageSelected);
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {
                //nothing to do here
            }
        });

    }

    @NonNull
    private IDataModel getDataModel() {
        return ((DroidconApplication) getApplication()).getDataModel();
    }
}
