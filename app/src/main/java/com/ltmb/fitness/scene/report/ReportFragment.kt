package com.ltmb.fitness.scene.report

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.core.content.ContextCompat
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
import com.ltmb.fitness.data.remote.model.workouthistory.WorkoutHistoryModel
import com.ltmb.fitness.databinding.FragmentReportBinding
import com.ltmb.fitness.internal.extension.observeNonNull
import com.ltmb.fitness.internal.util.functional.getColorInTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import java.util.Date
import java.util.Random


@AndroidEntryPoint
class ReportFragment : BaseFragment<ReportViewModel, FragmentReportBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_report

    override fun initialize() {
        super.initialize()

        binding.reportToday.setOnClickListener {
            viewModel.selectReport.value = true
            viewModel.getWorkoutHistoryToday()
        }

        binding.reportMonth.setOnClickListener {
            viewModel.selectReport.value = false
            viewModel.getWorkoutHistoryMonth()
        }

        viewModel.selectReport.observeNonNull(viewLifecycleOwner) {
            val primaryColor =
                getColorInTheme(requireContext(), androidx.appcompat.R.attr.colorPrimary)

            val whiteColor = ContextCompat.getColor(requireContext(), R.color.white)
            val hintColor = ContextCompat.getColor(requireContext(), R.color.alabaster)
            val textColor = ContextCompat.getColor(requireContext(), R.color.black)
            if (it) {
                binding.reportToday.setTextColor(primaryColor)
                binding.reportToday.setBackgroundColor(hintColor)
                binding.reportMonth.setTextColor(textColor)
                binding.reportMonth.setBackgroundColor(whiteColor)

            } else {
                binding.reportMonth.setTextColor(primaryColor)
                binding.reportMonth.setBackgroundColor(hintColor)
                binding.reportToday.setTextColor(textColor)
                binding.reportToday.setBackgroundColor(whiteColor)
            }
        }

        val barChart: BarChart = binding.barChart
        drawBarChart(barChart)

        val lineChart: LineChart = binding.lineChart


        viewModel.getWorkoutHistoryToday()

        viewModel.workoutHistoryList.observeNonNull(viewLifecycleOwner) { workoutHistoryList ->
            if (viewModel.selectReport.value == true) {
                drawLineChartDay(lineChart, workoutHistoryList)
            } else {
                drawLineChartMonth(lineChart, workoutHistoryList)
            }
        }

//        val lineChart: LineChart = binding.lineChart
//
//        val entries = arrayListOf(
//            Entry(0f, 62f),
//            Entry(1f, 69f),
//            Entry(2f, 64f),
//            Entry(3f, 75f),
//            Entry(4f, 68f)
//        )
//
//        val dataSet = LineDataSet(entries, "Data Set 1")
//        dataSet.color = Color.RED
//        dataSet.setDrawFilled(true)
//        dataSet.fillColor = Color.BLUE // Màu fill
//        dataSet.fillAlpha =
//            128 // Độ trong suốt của fill color (0-255), 255 là hoàn toàn không trong suốt
//
//        val lineData = LineData(dataSet)
//        lineChart.data = lineData
//        lineChart.invalidate()

//        pieChart()

    }

    private fun drawLineChart(lineChart: LineChart, entries: ArrayList<Entry>) {

        val dataSet = LineDataSet(entries, "Thời gian luyện tập trong ngày")
        dataSet.setColor(Color.BLUE)
        dataSet.setValueTextColor(Color.BLACK)

//        dataSet.setDrawValues(false)

        val lineData = LineData(dataSet)

        lineChart.setData(lineData)

        lineChart.description.isEnabled = false
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM

        lineChart.xAxis.setDrawGridLines(false)

        lineChart.axisLeft.setDrawGridLines(false)
        lineChart.axisRight.setDrawGridLines(false)

        lineChart.axisLeft.axisMinimum = 0f
        lineChart.axisRight.axisMinimum = 0f


        lineChart.invalidate()
    }

    private fun getHourOfDay(date: Date): Int {
        val calendar = Calendar.getInstance()
        calendar.time = date

        return calendar.get(Calendar.HOUR_OF_DAY)
    }
    private fun drawLineChartDay(
        lineChart: LineChart,
        workoutHistoryList: List<WorkoutHistoryModel>
    ) {
        val map = mutableMapOf<Int, Long>()
        val entries = ArrayList<Entry>()
        for (wh in workoutHistoryList) {
            val hours = getHourOfDay(wh.createdAt)
            if (map.containsKey(hours)) {
                map[hours] = map[hours]!! + wh.times
            } else {
                map[hours] = wh.times
            }
            println("Fragment: hours ${wh.createdAt.hours}")
        }
        for (i in 0 until 24) {
            if (map.containsKey(i)) {
                entries.add(Entry(i.toFloat(), map[i]?.toFloat()!!))
            } else {
                entries.add(Entry(i.toFloat(), 0f))
            }
        }
        drawLineChart(lineChart, entries)
    }
    private fun getDayOfMonth(date: Date): Int {
        val calendar = Calendar.getInstance()
        calendar.time = date

        return calendar.get(Calendar.DAY_OF_MONTH)
    }
    private fun drawLineChartMonth(
        lineChart: LineChart,
        workoutHistoryList: List<WorkoutHistoryModel>
    ) {
        val map = mutableMapOf<Int, Long>()
        val entries = ArrayList<Entry>()
        for (wh in workoutHistoryList) {
            val day = getDayOfMonth(wh.createdAt)
            if (map.containsKey(day)) {
                map[day] = map[day]!! + wh.times
            } else {
                map[day] = wh.times
            }
            println("Fragment: day ${day}")
        }
        val calendar = Calendar.getInstance()
        calendar.time = Date()
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        for (i in 1 until daysInMonth) {
            if (map.containsKey(i)) {
                entries.add(Entry(i.toFloat(), map[i]?.toFloat()!!))
            } else {
                entries.add(Entry(i.toFloat(), 0f))
            }
        }
        drawLineChart(lineChart, entries)
    }

    private fun barEntries(type: Int): ArrayList<BarEntry> {
        val barEntries = ArrayList<BarEntry>()
        val random = Random()
        var start = 0
        if (type == 1) {
            start = 5
        } else if (type == 2) {
            start = 30
        } else {
            start = 200
        }
        for (i in 0 until 7) {
            barEntries.add(BarEntry(i.toFloat(), (start + random.nextInt(start)).toFloat()))
        }
        return barEntries
    }


    private fun drawBarChart(barChart: BarChart) {
        val barDataSet1 = BarDataSet(barEntries(1), "Workout")
        barDataSet1.setColor(Color.RED)
        val barDataSet2 = BarDataSet(barEntries(2), "Minute")
        barDataSet2.setColor(Color.GREEN)
        val barDataSet3 = BarDataSet(barEntries(3), "Kcal")
        barDataSet3.setColor(Color.YELLOW)

        val barData = BarData(barDataSet1, barDataSet2, barDataSet3)

        barChart.setData(barData)

        val days = arrayOf("2", "3", "4", "5", "6", "7", "CN")

        val xAxis: XAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(days)
        xAxis.setCenterAxisLabels(true)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
//        xAxis.setGranularity(1f)
//        xAxis.isGranularityEnabled = true

//        barChart.setDragEnabled(true)
//        barChart.setVisibleXRangeMaximum(7f)

        val barSpace = 0.08f
        val groupSpace = 0.4f
        barData.barWidth = 0.12f

        barChart.description.isEnabled = false

        xAxis.axisMinimum = 0f
        xAxis.setAxisMaximum(0 + barChart.barData.getGroupWidth(groupSpace, barSpace) * 7)
        barChart.axisLeft.setAxisMinimum(0f)
        barChart.axisRight.setAxisMinimum(0f)

        barChart.xAxis.setDrawGridLines(false)

        barChart.groupBars(0f, groupSpace, barSpace)


        barChart.invalidate()
    }

    fun pieChart() {
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