//
//  UIViewExtension.swift
//
//  Copyright © 2017 Apps Cyclone. All rights reserved.
//

import UIKit

extension UIView{
    
    //MARK: - INSPECTABLE
    
    @IBInspectable var cornerRadius: CGFloat {
        get {
            return layer.cornerRadius
        }
        set {
            layer.cornerRadius = newValue
            layer.masksToBounds = newValue > 0
        }
    }
    
    @IBInspectable var borderWidth: CGFloat {
        get {
            return layer.borderWidth
        }
        set {
            layer.borderWidth = newValue
        }
    }
    
    @IBInspectable var borderColor: UIColor? {
        get {
            return UIColor(cgColor: layer.borderColor!)
        }
        set {
            layer.borderColor = newValue?.cgColor
        }
    }
    
    //MARK: - HELPERS
    
    func addShadow(width: CGFloat, height: CGFloat, color: UIColor, opacity: Float, radius: CGFloat) {
        
        layer.shadowOffset = CGSize(width: width, height: height)
        layer.shadowRadius = radius
        layer.shadowColor = color.cgColor
        layer.shadowOpacity = opacity
        
    }
    func addBottomBorder(color: UIColor) {
        
        let border = CALayer()
        let width = CGFloat(1.0)
        border.borderColor = color.cgColor
        border.frame = CGRect(x: 0, y: self.frame.size.height.heightRatio - width, width: self.frame.size.width.widthRatio, height: self.frame.size.height.heightRatio)
        
        
        border.borderWidth = width
        
        self.tintColor = .white
        self.layer.masksToBounds = true
        self.layer.addSublayer(border)
        
    }
    
    func addBottomBorderNoRatio(color: UIColor) {
        
        let border = CALayer()
        let width = CGFloat(1.0)
        border.borderColor = color.cgColor
        border.frame = CGRect(x: 0, y: self.frame.size.height - width, width: self.frame.size.width.widthRatio, height: self.frame.size.height)
        
        
        border.borderWidth = width
        
        self.tintColor = .white
        self.layer.masksToBounds = true
        self.layer.addSublayer(border)
        
    }
}
