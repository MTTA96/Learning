//
//  File.swift
//  Restaurants
//
//  Created by Anh Tran on 4/26/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation
import Firebase

struct Menu {
    var menu: Array<MenuItem> = []
}

// MARKS: Methods

extension Menu {
    
    // Get menu
    static func getMenu(resID: String!, result: @escaping (_ error: String?, _ menu: Menu?) -> Void ) {
        var menu = Menu()
        let ref = Database.database().reference()
        ref.child("menu").child(resID).observeSingleEvent(of: .value, with: { snapshot in
            
            // Handle data from server
            for child in snapshot.children {
                let itemSnapshot = child as! DataSnapshot
                let itemData = itemSnapshot.value as! NSDictionary
                
                // Create menu item
                let menuItem = MenuItem(name: itemData["name"] as? String,
                                        price: itemData["price"] as? Int,
                                        pictureUrl: itemData["picture"] as? String)
                
                // Add menu item to menu
                menu.menu.append(menuItem)
            }
            
            // Send result to presenter
            result(nil, menu)
        })
    }
}
