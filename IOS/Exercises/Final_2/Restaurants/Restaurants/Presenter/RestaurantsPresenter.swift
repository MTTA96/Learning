//
//  RestaurantsPresenter.swift
//  Restaurants
//
//  Created by Anh Tran on 4/20/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation

class RestaurantsPresenter {
    
    // Get restaurant list
    func getRestaurantList(result: @escaping (_ error: String?, _ restaurantList: Array<Restaurant>?) -> Void) {
        Restaurant.getRestaurantList() { (error, restaurantList) in
            if let error = error {
                print(error)
                result(error, nil)
                return
            }
            
            // Get list success
            result(nil, restaurantList)
        }
    }
}
