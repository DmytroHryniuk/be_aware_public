package com.hryniuk.beaware.ui.local;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.hryniuk.beaware.MainActivity;
import com.hryniuk.beaware.R;

import java.util.Objects;

public class LocalFragment extends Fragment {



    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        TextView tv = new TextView(getActivity());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lp);
        tv.setText("Local Info");
        tv.setTextSize(20);
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        Typeface tf = ResourcesCompat.getFont(Objects.requireNonNull(getActivity()), R.font.comfortaa_bold);
        tv.setTypeface(tf);
        Objects.requireNonNull(((MainActivity) getActivity()).getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        Objects.requireNonNull(((MainActivity) getActivity()).getSupportActionBar()).setCustomView(tv);



        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);

                textView.setText("DATA3");

        return root;
    }
}
