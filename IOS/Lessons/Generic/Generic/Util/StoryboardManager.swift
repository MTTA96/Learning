//
//  StoryboardManager.swift
//  Generic
//
//  Created by Anh Tran on 5/18/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

enum StoryboardManager: String {
    
    // Storyboard type
    case main
    case home
    
    // Storyboad's file name
    var fileName: String {
        return rawValue.capitalized
    }
    
}

extension StoryboardManager {
    
    // Use generic to load view controller
    func load<T: UIViewController>(_ vc: T.Type, bundle: Bundle? = nil) -> T {
        // Define storyboard
        let storyboard = UIStoryboard(name: StoryBoard.main.fileName, bundle: bundle)
        
        // Define view controller
        guard let viewController = storyboard.instantiateViewController(withIdentifier: T.reuseIdentifier) as? T else {
            fatalError("Couldn't load \(T.reuseIdentifier) in \(rawValue).storyboard")
        }
        
        return viewController
    }
}
