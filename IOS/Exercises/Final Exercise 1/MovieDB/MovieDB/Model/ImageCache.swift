//
//  ImageCache.swift
//  MovieDB
//
//  Created by Anh Tran on 3/29/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

class ImageCache {
    
    static let shared = ImageCache()
    var images: [String:UIImage] = [:]
    
    func get(key: String) -> UIImage? {
        return images[key]
    }
    
    func set(key: String, image: UIImage) {
        images[key] = image
    }
    
}
