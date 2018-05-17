//
//  MovieDetailsPresenter.swift
//  MovieDB
//
//  Created by Anh Tran on 3/30/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation

class MovieDetailsPresenter {
    
    //Get movie details
    func getMovieDetails(movieId: Int, result: @escaping (_ error: String?, _ movieDetails: MovieDetails?) -> Void) {
        MovieDetails.getMovieDetails(movieId: movieId) { (error, movieDetails) in
            if let error = error {
                result(error, nil)
                return
            }
            
            result(nil, movieDetails)
        }
    }
    
    //Check states
    func getStates(movieId: Int, result: @escaping (_ error: String?, _ states: MovieStates?) -> Void) {
        Movie.checkMovieStates(movieId: movieId) { (error, states) in
            guard let error = error else {
                result(nil, states)
                return
            }
            
            result(error, nil)
        }
    }
    
    //Mark favorite
    
    func markFavorite(movieId: Int, isMarked: Bool, result: @escaping (_ error: String?) -> Void) {
        User.markFavoriteMovie(movieId: movieId, isMarked: isMarked) { error in
            guard let error = error else { return result(nil) }
            result(error)
        }
    }
}
