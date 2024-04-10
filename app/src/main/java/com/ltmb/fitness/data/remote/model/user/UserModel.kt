package com.ltmb.fitness.data.remote.model.user

import com.ltmb.fitness.uimodel.GenderUiModel

data class UserModel(
    var gender: GenderUiModel = GenderUiModel.MALE,
    var age: Int = 0,
    var height: Int = 0,
    var weight: Int = 0,
    var dayPlan: Int = 0,
    var deviceToken: String = ""
)



