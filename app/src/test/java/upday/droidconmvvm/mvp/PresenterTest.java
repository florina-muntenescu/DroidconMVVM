package upday.droidconmvvm.mvp;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import rx.Observable;
import upday.droidconmvvm.datamodel.IDataModel;

public class PresenterTest {

    @Mock
    private IDataModel mDataModel;

    @Mock
    private IView mView;

    private Presenter mPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mPresenter = new Presenter(mDataModel, mView);
    }

    @Test
    public void testGetGreeting_set_whenViewBinded() {
        String greeting = "Hello!";
        Mockito.when(mDataModel.getGreeting()).thenReturn(Observable.just(greeting));

        mPresenter.bind();

        Mockito.verify(mView).setGreeting(greeting);
    }
}
