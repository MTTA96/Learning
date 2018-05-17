//
//  MoviesPageViewController.swift
//  MovieDB
//
//  Created by Anh Tran on 4/12/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

class MoviesPageViewController: UIPageViewController {
    
    lazy var pages: [UIViewController] = {
        return [
            self.getViewController(withUrl: ServerUrl.popularMoviesUrl),
            self.getViewController(withUrl: ServerUrl.nowPlayingMoviesUrl),
            self.getViewController(withUrl: ServerUrl.topRatedMoviesUrl)
        ]
    }()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Install this class as the data source
        dataSource = self
        
    }
    
    func loadData(type: String!) {
        switch type {
        case "popular":
            setViewControllers([pages[0]], direction: .forward, animated: false, completion: nil)
            break
            
        case "now":
            setViewControllers([pages[1]], direction: .forward, animated: false, completion: nil)
            break
            
        case "rated":
            setViewControllers([pages[2]], direction: .forward, animated: false, completion: nil)
            break
            
        default:
            break
        }
    }
    
    func getViewController(withUrl url: String) -> UIViewController
    {
        let pageVC = MoviesTableViewController.init(nibName: "MoviesTableViewController", bundle: nil)
        pageVC.urlMovies = url
        return pageVC
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

}

protocol MoviesPageVCDelegate {
    func loadPage(type: String!)
}

// MARK: - PageDelegate
extension MoviesPageViewController: MoviesPageVCDelegate {
    func loadPage(type: String!) {
        loadData(type: type)
    }
    
}

// MARK: - UIPageViewControllerDataSource
extension MoviesPageViewController: UIPageViewControllerDataSource {
    func pageViewController(_ pageViewController: UIPageViewController, viewControllerBefore viewController: UIViewController) -> UIViewController? {
        return nil
    }
    
    func pageViewController(_ pageViewController: UIPageViewController, viewControllerAfter viewController: UIViewController) -> UIViewController? {
        return nil
    }
    
    
}
