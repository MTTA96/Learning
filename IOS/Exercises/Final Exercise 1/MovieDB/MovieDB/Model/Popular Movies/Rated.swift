//
//  Rated.swift
//  MovieDB
//
//  Created by Anh Tran on 3/30/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation

struct Rated : Codable {
	let value : Int?

	enum CodingKeys: String, CodingKey {

		case value = "value"
	}

	init(from decoder: Decoder) throws {
		let values = try decoder.container(keyedBy: CodingKeys.self)
		value = try values.decodeIfPresent(Int.self, forKey: .value)
	}

}
