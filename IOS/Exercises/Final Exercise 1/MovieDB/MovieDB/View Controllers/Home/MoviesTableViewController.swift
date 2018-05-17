//
//  MoviesTableViewController.swift
//  MovieDB
//
//  Created by Anh Tran on 4/12/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

class MoviesTableViewController: UITableViewController {
    var movieList: [Movie] = []
    var urlMovies: String = ServerUrl.popularMoviesUrl
    
    let homePresenter = HomePresenter()
    
    let reuseCellIdentifier = "MovieTableCell"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        
        // Setup the TableView.
        tableView.rowHeight = UITableViewAutomaticDimension
        tableView.estimatedRowHeight = 181.0
        tableView.register(UINib.init(nibName: "MovieTableCell", bundle: nil), forCellReuseIdentifier: reuseCellIdentifier)
        
        // Refresh data on top
        let refreshControlView = UIRefreshControl()
        tableView.alwaysBounceVertical = true
        refreshControlView.tintColor = .white
        refreshControlView.addTarget(self, action: #selector(refreshData), for: .valueChanged)
        tableView.refreshControl = refreshControlView
        
        tableView.refreshControl?.beginRefreshing()
        refreshData()
        
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Update
    
    func loadData(url: String) {
        urlMovies = url
        homePresenter.getMovieList(url: urlMovies) { (error, movies) in
            guard let movies = movies?.moviesList else {
                print(error!)
                return
            }
            self.movieList.append(contentsOf: movies)
            self.tableView.reloadData()
        }
    }
    
    @IBAction func refreshData() {
        homePresenter.currentPage = 1
        homePresenter.getMovieList(url: urlMovies) { (error, movies) in
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
        if movieList.count - indexPath.row == 5 {
            loadData(url: urlMovies)
        }
        
        // Configure the cell
        cell.binData(movie: movieList[indexPath.row])
        
        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        let detailVC = UIStoryboard.init(name: "Main", bundle: nil).instantiateViewController(withIdentifier: "MovieDetailsViewController") as! MovieDetailsViewController
        
        detailVC.movieId = movieList[indexPath.row].id
        
        detailVC.navigationController?.title = "DETAILS"
        navigationController?.pushViewController(detailVC, animated: true)
        
    }

}
