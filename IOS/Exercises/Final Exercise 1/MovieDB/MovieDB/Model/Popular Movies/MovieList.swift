//
//  PopularMoviesList.swift
//  MovieDB
//
//  Created by Anh Tran on 3/29/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation
import Alamofire

struct MovieList : Codable {
    let moviesList : [Movie]?
    
    enum CodingKeys: String, CodingKey {
        case moviesList = "results"
    }
    
    init(from decoder: Decoder) throws {
        let values = try decoder.container(keyedBy: CodingKeys.self)
        moviesList = try values.decodeIfPresent([Movie].self, forKey: .moviesList)
    }
}

extension MovieList {
    
    //Get movie list
    static func getMovieList (url: String, page: Int, result: @escaping (_ error: String?, _ baseResponse: BaseResponse?, _ moviesList: MovieList?) -> Void) {
        
        let url = URL(string: url + "&session_id=\(UserDefaults.standard.string(forKey: DatabaseSupport.SessionID_KEY)!)")
        let params: Parameters = ["page": page]
        
        Alamofire.request(url!, parameters: params).response { response in
            
            if let jsonData = response.data {
                let jsonDecode = JSONDecoder()
                do {
                    let baseResponse = try jsonDecode.decode(BaseResponse.self, from: jsonData)
                    let movies = try jsonDecode.decode(MovieList.self, from: jsonData)
                    result(nil, baseResponse, movies)
                } catch {
                    result("Failed!", nil, nil)
                }
            } else {
                result("Failed!", nil, nil)
            }
        }
        
    }
    
    //Search movie
    static func searchMovie (page: Int, query: String, result: @escaping (_ error: String?, _ baseResponse: BaseResponse?, _ moviesList: MovieList?) -> Void) {

        let params: Parameters = ["query": query, "page": page]
        
        Alamofire.request(ServerUrl.searchMovieUrl, parameters: params).response { response in
            
            if let jsonData = response.data {
                let jsonDecode = JSONDecoder()
                do {
                    let baseResponse = try jsonDecode.decode(BaseResponse.self, from: jsonData)
                    let movies = try jsonDecode.decode(MovieList.self, from: jsonData)
                    result(nil, baseResponse, movies)
                } catch {
                    result("Failed!", nil, nil)
                }
            } else {
                result("Failed!", nil, nil)
            }
        }
        
    }
    
}

