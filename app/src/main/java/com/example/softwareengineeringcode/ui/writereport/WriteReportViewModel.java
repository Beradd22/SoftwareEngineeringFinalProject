package com.example.softwareengineeringcode.ui.writereport;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class WriteReportViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public WriteReportViewModel() {
        mText = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }
}