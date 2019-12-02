package com.example.softwareengineeringcode.ui.viewreports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.softwareengineeringcode.AccessDB;
import com.example.softwareengineeringcode.MainActivity;
import com.example.softwareengineeringcode.R;
import com.example.softwareengineeringcode.Report;

import java.util.List;

public class ViewReportFragment extends Fragment {

    private ViewReportViewModel viewReportViewModel;
    private ScrollView reportList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewReportViewModel =
                ViewModelProviders.of(this).get(ViewReportViewModel.class);
        View root = inflater.inflate(R.layout.fragment_view_report, container, false);
        final TextView textView = root.findViewById(R.id.view_report);
        viewReportViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        reportList = (ScrollView)root.findViewById(R.id.report_list);

        updateReportList();

        return root;
    }

    private void updateReportList() {
        List<Report> tempReportList = ((MainActivity)getActivity()).getAccessDB().getAllReports();

        for (int i = 0; i < tempReportList.size(); i++) {
            
        }
    }
}