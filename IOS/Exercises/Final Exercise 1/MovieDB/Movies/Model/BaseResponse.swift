import Foundation

struct BaseResponse: Codable {
	let stories : [Story]?

	enum CodingKeys: String, CodingKey {

		case stories = "stories"
	}

	init(from decoder: Decoder) throws {
		let values = try decoder.container(keyedBy: CodingKeys.self)
		stories = try values.decodeIfPresent([Story].self, forKey: .stories)
	}
}
