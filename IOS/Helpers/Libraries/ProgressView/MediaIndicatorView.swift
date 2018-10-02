//
//  MediaIndicatorView.swift
//  CHC
//
//  Created by UltraHigh on 8/15/18.
//

import UIKit

class MediaIndicatorView: UIView {
    
    //MARK:- SUPPORT VARIABLES
    private var allowAnimate: Bool = false
    private var stick1 = UIView()
    private var stick2 = UIView()
    private var stick3 = UIView()
    private var stick4 = UIView()
    private var stick5 = UIView()
    private let sizeAnimation = CABasicAnimation(keyPath: "bounds.size.height")
    private let sizeAnimation2 = CABasicAnimation(keyPath: "bounds.size.height")
    
    //MARK:- Custom color
    @IBInspectable var indicatorColor: UIColor {
        get {
            return self.color
        }
        set {
            self.color = newValue
        }
    }
    
    var color = UIColor.nothingMedia {
        didSet {
            stick1.backgroundColor = color
            stick2.backgroundColor = color
            stick3.backgroundColor = color
            stick4.backgroundColor = color
            stick5.backgroundColor = color
        }
    }
    
    //MARK:- Init
    override init(frame: CGRect) {
        super.init(frame: frame)
        self.backgroundColor = UIColor.clear
        self.addSubview(stick1)
        self.addSubview(stick2)
        self.addSubview(stick3)
        self.addSubview(stick4)
        self.addSubview(stick5)
        toBaseState()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
    }
    
    //MARK: - Config
    
    func toBaseState() {
        configStick(stick1, index: 0)
        configStick(stick2, index: 1)
        configStick(stick3, index: 2)
        configStick(stick4, index: 3)
        configStick(stick5, index: 4)
        allowAnimate = false
    }
    
    private func configStick(_ stick: UIView, index: Int) {
        stick.layer.removeAllAnimations()
        stick.backgroundColor = self.color
        
        if index == 0 || index == 4 {
            stick.frame.size = CGSize(width: frame.width / 9, height: frame.height)
        } else if index == 2 {
            stick.frame.size = CGSize(width: frame.width / 9, height: frame.height / 3)
        } else {
            stick.frame.size = CGSize(width: frame.width / 9, height: frame.height * 2 / 3)
        }
        
        stick.frame.origin.x = CGFloat(index*2) / 9 * self.frame.height
        stick.center.y = frame.height / 2
        stick.backgroundColor = self.color
        stick.layer.cornerRadius = stick.frame.width / 2
        stick.layoutIfNeeded()
    }
    
    func startAnimate() {
        toBaseState()
        allowAnimate = true
        animate(view: stick1, duration: 0)
        animate(view: stick2, duration: 0.25)
        animate(view: stick3, duration: 0.5)
        animate(view: stick4, duration: 0.25)
        animate(view: stick5, duration: 0)
    }
    
    private func animate(view: UIView, duration: Double) {
        view.layer.removeAllAnimations()
        //Beginning animate
        CATransaction.begin()
        CATransaction.setAnimationDuration(duration)
        
        CATransaction.setAnimationTimingFunction(CAMediaTimingFunction(name: kCAMediaTimingFunctionEaseInEaseOut))
        
        sizeAnimation.fromValue = view.frame.size.height
        sizeAnimation.toValue = self.frame.size.height
        sizeAnimation.isRemovedOnCompletion = false
        
        CATransaction.setCompletionBlock {
            
            //Comming animate
            CATransaction.begin()
            CATransaction.setAnimationDuration(0.5)
            CATransaction.setAnimationTimingFunction(CAMediaTimingFunction(name: kCAMediaTimingFunctionEaseInEaseOut))
            
            self.sizeAnimation2.fromValue = self.frame.size.height
            self.sizeAnimation2.toValue = self.frame.size.height / 3
            self.sizeAnimation2.autoreverses = true
            self.sizeAnimation2.repeatCount = HUGE
            self.sizeAnimation2.isRemovedOnCompletion = false
            view.layer.add(self.sizeAnimation2, forKey: view.description)
            if !self.allowAnimate {
                self.toBaseState()
            }
            CATransaction.commit()
        }
        
        view.layer.add(sizeAnimation, forKey: nil)
        
        CATransaction.commit()
    }
}



















