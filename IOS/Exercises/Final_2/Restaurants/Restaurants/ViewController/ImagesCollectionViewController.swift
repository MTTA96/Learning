//
//  ImagesCollectionViewController.swift
//  Restaurants
//
//  Created by Anh Tran on 4/26/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

private let reuseIdentifier = "ImageCell"

class ImagesCollectionViewController: UICollectionViewController {
    
    let imagesPresenter = ImagesPresenter()
    var imageList: Array<String> = []
    var resId: String?

    override func viewDidLoad() {
        super.viewDidLoad()

        // Register cell classes
        collectionView?.register(UINib.init(nibName: "ImageCollectionCell", bundle: nil), forCellWithReuseIdentifier: reuseIdentifier)
        
        // Configure RefreshControl
        let refreshControl = UIRefreshControl()
        refreshControl.tintColor = .white
        refreshControl.addTarget(self, action: #selector(loadData), for: .valueChanged)
        collectionView?.alwaysBounceVertical = true
        collectionView?.refreshControl = refreshControl
        
        configureCollectionView()
        loadData()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Methods
    
    @IBAction func loadData() {
        imagesPresenter.getImageList(resId: resId) { (error, images) in
            if let error = error {
                print(error)
                return
            }
            
            self.imageList = images!
            self.collectionView?.reloadData()
            self.collectionView?.refreshControl?.endRefreshing()
        }
    }
    
    // Configure cell size
    func configureCollectionView() {
        let screenWidth = UIScreen.main.bounds.width
        
        let layout: UICollectionViewFlowLayout = UICollectionViewFlowLayout()
        
        if screenWidth >= 414 {
            layout.itemSize = CGSize(width:  screenWidth/2.3, height:  self.view.frame.size.height*0.25)
        } else {
            if screenWidth >= 375 {
                layout.itemSize = CGSize(width:  screenWidth/2.3, height:  self.view.frame.size.height*0.2)
            } else {
                layout.itemSize = CGSize(width:  screenWidth/2.4, height:  self.view.frame.size.height*0.25)
            }
        }
        
        layout.sectionInset = UIEdgeInsets(top: 17, left: 15, bottom: 10, right: 15)
        layout.minimumInteritemSpacing = 10
        collectionView!.collectionViewLayout = layout
    }

    // MARK: UICollectionViewDataSource

    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }


    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of items
        return imageList.count
    }

    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! ImageCollectionViewCell
    
        // Configure the cell
        cell.bindData(imgUrl: imageList[indexPath.row])
        
        return cell
    }

}
