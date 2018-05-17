//
//  LoginViewController.swift
//  Restaurants
//
//  Created by Anh Tran on 4/19/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit
import Firebase
import FirebaseAuth
import GoogleSignIn
import FBSDKLoginKit

class LoginViewController: UIViewController {
    @IBOutlet weak var fbLogin: FBSDKLoginButton!
    
    var handle: AuthStateDidChangeListenerHandle?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Facebook
        fbLogin.delegate = self
        fbLogin.layer.cornerRadius = 20
        
        // Google
        GIDSignIn.sharedInstance().uiDelegate = self
        
        handle = Auth.auth().addStateDidChangeListener() { (auth, user) in
            if let user = user {
                //Check if user exists in db
                let databaseRef = Database.database().reference()
                
                databaseRef.child("users").observeSingleEvent(of: DataEventType.value, with: { (snapshot) in
                    
                    if snapshot.hasChild(user.uid){
//                        print("Existed in db")
                    }else{
                        databaseRef.child("users").child(user.uid).setValue(["name": user.displayName])
                    }
                    
                    
                })
                self.present((UIStoryboard(name: "Main", bundle: Bundle.main).instantiateViewController(withIdentifier: "MainController")), animated: true, completion: nil)
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
extension LoginViewController: GIDSignInUIDelegate {
    
}

// MARK: - FBSDKLoginButtonDelegate
extension LoginViewController: FBSDKLoginButtonDelegate {
    
    func loginButtonDidLogOut(_ loginButton: FBSDKLoginButton!) {
        return
    }
    
    func loginButton(_ loginButton: FBSDKLoginButton!, didCompleteWith result: FBSDKLoginManagerLoginResult!, error: Error!) {
        if let error = error {
            print(error.localizedDescription)
            return
        }
        
        if !result.isCancelled {
            let credential = FacebookAuthProvider.credential(withAccessToken: FBSDKAccessToken.current().tokenString)
            Auth.auth().signIn(with: credential) { (user, error) in
                if let error = error {
                    print(error.localizedDescription)
                    return
                }
                // User is signed in
                
            }
        }
    }
}
