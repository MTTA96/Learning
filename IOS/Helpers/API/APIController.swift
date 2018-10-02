//
//  APIController.swift
//
//  Copyright © 2018 Apps Cyclone. All rights reserved.

import Foundation
import Alamofire

typealias ErrorResponseBlock = (_ error: String?) -> Void
typealias ResponseBlock<T: Decodable> = (String?, T?) -> Void

struct DataInfo {
    
    var withName: String
    var fileName: String
    var mimeType: String

}

struct APIController {
    
    //MARK: - DEFAULT REQUEST

    static func request<T: Decodable>(responseType: T.Type, manager: APIManager, params: Parameters? = nil, completion: @escaping ResponseBlock<T>){
        
        //Note: - Allow special character query for Chinese parameters
        let url = manager.url.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed)
        
        Logger.log(message: "API: \(manager)\nURL: \(url ?? manager.url)", event: .info)
        
        Alamofire.request(url ?? manager.url, method: manager.method, parameters: params, encoding: manager.encoding, headers: manager.header).validate().responseData { (responseData) in
            
            Logger.log(message: "API response code: \(responseData.response?.statusCode ?? 0)", event: .info)
            if responseData.response?.statusCode == 401 {
                Logger.log(message: "Unauthorized", event: .error)
                //completion("The user credential is no longer valid. You must sign in again", nil)
                ProgressView.shared.hide()
                NotificationCenter.default.post(name: NSNotification.Name.moveToAuthenticationVC, object: nil)
                
                return
            }
            
            switch responseData.result {
                
            case .success(let data):
                JSONDecoder.decode(responseType, from: data, completion: { (error, response) in
                    completion(error, response)
                })
            case .failure(let error):
                Logger.log(message: error.localizedDescription, event: .error)
                if let error = error as? URLError {
                    if error.code == URLError.Code.notConnectedToInternet || error.code == URLError.Code.timedOut {
                        completion(error.localizedDescription, nil)
                        return
                    }
                }
                
                if let data = responseData.data {
                    JSONDecoder.decode(ErrorResponse.self, from: data, completion: { (serverError, response) in
                        
                        if let message = response?.statusMessage {
                            completion(message, nil)
                            return
                        }
                        
                        completion(serverError, nil)
                    })
                    return
                }
                
                completion(error.localizedDescription, nil)
            }
            
        }
    }

    //MARK: - MULTIPART UPLOAD
    
    static func upload<T: Decodable>(responseType: T.Type, manager: APIManager, params: [String: AnyObject]?, data: Data?, info: DataInfo, result: @escaping ResponseBlock<T>) {
        
        Logger.log(message: "API: \(manager)\nURL: \(manager.url)", event: .info)
        
        Alamofire.upload(multipartFormData: { (multipartFormData) in
            
            if let data = data{
                multipartFormData.append(data, withName: info.withName, fileName: info.fileName, mimeType: info.mimeType)
            }
            
            if let params = params {
                for (key,value) in params{
                    multipartFormData.append(value.data!(using: String.Encoding.utf8.rawValue)!, withName: key)
                }
            }
            
        }, to: manager.url, headers: manager.header) { (resultUpload) in
            
            switch resultUpload {
            case .success(let upload,_,_):
                upload.responseJSON { response in
                    
                    switch response.result {
                    case .failure(let error):
                        print(error.localizedDescription)
                        result(error.localizedDescription, nil)
                        
                    case .success(let value):
                        print("JSON: \(value)")
                        JSONDecoder.decode(responseType, from: response.data, completion: { (error, response) in
                            result(error, response)
                        })
                    }
                }
                
            case .failure(let error):
                result(error.localizedDescription, nil)
            }
            
        }
    }
    
}
