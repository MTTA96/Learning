//
//  ProgressView.swift
//
//  Copyright © 2018. All rights reserved.
//

import UIKit

class ProgressView {
    
    static let shared = ProgressView()
    var isShowing = false
    var indicator = HTIndicatorView(frame: CGRect(x: 0,y: 0,width: 60,height: 60))
    var containerView = UIView()
    var timer: Timer = Timer()
    
    private init() {}
    
    func show(into view: UIView) {
        
        view.layoutIfNeeded()
        
        containerView.frame = view.frame
        containerView.center = view.center
        containerView.backgroundColor = .clear
        
        indicator.center = containerView.center
        indicator.color = UIColor.appLightBlue
        indicator.startAnimate()
        containerView.addSubview(indicator)
        
        view.addSubview(containerView)
        
    }
  
    func show() {
        
        guard let window = UIApplication.shared.keyWindow else { return }

        if !self.isShowing {
            self.isShowing = true
                
            guard self.isShowing else { return }
            
            self.containerView.frame = window.frame
            self.containerView.center = window.center
            self.containerView.backgroundColor = .clear
            
            self.indicator.center = self.containerView.center
            self.indicator.color = UIColor.appLightBlue
            self.indicator.startAnimate()
            self.containerView.addSubview(self.indicator)
            
            window.addSubview(self.containerView)
        }
        
    }
    
    @objc func hide() {
        
        DispatchQueue.main.async {
            self.isShowing = false
            self.timer.invalidate()
            self.indicator.removeFromSuperview()
            self.containerView.removeFromSuperview()
        }
        
    }
    
}
