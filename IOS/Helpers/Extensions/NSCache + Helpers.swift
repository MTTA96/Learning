//
//  NSCache + Helpers.swift
//  CHC
//
//  Created by UltraHigh on 9/24/18.
//


import Foundation

class CellHeightCache: NSCache<NSString, NSNumber> {
    let key: String = ""
    
    subscript(key: String) -> CGFloat? {
        get {
            return object(forKey: key as NSString) as? CGFloat
        }
        set {
            if let newValue = newValue {
                setObject(NSNumber(value: Float(newValue)), forKey: key as NSString)
            }
            else {
                removeObject(forKey: key as NSString)
            }
        }
    }
}
