package com.devloper.squad.feature_beer.data.mapper

import com.devloper.squad.base.mapper.Mapper
import com.devloper.squad.feature_beer.data.model.Beer
import com.devloper.squad.feature_beer.domain.model.BeerDomain

class BeerMapper : Mapper<Beer, BeerDomain> {
  override fun map(input: Beer): BeerDomain {
    return BeerDomain().apply {
      addAll(input)
    }
  }
}