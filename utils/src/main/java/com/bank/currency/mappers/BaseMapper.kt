package com.bank.currency.mappers

interface BaseMapper<FirstModel, SecondModel> {

  fun map(model: FirstModel): SecondModel
}