//
//  MenuTableViewCell.swift
//  Restaurants
//
//  Created by Anh Tran on 4/26/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

class MenuTableViewCell: UITableViewCell {
    @IBOutlet weak var pitureImageView: UIImageView!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var priceLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

    func bindData(menuItem: MenuItem!) {
        pitureImageView?.downloadedFrom(link: menuItem.pictureUrl!)
        nameLabel.text = menuItem.name
        priceLabel.text = formatAmount(amount: menuItem.price!)
    }
    
    func formatAmount(amount: Int) -> String {
        let formatter = NumberFormatter()
        formatter.locale = Locale(identifier: "vi_VN")
        formatter.numberStyle = .currency
        if let formattedAmount = formatter.string(from: amount as NSNumber) {
            return formattedAmount
        }
        
        return ""
    }
}
