//
//  Restaurant.swift
//  Restaurants
//
//  Created by Anh Tran on 4/20/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation
import Firebase
import GoogleMaps

struct Restaurant {
    let id: String?
    let name: String?
    let address: String?
    let lat: CLLocationDegrees?
    let long: CLLocationDegrees?
    let price: String?
    let rating: Double?
    let banner: String?
    let open: String?
    let close: String?
}

// MARK: - Methods

extension Restaurant {
    // Parse from data snapshot to obj
    static func parseRestaurantData(dataSnapshot: DataSnapshot!) -> Restaurant{
        let restaurantData = dataSnapshot.value as! NSDictionary
        let restaurant = Restaurant(id: dataSnapshot.key,
                                    name: restaurantData["name"] as? String,
                                    address: restaurantData["address"] as? String,
                                    lat: restaurantData["lat"] as? CLLocationDegrees,
                                    long: restaurantData["long"] as? CLLocationDegrees,
                                    price: restaurantData["price"] as? String,
                                    rating: restaurantData["rating"] as? Double,
                                    banner: restaurantData["banner"] as? String,
                                    open: restaurantData["open"] as? String,
                                    close: restaurantData["close"] as? String)
        return restaurant
    }
    
    // Get restaurant list
    static func getRestaurantList(result: @escaping (_ error: String?, _ result: Array<Restaurant>?) -> Void) {
        var restaurantList: Array<Restaurant> = []
        let ref = Database.database().reference()
        ref.child("restaurants").observeSingleEvent(of: .value, with: { snapshot in
            for childData in snapshot.children {
                restaurantList.append(parseRestaurantData(dataSnapshot: childData as! DataSnapshot))
            }
            result(nil, restaurantList)
        })
    }
    
    // Get details
    static func getDetails(restaurantID: String!, result: @escaping (_ error: String?, _ details: Restaurant?) -> Void) {
        var restaurant: Restaurant?
        let ref = Database.database().reference()
        ref.child("restaurants").child(restaurantID).observeSingleEvent(of: .value, with: { snapshot in
            restaurant = parseRestaurantData(dataSnapshot: snapshot)
            result(nil, restaurant)
        })
    }
    
    // Get restaurant rating state
    static func getRestaurantRatingState(restaurantID: String!, result: @escaping (_ error: String?, _ result: Int?) -> Void) {
        let userID = Auth.auth().currentUser?.uid
        let ref = Database.database().reference()
        ref.child("ratinglist").child("\(userID!)").observeSingleEvent(of: .value, with: { snapshot in
            if snapshot.hasChild(restaurantID) {
                let ratingData = snapshot.value as? NSDictionary
                let point = ratingData![restaurantID] as! Int
                result(nil, point)
            } else {
                result("Empty", nil)
            }
        })
    }
    
    // Update rating
    static func updateRating(restaurantID: String!, point: Int!, result: @escaping (_ error: String?) -> Void) {
        let ref = Database.database().reference()
        
        // Get restaurant from server to update rating
        ref.child("restaurants").child(restaurantID).observeSingleEvent(of: .value, with: { snapshot in
            // Calculate point and post it to server
            let restaurant = parseRestaurantData(dataSnapshot: snapshot)
            var newRating = (restaurant.rating! + Double(point)) / 2
            newRating.round(FloatingPointRoundingRule.toNearestOrAwayFromZero)
            
            // Update restaurant data on server
            ref.child("restaurants").child(restaurantID).updateChildValues(["rating": newRating])
            
            // Update user data on server
            guard let userID = Auth.auth().currentUser?.uid else { return }
            ref.child("ratinglist").child("\(userID)").observeSingleEvent(of: .value, with: { snapshot in
                if snapshot.hasChild(restaurantID) {
                    ref.child("ratinglist").child("\(userID)").updateChildValues([restaurantID: point])
                } else {
                    ref.child("ratinglist").child("\(userID)").child(restaurantID).setValue(point)
                }
                result("Successed")
            })
        })
    }
    
    // Get images
    static func getImages(resId: String!, result: @escaping (_ error: String?, _ images: Array<String>) -> Void ) {
        var images: Array<String> = []
        let ref = Database.database().reference()
        ref.child("images").child(resId).observeSingleEvent(of: .value, with: { snapshot in
            for child in snapshot.children {
                let childSnapshot = child as! DataSnapshot
//                let imageData = childSnapshot.value as! NSDictionary
                images.append(childSnapshot.value as! String)
            }
            
            // Send data to presenter
            result(nil, images)
        })
    }
}
