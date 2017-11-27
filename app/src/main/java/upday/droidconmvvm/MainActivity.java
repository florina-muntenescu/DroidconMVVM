package upday.droidconmvvm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.disposables.CompositeDisposable;
import upday.droidconmvvm.model.Language;

public class MainActivity extends AppCompatActivity {

    @NonNull
    private CompositeDisposable mCompositeDisposable;

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

        mViewModel = getViewModel();
        setupViews();
    }

    private void setupViews() {
        mGreetingView = (TextView) findViewById(R.id.greeting);

        mLanguagesSpinner = (Spinner) findViewById(R.id.languages);
        assert mLanguagesSpinner != null;
        mLanguagesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> parent, final View view,
                                       final int position, final long id) {
                itemSelected(position);
            }

            @Override
            public void onNothingSelected(final AdapterView<?> parent) {
                //nothing to do here
            }
        });
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
        mCompositeDisposable = new CompositeDisposable();

        mCompositeDisposable.add(mViewModel.getGreeting()
                                    .subscribeOn(Schedulers.computation())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(this::setGreeting));

        mCompositeDisposable.add(mViewModel.getSupportedLanguages()
                                    .subscribeOn(Schedulers.computation())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(this::setLanguages));
    }

    private void unBind() {
        mCompositeDisposable.clear();
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
    }

    @NonNull
    private MainViewModel getViewModel() {
        return ((DroidconApplication) getApplication()).getViewModel();
    }

    private void itemSelected(final int position) {
        assert mLanguageSpinnerAdapter != null;

        Language languageSelected = mLanguageSpinnerAdapter.getItem(position);
        mViewModel.languageSelected(languageSelected);
    }
}
