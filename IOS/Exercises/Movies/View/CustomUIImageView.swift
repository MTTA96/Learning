//
//  CustomUIImageView.swift
//  Movies
//
//  Created by Anh Tran on 3/28/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit
import Alamofire
import AlamofireImage

extension UIImageView {
    func downloadedFrom(url: URL, contentMode mode: UIViewContentMode = .scaleAspectFill) {
        
        contentMode = mode
        
        let imageKey = url.absoluteString
            
        if let image = ImageCache.shared.get(key: imageKey) {
            self.image = image
            return
        }
        
        Alamofire.request(url).responseImage { response in
            if let image = response.result.value {
                DispatchQueue.main.async() {
                    self.image = image
                    ImageCache.shared.set(key: imageKey, image: image)
                }
            } else {
                self.image = nil
            }
            
        }
    }
    
    func downloadedFrom(link: String, contentMode mode: UIViewContentMode = .scaleAspectFill) {
        guard let url = URL(string: link) else { return }
        downloadedFrom(url: url, contentMode: mode)
    }
}
