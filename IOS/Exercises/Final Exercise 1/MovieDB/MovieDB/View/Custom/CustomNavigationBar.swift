//
//  CustomNavigationBar.swift
//  MovieDB
//
//  Created by Anh Tran on 3/26/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit
import Foundation


extension UINavigationController
{
    func makeBlackNavigationbar (){
        
        print("black navigation")
        
        navigationController?.isNavigationBarHidden = false
    }
}

extension UINavigationBar
{
    
    func makeBlackNavigationBar ()
    {
        barTintColor = UIColor.blue
        let titleDict: NSDictionary = [NSAttributedStringKey.foregroundColor: UIColor.yellow]
        titleTextAttributes = titleDict as? [NSAttributedStringKey : Any]
    }
}
