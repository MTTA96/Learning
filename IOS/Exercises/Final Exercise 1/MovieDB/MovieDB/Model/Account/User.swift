//
//  User.swift
//  MovieDB
//
//  Created by Anh Tran on 3/28/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation
import Alamofire

class User {
    
}

// MARK: - Methods
extension User {
    
    //Request token
    static func requestToken(result: @escaping (_ error: String?, _ token: String?) -> Void) {
        
        Alamofire.request(ServerUrl.requestTokenUrl).response { response in

            if let jsonData = response.data {
                do {
                    if let root = try JSONSerialization.jsonObject(with: jsonData, options: []) as? [String: Any] {
                        guard (root["success"] as? Bool) != nil else { return result("Login Failed", nil) }
                        result(nil, root["request_token"] as? String)
                    }
                } catch (let error) {
                    result(error.localizedDescription, nil)
                }
            }
        }
    }
    
    //Validate with login
    static func validateWithLogin(username: String, password: String, result: @escaping (_ error: String?, _ token: String?) -> Void) {
        
        //Request token
        requestToken() { (error, token) in
            if let error = error  {
                result(error, nil)
                return
            }
            
            //Validate request token with login
            let param: Parameters = ["username": username, "password": password, "request_token": token!]
            Alamofire.request(ServerUrl.validatingLoginUrl, parameters: param).response { response in

                if let jsonData = response.data {
                    do {
                        if let root = try JSONSerialization.jsonObject(with: jsonData, options: []) as? [String:Any] {
                            guard (root["success"] as? Bool) != nil else { return result("Login Failed", nil)}
                            result(nil, root["request_token"] as? String)
                        }
                    } catch (let error) {
                        result(error.localizedDescription, nil)
                    }
                }
            }
        }
    }
    
    //Create session ID
    static func login(username: String, password: String, result: @escaping (_ error: String?, _ token: String?) -> Void) {
        
        //Request token
        validateWithLogin(username: username, password: password) { (error, token) in
            if let error = error {
                result(error, nil)
                return
            }
            
            //Call api
            let param: Parameters = ["request_token": token!]
            Alamofire.request(ServerUrl.createSessionUrl, parameters: param).response { response in
                //print("session id: \(response.request)")
                
                if let jsonData = response.data {
                    do {
                        if let root = try JSONSerialization.jsonObject(with: jsonData, options: []) as? [String: Any] {
                            guard (root["success"] as? Bool) != nil else { return result("Login Failed", nil)}
                            result(nil, root["session_id"] as? String)
                        }
                    } catch (let error) {
                        result(error.localizedDescription, nil)
                    }
                }
            }
        }
    }
    
    // Mark favorite movie
    static func markFavoriteMovie(movieId: Int!, isMarked: Bool!, result: @escaping (_ error: String?) -> Void) {
        
        let headers = ["content-type": "application/json;charset=utf-8"]
        let parameters = [
            "media_type": "movie",
            "media_id": movieId,
            "favorite": isMarked
            ] as [String : Any]

        do {
            let postData = try JSONSerialization.data(withJSONObject: parameters, options: [])
            let sessionId = UserDefaults.standard.string(forKey: DatabaseSupport.SessionID_KEY)!
            var request = URLRequest(url: NSURL(string: "https://api.themoviedb.org/3/account/%7Baccount_id%7D/favorite?session_id=\(sessionId)&api_key=9d92d99f2296cccc3841576fb68e33ab")! as URL,
                                              cachePolicy: .useProtocolCachePolicy,
                                              timeoutInterval: 10.0)
            request.httpMethod = "POST"
            request.allHTTPHeaderFields = headers
            request.httpBody = postData

            Alamofire.request(request).response { response in
                
                if let jsonData = response.data {
                    do {
                        if let root = try JSONSerialization.jsonObject(with: jsonData, options: []) as? [String: Any] {
                            let statusCode = root["status_code"] as! Int
                            guard statusCode == 1 || statusCode == 13 else { return result("Mark Failed")}
                            result(nil)
                        }
                    } catch {}
                }
            }

        } catch {
            result("Mark Failed")
        }
        
    }
}
