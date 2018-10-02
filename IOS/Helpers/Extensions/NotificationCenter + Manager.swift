//
//  NotificationCenterExtension.swift
//  CHC
//
//  Created by Anh Tran on 6/8/18.
//  Copyright © 2018 Apps Cyclone. All rights reserved.
//

import Foundation

extension NSNotification.Name {
    
    static let moveToAuthenticationVC = NSNotification.Name(rawValue: "MoveToAuthenticationVC")
    static let enableEventTableScroll = NSNotification.Name(rawValue: "EnableEventTableScroll")
    static let disableEventTableScroll = NSNotification.Name(rawValue: "DiableEventTableScroll")
    
    static let expandEventMainBanner = NSNotification.Name(rawValue: "ExpandEventMainBanner")
    static let collapseEventMainBanner = NSNotification.Name(rawValue: "CollapseEventMainBanner")
    
    static let scrollToYOffset = NSNotification.Name(rawValue: "ScrollToYOffset")
    static let eventsPickerShowed = NSNotification.Name(rawValue: "PickerShowed")
    static let search = NSNotification.Name(rawValue: "CHCSearch")
    static let clearSearch = NSNotification.Name(rawValue: "ClearSearch")
    static let searchEditing = NSNotification.Name(rawValue: "SearchEditing")
    static let suggestionSelected = NSNotification.Name(rawValue: "SuggestionSelected")
    
    static let expandMusicPlayer = Notification.Name(rawValue: "CHCExpandMusicPlayer")
    
    static let selectedLanguageService = Notification.Name(rawValue: "CHCSelectedLanguageService")
    //static let changIconHeadphone = Notification.Name(rawValue: "CHCChangeIconHeadphone")
    static let playSong = Notification.Name(rawValue: "CHCPlaySongMiniPlayer")
    static let nextSong = Notification.Name(rawValue: "CHCNextSongMiniPlayer")
    static let previousSong = Notification.Name(rawValue: "CHCPreviousSongMiniPlayer")
    static let updateSongProgress = Notification.Name(rawValue: "CHCUpdateSongProgress")
    
    static let playMySong = Notification.Name(rawValue: "CHCPlayMySongMiniPlayer")
    static let nextMySong = Notification.Name(rawValue: "CHCNextMySongMiniPlayer")
    static let previousMySong = Notification.Name(rawValue: "CHCPreviousMySongMiniPlayer")
    static let updateMySongProgress = Notification.Name(rawValue: "CHCUpdateMySongProgress")
    
    static let playWoshipSong = Notification.Name(rawValue: "CHCPlayWoshipSongMiniPlayer")
    static let nextWoshipSong = Notification.Name(rawValue: "CHCNextWoshipSongMiniPlayer")
    static let previousWoshipSong = Notification.Name(rawValue: "CHCPreviousWoshipSongMiniPlayer")
    static let updateWoshipSongProgress = Notification.Name(rawValue: "CHCUpdateWoshipSongProgress")
    
    static let playServiceWoshipSong = Notification.Name(rawValue: "CHCPlayServiceWoshipSongMiniPlayer")
    static let nextServiceWoshipSong = Notification.Name(rawValue: "CHCNextServiceWoshipSongMiniPlayer")
    static let previousServiceWoshipSong = Notification.Name(rawValue: "CHCPreviousServiceWoshipSongMiniPlayer")
    
    static let changeMediaState = Notification.Name(rawValue: "CHCChangeMediaState")
    static let didAudioPlayerChangeState = Notification.Name("CHCDidAudioPlayerStateChange")
    static let didAudioPlayerChangeItem = Notification.Name("CHCDidAudioPlayerChangeItem")
    static let openAudioPlayer = Notification.Name("CHCOpenAudioPlayer")
    
    static let playlistDidChange = Notification.Name("CHCPlaylistDidChange")
    static let homeTabDidSwipe = Notification.Name("CHChomeTabDidSwipe")
    static let moveToMyPlaylist = Notification.Name("CHCMoveToMyPlaylist")
    static let moveToCityWorship = Notification.Name("CHCMoveToCityWorship")
    
    static let didRegisterForNotification = Notification.Name("CHCDidRegisterForNotification")

    static let selectedItemFavoritePhotos = Notification.Name("CHCSelectedItemFavoritePhotos")
    static let deletePhotosFavorite = Notification.Name("CHCDeletePhotosFavorite")
    
    static let finishedLiveService = Notification.Name("CHCFinishedLiveService")
    
}
