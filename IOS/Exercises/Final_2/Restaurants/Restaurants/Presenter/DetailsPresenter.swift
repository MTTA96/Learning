//
//  DetailsPresenter.swift
//  Restaurants
//
//  Created by Anh Tran on 4/20/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation

class DetailsPresenter {
    
    // Get restaurant detail
    func getRestaurantDetail(restaurantID: String!, result: @escaping (_ error: String?, _ details: Restaurant?) -> Void) {
        
        Restaurant.getDetails(restaurantID: restaurantID) { (error, details) in
            if let error = error {
                print(error)
                return
            }
            
            // Get details success
            result(nil, details)
            
        }
    }
    
    // Get restaurant rating state
    func getRestaurantRatingState(restaurantID: String!, result: @escaping (_ error: String?, _ result: Int?) -> Void) {
        Restaurant.getRestaurantRatingState(restaurantID: restaurantID) { (error, point) in
            if let error = error {
                result(error, nil)
                print(error.localizedCapitalized)
                return
            }
            
            result(nil, point)
        }
    }
    
    // Update rating point
    func updateRatingPoint(restaurantID: String!, point: Int!, result: @escaping (_ error: String?) -> Void) {
        Restaurant.updateRating(restaurantID: restaurantID, point: point) { error in
            result(error)
        }
    }
}
