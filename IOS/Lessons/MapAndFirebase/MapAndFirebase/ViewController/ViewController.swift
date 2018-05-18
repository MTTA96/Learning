//
//  ViewController.swift
//  MapAndFirebase
//
//  Created by Anh Tran on 4/9/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit
import GoogleMaps
import GooglePlaces
import Firebase
import Crashlytics

class ViewController: UIViewController {

    var mapView: GMSMapView?
    let locationManager = CLLocationManager()
    var currentLocation: CLLocationCoordinate2D?
    var defaultZoom: Float = 17.0
    
    //Firebase
    var firebaseRef: DatabaseReference!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Setup FireBase
        firebaseRef = Database.database().reference()
        
        // Setup MapView
        //Request permission
        //Request while app is in foreground
//        let locationManager = CLLocationManager()
        locationManager.requestWhenInUseAuthorization()
        locationManager.requestAlwaysAuthorization()
        
        initMapView()
        
        loadData()
        
        let button = UIButton(type: .roundedRect)
        button.frame = CGRect(x: 20, y: 50, width: 100, height: 30)
        button.setTitle("Crash", for: [])
        button.addTarget(self, action: #selector(self.crashButtonTapped(_:)), for: .touchUpInside)
        view.addSubview(button)
    }
    
    @IBAction func crashButtonTapped(_ sender: AnyObject) {
        Crashlytics.sharedInstance().crash()
    }
    
    // MARK: Setup and Update
    
    func loadData() {
        firebaseRef.child("food").observeSingleEvent(of: .value, with: { (snapshot) in
            //Get data value
            for childData in snapshot.children {
                let snap = childData as? DataSnapshot
                let foodLoc = snap?.value as! NSDictionary
                let locationName = foodLoc["name"] as? String ?? ""
//                let locationAddress = value?["address"] as? String ?? ""
                let lat = foodLoc["lat"] as? CLLocationDegrees ?? 0
                let long = foodLoc["long"] as? CLLocationDegrees ?? 0
//                let desc = value?["desc"] as? String ?? ""
                
                let marker = GMSMarker()
                marker.position = CLLocationCoordinate2D(latitude: lat, longitude: long)
                marker.title = locationName
                marker.map = self.mapView
                
//                self.mapView?.camera = GMSCameraPosition.camera(withLatitude: lat, longitude: long, zoom: self.defaultZoom)
            }

        }) { (error) in
            print(error.localizedDescription)
        }
    }
    
    // MapView
    func initMapView() {
        //Create  camera
        let camera = GMSCameraPosition.camera(withLatitude: 10.7933499, longitude: 106.66263879999997, zoom: defaultZoom)
        
        //Create mapview
        mapView = GMSMapView.map(withFrame: CGRect.zero, camera: camera)
        
        view = mapView
        
        //Check the location services are enabled
        if CLLocationManager.locationServicesEnabled() {
            locationManager.delegate = self
            locationManager.desiredAccuracy = kCLLocationAccuracyNearestTenMeters
            locationManager.startUpdatingLocation()
            
            // Get current place
            let placesClient = GMSPlacesClient()
            placesClient.currentPlace(callback: { (placeLikelihoodList, error) -> Void in
                if let error = error {
                    print(error.localizedDescription)
                    return
                }
                
                if let placeLikelihood = placeLikelihoodList {
                    if let likelihood = placeLikelihood.likelihoods.first {
                        let place = likelihood.place
                        
                        //Create marker
                        let marker = GMSMarker()
                        marker.position = CLLocationCoordinate2D(latitude:
                            place.coordinate.latitude, longitude:
                            place.coordinate.longitude)
                        marker.title = place.name
                        marker.map = self.mapView
                        
                        //Create camera
                        let camera = GMSCameraPosition.camera(withLatitude: place.coordinate.latitude, longitude: place.coordinate.longitude, zoom: self.defaultZoom)
                        self.mapView?.camera = camera
                    }
                }
            })
            
        }
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

}

// MARK: CLLocationManager

extension ViewController: CLLocationManagerDelegate {
    
    func locationManager(_ manager: CLLocationManager, didUpdateLocations locations: [CLLocation]) {
        guard let locValue: CLLocationCoordinate2D = manager.location?.coordinate else {
            return
        }
        

    

    }
}

