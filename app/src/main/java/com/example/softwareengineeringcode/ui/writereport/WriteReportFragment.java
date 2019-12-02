package com.example.softwareengineeringcode.ui.writereport;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.softwareengineeringcode.R;
import com.example.softwareengineeringcode.Report;

import java.sql.Blob;
import java.util.List;

public class WriteReportFragment extends Fragment {

    private WriteReportViewModel writeReportViewModel;
    private Button submitReportButton;
    private View writeReportView;
    private List<Blob> Images;

    private Button.OnClickListener submitReport = new Button.OnClickListener() {
        @Override
        public void onClick(View v){
            Report submittedReport = new Report();

            TextView reportTitle = (TextView)writeReportView.findViewById(R.id.title_text);
            submittedReport.setTitle(reportTitle.getText().toString());

            TextView reportDetails = (TextView)writeReportView.findViewById(R.id.detail_text);
            submittedReport.setDetails(reportDetails.getText().toString());


        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        writeReportViewModel =
                ViewModelProviders.of(this).get(WriteReportViewModel.class);
        View root = inflater.inflate(R.layout.fragment_write_report, container, false);
        writeReportView = root;
        final TextView textView = root.findViewById(R.id.write_report);
        writeReportViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        submitReportButton = (Button)root.findViewById(R.id.submit_button);
        submitReportButton.setOnClickListener(submitReport);
        return root;
    }
}