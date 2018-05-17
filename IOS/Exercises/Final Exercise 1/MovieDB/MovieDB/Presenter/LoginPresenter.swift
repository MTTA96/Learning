//
//  LoginPresenter.swift
//  MovieDB
//
//  Created by Anh Tran on 3/28/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation

class LoginPresenter {
    
    func login(userName: String, passWord: String, result: @escaping (_ errorMsg: String?) -> Void) {
        User.login(username: userName, password: passWord) { (error, sessionID) in
            if error != nil  {
                result("Login Failed!")
                return
            }
            
            UserDefaults.standard.set(sessionID, forKey: DatabaseSupport.SessionID_KEY)
            result("Login Success!")
        }
    }
}
