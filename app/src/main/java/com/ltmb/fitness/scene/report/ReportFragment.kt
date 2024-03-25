package com.ltmb.fitness.scene.report

import android.graphics.Color
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
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

        val barDataSet1 = BarDataSet(barEntries(), "Workouts")
        barDataSet1.setColor(Color.RED)
        val barDataSet2 = BarDataSet(barEntries(), "Minutes")
        barDataSet2.setColor(Color.GREEN)
        val barDataSet3 = BarDataSet(barEntries(), "Kcal")
        barDataSet3.setColor(Color.YELLOW)

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
        barChart.setVisibleXRangeMaximum(7f)

        val barSpace = 0.08f
        val groupSpace = 0.4f
        barData.barWidth = 0.12f

        barChart.getXAxis().setAxisMinimum(0f)
        barChart.getXAxis()
            .setAxisMaximum(0 + barChart.getBarData().getGroupWidth(groupSpace, barSpace) * 7)
        barChart.getAxisLeft().setAxisMinimum(0f)

        barChart.groupBars(0f, groupSpace, barSpace)

        barChart.setDrawGridBackground(false)

        barChart.invalidate()

        val lineChart: LineChart = binding.lineChart

        val entries = arrayListOf(
            Entry(0f, 62f),
            Entry(1f, 69f),
            Entry(2f, 64f),
            Entry(3f, 75f),
            Entry(4f, 68f)
        )

        val dataSet = LineDataSet(entries, "Data Set 1")
        dataSet.color = Color.RED
        dataSet.setDrawFilled(true)
        dataSet.fillColor = Color.BLUE // Màu fill
        dataSet.fillAlpha = 128 // Độ trong suốt của fill color (0-255), 255 là hoàn toàn không trong suốt

        val lineData = LineData(dataSet)
        lineChart.data = lineData
        lineChart.invalidate()

        pieChart()

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

    fun pieChart(){
        val pieChart: PieChart = binding.pieChart

        // Dữ liệu mẫu
        val entries = listOf(
            PieEntry(25f, "A"),
            PieEntry(20f, "B"),
            PieEntry(15f, "C"),
            PieEntry(10f, "D"),
            PieEntry(30f, "E")
        )

        val dataSet = PieDataSet(entries, "Pie Chart")

        // Thiết lập màu cho các phần
        val colors = listOf(Color.BLUE, Color.GREEN, Color.RED, Color.YELLOW, Color.MAGENTA)
        dataSet.colors = colors

        // Góc 180 độ
        pieChart.rotationAngle = 180f

        // Biểu thị phần được chọn
        dataSet.selectionShift = 5f

        // Tạo dữ liệu cho PieChart
        val pieData = PieData(dataSet)

        // Tắt hiển thị label và hiển thị phần trăm
        pieData.setDrawValues(false)
        pieChart.description.isEnabled = false

        // Đặt dữ liệu cho PieChart
        pieChart.data = pieData

        // Cập nhật PieChart
        pieChart.invalidate()
    }

}