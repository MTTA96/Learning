//
//  AddLocationViewController.swift
//  MapAndFirebase
//
//  Created by Anh Tran on 4/13/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit
import Firebase
import CoreLocation

class AddLocationViewController: UIViewController {
    
    @IBOutlet weak var locationImageView: UIImageView!
    @IBOutlet weak var locationName: UITextField!
    @IBOutlet weak var locationAddress: UITextField!
    @IBOutlet weak var locationDesc: UITextView!
    
    var firebaseRef: DatabaseReference!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Config firebase
        firebaseRef = Database.database().reference()
        
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    // MARK: - Actions
    
    @IBAction func addButtonTapped(_ sender: UIBarButtonItem) {
        convertAddressToCoordinates(address: locationAddress.text) { (error, location) in
            if let error = error {
                print(error)
                self.firebaseRef.child("food").childByAutoId().setValue(["name" : self.locationName.text!,
                                                                         "address" : self.locationAddress.text!,
                                                                         "lat" : 0, "long": 0,
                                                                         "desc" : self.locationDesc.text])
                return
            }
            self.firebaseRef.child("food").childByAutoId().setValue(["name" : self.locationName.text!,
                                                                     "address" : self.locationAddress.text!,
                                                                     "lat" : location?.coordinate.latitude,
                                                                     "long": location?.coordinate.longitude,
                                                                     "desc" : self.locationDesc.text])
        }
    }
    
    // MARK: - Caculate
    
    func convertAddressToCoordinates(address: String!, results: @escaping (_ error: String?, _ location: CLLocation?) -> Void ) {
        let geoCoder = CLGeocoder()
        geoCoder.geocodeAddressString(address) { (placemarks, error) in
            guard let location = placemarks?.first?.location else {
                results("Failed!", nil)
                return }
            results(nil, location)
        }
    }
    
}
