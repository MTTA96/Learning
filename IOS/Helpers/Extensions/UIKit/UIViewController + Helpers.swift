//
//  UIViewController + Extension.swift
//  CHC
//
//  Created by Phong Cao on 6/11/18.
//  Copyright © 2018 Apps Cyclone. All rights reserved.
//

import UIKit

extension UIViewController {
 
    func addChildVC(_ vc: UIViewController, into containerView: UIView){
        self.addChildViewController(vc)
        vc.view.frame = containerView.bounds
        containerView.addSubview(vc.view)
        vc.didMove(toParentViewController: self)
    }
    
    func initWithNavigation() -> UINavigationController {
        return UINavigationController(rootViewController: self)
    }

    func hideKeyboardWhenTappedAround() {
        let tap: UITapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(UIViewController.HdismissKeyboard))
        tap.cancelsTouchesInView = false
        view.addGestureRecognizer(tap)
    }
    
    @objc private func HdismissKeyboard() {
        view.endEditing(true)
    }
    
    func presentWithPushAnimate(_ viewController: UIViewController) {
        
        let transition = CATransition()
        transition.duration = 0.25
        transition.type = kCATransitionMoveIn
        transition.subtype = kCATransitionFromRight
        transition.timingFunction = CAMediaTimingFunction(name:kCAMediaTimingFunctionEaseInEaseOut)
        if let window = self.view.window {
            window.layer.add(transition, forKey: kCATransition)
        }
        self.present(viewController, animated: false)
    }
    
    func dismissWithPopAnimate() {
        
        let transition = CATransition()
        transition.duration = 0.25
        transition.type = kCATransitionReveal
        transition.subtype = kCATransitionFromLeft
        transition.timingFunction = CAMediaTimingFunction(name:kCAMediaTimingFunctionEaseInEaseOut)
        if let window = self.view.window {
            window.layer.add(transition, forKey: kCATransition)
        }
        self.dismiss(animated: false)
    }
    
    func presentAudioPlayer() {
        
        DispatchQueue.main.asyncAfter(deadline: .now() + 1) {
            let audioPlayer = HeaderAudioPlayerVC()
            self.present(audioPlayer, animated: false, completion: nil)
        }
        
    }
    
    func notifyMoveToMyPlaylistAction() {
        DispatchQueue.main.asyncAfter(deadline: .now() + 0.1) {
            NotificationCenter.default.post(name: .moveToMyPlaylist, object: nil)
        }
    }
    
}

extension UINavigationController {
    
    func pop(to vc: AnyClass) {
        
        for controller in viewControllers {
            if controller.isKind(of: vc) {
                popToViewController(controller, animated: true)
                break
            }
        }
        
    }
    
}
