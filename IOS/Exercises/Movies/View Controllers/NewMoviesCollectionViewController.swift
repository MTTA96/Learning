//
//  NewMoviesCollectionViewController.swift
//  Movies
//
//  Created by Anh Tran on 3/21/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

protocol NewStoriesVCDelegate {
    func dataCallBack (stories: [Story])
}

class NewMoviesCollectionViewController: UICollectionViewController {
    var newStoriesPresenter: NewStoriesPresenter?
    var stories: [Story] = []
    var footerView:CustomFooterView?
    var isLoading = false
    
    private let reuseIdentifier = "MovieCell"
    private let footerViewReuseIdentifier = "RefreshFooterView"

    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false
        newStoriesPresenter = NewStoriesPresenter(newStoriesVCCallBack: self)
        newStoriesPresenter?.getNewStories()

        // Register cell classes
        self.collectionView?.register(UINib.init(nibName: "CustomFooterView", bundle: nil), forSupplementaryViewOfKind: UICollectionElementKindSectionFooter, withReuseIdentifier: footerViewReuseIdentifier)
        self.collectionView!.register(UINib.init(nibName: "MovieCollectionViewCell", bundle: nil), forCellWithReuseIdentifier: reuseIdentifier)
        
        //Refresh data on top
        let refreshControlView = UIRefreshControl()
        self.collectionView!.alwaysBounceVertical = true
        refreshControlView.tintColor = .white
        refreshControlView.addTarget(self, action: #selector(myRefreshMethod), for: .valueChanged)
        self.collectionView!.refreshControl = refreshControlView
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Actions

    @IBAction func myRefreshMethod() {
        newStoriesPresenter?.currentPage = 1
        newStoriesPresenter?.getNewStories()
        stories = []
    }

    // MARK: Data Source

    override func numberOfSections(in collectionView: UICollectionView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of items
        return stories.count
    }

    override func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! MovieCollectionViewCell
        
        guard stories.count != 0 else { return cell }
        let story = stories[indexPath.row]
        cell.binData(title: story.title!, urlImage: story.image!)
        // Configure the cell
        return cell
    }
    
    override func collectionView(_ collectionView: UICollectionView, didSelectItemAt indexPath: IndexPath) {
        let detailVC = storyboard?.instantiateViewController(withIdentifier: "DetailViewController") as! DetailViewController
        detailVC.story = stories[indexPath.row]
        navigationController?.pushViewController(detailVC, animated: true)
    }
}

// MARK: - DelegateFlowLayout
extension NewMoviesCollectionViewController: UICollectionViewDelegateFlowLayout {
 
    // MARK: - Methods
    
    func calculatePullRatio(scrollView: UIScrollView) -> Float {
        let threshold   = 100.0 ;
        let contentOffset = scrollView.contentOffset.y;
        let contentHeight = scrollView.contentSize.height;
        let diffHeight = contentHeight - contentOffset;
        let frameHeight = scrollView.bounds.size.height;
        var triggerThreshold  = Float((diffHeight - frameHeight))/Float(threshold);
        triggerThreshold   =  min(triggerThreshold, 0.0)
        
        return min(fabs(triggerThreshold), 1.5);
    }
    
    func calculatePullHeight(scrollView: UIScrollView) -> CGFloat{
        let contentOffset = scrollView.contentOffset.y;
        let contentHeight = scrollView.contentSize.height;
        let diffHeight = contentHeight - contentOffset;
        let frameHeight = scrollView.bounds.size.height;
        
        return fabs(diffHeight - frameHeight);
    }
    
    // MARK: - Data Source
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, sizeForItemAt indexPath: IndexPath) -> CGSize {
        return CGSize(width: self.view.frame.size.width*0.4, height:  self.view.frame.size.height*0.4)
    }
    
    func collectionView(_ collectionView: UICollectionView, layout collectionViewLayout: UICollectionViewLayout, referenceSizeForFooterInSection section: Int) -> CGSize {
        if isLoading {
            return CGSize.zero
        }
        return CGSize(width: collectionView.bounds.size.width, height: 50)
    }
    
    override func collectionView(_ collectionView: UICollectionView, viewForSupplementaryElementOfKind kind: String, at indexPath: IndexPath) -> UICollectionReusableView {
        if kind == UICollectionElementKindSectionFooter {
            let aFooterView = collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: footerViewReuseIdentifier, for: indexPath) as! CustomFooterView
            self.footerView = aFooterView
            return aFooterView
        } else {
            let headerView = collectionView.dequeueReusableSupplementaryView(ofKind: kind, withReuseIdentifier: footerViewReuseIdentifier, for: indexPath)
            return headerView
        }
    }
    
    override func collectionView(_ collectionView: UICollectionView, willDisplaySupplementaryView view: UICollectionReusableView, forElementKind elementKind: String, at indexPath: IndexPath) {
        if elementKind == UICollectionElementKindSectionFooter {
            self.footerView?.prepareInitialAnimation()
        }
    }
    
    override func collectionView(_ collectionView: UICollectionView, didEndDisplayingSupplementaryView view: UICollectionReusableView, forElementOfKind elementKind: String, at indexPath: IndexPath) {
        if elementKind == UICollectionElementKindSectionFooter {
            self.footerView?.stopAnimate()
        }
    }
    
    override func scrollViewDidScroll(_ scrollView: UIScrollView) {
        let pullRatio = calculatePullRatio(scrollView: scrollView)
        
        self.footerView?.setTransform(inTransform: CGAffineTransform.identity, scaleFactor: CGFloat(pullRatio))
        if pullRatio >= 1.5 {
            self.footerView?.animateFinal()
        } else if pullRatio >= 0.5 {
            self.footerView?.refreshControlIndicator.color = .white
        }
        if pullRatio == 0.49 {
            self.footerView?.refreshControlIndicator.color = .clear
        }
        
        print("pullRation:\(pullRatio)")
    }
    
    override func scrollViewWillBeginDecelerating(_ scrollView: UIScrollView) {
        let pullHeight = calculatePullHeight(scrollView: scrollView)
        if pullHeight > 55.0 && pullHeight <= 285.0
        {
            footerView?.sizeThatFits(CGSize(width: collectionView!.bounds.size.width, height: 60))
        }
    }
    
    //compute the offset and call the load method
    override func scrollViewWillEndDragging(_ scrollView: UIScrollView, withVelocity velocity: CGPoint, targetContentOffset: UnsafeMutablePointer<CGPoint>) {
        let pullHeight = calculatePullHeight(scrollView: scrollView)
        print("pullHeight:\(pullHeight)");
        
        if pullHeight >= 150.0 && pullHeight <= 285.0
        {
            if (self.footerView?.isAnimatingFinal)! {
                print("load more trigger")
                self.isLoading = true
                self.footerView?.startAnimate()
                newStoriesPresenter?.getNewStories()
                self.isLoading = false
            }
        }
    }
}

// MARK: - NewMoviesDelegate

extension NewMoviesCollectionViewController: NewStoriesVCDelegate {
    func dataCallBack(stories: [Story]) {
        
        self.stories.append(contentsOf: stories)
        collectionView?.refreshControl?.endRefreshing()
        collectionView?.reloadData()
    }
}
