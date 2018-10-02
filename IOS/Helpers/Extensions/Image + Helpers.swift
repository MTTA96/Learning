//
//  Image + Helpers.swift
//  CHC
//
//  Created by Anh Tran on 7/9/18.
//  Copyright © 2018 Apps Cyclone. All rights reserved.
//

import Foundation
import UIKit
import ImageIO

extension UIImageView {
    
    func loadImageFrom(urlString: String?, placeholderImg: UIImage? = UIImage(), isAnimate: Bool = false) {

        guard let urlString = urlString?.replacingOccurrences(of: " ", with: "%20") else { return }
        
        self.alpha = isAnimate ? (self.image == nil ? 0 : 1) : 1
        
        self.sd_setImage(with: URL(string: urlString), placeholderImage: placeholderImg) { (img, error, cache, url) in
            
            if isAnimate {
                UIView.animate(withDuration: 0.3, animations: {
                    self.alpha = 1
                })
            }
        }
        
    }
    
}

extension UIImage {
    
    func rotate(radians: Float) -> UIImage? {
        var newSize = CGRect(origin: CGPoint.zero, size: self.size).applying(CGAffineTransform(rotationAngle: CGFloat(radians))).size
        // Trim off the extremely small float value to prevent core graphics from rounding it up
        newSize.width = floor(newSize.width)
        newSize.height = floor(newSize.height)
        
        UIGraphicsBeginImageContextWithOptions(newSize, true, self.scale)
        let context = UIGraphicsGetCurrentContext()!
        
        // Move origin to middle
        context.translateBy(x: newSize.width/2, y: newSize.height/2)
        // Rotate around middle
        context.rotate(by: CGFloat(radians))
        
        self.draw(in: CGRect(x: -self.size.width/2, y: -self.size.height/2, width: self.size.width, height: self.size.height))
        
        let newImage = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()
        
        return newImage
    }

    func cropToBounds(width: Double = 300, height: Double = 300) -> UIImage {
        
        let cgimage = self.cgImage!
        let contextImage: UIImage = UIImage(cgImage: cgimage)
        let contextSize: CGSize = contextImage.size
        var posX: CGFloat = 0.0
        var posY: CGFloat = 0.0
        var cgwidth: CGFloat = CGFloat(width)
        var cgheight: CGFloat = CGFloat(height)
        
        // See what size is longer and create the center off of that
        if contextSize.width > contextSize.height {
            posX = ((contextSize.width - contextSize.height) / 2)
            posY = 0
            cgwidth = contextSize.height
            cgheight = contextSize.height
        } else {
            posX = 0
            posY = ((contextSize.height - contextSize.width) / 2)
            cgwidth = contextSize.width
            cgheight = contextSize.width
        }
        
        let rect: CGRect = CGRect(x: posX, y: posY, width: cgwidth, height: cgheight)
        
        // Create bitmap image from context using the rect
        let imageRef: CGImage = cgimage.cropping(to: rect)!
        
        // Create a new image based on the imageRef and rotate back to the original orientation
        let image: UIImage = UIImage(cgImage: imageRef, scale: self.scale, orientation: self.imageOrientation)
        
        return image
        
    }
    
    func resizeImage(targetSize:CGSize = CGSize(width: 300, height: 300)) -> UIImage? {
        let originalSize = self.size
        
        let widthRatio = targetSize.width / originalSize.width
        let heightRatio = targetSize.height / originalSize.height
        
        let ratio = min(widthRatio, heightRatio)
        
        let newSize = CGSize(width: originalSize.width * ratio, height: originalSize.height * ratio)
        
        // preparing rect for new image size
        let rect = CGRect(x: 0, y: 0, width: newSize.width, height: newSize.height)
        
        // Actually do the resizing to the rect using the ImageContext stuff
        UIGraphicsBeginImageContextWithOptions(newSize, false, UIScreen.main.scale)
        self.draw(in: rect)
        
        let newImage = UIGraphicsGetImageFromCurrentImageContext()
        UIGraphicsEndImageContext()
        
        return newImage
    }
    
}

struct ImageHeaderData {
    
    static var PNG: [UInt8] = [0x89]
    static var JPEG: [UInt8] = [0xFF]
    static var GIF: [UInt8] = [0x47]
    static var TIFF_01: [UInt8] = [0x49]
    static var TIFF_02: [UInt8] = [0x4D]
    
}

enum ImageFormat {
    case Unknown, PNG, JPEG, GIF, TIFF
}


extension NSData {
    
    var imageFormat: ImageFormat {
        var buffer = [UInt8](repeating: 0, count: 1)
        self.getBytes(&buffer, range: NSRange(location: 0,length: 1))
        if buffer == ImageHeaderData.PNG
        {
            return .PNG
        } else if buffer == ImageHeaderData.JPEG
        {
            return .JPEG
        } else if buffer == ImageHeaderData.GIF
        {
            return .GIF
        } else if buffer == ImageHeaderData.TIFF_01 || buffer == ImageHeaderData.TIFF_02{
            return .TIFF
        } else{
            return .Unknown
        }
    }
    
}
