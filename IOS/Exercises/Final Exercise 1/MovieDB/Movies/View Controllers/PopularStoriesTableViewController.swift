//
//  PopularStoriesTableViewController.swift
//  Movies
//
//  Created by Anh Tran on 3/23/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

class PopularStoriesTableViewController: UITableViewController {
    
    var newStoriesPresenter: NewStoriesPresenter?
    var stories: [Story] = []
    
    private let reuseCellIdentifier = "StoryCell"
        
    override func viewDidLoad() {
        super.viewDidLoad()
        
        newStoriesPresenter = NewStoriesPresenter(newStoriesVCCallBack: self)
        newStoriesPresenter?.getNewStories()
        
        // Config cell
        tableView.rowHeight = UITableViewAutomaticDimension
        tableView.estimatedRowHeight = 200.0
        
        //Load more on top
        let refreshControlView = UIRefreshControl()
        self.tableView!.alwaysBounceVertical = true
        refreshControlView.tintColor = .white
        refreshControlView.addTarget(self, action: #selector(myRefreshMethod), for: .valueChanged)
        self.tableView!.refreshControl = refreshControlView
    }
    
    // MARK: - Actions
    
    @IBAction func myRefreshMethod() {
        newStoriesPresenter?.currentPage = 1
        newStoriesPresenter?.getNewStories()
        stories = []
        tableView?.reloadData()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
        
    // MARK: - Table view data source
        
    override func numberOfSections(in tableView: UITableView) -> Int {
        // Return the number of sections
        return 1
    }
        
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // Return the number of rows
        return stories.count
    }
        
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: reuseCellIdentifier, for: indexPath) as! PopularStoryTableViewCell
        let story = stories[indexPath.row]
        cell.bindData(story: story)
        
        //Check if reach the last element then load more story
        if stories.count > 3 {
            let lastElement = stories.count - 3
            if indexPath.row == lastElement {
                newStoriesPresenter?.getNewStories()
            }
        }
        return cell
    }
        
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        //Prepare data
        let detailVC = storyboard?.instantiateViewController(withIdentifier: "DetailViewController") as!DetailViewController
        let story = stories[indexPath.row]
        detailVC.story = story
            
        //Push view
        navigationController?.pushViewController(detailVC, animated: true)
    }
}

// MARK: - NewMoviesDelegate

extension PopularStoriesTableViewController: NewStoriesVCDelegate {
    func dataCallBack(stories: [Story]) {
        
        self.stories.append(contentsOf: stories)
        tableView?.refreshControl?.endRefreshing()
        tableView?.reloadData()
    }
}
