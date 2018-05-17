//
//  MovieCollectionViewCell.swift
//  Movies
//
//  Created by Anh Tran on 3/21/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

class PeopleCollectionViewCell: UICollectionViewCell {
    
    @IBOutlet weak var personImageView: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    func binData(title: String, urlImage: String) {
        personImageView.image = nil
        personImageView.downloadedFrom(link: ServerUrl.imageStorage + urlImage)
        titleLabel.text = title
    }
}

