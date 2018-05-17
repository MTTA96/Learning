//
//  Movie.swift
//  MovieDB
//
//  Created by Anh Tran on 3/29/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation
import Alamofire

struct Movie : Codable {
    let poster_path : String?
    let adult : Bool?
    let overview : String?
    let release_date : String?
    let genre_ids : [Int]?
    let id : Int?
    let original_title : String?
    let original_language : String?
    let title : String?
    let backdrop_path : String?
    let popularity : Double?
    let vote_count : Int?
    let video : Bool?
    let vote_average : Double?
    
    enum CodingKeys: String, CodingKey {
        
        case poster_path = "poster_path"
        case adult = "adult"
        case overview = "overview"
        case release_date = "release_date"
        case genre_ids = "genre_ids"
        case id = "id"
        case original_title = "original_title"
        case original_language = "original_language"
        case title = "title"
        case backdrop_path = "backdrop_path"
        case popularity = "popularity"
        case vote_count = "vote_count"
        case video = "video"
        case vote_average = "vote_average"
    }
    
    init(from decoder: Decoder) throws {
        let values = try decoder.container(keyedBy: CodingKeys.self)
        poster_path = try values.decodeIfPresent(String.self, forKey: .poster_path)
        adult = try values.decodeIfPresent(Bool.self, forKey: .adult)
        overview = try values.decodeIfPresent(String.self, forKey: .overview)
        release_date = try values.decodeIfPresent(String.self, forKey: .release_date)
        genre_ids = try values.decodeIfPresent([Int].self, forKey: .genre_ids)
        id = try values.decodeIfPresent(Int.self, forKey: .id)
        original_title = try values.decodeIfPresent(String.self, forKey: .original_title)
        original_language = try values.decodeIfPresent(String.self, forKey: .original_language)
        title = try values.decodeIfPresent(String.self, forKey: .title)
        backdrop_path = try values.decodeIfPresent(String.self, forKey: .backdrop_path)
        popularity = try values.decodeIfPresent(Double.self, forKey: .popularity)
        vote_count = try values.decodeIfPresent(Int.self, forKey: .vote_count)
        video = try values.decodeIfPresent(Bool.self, forKey: .video)
        vote_average = try values.decodeIfPresent(Double.self, forKey: .vote_average)
    }
    
}

// MARK: Methods

extension Movie {
    
    static func formatAmount(amount: Int) -> String {
        let formatter = NumberFormatter()
        formatter.locale = Locale.current
        formatter.numberStyle = .currency
        if let formattedAmount = formatter.string(from: amount as NSNumber) {
            return formattedAmount
        }
        
        return ""
    }
    
    // MARK: - Network
    
    //Get movie states
    static func checkMovieStates(movieId: Int!, result: @escaping (_ error: String?, _ status: MovieStates?) -> Void) {
        let sessionId = UserDefaults.standard.string(forKey: DatabaseSupport.SessionID_KEY)
        let link = ServerUrl.movieUrl + "/\(movieId!)" + ServerUrl.movieStatesUrl + "&session_id=\(sessionId!)"
        
        guard let url = URL(string: link) else { return result("Check Failed", nil)}
        
        Alamofire.request(url).response { response in
            
            if let jsonData = response.data {
                let jsonDecoder = JSONDecoder()
                do {
                    let states = try jsonDecoder.decode(MovieStates.self, from: jsonData)
                    result(nil, states)
                } catch {
                    result("Check Failed", nil)
                }
                
            }
            
        }
    }
    
    //Get official genres
    static func getOfficialGenres(result: @escaping(_ error: String?, _ genres: [Genre]?) -> Void ) {
        
        Alamofire.request(ServerUrl.officialGenreListUrl).response { response in

            if let jsonData = response.data {
                let jsonDecoder = JSONDecoder()
                do {
                    let genres = try jsonDecoder.decode(MovieDetails.self, from: jsonData)
                    result(nil, genres.genres)
                } catch {
                    result("Failed", nil)
                }
            } else {
                result("Failed", nil)
            }
        }
    }
}
