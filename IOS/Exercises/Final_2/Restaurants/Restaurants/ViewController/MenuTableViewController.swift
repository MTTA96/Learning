//
//  MenuTableViewController.swift
//  Restaurants
//
//  Created by Anh Tran on 4/26/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

class MenuTableViewController: UITableViewController {

    var menuPresenter: MenuPresenter?
    var resID: String?
    var menu: Menu = Menu()
    let cellReuseIdentifier = "MenuCell"
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Configure table
        tableView.rowHeight = UITableViewAutomaticDimension
        tableView.estimatedRowHeight = 175.0
        
        // Refresh data
        let refreshControl = UIRefreshControl()
        tableView.alwaysBounceVertical = true
        refreshControl.tintColor = .white
        refreshControl.addTarget(self, action: #selector(loadData), for: .valueChanged)
        tableView.refreshControl = refreshControl
        
        // Register cell nib
        tableView.register(UINib.init(nibName: "MenuTableCell", bundle: nil), forCellReuseIdentifier: cellReuseIdentifier)
        
        menuPresenter = MenuPresenter()
        loadData()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: Methods
    
    @IBAction func loadData() {
        menuPresenter?.getMenu(resId: resID) { (error, menu) in
            // Check error
            if let error = error {
                print(error)
                return
            }
            
            // Handle data
            self.menu = menu!
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
        return menu.menu.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: cellReuseIdentifier, for: indexPath) as! MenuTableViewCell

        // Configure the cell...
        cell.bindData(menuItem: menu.menu[indexPath.row])

        return cell
    }


}
