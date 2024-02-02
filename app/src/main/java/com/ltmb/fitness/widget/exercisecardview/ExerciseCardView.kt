package com.ltmb.fitness.widget.exercisecardview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ltmb.fitness.R
import com.ltmb.fitness.databinding.ViewExerciseCardViewBinding
import com.squareup.picasso.Picasso

class ExerciseCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding =
        ViewExerciseCardViewBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        )

    init {
        readAttributes(attrs)
    }

    private fun readAttributes(attrs: AttributeSet?) {
        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.ExerciseCardView,
            0,
            0
        ).apply {
            if (hasValue(R.styleable.ExerciseCardView_ecv_thumbnail_src)) {
                val url = getString(R.styleable.ExerciseCardView_ecv_thumbnail_src)
                Picasso.get().load(url).into(binding.ecvThumbnail)
            }

            if (hasValue(R.styleable.ExerciseCardView_ecv_title)) {
                val text = getString(R.styleable.ExerciseCardView_ecv_title)
                binding.ecvTitle.text = text
            }

            if (hasValue(R.styleable.ExerciseCardView_ecv_sub_title)) {
                val text = getString(R.styleable.ExerciseCardView_ecv_sub_title)
                binding.ecvSubTitle.text = text
            }
        }
    }
}