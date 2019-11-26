package com.example.softwareengineeringcode.ui.writereport;

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

public class WriteReportFragment extends Fragment {

    private WriteReportViewModel writeReportViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        writeReportViewModel =
                ViewModelProviders.of(this).get(WriteReportViewModel.class);
        View root = inflater.inflate(R.layout.fragment_write_report, container, false);
        final TextView textView = root.findViewById(R.id.write_report);
        writeReportViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}