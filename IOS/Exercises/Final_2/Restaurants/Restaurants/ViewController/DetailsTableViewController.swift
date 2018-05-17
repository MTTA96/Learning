//
//  DetailsTableViewController.swift
//  Restaurants
//
//  Created by Anh Tran on 4/20/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit

class DetailsTableViewController: UITableViewController {
    @IBOutlet weak var bannerImageView: UIImageView!
    @IBOutlet weak var ratingLabel: UILabel!
    @IBOutlet weak var nameLabel: UILabel!
    @IBOutlet weak var addressLabel: UILabel!
    @IBOutlet weak var menuLabel: UILabel!
    @IBOutlet weak var restaurantStateLabel: UILabel!
    @IBOutlet weak var workingTime: UILabel!
    @IBOutlet weak var ratingBackground: UIView!
    @IBOutlet weak var currentUserRatingLabel: UILabel!
    @IBOutlet weak var ratingSlider: UISlider!
    
    var restaurant: Restaurant?
    var restaurantID: String?
    var resName: String?
    var imageUrl: String?
    let detailsPresenter = DetailsPresenter()
    
    override func viewDidLoad() {
        super.viewDidLoad()

        setUpView()

        loadData()
    }
    
    func setUpView() {
        // Configure table row
        tableView.rowHeight = UITableViewAutomaticDimension
        tableView.estimatedRowHeight = 70
        
        // Table header
        let screenWidth = UIScreen.main.bounds.width
        if screenWidth < 375 {
            tableView.tableHeaderView?.frame.size.height = 200
            bannerImageView.frame.size.height = 200
        }
        
        // Table refreshControlView
        let refreshControlView = UIRefreshControl()
        tableView.alwaysBounceVertical = true
        refreshControl?.tintColor = .white
        refreshControlView.addTarget(self, action: #selector(loadData), for: .valueChanged)
        tableView.refreshControl = refreshControlView
        
        // Rating slide
        ratingSlider.addTarget(self, action: #selector(ratingSliderValueChanged), for: UIControlEvents.allEvents)
        ratingSlider.maximumValue = 10
        ratingSlider.setValue(5, animated: false)
        
        // Rating label
        ratingBackground.layer.borderWidth = 1.5
        ratingBackground.layer.borderColor = #colorLiteral(red: 1.0, green: 1.0, blue: 1.0, alpha: 1.0)
        
        self.nameLabel.text = "Trà Sữa Gong Cha - 貢茶 - Hồ Tùng Mậu"
    }
    
    @IBAction func loadData() {
        
        // Get restaurant details
        detailsPresenter.getRestaurantDetail(restaurantID: restaurantID) { (error, restaurant) in
            if let error = error {
                print(error)
                return
            }
            
            self.restaurant = restaurant
            
            // Banner
            self.bannerImageView.downloadedFrom(link: (restaurant?.banner)!)
            
            // Rating
            if let rating = restaurant?.rating {
                self.ratingLabel.textColor = rating >= 5 ? #colorLiteral(red: 0.4666666687, green: 0.7647058964, blue: 0.2666666806, alpha: 1) : .red
                self.ratingLabel.text = "\(rating)"
            }
            
            // Name
            self.nameLabel.text = restaurant?.name
            
            // Working time
            if let openTime = restaurant?.open, let closeTime = restaurant?.close {
                if self.checkWorkingTime(openTime: openTime, closeTime: closeTime) {
                    self.restaurantStateLabel.textColor = #colorLiteral(red: 0.4666666687, green: 0.7647058964, blue: 0.2666666806, alpha: 1)
                    self.restaurantStateLabel.text = "Đang mở cửa"
                } else {
                    self.restaurantStateLabel.textColor = .red
                    self.restaurantStateLabel.text = "Đóng cửa"
                }
                self.workingTime.text = "\(openTime) - \(closeTime)"
            }
            
            // Address
            self.addressLabel.text = restaurant?.address
            
            // Menu
            self.menuLabel.text = restaurant?.price
            
            // Current user rating
            self.currentUserRatingLabel.text = "\(self.ratingSlider.value)"
            
            self.tableView.refreshControl?.endRefreshing()
        }
        
        // Get user rating point of the restaurant
        detailsPresenter.getRestaurantRatingState(restaurantID: restaurantID) { (error, ratingPoint) in
            if let error = error {
                print(error.localizedCapitalized)
                self.currentUserRatingLabel.text = "Chưa đánh giá"
                return
            }
            
            // If user's already rated this restaurant
            self.currentUserRatingLabel.text = "\(ratingPoint!)"
            self.ratingSlider.setValue(Float(ratingPoint!), animated: false)
        }
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func checkWorkingTime(openTime: String!, closeTime: String!) -> Bool {
        // Get current time
        let date = NSDate()
        let calendar = NSCalendar.current
        let comp = calendar.dateComponents([.hour, .minute], from: date as Date)
        let currentTimeInMinute = (comp.hour! * 60) + comp.minute!
        
        // Check open and close time
        let openTimeArr = openTime.split(separator: ":")
        let openTimeInMinute = (Int(openTimeArr[0])! * 60) + Int(openTimeArr[1])!
        
        let closeTimeArr = closeTime.split(separator: ":")
        let closeTimeInMinute = (Int(closeTimeArr[0])! * 60) + Int(closeTimeArr[1])!
        
        if currentTimeInMinute > openTimeInMinute && currentTimeInMinute < closeTimeInMinute  {
            return true
        }
        
        return false
    }
    
    // MARK: - Action
    @IBAction func ratingSliderValueChanged(sender: UISlider) {
        let value = Int(ratingSlider.value)
        currentUserRatingLabel.text = "\(value)"
        
    }

    @IBAction func submitRatingButtonTapped(_ sender: UIButton) {
        detailsPresenter.updateRatingPoint(restaurantID: restaurantID, point: Int(ratingSlider.value)) { error in
            let alert: UIAlertController = UIAlertController(title: nil, message: "", preferredStyle: UIAlertControllerStyle.alert)
            let action = UIAlertAction(title: "Ok", style: .default, handler: { handler in
                alert.dismiss(animated: true , completion: nil)
            })
            alert.addAction(action)
            if error == "Failed" {
                alert.message = "Không thể đánh giá"
            } else {
                alert.message = "Đánh giá thành công"
            }
            self.present(alert, animated: true, completion: nil)
        }
    }
    
    // MARK: table view data scourse
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let mainStoryboard = UIStoryboard(name: "Main", bundle: Bundle.main)
        switch indexPath {
            // Address selected
            case [1,0]:
                let mapVC = mainStoryboard.instantiateViewController(withIdentifier: "MapViewController") as! MapViewController
                mapVC.restaurant = restaurant
                mapVC.navigationItem.title = "Bản đồ"
                navigationController?.pushViewController(mapVC, animated: true)
                break
            // Menu selected
            case [1,1]:
                let menuVC = mainStoryboard.instantiateViewController(withIdentifier: "MenuViewController") as! MenuTableViewController
                menuVC.resID = restaurantID
                menuVC.navigationItem.title = "Thực đơn"
                navigationController?.pushViewController(menuVC, animated: true)
                break
            
            // Images selected
            case [1,2]:
                let imagesVC = mainStoryboard.instantiateViewController(withIdentifier: "ImagesCollectionView") as! ImagesCollectionViewController
                imagesVC.resId = restaurantID
                imagesVC.navigationItem.title = "Hình ảnh"
                navigationController?.pushViewController(imagesVC, animated: true)
                break
            default:
                break
        }
    }

}
