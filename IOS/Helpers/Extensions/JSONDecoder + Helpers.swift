//
//  JSONDecoder + Extension.swift
//
//  Copyright © 2018. All rights reserved.
//

import Foundation

extension JSONDecoder {
    
    static func decode<T: Decodable>(_ type: T.Type, from data: Data?, completion: @escaping (_ error: String?,_ result: T?) -> Void) {
        
        guard let data = data else {
            completion("The data couldn't be read because it is missing", nil)
            return
        }
    
        let jsonDecoder = JSONDecoder()
        jsonDecoder.keyDecodingStrategy = .convertFromSnakeCase
        
        do {
            let result = try jsonDecoder.decode(type, from: data)
            completion(nil, result)
        } catch (let error) {
            JSONDecoder.logError(data: data)
            completion(error.localizedDescription, nil)
        }
        
    }
    
    // Log for some unknown case
    private static func logError(data: Data) {
        
        if let rawData = String.init(data: data, encoding: .utf8) {
            let errorLogsKey = "ErrorLogs"
            let userDefaults = UserDefaults.standard
            if var currentLog = userDefaults.value(forKey: errorLogsKey) as? [String] {
                currentLog.insert(rawData, at: 0)
                userDefaults.set(currentLog, forKey: errorLogsKey)
            } else {
                userDefaults.set([rawData], forKey: errorLogsKey)
            }
        }
        
    }
    
    static func showErrorLogs() {
        
        if let currentLog = UserDefaults.standard.value(forKey: "ErrorLogs") as? [String] {
            Logger.log(message: "ERROR LOGS: \(currentLog)", event: .error)
        }
        
    }
    
}
