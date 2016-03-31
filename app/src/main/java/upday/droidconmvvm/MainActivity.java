package upday.droidconmvvm;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;

import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    @NonNull
    private final CompositeSubscription mSubscription = new CompositeSubscription();

    @NonNull
    private MainViewModel mViewModel = MainViewModel.getInstance();

    @Nullable
    private TextView mGreetingView;

    @Nullable
    private Spinner mLanguagesSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                                    .subscribe(this::setGreeting));

        mSubscription.add(mViewModel.getSupportedLanguages()
                                    .subscribe(this::setLanguages));
    }

    private void unBind() {
        mSubscription.unsubscribe();
    }

    private void setGreeting(@NonNull final String greeting) {
        mGreetingView.setText(greeting);
    }

    private void setLanguages(@NonNull final List<String> languages) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                                                          R.layout.language_item,
                                                          languages);
        adapter.setDropDownViewResource(R.layout.language_item);
        mLanguagesSpinner.setAdapter(adapter);

    }
}
