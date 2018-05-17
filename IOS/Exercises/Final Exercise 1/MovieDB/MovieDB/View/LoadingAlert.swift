//
//  LoadingAlert.swift
//  MovieDB
//
//  Created by Anh Tran on 3/29/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

struct LoadingAlert {
    
    func createView () -> UIAlertController {
        let alert: UIAlertController = UIAlertController(title: nil, message: "Please wait...", preferredStyle: .alert)
        let loadingIndicator = UIActivityIndicatorView(frame: CGRect(x: 10, y: 5, width: 50, height: 50))
        loadingIndicator.hidesWhenStopped = true
        loadingIndicator.activityIndicatorViewStyle = UIActivityIndicatorViewStyle.gray
        loadingIndicator.startAnimating();
        
        alert.view.addSubview(loadingIndicator)
        return alert
    }
    
}
