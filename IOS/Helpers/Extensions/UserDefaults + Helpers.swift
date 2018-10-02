//
//  UserDefaults + Helpers.swift
//  CHC
//
//  Created by Anh Tran on 8/6/18.
//

import Foundation

extension UserDefaults {
    
    static func isFirstLaunch() -> Bool {
        let isFirstLaunch = !UserDefaults.standard.bool(forKey: CHCUserDefault.isFirstLaunch)
        if (isFirstLaunch) {
            UserDefaults.standard.set(true, forKey: CHCUserDefault.isFirstLaunch)
            UserDefaults.standard.synchronize()
        }
        return isFirstLaunch
    }
    
}
