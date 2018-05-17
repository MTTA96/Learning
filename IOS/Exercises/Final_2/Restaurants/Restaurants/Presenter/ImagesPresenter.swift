//
//  ImagesPresenter.swift
//  Restaurants
//
//  Created by Anh Tran on 4/26/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation

class ImagesPresenter {
    
    // Get image list
    func getImageList(resId: String!, result: @escaping (_ error: String?, _ images: Array<String>?) -> Void) {
        Restaurant.getImages(resId: resId) { (error, images) in
            if let error = error {
                print(error)
                result(error, nil)
                return
            }
            
            // Handle data
            result(nil, images)
        }
    }
}
