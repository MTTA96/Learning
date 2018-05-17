//
//  MoviesViewController.swift
//  MovieDB
//
//  Created by Anh Tran on 3/30/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

class MoviesViewController: UIViewController {
    @IBOutlet weak var segmentController: UISegmentedControl!
    @IBOutlet weak var containerView: UIView!
    var pageView: MoviesPageViewController?
    
    let homePresenter = HomePresenter()
    var moviesPageCallback: MoviesPageVCDelegate?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Setup SegmentController
        let segAttributes: NSDictionary = [ NSAttributedStringKey.foregroundColor: UIColor.white ]
        segmentController.setTitleTextAttributes(segAttributes as [NSObject : AnyObject], for: UIControlState.selected)

    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Actions
    
    @IBAction func segmentTapped(_ sender: UISegmentedControl) {
        

        switch segmentController.selectedSegmentIndex {

            //Popular movies
            case 0:
                moviesPageCallback?.loadPage(type: "popular")
                break

            // Now Playing Movies
            case 1:
                moviesPageCallback?.loadPage(type: "now")
                break

            // Top Rated Movies
            case 2:
                moviesPageCallback?.loadPage(type: "rated")
                break

            default:
                break
        }
    }
    
    @IBAction func searchButtonTapped(_ sender: UIBarButtonItem) {
        navigationController?.pushViewController((storyboard?.instantiateViewController(withIdentifier: "SearchView"))!, animated: true)
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        
        // Get child ViewController of container
        if segue.identifier == "pageSegue" {
            pageView = segue.destination as? MoviesPageViewController
            
            moviesPageCallback = pageView.self
            moviesPageCallback?.loadPage(type: "popular")
        }
    }
}



