package com.hryniuk.beaware.ui.chart;

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
import com.hryniuk.beaware.MainActivity;
import com.hryniuk.beaware.R;

import java.util.ArrayList;
import java.util.Objects;

public class ChartFragment extends Fragment {

    private BarChart mpBarChart;
    private ArrayList<BarEntry> dataValue1() {

        ArrayList<BarEntry> dataVals = new ArrayList<BarEntry>();
        dataVals.add(new BarEntry(0, 49));
        dataVals.add(new BarEntry(1, 55));
        dataVals.add(new BarEntry(2, 33));
        dataVals.add(new BarEntry(3, 67));
        dataVals.add(new BarEntry(4, 22));
        return dataVals;
    }
    private ArrayList<BarEntry> dataValue2() {

        ArrayList<BarEntry> dataVals = new ArrayList<BarEntry>();
        dataVals.add(new BarEntry(0, 16));
        dataVals.add(new BarEntry(1, 15));
        dataVals.add(new BarEntry(2, 23));
        dataVals.add(new BarEntry(3, 17));
        dataVals.add(new BarEntry(4, 12));
        return dataVals;
    }
    private ArrayList<BarEntry> dataValue3() {

        ArrayList<BarEntry> dataVals = new ArrayList<BarEntry>();
        dataVals.add(new BarEntry(0, 13));
        dataVals.add(new BarEntry(1, 11));
        dataVals.add(new BarEntry(2, 8));
        dataVals.add(new BarEntry(3, 12));
        dataVals.add(new BarEntry(4, 11));
        return dataVals;
    }
    private String[] country = new String[] {"USA", "Australia", "Afrika", "Australia", "Netherlands"};

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



        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        mpBarChart = root.findViewById(R.id.grouped_BarChart);
        chartInit();



        return root;
    }



    private void chartInit() {

        BarDataSet barDataSet1 = new BarDataSet(dataValue1(), "Total cases");
        barDataSet1.setColor(Color.parseColor("#FBC02D"));
        BarDataSet barDataSet2 = new BarDataSet(dataValue2(), "Deaths");
        barDataSet2.setColor(Color.parseColor("#E64A19"));
        BarDataSet barDataSet3 = new BarDataSet(dataValue3(), "Recovered");
        barDataSet3.setColor(Color.parseColor("#388E3C"));

        Description description = new Description();
        description.setText("");
        mpBarChart.setDescription(description);

        BarData data = new BarData(barDataSet1, barDataSet2, barDataSet3);
        data.setValueFormatter(new MyValueFormatter());
        data.setValueTextSize(13f);
        data.setValueTextColor(Color.BLACK);
        mpBarChart.setData(data);

        Legend legend = mpBarChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextSize(14f);
        legend.setFormSize(10f);
        legend.setXEntrySpace(20.0f);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);

        XAxis xAxis = mpBarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(country));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);
        xAxis.setTextSize(13f);

        YAxis yAxis = mpBarChart.getAxisLeft();
        YAxis yAxisright = mpBarChart.getAxisRight();
        yAxis.setDrawGridLines(false);
        yAxis.setDrawAxisLine(false);
        yAxis.setValueFormatter(new MyYAxisValueFormatter());
        yAxis.setTextSize(13f);
        yAxis.setGranularity(1);
        yAxis.setGranularityEnabled(true);
        yAxisright.setGranularity(1);
        yAxisright.setGranularityEnabled(true);
        yAxisright.setValueFormatter(new MyYAxisValueFormatter());
        yAxisright.setDrawLabels(true);
        yAxisright.setTextSize(13f);

        mpBarChart.setDragEnabled(true);
        mpBarChart.setVisibleXRangeMaximum(3);

        float barSpace = 0.05f;
        float groupSpace = 0.19f;
        data.setBarWidth(0.22f);

        mpBarChart.getXAxis().setAxisMinimum(0);
        mpBarChart.getXAxis().setAxisMaximum(0+ mpBarChart.getBarData().getGroupWidth(groupSpace, barSpace)*5);
        mpBarChart.getAxisLeft().setAxisMinimum(0);
        mpBarChart.groupBars(0,groupSpace,barSpace);
        mpBarChart.setHighlightPerTapEnabled(false);

        mpBarChart.animateX(1000);
        mpBarChart.animateY(1000);

        mpBarChart.setDrawBarShadow(false);
        mpBarChart.setDrawValueAboveBar(true);
        mpBarChart.setPinchZoom(false);
        mpBarChart.setDrawGridBackground(false);


        // mpBarChart.setDrawValueAboveBar(false);
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
