//
//  SearchPresenter.swift
//  MovieDB
//
//  Created by Anh Tran on 4/4/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation

class SearchPresenter {
    var totalPages = 1
    var currentPage = 1
    
    //Get result
    func getResult(query: String!, result: @escaping (_ error: String?, _ movies: MovieList?) -> Void ) {
        if currentPage > totalPages {
            result("End", nil)
            return
        }
        
        MovieList.searchMovie(page: currentPage, query: query) { (error, baseResponse, movies) in
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
