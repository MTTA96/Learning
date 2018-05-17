//
//  ImageCollectionViewCell.swift
//  Restaurants
//
//  Created by Anh Tran on 4/26/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

class ImageCollectionViewCell: UICollectionViewCell {
    @IBOutlet weak var resImageView: UIImageView!
    
    override func awakeFromNib() {
        resImageView.layer.borderWidth = 2.0
        resImageView.layer.borderColor = #colorLiteral(red: 1.0, green: 1.0, blue: 1.0, alpha: 1.0)
    }
    
    func bindData(imgUrl: String!) {
        resImageView.downloadedFrom(link: imgUrl)
    }
}
