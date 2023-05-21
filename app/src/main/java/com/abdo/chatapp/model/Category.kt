package com.abdo.chatapp.model

import com.abdo.chatapp.R

data class Category(
    val id: String? = null,
    val name: String? = null,
    val imageId: Int? = null
) {
    companion object {
        const val MUSIC = "music"
        const val SPORTS = "sports"
        const val CARS = "cars"
        const val MOVIES = "movies"
        const val TECH = "tech"
        const val SCIENCE = "science"
        const val HEALTH = "health"
        fun fromId(catId: String): Category {
            when (catId) {
                MUSIC -> {
                    return Category(
                        MUSIC,
                        name = "Music",
                        imageId = R.drawable.music
                    )
                }

                SPORTS -> {
                    return Category(
                        SPORTS,
                        name = "Sports",
                        imageId = R.drawable.sports
                    )
                }

                CARS -> {
                    return Category(
                        CARS,
                        name = "Cars",
                        imageId = R.drawable.car
                    )
                }

                MOVIES -> {
                    return Category(
                        MOVIES,
                        name = "Movies",
                        imageId = R.drawable.movies
                    )
                }

                TECH -> {
                    return Category(
                        TECH,
                        name = "Tech",
                        imageId = R.drawable.tech
                    )
                }

                SCIENCE -> {
                    return Category(
                        SCIENCE,
                        name = "Science",
                        imageId = R.drawable.scince
                    )
                }

                else -> {
                    return Category(
                        HEALTH,
                        name = "Health",
                        imageId = R.drawable.healthcare
                    )
                }

            }

        }

        fun getCategoriesList(): List<Category> {
            return listOf(
                fromId(MUSIC),
                fromId(SPORTS),
                fromId(CARS),
                fromId(MOVIES),
                fromId(TECH),
                fromId(SCIENCE),
                fromId(HEALTH)
            )

        }
    }

}