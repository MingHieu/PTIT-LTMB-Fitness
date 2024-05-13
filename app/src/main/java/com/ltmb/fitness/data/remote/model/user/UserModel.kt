package com.ltmb.fitness.data.remote.model.user

import com.ltmb.fitness.uimodel.GenderUiModel

data class UserModel(
    var gender: GenderUiModel = GenderUiModel.MALE,
    var age: Int = 18,
    var height: Int = 170,
    var weight: Int = 50,
    var dayPlan: Int = 1,
    var deviceToken: String = ""
)



