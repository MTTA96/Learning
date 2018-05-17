//
//  StoryContent.swift
//  Movies
//
//  Created by Anh Tran on 3/23/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation
import Alamofire

struct StoryContent : Codable {
    let content : String?
    
    enum CodingKeys: String, CodingKey {
        
        case content = "content"
    }
    
    init(from decoder: Decoder) throws {
        let values = try decoder.container(keyedBy: CodingKeys.self)
        content = try values.decodeIfPresent(String.self, forKey: .content)
    }
    
}
