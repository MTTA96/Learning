//
//  BaseResponse.swift
//  MovieDB
//
//  Created by Anh Tran on 4/11/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation

struct BaseResponse: Codable {
    let page : Int?
    let totalResults : Int?
    let totalPages : Int?
    
    enum CodingKeys: String, CodingKey {
        case page = "page"
        case totalResults = "total_results"
        case totalPages = "total_pages"
    }
    
    init(from decoder: Decoder) throws {
        let values = try decoder.container(keyedBy: CodingKeys.self)
        page = try values.decodeIfPresent(Int.self, forKey: .page)
        totalResults = try values.decodeIfPresent(Int.self, forKey: .totalResults)
        totalPages = try values.decodeIfPresent(Int.self, forKey: .totalPages)
    }
}
