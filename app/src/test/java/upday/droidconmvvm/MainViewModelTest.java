package upday.droidconmvvm;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import rx.observers.TestSubscriber;
import upday.droidconmvvm.datamodel.IDataModel;

public class MainViewModelTest {

    @Mock
    private IDataModel mDataModel;

    private MainViewModel mMainViewModel;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mMainViewModel = new MainViewModel(mDataModel);
    }

    @Test
    public void testGetGreeting_emitsCorrectGreeting() {
        String greeting = "Hello!";
        Mockito.when(mDataModel.getGreeting()).thenReturn(Observable.just(greeting));
        TestSubscriber<String> testSubscriber = new TestSubscriber<>();

        mMainViewModel.getGreeting().subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(greeting);
    }

}

