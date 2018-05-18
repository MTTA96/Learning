//
//  TableViewCell + Reusable.swift
//  Reusable
//
//  Created by Phong Cao on 4/10/18.
//  Copyright © 2018 Phong Cao. All rights reserved.
//

import UIKit

extension UITableViewCell: Reusable {}

extension UITableView {
    
    // Register
    
    func register<T: UITableViewCell>(nib: T.Type, bundle: Bundle? = nil) {
        let nib = UINib(nibName: T.reuseIdentifier, bundle: bundle)
        register(nib, forCellReuseIdentifier: T.reuseIdentifier)
    }
    
    func register<T: UITableViewCell>(cellClass: T.Type) {
        register(T.self, forCellReuseIdentifier: T.reuseIdentifier)
    }
    
    // Dequeue
    
    func dequeueResuableCell<T: UITableViewCell>(for indexPath: IndexPath) -> T {
        guard let cell = dequeueReusableCell(withIdentifier: T.reuseIdentifier, for: indexPath) as? T else {
            fatalError("Could not dequeue cell with identifier: '\(T.reuseIdentifier)'")
        }
        return cell
    }
    
}
