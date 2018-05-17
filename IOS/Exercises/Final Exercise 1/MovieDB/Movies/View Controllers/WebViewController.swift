//
//  WebViewController.swift
//  Movies
//
//  Created by Anh Tran on 3/23/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit
import WebKit

class WebViewController: UIViewController, WKUIDelegate {
  
    var webView: WKWebView!
    
    var detailPresenter: DetailPresenter?
    var content: String?
    var link: String?
    var baseURL: URL?
    
    override func viewDidLoad() {
        super.viewDidLoad()
        let webConfiguration = WKWebViewConfiguration()
        webView = WKWebView(frame: .zero, configuration: webConfiguration)
        webView.uiDelegate = self
        view = webView
        tabBarController?.tabBar.isHidden = true
        
        detailPresenter = DetailPresenter(detailVCCallBack: self)
        detailPresenter?.getStoryDetail(link: link!)
    }
    
    // MARK: - Methods
    func loadData() {
        webView.loadHTMLString(content!, baseURL: URL(string: "\(UrlUtils.StoryDetails)?url=\(link!)"))
    }
    
    override func viewWillDisappear(_ animated: Bool) {
                tabBarController?.tabBar.isHidden = false
    }
}

//MARK: - Receive data from presenter
extension WebViewController: DetailVCDelegate {
    func dataCallBack(content: String) {
        
       self.content = content
       loadData()
    }
}
