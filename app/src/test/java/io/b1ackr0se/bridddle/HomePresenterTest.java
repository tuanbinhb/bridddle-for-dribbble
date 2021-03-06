package io.b1ackr0se.bridddle;


import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import io.b1ackr0se.bridddle.data.model.Shot;
import io.b1ackr0se.bridddle.data.remote.dribbble.DataManager;
import io.b1ackr0se.bridddle.test.common.MockModel;
import io.b1ackr0se.bridddle.test.common.RxSchedulersOverrideRule;
import io.b1ackr0se.bridddle.ui.home.HomePresenter;
import io.b1ackr0se.bridddle.ui.home.HomeView;
import rx.Observable;

import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class HomePresenterTest {

    @Mock HomeView mockHomeView;
    @Mock DataManager mockDataManager;

    private HomePresenter homePresenter;

    @Rule
    public final RxSchedulersOverrideRule overrideSchedulersRule = new RxSchedulersOverrideRule();

    @Before
    public void setUp() {
        homePresenter = new HomePresenter(mockDataManager);
        homePresenter.attachView(mockHomeView);

    }

    @After
    public void detachView() {
        homePresenter.detachView();
    }

    @Test
    public void loadInitialShotsSuccessful() {
        List<Shot> shotList = MockModel.newShotList(50);

        dataManagerGetPopularShots(Observable.just(shotList), 1);

        homePresenter.loadShots();

        verify(mockHomeView).showInitialProgress(true);
        verify(mockHomeView).showShots(shotList);
    }

    @Test
    public void loadInitialShotsFailed() {
        dataManagerGetPopularShots(Observable.error(new RuntimeException()), 1);

        homePresenter.loadShots();

        verify(mockHomeView).showInitialProgress(true);
        verify(mockHomeView, never()).showShots(anyListOf(Shot.class));
        verify(mockHomeView).showError();
    }

    @Test
    public void loadMoreShotsSuccessful() {
        List<Shot> shotList = MockModel.newShotList(50);

        dataManagerGetPopularShots(Observable.just(shotList), homePresenter.getCurrentPage());

        homePresenter.loadShots(false);

        verify(mockHomeView, never()).showInitialProgress(anyBoolean());
        verify(mockHomeView).showContinuousProgress(true);
        verify(mockHomeView).showContinuousProgress(false);
        verify(mockHomeView, never()).showShots(anyListOf(Shot.class));
        verify(mockHomeView).showMoreShots(shotList);
    }

    @Test
    public void loadMoreShotsFailed() {
        dataManagerGetPopularShots(Observable.error(new RuntimeException()), homePresenter.getCurrentPage());

        homePresenter.loadShots(false);

        verify(mockHomeView, never()).showInitialProgress(anyBoolean());
        verify(mockHomeView).showContinuousProgress(true);
        verify(mockHomeView).showContinuousProgress(false);
        verify(mockHomeView).showLoadMoreError();
    }



    private void dataManagerGetPopularShots(Observable observable, int page) {
        doReturn(observable)
                .when(mockDataManager)
                .getPopular(page, 50);
    }

}
