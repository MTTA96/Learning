//
//  RestaurantTableViewCell.swift
//  Restaurants
//
//  Created by Anh Tran on 4/20/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

class RestaurantTableViewCell: UITableViewCell {
    @IBOutlet weak var restaurantImage: UIImageView!
    @IBOutlet weak var restaurantNameLabel: UILabel!
    @IBOutlet weak var ratingLabel: UILabel!
    @IBOutlet weak var addressLabel: UILabel!
    @IBOutlet weak var priceLabel: UILabel!
    
    func bindData(restaurant: Restaurant!) {
        restaurantImage.downloadedFrom(link: restaurant.banner!)
        restaurantNameLabel.text = restaurant.name
        addressLabel.text = restaurant.address
        priceLabel.text = restaurant.price
        
        if let rating = restaurant.rating {
            ratingLabel.textColor = rating >= 5.0 ? .green : .red
            ratingLabel.text = "\(rating)"
        }
    }

}
