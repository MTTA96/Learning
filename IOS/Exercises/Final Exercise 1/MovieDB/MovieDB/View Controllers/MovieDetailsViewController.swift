//
//  MovieDetailsViewController.swift
//  MovieDB
//
//  Created by Anh Tran on 3/30/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

class MovieDetailsViewController: UIViewController {

    @IBOutlet weak var ratingLabel: UILabel!
    @IBOutlet weak var movieImageView: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var genresLabel: UILabel!
    @IBOutlet weak var overviewLabel: UILabel!
    @IBOutlet weak var runtimeLabel: UILabel!
    @IBOutlet weak var revenueLabel: UILabel!
    @IBOutlet weak var budgetLabel: UILabel!
    @IBOutlet weak var favoriteButton: UIButton!
    
    let movieDetailsPresenter = MovieDetailsPresenter()
    var movieId: Int?
    var movieStates: MovieStates?
    var movieDetails: MovieDetails?
    var isMarked = false
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //Get movie details
        movieDetailsPresenter.getMovieDetails(movieId: movieId!) { (error, movieDetails) in
            guard error == nil else {
                print(error!)
                return
            }
            
            //Check movie states
            self.movieDetailsPresenter.getStates(movieId: self.movieId!) { (error, states) in
                guard error == nil else {
                    print(error!)
                    return
                }
                self.movieStates = states
                self.isMarked = states!.favorite!
                self.updateMarkButtonStates(isMarked: (states?.favorite)!)
            }
            
            self.movieDetails = movieDetails
            self.loadData()
        }

    }
    
    override func viewWillAppear(_ animated: Bool) {
        //Check movie states
        self.movieDetailsPresenter.getStates(movieId: self.movieId!) { (error, states) in
            guard error == nil else {
                print(error!)
                return
            }
            self.movieStates = states
            self.isMarked = states!.favorite!
            self.updateMarkButtonStates(isMarked: (states?.favorite)!)
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Update
    
    func loadData() {
        ratingLabel.text = "\(movieDetails?.vote_average ?? 0)/10.0"
        titleLabel.text = movieDetails?.original_title
        
        var genres: String = ""
        var genreNumber = 0
        for genre: Genre in (movieDetails?.genres)! {
            if genreNumber == (movieDetails?.genres?.count)! - 1{
                genres.append(genre.name!)
            } else {
                genres.append(genre.name! + ", ")
            }
            genreNumber += 1
        }
        
        genresLabel.text = genres
        overviewLabel.text = movieDetails?.overview
        runtimeLabel.text = "\(movieDetails?.runtime ?? 0)m"
        revenueLabel.text = Movie.formatAmount(amount: (movieDetails?.revenue)!)
        budgetLabel.text = Movie.formatAmount(amount: (movieDetails?.budget)!)
        guard let imgPath = movieDetails?.backdrop_path else { return }
        if let imageUrl = URL(string: ServerUrl.imageStorage + imgPath) {
            movieImageView.downloadedFrom(url: imageUrl)
        }
    }
    
    func updateMarkButtonStates(isMarked: Bool) {
        if isMarked {
            favoriteButton.backgroundColor = .gray
            favoriteButton.setTitle("REMOVE FROM FAVORITE", for: .normal)
        } else {
            favoriteButton.backgroundColor = UIColor(named: "main_color")
            favoriteButton.setTitle("MARK AS FAVORITE", for: .normal)
        }
    }
    
    // MARK: - Actions
    
    @IBAction func markButtonTapped(_ sender: UIButton) {
        movieDetailsPresenter.markFavorite(movieId: (movieDetails?.id)!, isMarked: !isMarked) { error in
            guard let error = error else {
                self.isMarked = !self.isMarked
                self.updateMarkButtonStates(isMarked: self.isMarked)
                return
            }
            print(error)
        }
    }
    
}
