package com.example.bajp_submission3.utils

import com.example.bajp_submission3.data.source.local.entity.MovieEntity
import com.example.bajp_submission3.data.source.local.entity.TvShowEntity
import com.example.bajp_submission3.data.source.remote.response.DetailContentResponse
import com.example.bajp_submission3.data.source.remote.response.movie.DetailMovieGenre

object DataDummy {
    fun dataDummyMovies(): ArrayList<MovieEntity> {
        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                634649,
                "Spider-Man: No Way Home",
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                "2021-12-15",
                "",
                8.5,
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg"
            )
        )

        movies.add(
            MovieEntity(
                524434,
                "Eternals",
                "The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. When an unexpected tragedy forces them out of the shadows, they are forced to reunite against mankind’s most ancient enemy, the Deviants.",
                "2021-11-03",
                "",
                7.3,
                "/bcCBq9N1EMo3daNIjWJ8kYvrQm6.jpg"
            )
        )

        movies.add(
            MovieEntity(
                585083,
                "Hotel Transylvania: Transformania",
                "When Van Helsing's mysterious invention, the \\\"Monsterfication Ray,\\\" goes haywire, Drac and his monster pals are all transformed into humans, and Johnny becomes a monster. In their new mismatched bodies, Drac and Johnny must team up and race across the globe to find a cure before it's too late, and before they drive each other crazy.",
                "Sep 30, 2021",
                "",
                8.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg"
            )
        )

        return movies
    }

    fun dataDummyDetailMovies(): MovieEntity {
        return MovieEntity(
            634649,
            "Spider-Man: No Way Home",
            "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            "2021-12-15",
            "Action & Adventure, Comedy, Sci-Fi & Fantasy",
            8.4,
            "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            false
        )
    }

    fun dataDummyTvShow(): ArrayList<TvShowEntity> {
        val tvShow = ArrayList<TvShowEntity>()

        tvShow.add(
            TvShowEntity(
                110492,
                "Peacemaker",
                "The continuing story of Peacemaker – a compellingly vainglorious man who believes in peace at any cost, no matter how many people he has to kill to get it – in the aftermath of the events of “The Suicide Squad.”",
                "2022-01-13",
                "",
                8.5,
                "/hE3LRZAY84fG19a18pzpkZERjTE.jpg",
            )
        )

        tvShow.add(
            TvShowEntity(
                85552,
                "Euphoria",
                "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media.",
                "2019-06-16",
                "",
                8.4,
                "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
            )
        )

        tvShow.add(
            TvShowEntity(
                115036,
                "The Book of Boba Fett",
                "Legendary bounty hunter Boba Fett and mercenary Fennec Shand must navigate the galaxy’s underworld when they return to the sands of Tatooine to stake their claim on the territory once ruled by Jabba the Hutt and his crime syndicate.",
                "2021-12-29",
                "",
                8.1,
                "/gNbdjDi1HamTCrfvM9JeA94bNi2.jpg",
            )
        )

        return tvShow
    }

    fun dataDummyDetailTvShow(): TvShowEntity {
        return TvShowEntity(
            110492,
            "Euphoria",
            "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media.",
            "2019-06-16",
            "Drama",
            8.4,
            "/gNbdjDi1HamTCrfvM9JeA94bNi2.jpg",
            false
        )
    }

    // remote
    fun dataDummyRemoteMovie(): List<DetailContentResponse> {
        return listOf(
            DetailContentResponse(
                634649,
                originalTitle = "Spider-Man: No Way Home",
                "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
                "2021-12-15",
                8.5,
                "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
                originalName = "",
                FirstAirDate = "",
                genres = listOf(
                    DetailMovieGenre(
                        id = 28,
                        name = "Action"
                    ),
                    DetailMovieGenre(
                        id = 12,
                        name = "Adventure"
                    ),
                    DetailMovieGenre(
                        id = 878,
                        name = "Science Fiction"
                    )
                )
            ),

            DetailContentResponse(
                585083,
                originalTitle = "Hotel Transylvania: Transformania",
                "When Van Helsing's mysterious invention, the \\\"Monsterfication Ray,\\\" goes haywire, Drac and his monster pals are all transformed into humans, and Johnny becomes a monster. In their new mismatched bodies, Drac and Johnny must team up and race across the globe to find a cure before it's too late, and before they drive each other crazy.",
                "Sep 30, 2021",
                8.2,
                "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/rjkmN1dniUHVYAtwuV3Tji7FsDO.jpg",
                originalName = "",
                FirstAirDate = "",
                genres = listOf(
                    DetailMovieGenre(
                        id = 16,
                        name = "Animation"
                    ),
                    DetailMovieGenre(
                        id = 10751,
                        name = "Family"
                    ),
                    DetailMovieGenre(
                        id = 14,
                        name = "Fantasy"
                    )
                )
            ),

            DetailContentResponse(
                524434,
                originalTitle = "Eternals",
                "The Eternals are a team of ancient aliens who have been living on Earth in secret for thousands of years. When an unexpected tragedy forces them out of the shadows, they are forced to reunite against mankind’s most ancient enemy, the Deviants.",
                "2021-11-03",
                7.3,
                "/bcCBq9N1EMo3daNIjWJ8kYvrQm6.jpg",
                originalName = "",
                FirstAirDate = "",
                genres = listOf(
                    DetailMovieGenre(
                        id = 28,
                        name = "Action"
                    ),
                    DetailMovieGenre(
                        id = 12,
                        name = "Adventure"
                    ),
                    DetailMovieGenre(
                        id = 14,
                        name = "Fantasy"
                    )
                )
            )
        )
    }

    fun dataDummyRemoteDetailMovie(): DetailContentResponse {
        return DetailContentResponse(
            634649,
            originalTitle = "Spider-Man: No Way Home",
            "Peter Parker is unmasked and no longer able to separate his normal life from the high-stakes of being a super-hero. When he asks for help from Doctor Strange the stakes become even more dangerous, forcing him to discover what it truly means to be Spider-Man.",
            "2021-12-15",
            8.5,
            "/1g0dhYtq4irTY1GPXvft6k4YLjm.jpg",
            originalName = "",
            FirstAirDate = "",
            genres = listOf(
                DetailMovieGenre(
                    id = 28,
                    name = "Action"
                ),
                DetailMovieGenre(
                    id = 12,
                    name = "Adventure"
                ),
                DetailMovieGenre(
                    id = 878,
                    name = "Science Fiction"
                )
            )
        )
    }

    fun dataDummyRemoteTvShow(): List<DetailContentResponse> {
        return listOf(
            DetailContentResponse(
                110492,
                originalTitle = "",
                "The continuing story of Peacemaker – a compellingly vainglorious man who believes in peace at any cost, no matter how many people he has to kill to get it – in the aftermath of the events of “The Suicide Squad.",
                releaseDate = "",
                8.5,
                "/hE3LRZAY84fG19a18pzpkZERjTE.jpg",
                originalName = "Peacemaker",
                FirstAirDate = "2022-01-13",
                genres = listOf(
                    DetailMovieGenre(
                        id = 10759,
                        name = "Action & Adventure"
                    ),
                    DetailMovieGenre(
                        id = 35,
                        name = "Comedy"
                    ),
                    DetailMovieGenre(
                        id = 10765,
                        name = "Sci-Fi & Fantasy"
                    )
                )
            ),

            DetailContentResponse(
                85552,
                originalTitle = "",
                "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media.",
                releaseDate = "",
                8.4,
                "/jtnfNzqZwN4E32FGGxx1YZaBWWf.jpg",
                originalName = "Euphoria",
                FirstAirDate = "2019-06-16",
                genres = listOf(
                    DetailMovieGenre(
                        id = 18,
                        name = "Drama"
                    )
                )
            ),

            DetailContentResponse(
                77169,
                originalTitle = "",
                "The tournament comes to a shocking end, with the aftermath leaving its participants reeling – and two champions facing uncertain futures.",
                releaseDate = "",
                8.6,
                "/6hQPOqaNZ1aKwuPlcgfCvjjH0kj.jpg",
                originalName = "Cobra Kai",
                FirstAirDate = "2018-05-02",
                genres = listOf(
                    DetailMovieGenre(
                        id = 10759,
                        name = "Action & Adventure"
                    ),
                    DetailMovieGenre(
                        id = 18,
                        name = "Drama"
                    )
                )
            )
        )
    }

    fun dataDummyRemoteDetailTvShow(): DetailContentResponse {
        return DetailContentResponse(
            110492,
            originalTitle = "",
            "A group of high school students navigate love and friendships in a world of drugs, sex, trauma, and social media.",
            releaseDate = "",
            8.4,
            "/gNbdjDi1HamTCrfvM9JeA94bNi2.jpg",
            originalName = "Euphoria",
            FirstAirDate = "2019-06-16",
            genres = listOf(
                DetailMovieGenre(
                    id = 18,
                    name = "Drama"
                )
            )
        )
    }
}