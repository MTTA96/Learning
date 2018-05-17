//
//  FavoritePresenter.swift
//  MovieDB
//
//  Created by Anh Tran on 4/2/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation

class FavoritePresenter {
    
    var currentPage = 1
    var totalPages = 1
    
    // Get movies list
    func getMovieList (url: String!, result: @escaping (_ error: String?, _ movies: MovieList?) -> Void) {
        if currentPage > totalPages {
            result("End", nil)
            return
        }
        MovieList.getMovieList(url: url, page: currentPage) { (error, baseResponse, movies) in
            if let error = error {
                result(error, nil)
                return
            }
                
            result(nil, movies)
            self.currentPage += 1
            self.totalPages = (baseResponse?.totalPages)!
        }
        
    }
}
