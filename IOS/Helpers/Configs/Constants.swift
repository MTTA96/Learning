//
//  Constant.swift
//
//  Copyright © 2017. All rights reserved.
//

import Foundation

struct AppLayout {
    
    static let navBarHeight: CGFloat = 44
    static let statusBarHeight: CGFloat = 20
    static let rabbitEarsHeight: CGFloat = 24
    static let homeIndicatorHeight: CGFloat = 39
    
}

struct ErrorString {
    
    static let AudioURLFailed: String = "Sorry, the audio version is currently unavailable."
    
}

struct OneSignalKey {
    
    //static let appID = "399fc6e7-a296-4f7e-bbfd-8eb8d81897ed" // vendor
    
    #if DEV
    
        static let appID = "5a680075-4021-4405-80f2-faa364348ca4"
        static let restKey = "ZWI1MDM2NGItMWM3Zi00ZDE4LTljNWYtZWMxN2M4NmE0N2Q4"
    
    #elseif PRODUCTION
    
        static let appID = "2be95a14-5fc2-4c50-b19c-838dccdd81fd"
        static let restKey = "YmM5NGJiZTEtMTQxZS00MDc4LTg0NzEtZjY3ZGY5Mzk4YjI3"
    
    #endif
    
}

struct MixPanelKey {
    
    static let projectToken = ""
    
}

struct CHCDateFormat {
    
    static let eventDateTime = "MMM d • EEE • h:mm a"
    static let monthDay = "MMM dd"
    static let dayOfWeek = "EEE"
    static let time = "h a"
    static let liveDateTime = "MMM d, h:mm a"
    
    static let dayInterval: Int = 86400
    
}

struct CHCUserDefault {
    
    static let isFirstLaunch = "HasBeenLaunchedBeforeFlag"
    static let eventRecentSearch = "EventRecentSearch"
    static let musicRecentSearch = "MusicRecentSearch"
    static let homeRecentSearch = "HomeRecentSearch"
    static let radioRecentSearch = "RadioRecentSearch"
    static let promotionRecentSearch = "PromotionRecentSearch"
    static let userEmail = "UserEmail"
    static let deviceToken = "DeviceToken"
    static let currentCreditCardId = "CurrentCreditCardId"
    static let currentLanguage = "CHCCurrenLanguageSelected"
    
}

struct KeyStore {
    
    static let keychainService = "CHCKeychainService"
    static let userToken = "UserToken"
    static let password = "CHCUserPassword"
    
}

struct Media {
    static let state = "CHCState"
}

struct Parameter {
    static var limitPerPage = 10
}

// Support to action requires authentication

enum ActionType {
    case addFavorite
    case addPlaylist
    case postComment
}



