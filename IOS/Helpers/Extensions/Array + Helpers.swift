//
//  Array + Extension.swift
//  CHC
//
//  Created by Anh Tran on 6/26/18.
//  Copyright © 2018 Apps Cyclone. All rights reserved.
//

import Foundation

extension Array where Element: Equatable {
    
    mutating func removeDuplicates() {
        var result = [Element]()
        for value in self {
            if !result.contains(value) {
                result.append(value)
            }
        }
        self = result
    }
    
    mutating func shuffled() -> [Element] {
        
        if self.count < 2 { return self }
        
        var shuffleArray = self
        
        for index in 0..<self.count {
            let randomIndex = Int(arc4random_uniform(UInt32(self.count - index))) + index
            shuffleArray.swapAt(index, randomIndex)
        }
        
        return shuffleArray
        
    }
    
}
