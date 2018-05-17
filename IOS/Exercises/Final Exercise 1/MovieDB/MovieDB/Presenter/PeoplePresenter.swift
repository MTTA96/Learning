//
//  PeoplePresenter.swift
//  MovieDB
//
//  Created by Anh Tran on 3/30/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation

class PeoplePresenter {
    
    var currentPage = 1
    var totalPages = 1
    
    // Get people
    func getPeople (result: @escaping (_ error: String?, _ people: People?) -> Void) {
        
        if currentPage > totalPages {
            result("End", nil)
            return
        }
        
        People.getPeople(page: currentPage) { (error, baseResponse, people) in
            if let error = error {
                result(error, nil)
                return
            }
            
            result(nil, people)
            self.currentPage += 1
            self.totalPages = (baseResponse?.totalPages)!
        }
    }
    
}
