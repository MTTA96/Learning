//
//  APIMethod.swift
//
//  Copyright © 2018 Apps Cyclone. All rights reserved.

import Alamofire

enum APIManager {
    
    //Background image
    case getSplashScreenBI
    case getLogInBI
    case getGeneralBI
    
    //Authentication
    case register
    case login
    case getStatusOfSetup
    case updateStatusOfSetup
    case getProfile
    case uploadProfile
    case uploadPhoto
    case resetPassword
    case changePassword
//    case getNotificationStatus(String)
//    case updateNotificationStatus(String)
//    case setUpNotificationStatus
    case getTermOfService
    case getPDPA
    
    // Radio
    case getListRadio
    case getRadio(String)
    
    // Event
    case listEventsType
    case listEventsByType(String, Int)
    case listFeaturedEventByType(String)
    case filterEventByDate(String, String, Int)
    case searchEvent(String)

    // Music
    case getMusicList(Int)
    case getMusicDetails(String)
    case addFavorite(String)
    case searchMusic(String)
    case getPlaylist
    case addPlaylist
    case removeFromPlaylist(Double)
    case orderFromPlaylist
    
    // Home (LiveWebcast)
    case getWebcastService
    case getWebsastServiceById(String)
    case getThemeBanner()
    
    // Home (Feed)
    case getFeedData(String, Int)
    
    // Home (Service)
    case getServicePackages(Int)
    case getServiceDetail(String)
    case getServiceFilterList
    case getComments(String, Int)
    case postComments
    case filterService(String, String)
    
    // Home (Worship)
    case getWorshipList(String, Int)
    
    // Home (Highlight)
    case getHighlightList(String, Int)
    
    // Home (Gallery)
    case getGalleries(Int)
    
    // Home (News)
    case getCityNewsFeed(Int)
    
    // More (Card)
    case createCreditCardRD
    case getCreditCardsCHC(String)
    case postCreditCardCHC
    case putCreditCardCHC(String)
    case deleteCreditCardCHC(String)
    case getCards(Int)
    
    // More (Favourite)
    case getFavouriteList(String, Int)
    case getFavoritesPhoto(Int)
    case getFavoritesVideo(Int)
    case removeFavoriteItem(String, String)
    
    // More (Give)
    case getPaymentsList
    
    // More (About us)
    case getAboutUsLink
    
    // More (Contact us)
    case getEmailContact
    
    // More (Promotion)
    case getPromotionList(Int)
    case getPromotionDetails(String)
    case searchPromotion(String)
    
    // More (Notification)
    
    case getBadgeCount(String, String)
    case getListNotifications(String, Int, Int)
    
    
    // App language
    case getAppConfigsLanguage
    
}

extension APIManager {
        
    var baseURL: String { return "https://api.chc.app/v1/" }
    
    //MARK: - URL
    
    var url: String {
        
        var path = ""
        
        switch self {
            
        //Background image
        case .getSplashScreenBI: path = "content/chc/appConfigs/splash.ios.background.image"
        case .getLogInBI: path = "content/chc/appConfigs/login.ios.background.image"
        case .getGeneralBI: path = "content/chc/appConfigs/general.ios.background.image"
            
        // Authentication
        case .register: path = "account/registration"
        case .login: path = ""
        case .getStatusOfSetup: path = "profile/data/initialsetup"
        case .updateStatusOfSetup: path = "profile/data/initialsetup"
        case .getProfile: path = "profile/"
        case .uploadProfile: path = "profile/"
        case .uploadPhoto: path = "profile/photo"
        case .resetPassword: path = "account/requestPasswordReset"
        case .changePassword: path = "profile/changePassword"
//        case .getNotificationStatus(let deviceToken) : path = "profile/notifications/configs/\(deviceToken)"
//        case .updateNotificationStatus(let deviceToken): path = "profile/notifications/configs/\(deviceToken)"
//        case .setUpNotificationStatus: path = "profile/notifications/configs/"
        case .getTermOfService: path = "content/chc/appConfigs/registration.terms.link"
        case .getPDPA: path = "content/chc/appConfigs/registration.pdpa.link"
            
        case .getListRadio: path = "feeds/cityradio"
        case .getRadio(let id): path = "feeds/cityradio/?p=\(id)"
        case .listEventsType: path = "content/chc/events/types"
        case .listEventsByType(let id, let page):  path = "content/chc/events/\(id)/?page=\(page)&per_page=20"
        case .listFeaturedEventByType(let id): path = "content/chc/events/\(id)/featured"
        case .filterEventByDate(let type, let date, let page): path = "content/chc/events/\(type)/?since=\(date)&page=\(page)&per_page=20"
        case .searchEvent(let keyword): path = "content/chc/events/all/?query=\(keyword)"

        // Music
        case .getMusicList(let page): path = "content/chc/cityWorship/?page=\(page)&per_page=\(Parameter.limitPerPage)"
        case .getMusicDetails(let id): path = "content/chc/cityWorship/\(id)"
        case .searchMusic(let keyword): path = "content/chc/cityWorship/?query=\(keyword)"
        case .addFavorite(let ContentTypeID): path = "profile/favorites/\(ContentTypeID)/"
        case .getPlaylist: path = "profile/playlist/"
        case .addPlaylist: path = "profile/playlist/"
        case .removeFromPlaylist(let orderID): path = "profile/playlist/\(orderID)"
        case .orderFromPlaylist: path = "profile/playlist/order"

        // Home (LiveWebcast)
        case .getWebcastService: path = "content/chc/webcastServices/status"
        case .getWebsastServiceById(let id): path = "content/chc/webcastServices/\(id)"
        case .getThemeBanner(): path = "content/chc/appConfigs/home.theme.image"
            
        // Home (Feed)
        case .getFeedData(let keyword, let page): path = "content/chc/home/feed?query=\(keyword)&page=\(page)&per_page=\(Parameter.limitPerPage)"
        
        // Home (Service)
        case .getServicePackages(let page): path = "content/chc/servicePackages/?page=\(page)&per_page=\(Parameter.limitPerPage)"
        case .getServiceDetail(let id): path = "content/chc/servicePackages/\(id)"
        case .getServiceFilterList: path = "content/chc/tags"
        case .getComments(let id, let page): path = "comments/servicePackages/\(id)?page=\(page)&per_page=\(Parameter.limitPerPage)"
        case .postComments: path = "comments/servicePackages"
        case .filterService(let id, let keyword): path = "content/chc/servicePackages/?tags=\(id)&query=\(keyword)"
            
        // Home (Worship)
        case .getWorshipList(let keyword, let page): path = "content/chc/worshipVideos?query=\(keyword)&page=\(page)&per_page=\(Parameter.limitPerPage)"
            
        // Home (Highlight)
        case .getHighlightList(let keyword, let page): path = "content/chc/highlightVideos?query=\(keyword)&page=\(page)&per_page=\(Parameter.limitPerPage)"
            
        // Home (Gallery)
        case .getGalleries(let page): path = "content/chc/galleries/?page=\(page)&per_page=\(Parameter.limitPerPage)"
            
        // Home (News)
        case .getCityNewsFeed(let page): path = "feeds/citynewsFeed/?page=\(page)&per_page=\(Parameter.limitPerPage)"
            
        // More (Card)
        case .createCreditCardRD: return "https://secure-dev.reddotpayment.com/service/token-api"
        case .getCreditCardsCHC(let cardId): path = "payment/cardTokens/\(cardId)"
        case .postCreditCardCHC: path = "payment/cardTokens/"
        case .putCreditCardCHC(let cardId): path = "payment/cardTokens/\(cardId)"
        case .deleteCreditCardCHC(let cardId): path = "payment/cardTokens/\(cardId)"
            
        case .getCards(let page): path = "profile/cards/?page=\(page)&per_page=\(Parameter.limitPerPage)"
            
        // More (Favourite)
        case .getFavouriteList(let contentTypeID, let page): path = "profile/favorites/\(contentTypeID != "" ? "\(contentTypeID)/" : "")?page=\(page)&per_page=\(Parameter.limitPerPage)"
        case .getFavoritesPhoto(let page): path = "profile/favorites/image/?page=\(page)&per_page=\(Parameter.limitPerPage)"
        case .getFavoritesVideo(let page): path = "profile/favorites/video/?page=\(page)&per_page=\(Parameter.limitPerPage)"
        case .removeFavoriteItem(let contentTypeId, let itemId): path = "profile/favorites/\(contentTypeId)/\(itemId)"
            
        // More (Give)
        case .getPaymentsList: path = "payment/store/products/"
            
        // More (About us)
        case .getAboutUsLink: path = "content/chc/appConfigs/more.aboutUs.link"
            
        // More (Contact us)
        case .getEmailContact: path = "content/chc/appConfigs/more.contactUs.email"
            
        // More (Promotion)
        case .getPromotionList(let page): path = "content/chc/promotions/?page=\(page)&per_page=\(Parameter.limitPerPage)"
        case .getPromotionDetails(let promotionId): path = "content/chc/promotions/\(promotionId)"
        case .searchPromotion(let keyword): path = "content/chc/promotions?query=\(keyword)"
            
        // More (Notification)
        case .getBadgeCount(let oneSignalId, let appId): return "https://onesignal.com/api/v1/players/\(oneSignalId)?app_id=\(appId)"
        case .getListNotifications(let appId, let limit, let offset): return "https://onesignal.com/api/v1/notifications?app_id=\(appId)&limit=\(limit)&offset=\(offset)"
            
        // App language
        case .getAppConfigsLanguage: path = "content/chc/appConfigs/languages"
        }
        return baseURL + path
        
    }
    
    //MARK: - METHOD
    
    var method: HTTPMethod {
        switch self {

        case .login, .getListRadio, .getRadio, .listEventsType, .listEventsByType, .listFeaturedEventByType, .getMusicList, .getMusicDetails, .filterEventByDate, .searchEvent, .searchMusic, .getProfile, .getPlaylist, .getWebcastService, .getWebsastServiceById, .getWorshipList, .getHighlightList, .getServicePackages, .getServiceDetail, .getFeedData, .getComments, .getServiceFilterList, .getCityNewsFeed, .getGalleries, .getFavouriteList, .filterService, .getCards, .getPaymentsList, .getCreditCardsCHC, .getStatusOfSetup, .getSplashScreenBI, .getLogInBI, .getGeneralBI, .getAboutUsLink, .getPromotionList, .getPromotionDetails, .searchPromotion, .getEmailContact, .getPDPA, .getTermOfService, .getAppConfigsLanguage, .getThemeBanner, .getFavoritesPhoto, .getFavoritesVideo, .getBadgeCount, .getListNotifications:

            return .get
            
        case .removeFromPlaylist, .removeFavoriteItem, .deleteCreditCardCHC:
            return .delete

        case .register, .uploadPhoto, .addPlaylist, .addFavorite, .postComments, .createCreditCardRD, .postCreditCardCHC, .resetPassword:
            return .post
            
        case .orderFromPlaylist, .uploadProfile, .changePassword, .putCreditCardCHC, .updateStatusOfSetup:
            return .put

        }
    }
    
    //MARK: - HEADER
    
    var header: [String : String]? {
        switch self{
        case .uploadPhoto, .getProfile, .getPlaylist, .addPlaylist, .removeFromPlaylist, .orderFromPlaylist, .addFavorite, .getWorshipList, .getHighlightList, .getServicePackages, .getServiceDetail, .getComments, .postComments, .uploadProfile, .getCityNewsFeed, .getFavouriteList, .removeFavoriteItem, .changePassword, .getCards, .postCreditCardCHC, .putCreditCardCHC, .getCreditCardsCHC, .getStatusOfSetup, .updateStatusOfSetup, .deleteCreditCardCHC, .getFavoritesVideo, .getFavoritesPhoto:
            return [Header.authorization : "Bearer \(UserManager.shared.token ?? "")"]
            
        case .getListNotifications:
            return [Header.authorization : "Basic \(OneSignalKey.restKey)"]
        default:
            return nil
        }
    }
    
    //MARK: - ENCODING
    
    var encoding: ParameterEncoding {
        
        switch self {
        case .register, .uploadPhoto, .addPlaylist, .addFavorite, .orderFromPlaylist, .postComments, .createCreditCardRD, .uploadProfile, .changePassword, .postCreditCardCHC, .putCreditCardCHC, .updateStatusOfSetup, .resetPassword:
            return JSONEncoding.default
        default:
            return URLEncoding.default
        }
        
    }
}
