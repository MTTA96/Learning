

import Foundation
import Alamofire

struct People : Codable {
	let people : [Person]?

	enum CodingKeys: String, CodingKey {
		case people = "results"
	}

	init(from decoder: Decoder) throws {
		let values = try decoder.container(keyedBy: CodingKeys.self)
		people = try values.decodeIfPresent([Person].self, forKey: .people)
	}
    
}

// MARK: - Methods
extension People {
    
    //Get people
    static func getPeople (page: Int, result: @escaping (_ error: String?, _ response: BaseResponse?, _ people: People?) -> Void) {
        let params: Parameters = ["page": page]
        
        Alamofire.request(ServerUrl.popularPeopelUrl, parameters: params).response { response in

            if let jsonData = response.data {
                let jsonDecode = JSONDecoder()
                do {
                    let baseResponse = try jsonDecode.decode(BaseResponse.self, from: jsonData)
                    let people = try jsonDecode.decode(People.self, from: jsonData)
                    result(nil, baseResponse, people)
                } catch {
                    result("Failed!", nil, nil)
                }
            } else {
                result("Failed!", nil, nil)
            }
        }
    }
    
}

