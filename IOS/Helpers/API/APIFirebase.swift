//
//  APIFirebase.swift
//  CHC
//
//  Created by Hai Nguyen on 7/5/18.
//  Copyright © 2018 Apps Cyclone. All rights reserved.
//

import Foundation
import FirebaseAuth
import Firebase

struct APIFirebase {
    
    //SignUP
    static func signUp(mail: String, password: String, completion: @escaping ( _ authDataResult: AuthDataResult?,_ error: Error?) -> Void) {
        Auth.auth().createUser(withEmail: mail, password: password) { (result, error) in
            if let error = error {
                completion(nil, error)
            } else {
                completion(result, nil)
            }
        }
    }
    
    //SignIn
    static func signIn(mail: String, password: String, completion: @escaping ( _ authDataResult: AuthDataResult?,_ error: Error?) -> Void){
        Auth.auth().signIn(withEmail: mail, password: password) { (result, error) in
            if let error = error {
                completion(nil, error)
            } else {
                completion(result, nil)
            }
        }
    }
    
    //SignOut
    static func signOut(completion: @escaping (_ error: Error?) -> Void) {
        do {
            try Auth.auth().signOut()
            self.currentUser = { return Auth.auth().currentUser }
            completion(nil)
        } catch let error {
            completion(error)
        }
    }
    
    //Forgot Password
    static func forgotPassword(mail: String, completion: @escaping (_ error: Error?) -> Void) {
        Auth.auth().sendPasswordReset(withEmail: mail) { (error) in
            if let error = error {
                completion(error)
            } else {
                completion(nil)
            }
        }
    }
    
    static var currentUser =  { return Auth.auth().currentUser }
    
}
