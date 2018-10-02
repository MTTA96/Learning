//
//  UIFont+Manager.swift
//  CHC
//
//  Created by Phong Cao on 6/7/18.
//  Copyright © 2018 Apps Cyclone. All rights reserved.
//

import UIKit

extension UIFont {
    
    static func deLaFuente(_ size: CGFloat = 15.0) -> UIFont {
        if let font = UIFont(name: "DeLaFuente", size: size) {
            return font
        }
        return UIFont.systemFont(ofSize: size)
    }
    
    static func lato(_ size: CGFloat = 15.0) -> UIFont {
        if let font = UIFont(name: "Lato-Medium", size: size) {
            return font
        }
        return UIFont.systemFont(ofSize: size)
    }

}
