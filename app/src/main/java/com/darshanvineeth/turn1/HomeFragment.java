package com.darshanvineeth.turn1;

import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by sWoRd f!sH on 19-03-2019.
 */

public class HomeFragment extends Fragment {

    ImageButton scanButton;
    private ArrayList<String> mImageUrls = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        scanButton = view.findViewById(R.id.scan_button);
        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Please wait..", Toast.LENGTH_SHORT).show();
                //getFragmentManager().beginTransaction().replace(R.id.fragment_container, new ScanFragment()).commit();
                Intent intent = new Intent(getActivity(), ScanActivity.class);
                startActivity(intent);
                getActivity().finish();


            }
        });
        //TODO: Pass array from JSON
        String[] lectures = {"Data Analysis", "People Management", "Brand Management", "Management Accounting", "Strategy", "Economics for Managers", "Finance", "Social Responsibility", "Marketing", "Innovation Bootcamp"};
        String[] lec_hall = {"M101", "M102", "M103", "M104", "M105", "M106"};
        String[] faculty = {"APJ", "RJS", "MAC", "TKM", "CET", "GEC"};


        TextView dateText = view.findViewById(R.id.home_date);
        TextView hallText = view.findViewById(R.id.home_lec_hall);
        TextView facultyText = view.findViewById(R.id.home_faculty);
        TextView lectureText = view.findViewById(R.id.home_lecture);

        //Get Time
        final Calendar c = Calendar.getInstance();
        SimpleDateFormat date = new SimpleDateFormat("dd MMMM yyyy");
        SimpleDateFormat time = new SimpleDateFormat("HH:mm");
        String timme = time.format(c.getTime());
        String[] timeString = timme.split(":");
        int hour = Integer.parseInt(timeString[0].trim());
        //int min = Integer.parseInt(timeString[1].trim());  //use min if required
        dateText.setText(date.format(c.getTime()));

        //TODO: % is used for random lecture,hall and faculty(for sample). Fill in array in the correct order from db and alter the code below accordingly.
        //Set Text Samples
        lectureText.setText("Lecture : " + lectures[hour%10] + " [" + hour + ":00 - " + (hour + 1) + ":00]");
        hallText.setText(lec_hall[hour % 6]);
        facultyText.setText(faculty[hour % 6]);

        //add images to arraylist
        getImages();

        //Recycler View for lectures for the day
        RecyclerView subjectList = view.findViewById(R.id.subjectList);
        subjectList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        subjectList.setAdapter(new subjectAdapter(getActivity(), lectures, mImageUrls));
        //Lectures are passed onto 'subjectAdapter' to inflate into 'list_item_layout'

        return view;
    }
    private void getImages(){

        mImageUrls.add("https://wallpaperplay.com/walls/full/3/6/0/63336.jpg");

        mImageUrls.add("https://wallpaperplay.com/walls/full/b/0/1/63337.jpg");

        mImageUrls.add("https://wallpaperplay.com/walls/full/f/1/4/63349.jpg");

        mImageUrls.add("https://wallpaperplay.com/walls/full/2/7/4/63352.jpg");

        mImageUrls.add("https://wallpaperplay.com/walls/full/6/d/8/63384.jpg");

        mImageUrls.add("https://wallpaperplay.com/walls/full/b/8/8/117859.jpg");

        mImageUrls.add("https://wallpaperplay.com/walls/full/e/5/8/117861.jpg");

        mImageUrls.add("https://images.pexels.com/photos/106344/pexels-photo-106344.jpeg");

        mImageUrls.add("https://images.pexels.com/photos/590045/pexels-photo-590045.jpeg");

        mImageUrls.add("https://images.pexels.com/photos/938965/pexels-photo-938965.jpeg");

    }

}
