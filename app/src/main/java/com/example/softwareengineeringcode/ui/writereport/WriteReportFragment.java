package com.example.softwareengineeringcode.ui.writereport;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
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

import com.example.softwareengineeringcode.MainActivity;
import com.example.softwareengineeringcode.R;
import com.example.softwareengineeringcode.Report;

import java.sql.Blob;
import java.util.List;

public class WriteReportFragment extends Fragment {

    // Generated with the fragment on project creation
    private WriteReportViewModel writeReportViewModel;

    // Global fragment view holder
    private View writeReportView;

    // Keeps track of the number of pictures taken
    private TextView imageCount;

    // Stores all the images taken
    private List<Blob> Images;

    // Static variable used for the image saving function
    private static final int pic_id = 123;

    // Button listener for the Submit button
    private Button.OnClickListener submitReport = new Button.OnClickListener() {
        @Override
        public void onClick(View v){

            // Empty report
            Report submittedReport = new Report();

            // Sets the report title
            TextView reportTitle = writeReportView.findViewById(R.id.title_text);
            submittedReport.setTitle(reportTitle.getText().toString());

            // Sets the report details
            TextView reportDetails = writeReportView.findViewById(R.id.detail_text);
            submittedReport.setDetails(reportDetails.getText().toString());

            // Sets the report image list
            submittedReport.setPictures(Images);

            // Sets the report weather data taken from the weather api
            submittedReport.setwType(getWeather());

            // Sets the location after receiving GPS data
            submittedReport.setLocation(getLocation());

            // Calls the access database class
            // from main activity and sends the
            // report to the database
            if (submittedReport.getTitle().trim() != "" && submittedReport.getPictures().size() > 0
                    && submittedReport.getDetails().trim() != "") {
                ((MainActivity) getActivity()).getAccessDB().sendReport(submittedReport);
            }
        }
    };

    // Button listener for the Take Photo button
    private Button.OnClickListener takePicture = new Button.OnClickListener() {
        @Override
        public void onClick(View v){
            Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera_intent, pic_id);
        }
    };

    // Camera capture helper function
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == pic_id) {
            Images.add((Blob)data.getExtras().get("data"));
        }

        String countUpdate = Images.size() + " photos";
        imageCount.setText(countUpdate);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // Displays the fragment
        writeReportViewModel =
                ViewModelProviders.of(this).get(WriteReportViewModel.class);
        View root = inflater.inflate(R.layout.fragment_write_report, container, false);
        writeReportView = root;
        final TextView textView = root.findViewById(R.id.write_report);

        // Created with the project creation
        writeReportViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        // Set the listener for the Submit button
        Button submitReportButton = (Button)root.findViewById(R.id.submit_button);
        submitReportButton.setOnClickListener(submitReport);

        // Set the listener for the Take Photo button
        Button takePictureButton = (Button)root.findViewById((R.id.camera_button));
        takePictureButton.setOnClickListener(takePicture);

        // Save the reference to the photo count text
        imageCount = (TextView)root.findViewById(R.id.image_count);

        return root;
    }

    private String getWeather() {
        String weather = "";

        return weather;
    }

    // Calls the get location function in main
    private  String getLocation() {
        return ((MainActivity)getActivity()).getLocation();
    }
}