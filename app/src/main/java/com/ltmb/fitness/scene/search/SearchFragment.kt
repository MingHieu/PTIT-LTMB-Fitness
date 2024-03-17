package com.ltmb.fitness.scene.search

import android.content.res.ColorStateList
import androidx.core.content.ContextCompat
import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentSearchBinding
import com.ltmb.fitness.internal.extension.observeNonNull
import com.ltmb.fitness.internal.util.functional.getColorInTheme
import com.ltmb.fitness.uimodel.SearchFilter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>() {

    override val layoutId get() = R.layout.fragment_search

    override fun initialize() {
        super.initialize()

        binding.searchAdapter = SearchAdapter(object : SearchCallback {
            override fun onSearchItemClick() {
                TODO("Not yet implemented")
            }
        })
        binding.searchBox.focus()
        binding.searchBox.onTextChanged = { text ->
            viewModel.onSearch(text)
        }

        viewModel.filterSelected.observeNonNull(viewLifecycleOwner) { it1 ->
            val filterValues = listOf(SearchFilter.ALL, SearchFilter.WORKOUT, SearchFilter.FOOD)
            val filterBtnList = listOf(
                binding.filterAllButton,
                binding.filterWorkoutButton,
                binding.filterFoodButton
            )
            val filterTextList = listOf(
                binding.filterAllText,
                binding.filterWorkoutText,
                binding.filterFoodText
            )
            filterValues.zip(filterBtnList.zip(filterTextList)) { value, (btn, text) ->
                val isSelected = value == it1
                val bgTint =
                    if (isSelected) androidx.appcompat.R.attr.colorPrimary else R.attr.colorBackground
                val textTint =
                    if (isSelected) com.google.android.material.R.attr.colorOnPrimary else R.attr.colorText
                val stroke =
                    if (isSelected) android.R.color.transparent else R.color.athens_gray

                with(btn) {
                    backgroundTintList =
                        ColorStateList.valueOf(
                            getColorInTheme(
                                requireContext(),
                                bgTint
                            )
                        )
                    strokeColor = ContextCompat.getColor(requireContext(), stroke)
                }
                text.setTextColor(getColorInTheme(requireContext(), textTint))
            }
        }
    }
}