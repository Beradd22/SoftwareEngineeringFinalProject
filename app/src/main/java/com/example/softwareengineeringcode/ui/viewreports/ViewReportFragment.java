package com.example.softwareengineeringcode.ui.viewreports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.softwareengineeringcode.R;

public class ViewReportFragment extends Fragment {

    private ViewReportViewModel viewReportViewModel;

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
        return root;
    }
}