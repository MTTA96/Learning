//
//  Storyboard + Reusable.swift
//
//  Copyright © 2018. All rights reserved.
//

import UIKit

enum SBManager: String {
    
    // Define your storyboard name in here.
    // Example: For Main.storyboard, you need to define "case Main".
    // Usage: SBManager.Main.load(ViewController.self)

    case TabBar
    
}

extension SBManager {
    
    func load<T: UIViewController>(_ vc: T.Type, bundle: Bundle? = nil) -> T {
        let storyboard = UIStoryboard(name: rawValue, bundle: bundle)
        guard let vc = storyboard.instantiateViewController(withIdentifier: T.reuseIdentifier) as? T else {
            fatalError("Could not load: \"\(T.reuseIdentifier)\" from \"\(rawValue).storyboard\"")
        }
        return vc
    }
    
}

