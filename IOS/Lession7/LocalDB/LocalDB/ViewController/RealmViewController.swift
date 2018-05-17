//
//  RealmViewController.swift
//  LocalDB
//
//  Created by Anh Tran on 5/3/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit
import RealmSwift

class RealmViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

}

class Dog: Object {
    @objc dynamic var name = ""
    @objc dynamic var age = 0
}
