//
//  MovieCollectionViewCell.swift
//  Movies
//
//  Created by Anh Tran on 3/21/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

class MovieCollectionViewCell: UICollectionViewCell {
    
    @IBOutlet weak var movieImageView: UIImageView!
    @IBOutlet weak var movieTitle: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func prepareForReuse() {
        movieImageView.af_cancelImageRequest()
        movieImageView.image = nil
    }
    
    func binData(title: String, urlImage: String) {
        movieImageView.downloadedFrom(link: urlImage)
        movieTitle.text = title
    }
}

