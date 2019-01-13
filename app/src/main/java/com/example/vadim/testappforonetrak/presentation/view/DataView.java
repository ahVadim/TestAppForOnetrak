package com.example.vadim.testappforonetrak.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.example.vadim.testappforonetrak.Model.Data;

import java.util.List;

public interface DataView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void setRefreshing(boolean isRefreshing);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void updateAimSteps(int aimSteps);

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showData(List<Data> data, int aimSteps);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showDownloadErrorMessage();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showIncorrectValueErrorMessage();
}
