//
//  UITableView + Extension.swift
//  CHC
//
//  Created by AC Tester on 6/18/18.
//  Copyright © 2018 Apps Cyclone. All rights reserved.
//

import Foundation

extension UIResponder {
    
    func next<T: UIResponder>(_ type: T.Type) -> T? {
        return next as? T ?? next?.next(type)
    }
    
}

extension UITableViewCell {
    
    var tableView: UITableView? {
        return next(UITableView.self)
    }
    
    var indexPath: IndexPath? {
        return tableView?.indexPath(for: self)
    }
}

extension UICollectionViewCell {
    
    var collectionView: UICollectionView? {
        return next(UICollectionView.self)
    }
    
    var indexPath: IndexPath? {
        return collectionView?.indexPath(for: self)
    }
    
}
