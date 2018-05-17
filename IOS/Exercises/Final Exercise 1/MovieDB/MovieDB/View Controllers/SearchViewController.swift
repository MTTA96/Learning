//
//  SearchViewController.swift
//  MovieDB
//
//  Created by Anh Tran on 4/4/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

class SearchViewController: UIViewController {
    @IBOutlet weak var moviesTableView: UITableView!
    @IBOutlet weak var searchBar: UISearchBar!
    
    let searchPresenter = SearchPresenter()
    var movies: [Movie] = []
    var query = ""
    
    let reuseCellIdentifier = "MovieTableCell"
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Set up table view
        moviesTableView.delegate = self
        moviesTableView.dataSource = self
        moviesTableView.register(UINib.init(nibName: "MovieTableCell", bundle: nil), forCellReuseIdentifier: reuseCellIdentifier)
        
        // Set up refresh action
        let refreshControllView = UIRefreshControl()
        moviesTableView.alwaysBounceVertical = true
        refreshControllView.tintColor = .white
        refreshControllView.addTarget(self, action: #selector(refreshData), for: .valueChanged)
        moviesTableView.refreshControl = refreshControllView
        
        // Setup the Search Controller
        searchBar.delegate = self
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func loadResults(query: String) {
        searchPresenter.getResult(query: query) { (error, results) in
            if let error = error {
                print(error)
                return
            }
            if let movies = results?.moviesList {
                self.movies.append(contentsOf: movies)
                self.moviesTableView.reloadData()
            }
        }
    }
    
    @IBAction func refreshData() {
        guard query != "" else {
            movies = []
            moviesTableView.reloadData()
            moviesTableView.refreshControl?.endRefreshing()
            return
        }
        
        searchPresenter.currentPage = 1
        searchPresenter.getResult(query: query) { (error, results) in
            if let error = error {
                print(error)
                return
            }
            if let movies = results?.moviesList {
                self.movies = movies
                self.moviesTableView.reloadData()
                self.moviesTableView.refreshControl?.endRefreshing()
            }
            
        }
    }
    
    @IBAction func returnTextView(gesture: UIGestureRecognizer) {
        searchBar?.resignFirstResponder()
    }
    
    func filterContentForSearchText(_ searchText: String, scope: String = "All") {
        query = searchText
        guard query != "" else {
            movies = []
            moviesTableView.reloadData()
            moviesTableView.refreshControl?.endRefreshing()
            return
        }
        refreshData()
    }
}

// MARK: - Search Result Updating

extension SearchViewController: UISearchBarDelegate ,UISearchResultsUpdating {
    func updateSearchResults(for searchController: UISearchController) {
        filterContentForSearchText(searchBar.text!)
    }

    func searchBar(_ searchBar: UISearchBar, textDidChange searchText: String) {

        filterContentForSearchText(searchBar.text!)
    }
}

extension SearchViewController: UITableViewDelegate, UITableViewDataSource {
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return 1
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        
        return movies.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: reuseCellIdentifier, for: indexPath) as! MovieTableViewCell
        let movie = movies[indexPath.row]

        cell.binData(movie: movie)
        
        // Load more data
        guard movies.count > 0 else { return cell }
        if movies.count - indexPath.row == 5 {
            loadResults(query: query)
        }
        
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let detailsVC = storyboard?.instantiateViewController(withIdentifier: "MovieDetailsViewController") as! MovieDetailsViewController
        detailsVC.movieId = movies[indexPath.row].id
        
        searchBar.resignFirstResponder()
        navigationController?.pushViewController(detailsVC, animated: true)
    }
    
    func scrollViewDidScroll(_ scrollView: UIScrollView) {
        searchBar.resignFirstResponder()
    }
}

