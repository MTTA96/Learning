//
//  MovieStates.swift
//  MovieDB
//
//  Created by Anh Tran on 3/30/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation

struct MovieStates : Codable {
	let id : Int?
	let favorite : Bool?
	let rated : Rated?
	let watchlist : Bool?

	enum CodingKeys: String, CodingKey {

		case id = "id"
		case favorite = "favorite"
		case rated
		case watchlist = "watchlist"
	}

	init(from decoder: Decoder) throws {
		let values = try decoder.container(keyedBy: CodingKeys.self)
		id = try values.decodeIfPresent(Int.self, forKey: .id)
		favorite = try values.decodeIfPresent(Bool.self, forKey: .favorite)
		rated = try Rated(from: decoder)
		watchlist = try values.decodeIfPresent(Bool.self, forKey: .watchlist)
	}

}
