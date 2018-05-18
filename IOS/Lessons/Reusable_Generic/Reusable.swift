//
//  Reusable.swift
//  Reusable
//
//  Created by Phong Cao on 4/10/18.
//  Copyright © 2018 Phong Cao. All rights reserved.
//

import UIKit

protocol Reusable {}

extension Reusable {
    static var reuseIdentifier: String {
        return String(describing: self)
    }
}
