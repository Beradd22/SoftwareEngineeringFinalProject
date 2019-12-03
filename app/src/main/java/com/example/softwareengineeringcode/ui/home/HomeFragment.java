package com.example.softwareengineeringcode.ui.home;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.softwareengineeringcode.MainActivity;
import com.example.softwareengineeringcode.R;
import com.example.softwareengineeringcode.Report;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private int imageCounter = 0;
    private Button nextImageButton;
    private Button prevImageButton;
    private Button lastReportButton;
    private Button selectedReportButton;

    private TextView reportTitle;
    private TextView reportDets;
    private ImageView reportImage;
    private TextView weatherDets;
    private TextView Date;

    private Button.OnClickListener nextImage = new Button.OnClickListener() {
        @Override
        public void onClick(View v){

        }
    };

    private Button.OnClickListener previousImage = new Button.OnClickListener() {
        @Override
        public void onClick(View v){

        }
    };

    private Button.OnClickListener showLastReport = new Button.OnClickListener() {
        @Override
        public void onClick(View v){
            Report tempReport = ((MainActivity)getActivity()).getAccessDB().getNewestReport();

            reportTitle.setText(tempReport.getTitle());
            reportDets.setText(tempReport.getDetails());
            weatherDets.setText(tempReport.getwType());
            Date.setText(tempReport.getDateTime());

            if (tempReport.getPictures().size() > 0) {
                Bitmap bmp = BitmapFactory.decodeByteArray(tempReport.getPictures().get(0),
                        0, tempReport.getPictures().get(0).length);
                reportImage.setImageBitmap(Bitmap.createScaledBitmap(bmp, reportImage.getWidth(),
                        reportImage.getHeight(), false));
            }
        }
    };

    private Button.OnClickListener showSelectedReport = new Button.OnClickListener() {
        @Override
        public void onClick(View v){
            Report tempReport = ((MainActivity)getActivity()).getReportToDisplay();

            reportTitle.setText(tempReport.getTitle());
            reportDets.setText(tempReport.getDetails());
            weatherDets.setText(tempReport.getwType());
            Date.setText(tempReport.getDateTime());

            if (tempReport.getPictures().size() > 0) {
                Bitmap bmp = BitmapFactory.decodeByteArray(tempReport.getPictures().get(0),
                        0, tempReport.getPictures().get(0).length);
                reportImage.setImageBitmap(Bitmap.createScaledBitmap(bmp, reportImage.getWidth(),
                        reportImage.getHeight(), false));
            }
        }
    };

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        reportTitle = (TextView)root.findViewById(R.id.report_title);
        reportDets = (TextView)root.findViewById(R.id.report_details);
        weatherDets= (TextView)root.findViewById(R.id.weather_details);
        Date = (TextView)root.findViewById(R.id.report_date);
        reportImage = (ImageView)root.findViewById(R.id.report_images);

        nextImageButton = (Button)root.findViewById(R.id.next_image_button);
        nextImageButton.setOnClickListener(nextImage);

        prevImageButton = (Button)root.findViewById(R.id.previous_image_button);
        prevImageButton.setOnClickListener(previousImage);

        lastReportButton = (Button)root.findViewById(R.id.last_report_button);
        lastReportButton.setOnClickListener(showLastReport);

        selectedReportButton = (Button)root.findViewById(R.id.report_selected_button);
        selectedReportButton.setOnClickListener(showSelectedReport);

        return root;
    }
}