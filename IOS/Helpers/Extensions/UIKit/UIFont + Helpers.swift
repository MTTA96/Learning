//
//  UIFont+Helpers.swift
//
//  Created by Phong Cao on 8/1/17.
//  Copyright © 2017 Apps Cyclone. All rights reserved.
//

import UIKit

extension UIFont {
    
    var ratioSize: UIFont {
        return self.withSize(self.pointSize.heightRatio)
    }
    
}
