//
//  Movie.swift
//  Movies
//
//  Created by Anh Tran on 3/22/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//


import Foundation
import Alamofire

struct Story : Codable {
    let title : String?
    let link : String?
    let image : String?
    let desc : String?
    let view : String?
    
    enum CodingKeys: String, CodingKey {
        
        case title = "title"
        case link = "link"
        case image = "image"
        case desc = "desc"
        case view = "view"
    }
    
    init(from decoder: Decoder) throws {
        let values = try decoder.container(keyedBy: CodingKeys.self)
        title = try values.decodeIfPresent(String.self, forKey: .title)
        link = try values.decodeIfPresent(String.self, forKey: .link)
        image = try values.decodeIfPresent(String.self, forKey: .image)
        desc = try values.decodeIfPresent(String.self, forKey: .desc)
        view = try values.decodeIfPresent(String.self, forKey: .view)
    }
    
}

// MARK: - Methods
extension Story {
    //Get details
    static func getDetails(param: String, result: @escaping (_ error: String?,_ content: String) -> Void) {
        
        let param: Parameters = ["url": param]
        
        Alamofire.request(UrlUtils.StoryDetails, parameters: param).response { response in
            if let jsonData = response.data {
                let jsonDecoder = JSONDecoder()
                do {
                    let responseModel = try jsonDecoder.decode(StoryContent.self, from: jsonData)
                    guard let content = responseModel.content else { return result("JSON decode failed" , "") }
                    result(nil, content)
                } catch (let error) {
                    result(error.localizedDescription, "")
                }
                return
            }
            result("Data failed", "")
        }
        
    }
    
    // Get movies list
    static func getMovies(page: String, result: @escaping (_ error: String?,_ stories: [Story]) -> Void) {
        
        let param: Parameters = ["page": page]
        
        Alamofire.request(UrlUtils.NewMovies, parameters: param).response { response in
            if let jsonData = response.data {
                let jsonDecoder = JSONDecoder()
                do {
                    let responseModel = try jsonDecoder.decode(BaseResponse.self, from: jsonData)
                    guard let stories = responseModel.stories else { return result("JSON decode failed" ,[]) }
                    result(nil, stories)
                } catch (let error) {
                    result(error.localizedDescription, [])
                }
                return
            }
            result("Data failed",[])
        }
        
    }
}
