package iut.nantes.exo0.controller

import iut.nantes.exo0.validation.ValidAgeRange

@ValidAgeRange(maxRange = 100)
data class PetSearch(val minAge: Int?, val maxAge: Int?)
