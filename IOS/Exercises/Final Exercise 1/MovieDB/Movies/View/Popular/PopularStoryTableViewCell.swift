//
//  PopularStoryTableViewCell.swift
//  Movies
//
//  Created by Anh Tran on 3/23/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit
import AlamofireImage

class PopularStoryTableViewCell: UITableViewCell {
    @IBOutlet weak var storyImageView: UIImageView!
    @IBOutlet weak var titleLabel: UILabel!
    @IBOutlet weak var viewsLabel: UILabel!
    @IBOutlet weak var descriptionLabel: UILabel!
    
    override func prepareForReuse() {
        super.prepareForReuse()
        
        storyImageView.af_cancelImageRequest()
        storyImageView.image = nil
    }
    
    func bindData(story: Story) {
        titleLabel.text = story.title
        viewsLabel.text = "Views: \(story.view ?? "0")"
        descriptionLabel.text = story.desc
        storyImageView.downloadedFrom(link: story.image!)
    }
}
