//
//  UIColorExtension.swift
//
//  Copyright © 2017 Apps Cyclone. All rights reserved.
//

import UIKit

extension UIColor {
    
    convenience init(hex: UInt32, alpha: CGFloat) {
        
        let red = CGFloat((hex & 0xFF0000) >> 16)/255.0
        let green = CGFloat((hex & 0xFF00) >> 8)/255.0
        let blue = CGFloat(hex & 0xFF)/255.0
        self.init(red: red, green: green, blue: blue, alpha: alpha)
    }
    
    convenience init(r: Int, g: Int, b: Int, alpha: CGFloat) {
        self.init(red: CGFloat(r) / 255.0,
                  green: CGFloat(g) / 255.0,
                  blue: CGFloat(b) / 255.0,
                  alpha: alpha)
    }
    
    //MARK: - COLOR MANAGER
    
    static var background: UIColor {
        return UIColor(red:0.13, green:0.15, blue:0.18, alpha:1)
    }
    
    static var appStatusbar: UIColor {
        return UIColor(red:0.13, green:0.15, blue:0.18, alpha:1)
    }
    
    static var appLightBlue: UIColor {
        return UIColor(red:0.42, green:0.82, blue:0.9, alpha:1)
    }
    
    static var appUnSelectedButton: UIColor {
        return UIColor(red:0.44, green:0.47, blue:0.55, alpha:1)
    }

    static var appUnselectedFilter: UIColor {
        return UIColor(red:0.44, green:0.46, blue:0.55, alpha:1)
    }

    static var miniPlayerBackground: UIColor {
        return UIColor(r: 21, g: 29, b: 37, alpha: 1)
    }
    
    static var nothingMedia: UIColor{
        return UIColor(r: 67, g: 77, b: 92, alpha: 1)
    }
    
    static var bottomBorder: UIColor{
        return UIColor(r: 255, g: 255, b: 255, alpha: 0.5)
    }
}
