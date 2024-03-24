package com.ltmb.fitness.scene.report

import android.graphics.Color
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentReportBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Random

@AndroidEntryPoint
class ReportFragment : BaseFragment<ReportViewModel, FragmentReportBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_report

    override fun initialize() {
        super.initialize()

        val barChart: BarChart = binding.barChart

        val barDataSet1 = BarDataSet(barEntries(), "DataSet 1")
        barDataSet1.setColor(Color.RED)
        val barDataSet2 = BarDataSet(barEntries(), "DataSet 2")
        barDataSet2.setColor(Color.BLUE)
        val barDataSet3 = BarDataSet(barEntries(), "DataSet 3")
        barDataSet3.setColor(Color.MAGENTA)

        val barData = BarData(barDataSet1, barDataSet2, barDataSet3)

        barChart.setData(barData)

        val days = arrayOf("2", "3", "4", "5", "6", "7", "8")

        val xAxis: XAxis = barChart.getXAxis()
        xAxis.valueFormatter = IndexAxisValueFormatter(days)
        xAxis.setCenterAxisLabels(true)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setGranularity(1f)
        xAxis.isGranularityEnabled = true

        barChart.setDragEnabled(true)
        barChart.setVisibleXRangeMaximum(3f)

        val barSpace = 0.08f
        val groupSpace = 0.4f
        barData.barWidth = 0.1f

        barChart.getXAxis().setAxisMinimum(0f)
        barChart.getXAxis()
            .setAxisMaximum(0 + barChart.getBarData().getGroupWidth(groupSpace, barSpace) * 7)
        barChart.getAxisLeft().setAxisMinimum(0f)

        barChart.groupBars(0f, groupSpace, barSpace)

        barChart.invalidate()
    }

    private fun barEntries(): ArrayList<BarEntry> {
        val barEntries = ArrayList<BarEntry>()
        val random = Random()
        barEntries.add(BarEntry(1f, (200 + random.nextInt(300)).toFloat()))
        barEntries.add(BarEntry(2f, (200 + random.nextInt(300)).toFloat()))
        barEntries.add(BarEntry(3f, (200 + random.nextInt(300)).toFloat()))
        barEntries.add(BarEntry(4f, (200 + random.nextInt(300)).toFloat()))
        barEntries.add(BarEntry(5f, (200 + random.nextInt(300)).toFloat()))
        barEntries.add(BarEntry(6f, (200 + random.nextInt(300)).toFloat()))
        barEntries.add(BarEntry(7f, (200 + random.nextInt(300)).toFloat()))
        return barEntries
    }
}