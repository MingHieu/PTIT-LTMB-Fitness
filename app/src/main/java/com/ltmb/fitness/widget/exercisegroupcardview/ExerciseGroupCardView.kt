package com.ltmb.fitness.widget.exercisegroupcardview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.ltmb.fitness.R
import com.ltmb.fitness.databinding.ViewExerciseGroupCardViewBinding
import com.squareup.picasso.Picasso

class ExerciseGroupCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    private val binding =
        ViewExerciseGroupCardViewBinding.inflate(
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
            R.styleable.ExerciseGroupCardView,
            0,
            0
        ).apply {
            if (hasValue(R.styleable.ExerciseGroupCardView_egc_thumbnail_src)) {
                val url = getString(R.styleable.ExerciseGroupCardView_egc_thumbnail_src)
                Picasso.get().load(url).into(binding.egcThumbnail)
            }

            if (hasValue(R.styleable.ExerciseGroupCardView_egc_title)) {
                val text = getString(R.styleable.ExerciseGroupCardView_egc_title)
                binding.egcTitle.text = text
            }

            if (hasValue(R.styleable.ExerciseGroupCardView_egc_sub_title)) {
                val text = getString(R.styleable.ExerciseGroupCardView_egc_sub_title)
                binding.egcSubTitle.text = text
            }
        }
    }
}