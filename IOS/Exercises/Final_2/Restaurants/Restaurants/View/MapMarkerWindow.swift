//
//  MapMarkerWindow.swift
//  Restaurants
//
//  Created by Anh Tran on 4/27/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit
import GoogleMaps

protocol MapMarkerDelegate: class {
    func didTapDirectionButton(data: CLLocationCoordinate2D)
}

class MapMarkerWindow: UITableViewCell {
    @IBOutlet weak var banner: UIImageView!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var availibilityLabel: UILabel!
    
    weak var delegate: MapMarkerDelegate?
    var data: CLLocationCoordinate2D?
    
    @IBAction func didTapDirectionButton(_ sender: UIButton) {
        delegate?.didTapDirectionButton(data: data!)
    }
    
    class func instanceFromNib() -> UIView {
        return UINib(nibName: "MapMarkerWindowView", bundle: nil).instantiate(withOwner: self, options: nil).first as! UIView
    }
}
