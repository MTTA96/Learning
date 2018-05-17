//
//  SignInViewController.swift
//  MapAndFirebase
//
//  Created by Anh Tran on 4/9/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit
import GoogleSignIn
import FirebaseAuth

class SignInViewController: UIViewController {
    
    var handle: AuthStateDidChangeListenerHandle?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        //Setup google sign in
        GIDSignIn.sharedInstance().uiDelegate = self
        GIDSignIn.sharedInstance().signInSilently()
        
        //getting the signin button and adding it to view
        
        handle = Auth.auth().addStateDidChangeListener() { (auth, user) in
            if user != nil {
                MeasurementHelper.sendLoginEvent()
                self.present((self.storyboard?.instantiateViewController(withIdentifier: "MainController"))!, animated: true, completion: nil)
            }
        }
    
    }
    
    deinit {
        if let handle = handle {
            Auth.auth().removeStateDidChangeListener(handle)
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    

}


// MARK: - GIDSignInUIDelegate
extension SignInViewController: GIDSignInUIDelegate {
    
}
