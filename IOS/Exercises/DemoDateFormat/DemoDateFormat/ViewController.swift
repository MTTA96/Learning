//
//  ViewController.swift
//  DemoDateFormat
//
//  Created by Anh Mai on 8/30/18.
//  Copyright © 2018 Anh Mai. All rights reserved.
//

import UIKit
import SwiftDate

class ViewController: UIViewController {

    override func viewDidLoad() {
        super.viewDidLoad()
        let formats = [   "yyyy-MM-dd'T'HH:mm:ssZZZZZ",
            "yyyy'-'MM'-'dd'T'HH':'mm':'ss'Z'",
            "yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'",
            "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
            "yyyy-MM-dd",
            "yyyy-MM-dd HH:mm:ss",
            "yyyy-MM-dd HH:mm",
            "h:mm:ss A",
            "h:mm A",
            "MM/dd/yyyy",
            "MMMM d, yyyy",
            "MMMM d, yyyy LT",
            "dddd, MMMM D, yyyy LT",
            "yyyyyy-MM-dd",
            "yyyy-MM-dd",
            "GGGG-[W]WW-E",
            "GGGG-[W]WW",
            "yyyy-ddd",
            "HH:mm:ss.SSSS",
            "HH:mm:ss",
            "HH:mm",
            "HH"
        ]
        
//        var formatter = DateFormatter()
//
//        for format in formats {
//            formatter.dateFormat = format
//
//            print(formatter.date(from: "2011-11-02T02:50+08:00"))
//        }
        
        // All default datetime formats (15+) are recognized automatically
        var test = "2010-05-20 15:30:00".toDate()
        print(test?.toFormat("MMM dd, YYYY"))
        // You can also provide your own format!
        test = "2010-05-20 15:30".toDate("yyyy-MM-dd HH:mm")
        print(test?.toFormat("MMM dd, YYYY"))
        // All ISO8601 variants are supported too with timezone parsing!
        test = "2017-09-17T11:59:29+02:00".toISODate()
        print(test?.toFormat("MMM dd, YYYY"))
        
        test = "2011-11-02T02:50+08:00".toISODate()
        print(test?.toFormat("MMM dd, YYYY"))
        // RSS, Extended, HTTP, SQL, .NET and all the major variants are supported!
        //        let _ = "19 Nov 2015 22:20:40 +0100".toRSS(alt: true)
        
    }

}

