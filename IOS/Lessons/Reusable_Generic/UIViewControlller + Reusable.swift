//
//  UINibView + Reusable.swift
//  Reusable
//
//  Created by Phong Cao on 4/10/18.
//  Copyright © 2018 Phong Cao. All rights reserved.
//

import UIKit

extension UIViewController: Reusable {
    
    static func loadFromNib<T: UIViewController>(bundle: Bundle? = nil) -> T {
        return T(nibName: T.reuseIdentifier, bundle: bundle)
    }
    
}
