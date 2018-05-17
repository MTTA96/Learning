//
//  DetailPresenter.swift
//  Movies
//
//  Created by Anh Tran on 3/22/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import Foundation

protocol DetailPresenterDelegate {
    func getDataSuccess(content: String)
}

class DetailPresenter {
    var detailVCCallBack: DetailVCDelegate
    
    init(detailVCCallBack: DetailVCDelegate) {
        self.detailVCCallBack = detailVCCallBack
    }
    
    func getStoryDetail(link: String) {
        Story.getDetails(param: link) { (error, content) in
            if let error = error {
                print (error)
                return
            }
            guard content != "" else { return }
            self.detailVCCallBack.dataCallBack(content: content)
        }
    }
}
