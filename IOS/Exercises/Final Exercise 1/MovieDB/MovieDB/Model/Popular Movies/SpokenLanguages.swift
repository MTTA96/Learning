//
//  SpokenLanguages.swift
//  MovieDB
//
//  Created by Anh Tran on 3/29/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation

struct SpokenLanguages : Codable {
	let iso_639_1 : String?
	let name : String?

	enum CodingKeys: String, CodingKey {

		case iso_639_1 = "iso_639_1"
		case name = "name"
	}

	init(from decoder: Decoder) throws {
		let values = try decoder.container(keyedBy: CodingKeys.self)
		iso_639_1 = try values.decodeIfPresent(String.self, forKey: .iso_639_1)
		name = try values.decodeIfPresent(String.self, forKey: .name)
	}

}
