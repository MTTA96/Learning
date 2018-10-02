//
//  NSLayoutConstraint + Extension.swift
//  CHC
//
//  Created by AC Tester on 7/24/18.
//

import Foundation
extension NSLayoutConstraint{
    func configHeightRatio(){
        self.constant = self.constant.heightRatio
    }
    
    func configWidthRatio(){
        self.constant = self.constant.widthRatio
    }
}
