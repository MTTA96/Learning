//
//  MapViewController.swift
//  Restaurants
//
//  Created by Anh Tran on 4/26/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit
import GoogleMaps
import GooglePlaces
import SafariServices

class MapViewController: UIViewController {

    @IBOutlet weak var mapView: GMSMapView!
    @IBOutlet weak var addressLabel: UILabel!
    
    let locationManager = CLLocationManager()
    var restaurantLocation: CLLocationCoordinate2D?
    var defaultZoom: Float = 17.0
    
    // Marker info window
    private var infoWindow = MapMarkerWindow()
    fileprivate var locationMarker : GMSMarker? = GMSMarker()
    
    var restaurant: Restaurant?
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Request permission
        locationManager.requestWhenInUseAuthorization()
        locationManager.requestAlwaysAuthorization()
        
        // Configure map
        mapView.delegate = self
        self.infoWindow = loadNiB()
        
        tabBarController?.tabBar.isHidden = true
        loadData()
    }

    override func viewWillDisappear(_ animated: Bool) {
        tabBarController?.tabBar.isHidden = false
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func loadData() {
        addressLabel.text = restaurant?.address
        // Convert success
        self.restaurantLocation = CLLocationCoordinate2D(latitude: (restaurant?.lat)!, longitude: (restaurant?.long)!)
        
        // Create marker
        let marker = GMSMarker()
        marker.position = CLLocationCoordinate2D(latitude: restaurantLocation!.latitude, longitude: restaurantLocation!.longitude)
        marker.userData = self.restaurant
        marker.map = self.mapView
        self.mapView.selectedMarker = marker
        
        // Handle camera
        self.mapView?.camera = GMSCameraPosition.camera(withTarget: restaurantLocation!, zoom: self.defaultZoom)
        
    }
    
    // MARK: - Actions
    @IBAction func didTapDirectionButton(_ sender: UIButton) {
 
        let stringUrl = "https://www.google.com/maps/dir/?api=1&origin=10.794684,106.662796&destination=\((restaurantLocation?.latitude)!),\((restaurantLocation?.longitude)!)&directionsmode=transit"
        //myString = myString.removingWhitespaces()
        //print(myString)
        if let url = URL.init(string: stringUrl) {
            
            let safariViewController = SFSafariViewController(url: url)
            present(safariViewController, animated: true,
                    completion: nil)
        }

    }
}
extension String {
    func removingWhitespaces() -> String {
        return components(separatedBy: .whitespaces).joined()
    }
}

// MARK: GMSMapViewDelegate

extension MapViewController: GMSMapViewDelegate {
    func loadNiB() -> MapMarkerWindow {
        let infoWindow = MapMarkerWindow.instanceFromNib() as! MapMarkerWindow
        return infoWindow
    }
    
    func mapView(_ mapView: GMSMapView, markerInfoWindow marker: GMSMarker) -> UIView? {
        locationMarker = marker
        infoWindow.removeFromSuperview()
        infoWindow = loadNiB()

        // Pass the spot data to the info window, and set its delegate to self
        infoWindow.data = restaurantLocation
        
        // Configure UI properties of info window
        infoWindow.alpha = 0.9
        infoWindow.layer.cornerRadius = 12
        infoWindow.layer.borderWidth = 2
        infoWindow.layer.borderColor = #colorLiteral(red: 1.0, green: 1.0, blue: 1.0, alpha: 1.0)
        
        infoWindow.banner.downloadedFrom(link: (restaurant?.banner)!)
        infoWindow.nameLabel.text = restaurant?.name
        infoWindow.availibilityLabel.text = "\((restaurant?.open)!) - \((restaurant?.close)!)"
        
        // Offset the info window to be directly above the tapped marker
        // infoWindow.center = mapView.projection.point(for: location)
        // infoWindow.center.y = infoWindow.center.y - 50
        // self.view.addSubview(infoWindow)
        
        return infoWindow
    }
    
    func mapView(_ mapView: GMSMapView, didChange position: GMSCameraPosition) {
        if (locationMarker != nil){
            guard let location = locationMarker?.position else {
                print("locationMarker is nil")
                return
            }
            infoWindow.center = mapView.projection.point(for: location)
            infoWindow.center.y = infoWindow.center.y - 50
        }
    }
    
    func mapView(_ mapView: GMSMapView, didTapAt coordinate: CLLocationCoordinate2D) {
        infoWindow.removeFromSuperview()
    }
}
