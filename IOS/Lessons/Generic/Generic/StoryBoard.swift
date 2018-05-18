//
//  StoryBoard.swift
//  Generic
//
//  Created by Anh Tran on 5/17/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

enum StoryBoard: String {
    
    case main
    case home
    
    var fileName: String {
        return self.rawValue.capitalized
    }
    
}

extension StoryBoard {
    
    // Load View Controller using generic
    func load<T: UIViewController>(_ vc: T.Type) -> T {
        
        let storyboard = UIStoryboard(name: fileName, bundle: nil)
        guard let viewController = storyboard.instantiateViewController(withIdentifier: T.identifier) as? T else {
            fatalError("Can't init view controller")
        }
        
        return viewController
        
    }
    
}

extension UIViewController {
    
    // Get view controller identifier
    static var identifier: String {
        return String(describing: self)
    }
    
}
