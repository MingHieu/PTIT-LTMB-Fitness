package com.ltmb.fitness.scene.report

import android.annotation.SuppressLint
import android.graphics.Color
import android.widget.TextView
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
import com.ltmb.fitness.data.remote.model.user.UserModel
import com.ltmb.fitness.data.remote.model.workouthistory.WorkoutHistoryModel
import com.ltmb.fitness.databinding.FragmentReportBinding
import com.ltmb.fitness.internal.extension.observeNonNull
import com.ltmb.fitness.internal.util.functional.getColorInTheme
import com.ltmb.fitness.uimodel.GenderUiModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.DecimalFormat
import java.util.Calendar
import java.util.Date
import java.util.Random


@AndroidEntryPoint
class ReportFragment : BaseFragment<ReportViewModel, FragmentReportBinding>() {
    override val layoutId: Int
        get() = R.layout.fragment_report

    override fun initialize() {
        super.initialize()

        viewModel.getWorkoutHistoryToday()
        viewModel.getUser()
        viewModel.getWorkoutHistory7day()

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

//        drawBarChart(binding.barChart)

        val lineChart: LineChart = binding.lineChart

        viewModel.workoutHistoryList.observeNonNull(viewLifecycleOwner) { workoutHistoryList ->
            if (viewModel.selectReport.value == true) {
                drawLineChartDay(lineChart, workoutHistoryList)
            } else {
                drawLineChartMonth(lineChart, workoutHistoryList)
            }
        }

        viewModel.workoutHistory7day.observeNonNull(viewLifecycleOwner) {workoutHistory7day->
            drawBarChart(binding.barChart, workoutHistory7day)

        }
        viewModel.userModel.observeNonNull(viewLifecycleOwner) { userModel ->
            println(userModel)
            val weight = userModel.weight.toDouble()
            val height = userModel.height.toDouble() / 100
            val age = userModel.age
            val gender = userModel.gender
            val df = DecimalFormat("#.##")
            val bmi = viewModel.calculatorBMI(weight, height)
            val bfp = viewModel.calculateBFP(weight, height, age, gender)
            binding.tvBmi.text = "BMI: ${df.format(bmi)} kg/m²"
            binding.tvBfp.text = "BFP: ${df.format(bfp)}%"

            binding.bfpStatus.removeAllViews()
            var stringArr = resources.getStringArray(R.array.report_status_bfp_male)
            if (userModel.gender == GenderUiModel.FEMALE) {
                stringArr = resources.getStringArray(R.array.report_status_bfp_female)
            }
            for (value in stringArr) {
                val textView = TextView(requireContext())
                textView.text = value
                textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                binding.bfpStatus.addView(textView)
            }
        }

        val stringArr = resources.getStringArray(R.array.report_status_bmi)
        for (value in stringArr) {
            val textView = TextView(requireContext())
            textView.text = value
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            binding.bmiStatus.addView(textView)
        }

    }

    private fun setWorkoutTimeKcal(workout: Long, time: Long, kcal: Long) {
        binding.tvWorkout.text = workout.toString()
        binding.tvTime.text = time.toString()
        binding.tvKcal.text = kcal.toString()
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
        var totalWorkout = 0L
        var totalTime = 0L
        var totalKcal = 0L
        val map = mutableMapOf<Int, Long>()
        val entries = ArrayList<Entry>()
        for (wh in workoutHistoryList) {
            totalWorkout += wh.workouts
            totalTime += wh.times
            totalKcal += wh.kcal
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
        setWorkoutTimeKcal(totalWorkout, totalTime, totalKcal)
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
        var totalWorkout = 0L
        var totalTime = 0L
        var totalKcal = 0L
        val map = mutableMapOf<Int, Long>()
        val entries = ArrayList<Entry>()
        for (wh in workoutHistoryList) {
            totalWorkout += wh.workouts
            totalTime += wh.times
            totalKcal += wh.kcal
            val day = getDayOfMonth(wh.createdAt)
            if (map.containsKey(day)) {
                map[day] = map[day]!! + wh.times
            } else {
                map[day] = wh.times
            }
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
        setWorkoutTimeKcal(totalWorkout, totalTime, totalKcal)
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

    private fun putMap(key: Int, value: Long, map: MutableMap<Int, Long>){
        if(map.containsKey(key)){
            map[key] = map[key]!! + value
        }else{
            map[key] = value
        }
    }
    private fun getMapEntry(map: MutableMap<Int, Long>): ArrayList<BarEntry> {
        val listEntry = ArrayList<BarEntry>()
        val today = viewModel.startToday()
        val calendar = Calendar.getInstance()
        calendar.time = today
        calendar.add(Calendar.DAY_OF_YEAR, - 7)
        val day = getDayOfMonth(calendar.time)
        for(i in day + 1  until  day + 8){
            if(map.containsKey(i)){
                listEntry.add(BarEntry(i.toFloat(), map[i]!!.toFloat()))
                println("---------------${i}: ${map[i]}")
            }else{
                listEntry.add(BarEntry(i.toFloat(), 0f))
                println("----------------${i}: 0")
            }
        }
        return listEntry
    }
    private fun drawBarChart(barChart: BarChart, workoutHistory7day: List<WorkoutHistoryModel>) {
        val mapWorkout = mutableMapOf<Int, Long>()
        val mapTime = mutableMapOf<Int, Long>()
        val mapKcal = mutableMapOf<Int, Long>()
        for (wh in workoutHistory7day) {
            val day = getDayOfMonth(wh.createdAt)
            putMap(day, wh.workouts, mapWorkout)
            putMap(day, wh.times, mapTime)
            putMap(day, wh.kcal, mapKcal)
        }

        val barDataSet1 = BarDataSet(getMapEntry(mapWorkout), "Workout")
        barDataSet1.setColor(Color.RED)
        val barDataSet2 = BarDataSet(getMapEntry(mapTime), "Minute")
        barDataSet2.setColor(Color.GREEN)
        val barDataSet3 = BarDataSet(getMapEntry(mapKcal), "Kcal")
        barDataSet3.setColor(Color.YELLOW)

        val barData = BarData(barDataSet1, barDataSet2, barDataSet3)

        barChart.setData(barData)

        val today = viewModel.startToday()
        val calendar = Calendar.getInstance()
        calendar.time = today
        calendar.add(Calendar.DAY_OF_YEAR, - 7)
        val day = getDayOfMonth(calendar.time)
        val stringArray = ArrayList<String>()
        for(i in day +1 until day+8){
            stringArray.add(i.toString())
        }
        val days = stringArray.toTypedArray()
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

}