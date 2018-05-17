//
//  CustomUIImageView.swift
//  MovieDB
//
//  Created by Anh Tran on 3/29/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit
import SDWebImage

extension UIImageView {
    
    static var activeTasks: [Int : URLSessionDataTask] = [:]
    
    func downloadedFrom(url: URL) {
        
        tag = url.hashValue
        
        let imageKey = url.absoluteString
        
        if let image = ImageCache.shared.get(key: imageKey) {
            self.image = image
            return
        }
        
        var task = UIImageView.activeTasks[tag]
        
        if task == nil {
            
            UIImageView.activeTasks[tag] = URLSession.shared.dataTask(with: url) { data, response, error in
                guard
                    let httpURLResponse = response as? HTTPURLResponse, httpURLResponse.statusCode == 200,
                    let mimeType = response?.mimeType, mimeType.hasPrefix("image"),
                    let data = data, error == nil,
                    let image = UIImage(data: data)
                    else { return }
                
                DispatchQueue.main.async() {
                    if self.tag == url.hashValue {
                        self.image = image
                    }
                    ImageCache.shared.set(key: imageKey, image: image)
                }
                
            }
            
            task = UIImageView.activeTasks[tag]
            
        }
        
        task?.resume()
        
    }
    
    func cancelDownload() {
        
        if let task = UIImageView.activeTasks[tag] {
            task.cancel()
            UIImageView.activeTasks[tag] = nil
        }
        
    }
    
    
    func downloadedFrom(link: String, contentMode mode: UIViewContentMode = .scaleAspectFit) {
                guard let url = URL(string: link) else { return }
                downloadedFrom(url: url)
    }
}
