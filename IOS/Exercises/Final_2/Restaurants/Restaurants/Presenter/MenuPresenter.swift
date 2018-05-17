//
//  MenuPresenter.swift
//  Restaurants
//
//  Created by Anh Tran on 4/26/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation

class MenuPresenter {
    
    // Get menu
    func getMenu(resId: String!, result: @escaping (_ error: String?, _ menu: Menu?) -> Void) {
        
        Menu.getMenu(resID: resId) { (error, menu) in
            
            // Check error
            if let error = error {
                print(error)
                result(error, nil)
                return
            }
            
            // Handle data
            result(nil, menu)
        }
    }
}
