package com.example.softwareengineeringcode.ui.viewreports;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewReportViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ViewReportViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}