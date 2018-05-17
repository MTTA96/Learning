//
//  RestaurantsTableViewController.swift
//  Restaurants
//
//  Created by Anh Tran on 4/20/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit
import Crashlytics

class RestaurantsTableViewController: UITableViewController {

    var restaurantList: Array<Restaurant> = []
    let restaurantsPresenter = RestaurantsPresenter()
    
    let cellReuseIdentifier = "RestaurantTableCell"
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Configure table row
        tableView.rowHeight = UITableViewAutomaticDimension
        tableView.estimatedRowHeight = 175.0
        
        // Register cell nib
        tableView.register(UINib.init(nibName: "RestaurantTableCell", bundle: nil), forCellReuseIdentifier: cellReuseIdentifier)
    
        // Register loading on top
        let refreshControlView = UIRefreshControl()
        tableView.alwaysBounceVertical = true
        refreshControlView.tintColor = .white
        refreshControlView.addTarget(self, action: #selector(initData), for: .valueChanged)
        tableView.refreshControl = refreshControlView
    }

    override func viewWillAppear(_ animated: Bool) {
        initData()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Data
    @IBAction func initData() {
        restaurantsPresenter.getRestaurantList() { (error, restaurantList) in
            if let error = error {
                print(error)
                return
            }
            
            self.restaurantList = restaurantList!
            self.tableView.reloadData()
            self.tableView.refreshControl?.endRefreshing()
        }
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return restaurantList.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: cellReuseIdentifier) as! RestaurantTableViewCell
        
        cell.bindData(restaurant: restaurantList[indexPath.row])
        
        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let detailVC = UIStoryboard.init(name: "Main", bundle: Bundle.main).instantiateViewController(withIdentifier: "DetailVC") as! DetailsTableViewController
        detailVC.restaurantID = restaurantList[indexPath.row].id
        navigationController?.pushViewController(detailVC, animated: true)
    }

}
