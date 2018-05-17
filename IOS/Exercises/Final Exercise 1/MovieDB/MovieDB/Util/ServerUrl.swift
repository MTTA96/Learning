//
//  ServerUrl.swift
//  MovieDB
//
//  Created by Anh Tran on 3/28/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation

struct ServerUrl {
    static let baseUrl = "https://api.themoviedb.org"
    static let imageStorage = "https://image.tmdb.org/t/p/w500"
    
    static let apiKey = "9d92d99f2296cccc3841576fb68e33ab"
    static let language = "&language=en-US"
    
    // MARK: - Account
    static let authenticationUrl = baseUrl + "/3/authentication"
    static let requestTokenUrl = authenticationUrl + "/token/new?api_key=" + apiKey + language
    static let validatingLoginUrl = authenticationUrl + "/token/validate_with_login?api_key=" + apiKey + language
    static let createSessionUrl = authenticationUrl + "/session/new?api_key=" + apiKey + language
    static let signUpUrl = baseUrl + "/account/signup"
    static let markFavoriteUrl = baseUrl + "/3/account/favorite?api_key=" + apiKey
    static let favoriteMovieListUrl = baseUrl + "/3/account/%7Bmovie_id%7D/favorite/movies?api_key=" + apiKey
    
    // MARK: - Movie
    static let movieUrl = baseUrl + "/3/movie"
    static let movieStatesUrl = "/account_states?api_key=" + apiKey
    static let popularMoviesUrl = movieUrl + "/popular?api_key=" + apiKey + language
    static let topRatedMoviesUrl = movieUrl + "/top_rated?api_key=" + apiKey + language
    static let nowPlayingMoviesUrl = movieUrl + "/now_playing?api_key=" + apiKey + language
    static let officialGenreListUrl = baseUrl + "/3/genre/movie/list?api_key=" + apiKey + language
    static let searchMovieUrl = baseUrl + "/3/search/movie?api_key=" + apiKey + language
    
    //People
    static let personUrl = baseUrl + "/3/person"
    static let popularPeopelUrl = personUrl + "/popular?api_key=" + apiKey + language
    
}
