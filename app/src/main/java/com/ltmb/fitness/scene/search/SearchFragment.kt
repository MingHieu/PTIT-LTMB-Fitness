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

        viewModel.ignoreFilterFirstTime = true

        binding.keySearchHistoryAdapter = KeySearchHistoryAdapter(object : KeySearchCallback {
            override fun onItemClick(keySearch: String) {
                viewModel.instantSearch = true
                binding.searchBox.setValue(keySearch)
            }

            override fun onDeleteItem(id: String) {
                viewModel.deleteKeySearchHistory(id)
            }

        })

        binding.searchAdapter = SearchAdapter(object : SearchCallback {
            override fun onSearchItemClick(id: String) {
                viewModel.goToSearchDetail(id)
            }
        })
        binding.searchBox.focus()
        binding.searchBox.setValue(viewModel.keySearch.value.orEmpty())
        if (!viewModel.keySearch.value.isNullOrEmpty()) {
            viewModel.ignoreSearchFirstTime = true
        }
        binding.searchBox.onTextChanged = { text ->
            if (viewModel.ignoreSearchFirstTime) {
                viewModel.ignoreSearchFirstTime = false
            } else {
                viewModel.onSearch(text)
            }
        }

        viewModel.filterSelected.observeNonNull(viewLifecycleOwner) { it1 ->
            if (viewModel.ignoreFilterFirstTime) {
                viewModel.ignoreFilterFirstTime = false
                return@observeNonNull
            }
            viewModel.onChangeFilter()
            val filterValues = listOf(
                SearchFilter.ALL,
                SearchFilter.BEGINNER,
                SearchFilter.INTERMEDIATE,
                SearchFilter.ADVANCED
            )
            val filterBtnList = listOf(
                binding.filterAllButton,
                binding.filterWorkoutBeginnerButton,
                binding.filterWorkoutIntermediateButton,
                binding.filterWorkoutAdvancedButton
            )
            val filterTextList = listOf(
                binding.filterAllText,
                binding.filterWorkoutBeginnerText,
                binding.filterWorkoutIntermediateText,
                binding.filterWorkoutAdvancedText
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