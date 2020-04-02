package com.hryniuk.beaware.ui.chart;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.core.content.res.ResourcesCompat;
import androidx.appcompat.app.ActionBar;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.hryniuk.beaware.MainActivity;
import com.hryniuk.beaware.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class ChartFragment extends Fragment {

    private LinearLayout infoPanel;
    private RelativeLayout hideAnim;
    private View root;
    private BarChart mpBarChart;
    final Handler handler = new Handler();
    private ArrayList<Integer> dataVals1;
    private ArrayList<String> dataVals2, dataVals3;
    private ArrayList<String> countryArrayList;

    private ArrayList<BarEntry> dataValue1() {

        ArrayList<BarEntry> dataVals = new ArrayList<BarEntry>();
        for (int i = 0; i < dataVals1.size(); i++) {

            dataVals.add(new BarEntry(i, dataVals1.get(i)));


            // Log.i("DATA", dataVals.toString());
        }
        Collections.reverse(dataVals);
        return dataVals;
    }


    private ArrayList<BarEntry> dataValue2() {

        int tmp = 0;
        ArrayList<BarEntry> dataVals = new ArrayList<BarEntry>();
        for (int i = 0; i < dataVals1.size(); i++) {

            if (dataVals2.get(i).length() == 0) {

                tmp = 0;

            } else {
                //if (!dataVals2.get(i).equals("TotalDeaths"))
                tmp = Integer.parseInt(dataVals2.get(i).replace(",", ""));
            }

            dataVals.add(new BarEntry(i, tmp));

        }
        //Log.i("DATA", dataVals.toString());
        //Collections.reverse(dataVals);
        Collections.reverse(dataVals);
        return dataVals;
    }


    private ArrayList<BarEntry> dataValue3() {

        int tmp = 0;
        ArrayList<BarEntry> dataVals = new ArrayList<BarEntry>();
        for (int i = 0; i < dataVals1.size(); i++) {

            if (dataVals3.get(i).length() == 0) {

                tmp = 0;

            } else {
                // if (!dataVals3.get(i).equals("TotalRecovered"))
                tmp = Integer.parseInt(dataVals3.get(i).replace(",", ""));
            }

            dataVals.add(new BarEntry(i, tmp));
        }
        //Log.i("DATA", dataVals.toString());
        //Collections.reverse(dataVals);
        Collections.reverse(dataVals);
        return dataVals;
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        TextView tv = new TextView(getActivity());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lp);
        tv.setText("Chart Info");
        tv.setTextSize(20);
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        Typeface tf = ResourcesCompat.getFont(Objects.requireNonNull(getActivity()), R.font.comfortaa_bold);
        tv.setTypeface(tf);
        Objects.requireNonNull(((MainActivity) getActivity()).getSupportActionBar()).setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        Objects.requireNonNull(((MainActivity) getActivity()).getSupportActionBar()).setCustomView(tv);

        root = inflater.inflate(R.layout.fragment_notifications, container, false);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference eventIdRef = database.getReference("infoCountry");

        eventIdRef.orderByChild("totalcases").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                countryArrayList = new ArrayList<String>();
                dataVals1 = new ArrayList<Integer>();
                dataVals2 = new ArrayList<String>();
                dataVals3 = new ArrayList<String>();
                try {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                       /* String country = postSnapshot.child("countryother").getValue(String.class);
                        String data1 = postSnapshot.child("totalcases").getValue(String.class);*/

                        chartWorld cW = postSnapshot.getValue(chartWorld.class);



                        countryArrayList.add(cW.getCountryother());
                        dataVals1.add(cW.getTotalcases());
                        dataVals2.add(cW.getTotaldeaths());
                        dataVals3.add(cW.getTotalrecovered());


                        //  Log.d("TAG", countryArrayList.toString() + " " + dataVals3.toString());
                    }

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            init(root);
                        }
                    }, 1000);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        hideAnim = root.findViewById(R.id.hideAnim);
        infoPanel = root.findViewById(R.id.infoPanel);
        infoPanel.setVisibility(View.GONE);




        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hideAnim.setVisibility(View.GONE);
                infoPanel.setVisibility(View.VISIBLE);
            }
        }, 2200);

    }

    private void init(View root) {
        mpBarChart = root.findViewById(R.id.grouped_BarChart);
        BarDataSet barDataSet1 = new BarDataSet(dataValue1(), "Total cases");
        barDataSet1.setColor(Color.parseColor("#FBC02D"));
        BarDataSet barDataSet2 = new BarDataSet(dataValue2(), "Total fatal");
        barDataSet2.setColor(Color.parseColor("#E64A19"));
        BarDataSet barDataSet3 = new BarDataSet(dataValue3(), "Recovered");
        barDataSet3.setColor(Color.parseColor("#388E3C"));

        Description description = new Description();
        description.setText("");
        mpBarChart.setDescription(description);


        BarData data = new BarData(barDataSet1, barDataSet2, barDataSet3);
        data.setValueFormatter(new MyValueFormatter());
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.BLACK);
        data.setValueTypeface(ResourcesCompat.getFont(getActivity(), R.font.poppins_medium));

        mpBarChart.setData(data);


        Legend legend = mpBarChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextSize(14f);
        legend.setFormSize(10f);
        legend.setXEntrySpace(20.0f);
        legend.setTypeface(ResourcesCompat.getFont(getActivity(), R.font.comfortaa_bold));
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        XAxis xAxis = mpBarChart.getXAxis();

        Collections.reverse(countryArrayList);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(countryArrayList));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        xAxis.setTextSize(10f);
        xAxis.setLabelCount(countryArrayList.size());
        xAxis.setTypeface(ResourcesCompat.getFont(getActivity(), R.font.comfortaa_bold));
        YAxis yAxis = mpBarChart.getAxisLeft();
        YAxis yAxisright = mpBarChart.getAxisRight();
        yAxis.setDrawGridLines(false);
        yAxis.setDrawAxisLine(false);
        yAxis.setValueFormatter(new MyYAxisValueFormatter());
        yAxis.setTextSize(10f);
        yAxis.setGranularity(1);
        yAxis.setGranularityEnabled(true);
        yAxis.setDrawLabels(false);
        yAxisright.setGranularity(1);
        yAxisright.setGranularityEnabled(true);
        yAxisright.setValueFormatter(new MyYAxisValueFormatter());
        // yAxisright.setDrawLabels(true);
        yAxisright.setTextSize(13f);
        yAxisright.setDrawLabels(false);
        mpBarChart.setDragEnabled(true);

        mpBarChart.setVisibleXRangeMaximum(4);

        float barSpace = 0.05f;
        float groupSpace = 0.19f;
        data.setBarWidth(0.22f);

        mpBarChart.getXAxis().setAxisMinimum(0);
        mpBarChart.getXAxis().setAxisMaximum(0 + mpBarChart.getBarData().getGroupWidth(groupSpace, barSpace) * countryArrayList.size());

        mpBarChart.getAxisLeft().setAxisMinimum(0);
        mpBarChart.groupBars(0, groupSpace, barSpace);
        mpBarChart.setHighlightPerTapEnabled(false);

        mpBarChart.animateX(1000);
        mpBarChart.animateY(1000);

        mpBarChart.setDrawBarShadow(false);
        mpBarChart.setDrawValueAboveBar(true);
        mpBarChart.setPinchZoom(false);
        mpBarChart.setDrawGridBackground(false);


        // mpBarChart.setDrawValueAboveBar(false);
        mpBarChart.notifyDataSetChanged();
        mpBarChart.refreshDrawableState();
        mpBarChart.invalidate();
    }



    public static class MyValueFormatter implements IValueFormatter {
        @Override
        public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
            return String.valueOf((int) value);
        }
    }

    public static class MyYAxisValueFormatter implements IAxisValueFormatter {
        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            return String.valueOf((int) value);
        }
    }
}
