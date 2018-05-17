//
//  DetailViewController.swift
//  Movies
//
//  Created by Anh Tran on 3/15/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

protocol DetailVCDelegate {
    func dataCallBack(content: String)
}

class DetailViewController: UIViewController {

    var story: Story!
    
    @IBOutlet weak var posterImageView: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var viewsLabel: UILabel!
    @IBOutlet weak var descTextView: UITextView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        
        tabBarController?.hidesBottomBarWhenPushed = true

        loadData()
    }

    func loadData() {
        navigationItem.title = story.title
        titleLabel.text = story.title
        viewsLabel.text = "Views: \( story.view!)"
        posterImageView.downloadedFrom(link: story.image!)
        descTextView.text = story.desc
    }
    
    @IBAction func readStoryButtonTapped(_ sender: UIButton) {
        let webVC: WebViewController = storyboard?.instantiateViewController(withIdentifier: "WebViewController") as! WebViewController
        webVC.link = story.link
        //Push view
        navigationController?.pushViewController(webVC, animated: true)
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

}

