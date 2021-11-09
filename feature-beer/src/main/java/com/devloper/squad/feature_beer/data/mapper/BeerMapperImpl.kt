package com.devloper.squad.feature_beer.data.mapper

import com.devloper.squad.base.mapper.Mapper
import com.devloper.squad.feature_beer.data.model.Beer
import com.devloper.squad.feature_beer.domain.model.BeerDomain
import com.devloper.squad.feature_beer.domain.model.BeerItemDomain

class BeerMapperImpl : BeerMapper {
  override fun map(input: Beer): BeerDomain {
    val beerDomain = BeerDomain()
    input.forEach { beerItem ->
      beerDomain.add(
        BeerItemDomain(
          description = beerItem.description,
          imageUrl = beerItem.imageUrl,
          id = beerItem.id,
          name = beerItem.name
        )
      )
    }

    return beerDomain
  }
}

interface BeerMapper : Mapper<Beer, BeerDomain>
