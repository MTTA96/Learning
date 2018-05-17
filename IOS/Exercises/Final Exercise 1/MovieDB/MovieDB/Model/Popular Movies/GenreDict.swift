//
//  GenreList.swift
//  MovieDB
//
//  Created by Anh Tran on 4/4/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation

class GenreDict {
    static let shared = GenreDict()
    var genres: [String: String] = [:]
    
    func get(id: String) -> String? {
        return genres[id]
    }
    
    func set(key: String, name: String) {
        genres[key] = name
    }
}
