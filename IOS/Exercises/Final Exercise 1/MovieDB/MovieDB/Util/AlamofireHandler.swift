//
//  AlamofireHandler.swift
//  MovieDB
//
//  Created by Anh Tran on 4/2/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation
import Alamofire

struct AlamofireHandler {
    static func requestUrl(url: String!, params: [String : Any]?, result: @escaping (_ error: String?, _ result: Data?) -> Void) {
        
        if let url = URL(string: url) {
            if params != nil {
                Alamofire.request(url, parameters: params).response { response in
                    if let jsonData = response.data {
                        result(nil, jsonData)
                        return
                    }
                    result("Failed!", nil)
                }
            } else {
                Alamofire.request(url).response { response in
                    if let jsonData = response.data {
                        result(nil, jsonData)
                        return
                    }
                    result("Failed!", nil)
                }
            }
        }
        
    }
}
