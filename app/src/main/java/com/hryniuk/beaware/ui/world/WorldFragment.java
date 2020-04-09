package com.hryniuk.beaware.ui.world;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.ActionBar;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.WanderingCubes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hryniuk.beaware.MainActivity;
import com.hryniuk.beaware.R;

import java.util.Objects;


public class WorldFragment extends Fragment {

    private TextView total_cases_info, deaths_info, recovered_info;
    private LinearLayout infoPanel;
    private String TAG = "firebase:";
    private ProgressBar progressBar;
    private final Handler handler = new Handler();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        TextView tv = new TextView(getActivity());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        tv.setLayoutParams(lp);
        tv.setText(/*"World Info" + */getString(R.string.version));
        tv.setTextSize(20);
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        Typeface tf = ResourcesCompat.getFont(Objects.requireNonNull(getActivity()), R.font.comfortaa_bold);
        tv.setTypeface(tf);
        Objects.requireNonNull(((MainActivity) getActivity()).getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        Objects.requireNonNull(((MainActivity) getActivity()).getSupportActionBar()).setCustomView(tv);


        View root = inflater.inflate(R.layout.fragment_home, container, false);
        progressBar = (ProgressBar) root.findViewById(R.id.spin_kit);
        Sprite sprite = new WanderingCubes();
        progressBar.setIndeterminateDrawable(sprite);
        total_cases_info = root.findViewById(R.id.total_cases_info);
        deaths_info = root.findViewById(R.id.deaths_info);
        recovered_info = root.findViewById(R.id.recovered_info);
        infoPanel = root.findViewById(R.id.infoPanel);
        infoPanel.setVisibility(View.GONE);
        return root;
    }


    @Override
    public void onStart() {
        super.onStart();
        readData();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                infoPanel.setVisibility(View.VISIBLE);
            }
        }, 1500);


    }

    private void readData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("infoALL");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                WorldOBJ world = dataSnapshot.getValue(WorldOBJ.class);
                //Log.d(TAG, world.getCases() + "\n" + world.getDeath() + "\n" + world.getRecovered());
                //textView.setText(world.getCases() + "\n" + world.getDeath() + "\n" + world.getRecovered());
                total_cases_info.setText(world.getCases());
                deaths_info.setText(world.getDeath());
                recovered_info.setText(world.getRecovered());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

}
