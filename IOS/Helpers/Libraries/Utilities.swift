//
//  Utilities.swift
//  CHC
//
//  Created by Hai Nguyen on 6/26/18.
//  Copyright © 2018 Apps Cyclone. All rights reserved.
//

import Foundation

class Utilities {
    
    static func showAboutPopup(viewController: UIViewController, title: String, message: String){
        let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
        let alertActionOk = UIAlertAction(title: "OK", style: .default) { (action) in
            alertController.dismiss(animated: false, completion: nil)
//            viewController.dismiss(animated: false, completion: nil)
        }
        alertController.addAction(alertActionOk)
        viewController.present(alertController, animated: false, completion: nil)
    }
    
    static func showAlert(_ target: UIViewController,title: String, messages: String, cancelTitle: String, action: UIAlertAction? = nil, cancelAction: ((UIAlertAction) -> Void)? = nil) {
        let alert = UIAlertController(title: title, message: messages, preferredStyle: .alert)
        alert.addAction(UIAlertAction(title: cancelTitle, style: .cancel, handler: cancelAction))
        if let action = action {
            alert.addAction(action)
        }
        target.present(alert, animated: true, completion: nil)
    }
    
    static func countDaysFromNowTo(date: Date) -> String {
        
        let calendar = NSCalendar.current
        
        let components = calendar.dateComponents([.second, .minute, .hour, .day, .month, .year], from: Date(), to: date)
        
        if let year = components.year, year != 0 {
            if abs(year) >= 2 {
                return "\(abs(year)) years ago"
            }
            return "\(abs(year)) year ago"
        }
        
        if let month = components.month, month != 0 {
            if abs(month) >= 2 {
                return "\(abs(month)) months ago"
            }
            return "\(abs(month)) month ago"
        }
        
        if let day = components.day, day != 0 {
            if abs(day) >= 2 {
                return "\(abs(day)) days ago"
            }
            return "\(abs(day)) day ago"
        }
        
        if let hour = components.hour, hour != 0 {
            if abs(hour) >= 2 {
                return "\(abs(hour)) hours ago"
            }
            return "\(abs(hour)) hour ago"
        }
        
        if let minute = components.minute, minute != 0 {
            if abs(minute) >= 2 {
                return "\(abs(minute)) minutes ago"
            }
            return "\(abs(minute)) minute ago"
        }
        
        if let second = components.second, second != 0 {
            if abs(second) >= 2 {
                return "\(abs(second)) seconds ago"
            }
            return "\(abs(second)) second ago"
        }
        
        return "Just now"
    }
    
    static func getRowCount(withData tags: [String], containerWidth: CGFloat, textFont: UIFont) -> Int {
        
        if tags.count <= 0 {
            return 0
        } else {
            var totalWidthPerRow: CGFloat = 0
            var rowCounts = 1
            
            for tag in tags {
                let dynamicCellWidth: CGFloat = (tag).width(withConstrainedHeight: 23, font: textFont) + 35
                
                totalWidthPerRow += dynamicCellWidth + CGFloat(5)
                if totalWidthPerRow > containerWidth {
                    rowCounts += 1
                    totalWidthPerRow = dynamicCellWidth + CGFloat(5)
                }
            }
            
            return rowCounts
        }
    }
    
    static func getRowCount(withData eventTypes: [EventType], containerWidth: CGFloat, textFont: UIFont) -> Int {
        
        if eventTypes.count <= 0 {
            return 0
        } else {
            var totalWidthPerRow: CGFloat = 0
            var rowCounts = 1
            
            for eventType in eventTypes {
                let dynamicCellWidth: CGFloat = (eventType.title ?? "").width(withConstrainedHeight: 23, font: textFont) + 35
                totalWidthPerRow += dynamicCellWidth + CGFloat(5)
                if totalWidthPerRow > containerWidth {
                    rowCounts += 1
                    totalWidthPerRow = dynamicCellWidth + CGFloat(5)
                }
            }
            
            return rowCounts
        }
    }
    
    static func getCardIcon(cardType: String) -> UIImage {

        if cardType.caseInsensitiveCompare("visa") == .orderedSame {
            return #imageLiteral(resourceName: "visaCard_ic")
        } else if cardType.caseInsensitiveCompare("mastercard") == .orderedSame {
            return #imageLiteral(resourceName: "masterCard_ic")
        } else {
            return UIImage()
        }
    }
    
    static func userAvatar() -> String {
        if let userInfo = User.loadFromKeychain() {
            if let userAvatar = userInfo.profile?.photoUrl {
                return userAvatar
            }
        }
        
        return ""
    }
    
    static func getImageWidthHeight(with data: Image?) -> (width: CGFloat, height: CGFloat) {
        
        guard let image = data, let imgWidth = image.width, let imgHeight = image.height
            else {
                return (ScreenSize.SCREEN_WIDTH - 40, 188)
        }
        
        return (CGFloat(imgWidth), CGFloat(imgHeight))
    }
}

extension Double {
    
    func convertToTime() -> String {
        
        if self.isNaN { return "00:00" }
        let minute = Int(self) / 60
        let second = Int(self) - minute * 60
        let space = second < 10 ? "0" : ""
        return "\(minute):\(space)\(second)"
        
    }
    
}


