//
//  CGFloat+Helpers.swift
//  NOVU
//
//  Created by Phong Cao on 7/26/17.
//  Copyright © 2017 Apps Cyclone. All rights reserved.
//

import UIKit

extension CGFloat {
    
    var widthRatio: CGFloat {
        return floor(ScreenSize.SCREEN_WIDTH * self / ScreenSize.IP6_WIDTH)
    }
    
    var heightRatio: CGFloat {
        return floor(self * ScreenSize.SCREEN_WIDTH / ScreenSize.IP6_WIDTH)
    }
    
    func getHeightRatio(width: CGFloat, ratioWidth: CGFloat) -> CGFloat {
        return self * ratioWidth / width
    }
    
}
