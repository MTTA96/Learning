//
//  String + Extension.swift
//  CHC
//
//  Created by Hai Nguyen on 6/19/18.
//  Copyright © 2018 Apps Cyclone. All rights reserved.
//

import Foundation
import SwiftDate

extension String {
    
    func formatAttributedString(lineSpacing: CGFloat) -> NSMutableAttributedString {
        
        let attributedString = NSMutableAttributedString(string: self)
        let paragraphStyle = NSMutableParagraphStyle()
        paragraphStyle.lineSpacing = lineSpacing
        paragraphStyle.paragraphSpacing = 5
        attributedString.addAttribute(.paragraphStyle, value:paragraphStyle, range:NSMakeRange(0, attributedString.length))
        
        return attributedString
        
    }
    
    
    var html2Attributed: NSAttributedString? {
        
        do {
            guard let data = data(using: String.Encoding.utf8) else {
                return nil
            }
            return try NSAttributedString(data: data,
                                          options: [.documentType: NSAttributedString.DocumentType.html,
                                                    .characterEncoding: String.Encoding.utf8.rawValue],
                                          documentAttributes:
                nil)
        } catch {
            print("error: ", error)
            return nil
        }
        
    }
    
    func parseHTMLString(fontFamily: String, fontSize: Int) -> NSAttributedString {
        
        let styleAndSize = "<style>* { font-family: \(fontFamily); font-size: \(fontSize) }</style>"
        let htmlData = NSString(string: styleAndSize + self).data(using: String.Encoding.unicode.rawValue)
        let options = [NSAttributedString.DocumentReadingOptionKey.documentType: NSAttributedString.DocumentType.html]
        let attributedString = try! NSAttributedString(data: htmlData!, options: options, documentAttributes: nil)
        
        return attributedString
        
    }
    
    // Convert from Date to String
    
    func convertToDateString(from format: String?  = "yyyy-MM-dd'T'HH:mmZ", toFormat: String? = "MMM d, YYYY")  -> String {
        
        if let result = self.toISODate() {
            //print(result.convertTo(region: Region.current).toFormat(toFormat!))
            return result.convertTo(region: Region.current).toFormat(toFormat!)
        }
        
        print("String + helpers: convertTimeWithFormat - Can't format date with input: \(self)")
        return " "
        
    }
    
    func toDate(dateFormat: String = "yyyy-MM-dd'T'HH:mmZ") -> Date? {
        
        guard let date = self.toISODate() else {
            print("String + helpers: toDate - Can't format date with input: \(self)")
            return nil
        }
        
        //print(self.toISODate()?.convertTo(region: Region.current))
        return date.convertTo(region: Region.current).date
        
    }
    
    func toInterval() -> Int{
        let date = self.toDate()
        let interval = date?.timeIntervalSince1970
        return Int(interval!)
    }
    
    func isBlank() -> Bool {
        return self.trimmingCharacters(in: .whitespaces).count == 0
    }
    
    func height(withConstrainedWidth width: CGFloat, font: UIFont) -> CGFloat {
        let constraintRect = CGSize(width: width, height: .greatestFiniteMagnitude)
        let boundingBox = self.boundingRect(with: constraintRect, options: .usesLineFragmentOrigin, attributes: [.font: font], context: nil)
        
        return ceil(boundingBox.height)
    }
    
    func width(withConstrainedHeight height: CGFloat, font: UIFont) -> CGFloat {
        let constraintRect = CGSize(width: .greatestFiniteMagnitude, height: height)
        let boundingBox = self.boundingRect(with: constraintRect, options: .usesLineFragmentOrigin, attributes: [.font: font], context: nil)
        
        return ceil(boundingBox.width)
    }
    
    func getIndex(_ index: Int) -> String.Index {
        return self.index(self.startIndex, offsetBy: index)
    }
    
    mutating func insert(string: String, at index: Int) {
        self.insert(contentsOf: string, at: self.getIndex(index))
    }
    
    func getCharacter(at index: Int) -> Character {
        return self[self.getIndex(index)]
    }
    
    func isMoreThan2Decimal() -> Bool{
        var number: String = self
        if number.range(of: ".") != nil {
            let afterDot = number.components(separatedBy: ".")[1]
            if afterDot.count > 2 {
                return true
            }
        }
        return false
    }
    
    func get2Decimal() -> String {
        
        var number: String = self
        if number.range(of: ".") != nil {
            let afterDot = number.components(separatedBy: ".")[1]
            let beforeDot = number.components(separatedBy: ".")[0]
            if afterDot.count > 2 {
                number = "\(beforeDot).\(afterDot.prefix(2))"
            }
        }
        return number
    }
    
    func addZeroFor2Decimal() -> String {
        
        guard self != "" && Double(self) != 0 else { return "" }
        var number: String = self
        if number.range(of: ".") != nil {
            
            let beforeDot = number.components(separatedBy: ".")[0]
            let afterDot = number.components(separatedBy: ".")[1]
            
            if Double(beforeDot) == 0 || beforeDot == "" {
                return ""
            }
            
            if afterDot.count == 1 {
                number = number + "0"
            } else if afterDot.count == 0 {
                number = number + "00"
            }
        } else {
            number = number + ".00"
        }
        return number
    }
}




