package com.example.softwareengineeringcode.ui.viewreports;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.softwareengineeringcode.MainActivity;
import com.example.softwareengineeringcode.R;
import com.example.softwareengineeringcode.Report;

import java.util.List;

public class ViewReportFragment extends Fragment {

    private ViewReportViewModel viewReportViewModel;
    private LinearLayout reportList;
    private List<Report> listOfReports;
    private Button updateButton;

    private Button.OnClickListener getSelectedReport = new Button.OnClickListener() {

        @Override
        public void onClick(View v){
            Button clicked = (Button)v;
            String buttonText = clicked.getText().toString();

            String[] getDate = buttonText.split(" ");

            for (Report report : listOfReports) {
                if (report.getDateTime().equals(getDate[1])) {
                    ((MainActivity)getActivity()).setReportToDisplay(report);
                }
            }


        }
    };

    private Button.OnClickListener updateList = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            listOfReports = ((MainActivity) getActivity()).getAccessDB().getAllReports();

            updateReportList();

        }
    };

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

        reportList = (LinearLayout)root.findViewById(R.id.report_list);

        updateButton = (Button)root.findViewById(R.id.update_list_button);
        updateButton.setOnClickListener(updateList);

        return root;
    }

    private void updateReportList() {
        reportList.removeAllViews();

        for (int i = listOfReports.size()-1; i >= 0; i--) {
            Button newTextView =
                    new Button(getActivity().getApplicationContext());

            String toDisplay = listOfReports.get(i).getTitle() + " "
                    + listOfReports.get(i).getDateTime();

            newTextView.setText(toDisplay);

            newTextView.setOnClickListener(getSelectedReport);

            reportList.addView(newTextView);
        }
    }
}