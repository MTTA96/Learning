//
//  AccountViewController.swift
//  Restaurants
//
//  Created by Anh Tran on 4/19/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit
import FirebaseAuth
import GoogleSignIn
import FBSDKLoginKit

class AccountViewController: UIViewController {
    @IBOutlet weak var userImageView: UIImageView!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var signOutView: UIStackView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Set signOut action
        signOutView.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(self.signOut)))

        // Configure UserImage
        userImageView.layer.cornerRadius = 22
        userImageView.layer.borderWidth = 2.0
        userImageView.layer.borderColor = #colorLiteral(red: 1.0, green: 1.0, blue: 1.0, alpha: 1.0)
        userImageView.clipsToBounds = true
        
        loadData()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Update
    func loadData() {
        if let user = Auth.auth().currentUser {
            userImageView.downloadedFrom(url: user.photoURL!)
            nameLabel.text = user.displayName
        }
    }
    
    // MARK: - Actions
    @IBAction func signOut() {
        let firebaseAuth = Auth.auth()
        do {
            try firebaseAuth.signOut()
            GIDSignIn.sharedInstance().signOut()
            
            // Facebook logout
            if FBSDKAccessToken.current() != nil {
                let loginManager = FBSDKLoginManager()
                loginManager.logOut()
            }
            
            
            self.present((UIStoryboard(name: "Login", bundle: Bundle.main).instantiateViewController(withIdentifier: "LoginVC")), animated: true, completion: nil)
        } catch let signOutError as NSError {
            print ("Error signing out: %@", signOutError)
        }
    }

}
