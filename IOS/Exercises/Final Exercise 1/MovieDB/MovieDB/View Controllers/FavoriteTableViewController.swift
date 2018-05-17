//
//  FavoriteTableViewController.swift
//  MovieDB
//
//  Created by Anh Tran on 4/2/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

class FavoriteTableViewController: UITableViewController {
    
    let favoritePresenter = FavoritePresenter()
    var movieList: [Movie] = []
    
    let reuseCellIdentifier = "MovieTableCell"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Setup after loading the view.
        
        tableView.rowHeight = UITableViewAutomaticDimension
        tableView.estimatedRowHeight = 175.0
        
        tableView.register(UINib.init(nibName: "MovieTableCell", bundle: nil), forCellReuseIdentifier: reuseCellIdentifier)
        
        //Refresh data on top
        let refreshControlView = UIRefreshControl()
        tableView.alwaysBounceVertical = true
        refreshControlView.tintColor = .white
        refreshControlView.addTarget(self, action: #selector(refreshData), for: .valueChanged)
        tableView.refreshControl = refreshControlView
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        movieList = []
        tableView.reloadData()
        refreshData()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Update
    
    func loadData() {
        favoritePresenter.getMovieList(url: ServerUrl.favoriteMovieListUrl) { (error, movies) in
            guard let movies = movies?.moviesList else {
                print(error!)
                return
            }
            self.movieList.append(contentsOf: movies)
            self.tableView.reloadData()
        }
    }
    
    @IBAction func refreshData() {
        favoritePresenter.currentPage = 1
        favoritePresenter.getMovieList(url: ServerUrl.favoriteMovieListUrl) { (error, movies) in
            guard let movies = movies?.moviesList else {
                print(error!)
                return
            }
            
            self.movieList = movies
            self.tableView.reloadData()
            self.tableView.refreshControl?.endRefreshing()
        }
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return movieList.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: reuseCellIdentifier, for: indexPath) as! MovieTableViewCell
        
        guard movieList.count > 0 else { return cell }
        if movieList.count - indexPath.row == 7 {
            refreshData()
        }
        
        // Configure the cell...
        cell.binData(movie: movieList[indexPath.row])
        
        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        let detailVC = storyboard?.instantiateViewController(withIdentifier: "MovieDetailsViewController") as! MovieDetailsViewController
        
        detailVC.movieId = movieList[indexPath.row].id
        
        detailVC.navigationController?.title = "DETAILS"
        navigationController?.pushViewController(detailVC, animated: true)
        
    }
    
}
