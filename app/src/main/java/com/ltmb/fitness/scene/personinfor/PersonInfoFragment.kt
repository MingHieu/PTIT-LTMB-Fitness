package com.ltmb.fitness.scene.personinfor

import com.ltmb.fitness.R
import com.ltmb.fitness.base.BaseFragment
import com.ltmb.fitness.databinding.FragmentPersonInfoBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonInfoFragment : BaseFragment<PersonInfoViewModel, FragmentPersonInfoBinding>() {
    override val layoutId get() = R.layout.fragment_person_info
}