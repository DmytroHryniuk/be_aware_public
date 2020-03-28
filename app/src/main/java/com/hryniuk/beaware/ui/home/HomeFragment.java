package com.hryniuk.beaware.ui.home;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;

import com.hryniuk.beaware.MainActivity;
import com.hryniuk.beaware.R;

import java.util.Objects;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        TextView tv = new TextView(getActivity());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        tv.setLayoutParams(lp);
        tv.setText("World Info");
        tv.setTextSize(20);
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        Typeface tf = ResourcesCompat.getFont(Objects.requireNonNull(getActivity()), R.font.comfortaa_bold);
        tv.setTypeface(tf);
        Objects.requireNonNull(((MainActivity) getActivity()).getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        Objects.requireNonNull(((MainActivity) getActivity()).getSupportActionBar()).setCustomView(tv);


        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
