//
//  PeopleViewController.swift
//  MovieDB
//
//  Created by Anh Tran on 4/2/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

class PeopleViewController: UIViewController {
    
    @IBOutlet weak var collectionView: UICollectionView!
    
    let peoplePresenter = PeoplePresenter()
    var people: [Person] = []
    
    private let reuseIdentifier = "PersonCell"
    
    override func viewDidLoad() {
        super.viewDidLoad()

        collectionView.delegate = self
        collectionView.dataSource = self
        
        // Register cell classes
        self.collectionView!.register(UINib.init(nibName: "PeopleCollectionCell", bundle: nil), forCellWithReuseIdentifier: reuseIdentifier)
        
        //Refresh data on top
        let refreshControlView = UIRefreshControl()
        self.collectionView!.alwaysBounceVertical = true
        refreshControlView.tintColor = .white
        refreshControlView.addTarget(self, action: #selector(refreshData), for: .valueChanged)
        self.collectionView!.refreshControl = refreshControlView
        
        // Configure view
        configureCollectionView()
        loadData()
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Update
    
    func loadData() {
        peoplePresenter.getPeople() { (error, people) in
            guard let people = people?.people else {
                print(error!)
                return
            }
            self.people = people
            self.collectionView?.reloadData()
        }
    }
    
    // Configure cell size
    func configureCollectionView() {
        let screenWidth = UIScreen.main.bounds.width
        
        let layout: UICollectionViewFlowLayout = UICollectionViewFlowLayout()
        
        if screenWidth >= 414 {
            layout.itemSize = CGSize(width:  screenWidth/2.3, height:  self.view.frame.size.height*0.3)
        } else {
            if screenWidth >= 375 {
                layout.itemSize = CGSize(width:  screenWidth/2.3, height:  self.view.frame.size.height*0.2)
            } else {
                layout.itemSize = CGSize(width:  screenWidth/2.4, height:  self.view.frame.size.height*0.3)
            }
        }
        
        layout.sectionInset = UIEdgeInsets(top: 17, left: 0, bottom: 10, right: 0)
        layout.minimumInteritemSpacing = 10
        collectionView!.collectionViewLayout = layout
    }
    
    // MARK: - Actions
    
    @IBAction func refreshData() {
        peoplePresenter.getPeople() { (error, people) in
            guard let people = people?.people else {
                print(error!)
                return
            }
            self.people.append(contentsOf: people)
            self.collectionView?.reloadData()
            self.collectionView?.refreshControl?.endRefreshing()
        }
    }

}

// MARK: - UICollectionView

extension PeopleViewController: UICollectionViewDelegate, UICollectionViewDataSource {
    // MARK: Data Source
    
    func numberOfSections(in collectionView: UICollectionView) -> Int {
        // Return the number of sections
        return 1
    }
    
    func collectionView(_ collectionView: UICollectionView, numberOfItemsInSection section: Int) -> Int {
        // Return the number of items
        return people.count
    }
    
    func collectionView(_ collectionView: UICollectionView, cellForItemAt indexPath: IndexPath) -> UICollectionViewCell {
        let cell = collectionView.dequeueReusableCell(withReuseIdentifier: reuseIdentifier, for: indexPath) as! PeopleCollectionViewCell
        guard people.count > 0 else { return cell }
        let person = people[indexPath.row]
        
        if let name = person.name, let avatar = person.profile_path {
            cell.binData(title: name, urlImage: avatar)
        }
        
        //Load more people
        if people.count - indexPath.row == 4 {
            refreshData()
        }
        
        return cell
    }
    
}
