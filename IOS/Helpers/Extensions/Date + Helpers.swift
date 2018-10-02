//
//  DateExtension.swift
//
//  Copyright © 2017. All rights reserved.
//

import Foundation

extension Date {
        
    func toString(dateFormat format: String = CHCDateFormat.eventDateTime) -> String{
        
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = format
        dateFormatter.amSymbol = "a.m."
        dateFormatter.pmSymbol = "p.m."
        return dateFormatter.string(from: self)
        
    }
    
}
