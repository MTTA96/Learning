//
//  APIResponse.swift
//
//  Copyright © 2018 Apps Cyclone. All rights reserved.


struct ErrorResponse: Decodable {
    var statusCode: Int?
    var statusMessage: String?
}

struct Response<T: Decodable>: Decodable {
    var status: String?
    var total: Int?
    var items: T?
}

struct ResponseOfPost: Decodable {
    var success: Bool?
    var message: String?
    var error: String?
}

struct RegisterResponse: Decodable {
    let uid: String?
    let message: String?
    let success: Bool?
}

struct UploadPhotoResponse: Decodable {
    let photoUrl: String?
    let message: String?
    let success: Bool?
}

struct PostCHCCardResponse: Decodable {
    let success: Bool?
    let cardId: String?
}

//struct GetValueRepsonse: Decodable {
//    let name: String?
//    let value: String?
//}

struct GetValueRepsonse<T: Decodable>: Decodable {
    let name: String?
    let value: T?
}

struct GetValueNotificationBadge<T: Decodable>: Decodable {
    let name: String?
    let value: T?
}

struct GetListOneSignalNotifications<T: Decodable>: Decodable {
    var totalCount: Int?
    var notifications: T?
}
