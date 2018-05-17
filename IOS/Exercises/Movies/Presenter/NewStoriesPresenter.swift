//
//  NewMoviesPresenter.swift
//  Movies
//
//  Created by Anh Tran on 3/21/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation
import Alamofire

class NewStoriesPresenter {
    
    var newStoriesVCCallBack: NewStoriesVCDelegate
    var stories: [Story]?
    var currentPage = 1;
    
    init(newStoriesVCCallBack: NewStoriesVCDelegate) {
        self.newStoriesVCCallBack = newStoriesVCCallBack
    }
    
    func getNewStories() {
        
        Story.getMovies(page: "\(currentPage)") { (error, stories) in
            if let error = error {
                print(error)
                return
            }
            guard stories.count != 0 else { return }
            self.newStoriesVCCallBack.dataCallBack(stories: stories)
            self.currentPage += 1
        }
        
    }
}
