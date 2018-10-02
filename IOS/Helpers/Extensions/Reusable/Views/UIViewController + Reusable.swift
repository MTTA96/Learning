//
//  UIViewController + Reusable.swift
//
//  Copyright © 2018. All rights reserved.
//

import UIKit

extension UIViewController: Reusable {
    
    static func loadFromNib<T: UIViewController>(bundle: Bundle? = nil) -> T {
        return T(nibName: String(describing: self), bundle: bundle)
    }
    
}
