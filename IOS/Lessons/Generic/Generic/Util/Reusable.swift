//
//  Reusable.swift
//  Generic
//
//  Created by Anh Tran on 5/18/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

protocol Reusable {}

extension Reusable {
    static var reuseIdentifier: String {
        return String(describing: self)
    }
}

// UITableView extension
extension UITableViewController: Reusable {}

// UIViewContrller extension
extension UIViewController: Reusable {}
