package upday.droidconmvvm;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;
import upday.droidconmvvm.datamodel.IDataModel;
import upday.droidconmvvm.model.Language;
import upday.droidconmvvm.schedulers.ImmediateSchedulerProvider;

import static upday.droidconmvvm.model.Language.LanguageCode;

public class MainViewModelTest {

    @Mock
    private IDataModel mDataModel;

    private MainViewModel mMainViewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mMainViewModel = new MainViewModel(mDataModel, new ImmediateSchedulerProvider());
    }

    @Test
    public void testGetSupportedLanguages_emitsCorrectLanguages() {
        Language de = new Language("German", LanguageCode.DE);
        Language en = new Language("English", LanguageCode.EN);
        List<Language> languages = Arrays.asList(de, en);
        Mockito.when(mDataModel.getSupportedLanguages()).thenReturn(Observable.just(languages));
        TestSubscriber<List<Language>> testSubscriber = new TestSubscriber<>();

        mMainViewModel.getSupportedLanguages().subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(languages);
    }

    @Test
    public void testGetGreeting_doesNotEmit_whenNoLanguageSet() {
        TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        mMainViewModel.getGreeting().subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertNoValues();
    }

    @Test
    public void testGetGreeting_emitsCorrectGreeting_whenLanguageSet() {
        String enGreeting = "Hello";
        Language en = new Language("English", LanguageCode.EN);
        Mockito.when(mDataModel.getGreetingByLanguageCode(LanguageCode.EN))
               .thenReturn(Observable.just(enGreeting));
        TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        mMainViewModel.getGreeting().subscribe(testSubscriber);

        mMainViewModel.languageSelected(en);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(enGreeting);
    }
}

