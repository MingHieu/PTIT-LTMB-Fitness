package com.ltmb.fitness.scene.report;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.ltmb.fitness.R;

import java.util.ArrayList;
import java.util.Random;

public class ReportActivity extends AppCompatActivity {

    BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_report);

        barChart = findViewById(R.id.barChart);

        BarDataSet barDataSet1 = new BarDataSet(barEntries(), "DataSet 1");
        barDataSet1.setColor(Color.RED);
        BarDataSet barDataSet2 = new BarDataSet(barEntries(), "DataSet 2");
        barDataSet2.setColor(Color.BLUE);
        BarDataSet barDataSet3 = new BarDataSet(barEntries(), "DataSet 3");
        barDataSet3.setColor(Color.MAGENTA);

        BarData barData = new BarData(barDataSet1, barDataSet2, barDataSet3);

        barChart.setData(barData);

        String[] days = new String[]{"2", "3", "4", "5", "6", "7", "8"};

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(days));
        xAxis.setCenterAxisLabels(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setGranularity(1);
        xAxis.setGranularityEnabled(true);

        barChart.setDragEnabled(true);
        barChart.setVisibleXRangeMaximum(3);

        float barSpace = 0.08f;
        float groupSpace = 0.4f;
        barData.setBarWidth(0.1f);

        barChart.getXAxis().setAxisMinimum(0);
        barChart.getXAxis().setAxisMaximum(0 + barChart.getBarData().getGroupWidth(groupSpace, barSpace) * 7);
        barChart.getAxisLeft().setAxisMinimum(0);

        barChart.groupBars(0, groupSpace, barSpace);

        barChart.invalidate();

    }

    private ArrayList<BarEntry> barEntries() {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        Random random = new Random();
        barEntries.add(new BarEntry(1, 200 + random.nextInt(300)));
        barEntries.add(new BarEntry(2, 200 + random.nextInt(300)));
        barEntries.add(new BarEntry(3, 200 + random.nextInt(300)));
        barEntries.add(new BarEntry(4, 200 + random.nextInt(300)));
        barEntries.add(new BarEntry(5, 200 + random.nextInt(300)));
        barEntries.add(new BarEntry(6, 200 + random.nextInt(300)));
        barEntries.add(new BarEntry(7, 200 + random.nextInt(300)));
        return barEntries;
    }

}
