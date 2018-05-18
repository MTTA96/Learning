//
//  ViewController.swift
//  Generic
//
//  Created by Anh Tran on 5/17/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        let tableView = UITableView()
        
        let nib = UINib(nibName: "khkj", bundle: nil)
        tableView.register(nib, forCellReuseIdentifier: "hkkjkhk")
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func button(_ sender: Any) {
//        let storyboard = UIStoryboard(name: "Home", bundle: nil)
//        let homeVC = storyboard.instantiateViewController(withIdentifier: "HomeViewController")
        
        let homeVC = StoryBoard.home.load(HomeViewController.self)
        present(homeVC, animated: true, completion: nil)
    }
    
}

