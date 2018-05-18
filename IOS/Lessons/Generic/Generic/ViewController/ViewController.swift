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
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    @IBAction func button(_ sender: Any) {
        
        let homeVC = StoryboardManager.home.load(HomeViewController.self, bundle: nil)
        present(homeVC, animated: true, completion: nil)
    }
    
}

