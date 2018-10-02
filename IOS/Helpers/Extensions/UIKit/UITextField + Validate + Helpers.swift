//
//  UITextFieldExtension.swift
//
//  Copyright © 2017 Apps Cyclone. All rights reserved.
//

import UIKit

extension UITextField{
    
    func hasWhiteSpaceText() -> Bool{
        if !self.hasText{
            return true
        }
        return (self.text?.trimmingCharacters(in: CharacterSet.whitespaces) == "")
    }
    
    func isValidEmail()-> Bool{
        
        if self.text!.count > 254 {
            return false
        }
        
        let emailRegEx = "[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}"
        let email = NSPredicate(format:"SELF MATCHES %@", emailRegEx)
        return email.evaluate(with: self.text)
    }
    
    func setCursorPosition(at index: Int) {
        guard let positionToSet = self.position(from: self.beginningOfDocument, offset: index) else { return }
        self.selectedTextRange = self.textRange(from: positionToSet, to: positionToSet)
    }
    
    func formatPlaceholer(){
        self.addBottomBorderNoRatio(color: UIColor.bottomBorder)
        self.attributedPlaceholder = NSAttributedString(string:"0.00", attributes: [NSAttributedStringKey.foregroundColor: UIColor.bottomBorder])
    }
}
