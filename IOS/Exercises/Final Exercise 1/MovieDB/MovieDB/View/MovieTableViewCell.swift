//
//  MovieTableViewCell.swift
//  MovieDB
//
//  Created by Anh Tran on 3/28/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

class MovieTableViewCell: UITableViewCell {
    @IBOutlet weak var posterImageView: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var ratingLabel: UILabel!
    @IBOutlet weak var genresLabel: UILabel!
    @IBOutlet weak var overviewLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
    func binData(movie: Movie) {
        posterImageView.image = nil
        if let imgPath = movie.poster_path {
            posterImageView.downloadedFrom(url: URL(string: ServerUrl.imageStorage + imgPath)!)
        }
        titleLabel.text = movie.title
        ratingLabel.text = "\(movie.vote_average!)"
        
        var genres: String = ""
        var genreNumber = 0
        for genreId: Int  in movie.genre_ids! {
            if genreNumber == (movie.genre_ids!.count) - 1{
                genres.append(GenreDict.shared.get(id: "\(genreId)")!)
            } else {
                genres.append(GenreDict.shared.get(id: "\(genreId)")! + ", ")
            }
            genreNumber += 1
        }
        
        genresLabel.text = genres
        overviewLabel.text = movie.overview

    }
    
}
