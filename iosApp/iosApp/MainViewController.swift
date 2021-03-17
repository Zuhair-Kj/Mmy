//
//  MainViewController.swift
//  iosApp
//
//  Created by Zuheir on 16/03/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import UIKit
import shared

class MainViewController: UIViewController {

    @IBOutlet var fieldFirstName: UITextField?
    @IBOutlet var fieldLastName: UITextField?
    @IBOutlet var fieldPhoneNumber: UITextField?
    @IBOutlet var fieldAddress: UITextField?
    @IBOutlet var labelID: UILabel?

    let engine = ApiEngine()
    override func viewDidLoad() {
        super.viewDidLoad()
        
        getProfile()
    }
    
    func getProfile() {
        engine.getProfile(userId: "1") { (profile, error) in
            if (profile != nil) {
                self.fieldFirstName?.text = profile!.firstName
                self.fieldLastName?.text = profile!.lastName
                self.fieldPhoneNumber?.text = profile!.phoneNumber
                self.fieldAddress?.text = profile!.address
                self.labelID?.text = profile!.id
            } else {
                self.showAlert(message: "Failed to get Profile please try again later")
            }
        }
    }
    
    @IBAction func updateProfile() {
        let profile = Profile(id: labelID?.text!, firstName: fieldFirstName?.text!,
                              lastName: fieldLastName?.text, phoneNumber: fieldPhoneNumber?.text, address: fieldAddress?.text!)
        
        engine.updateProfile(profile: profile) { (unit, error) in
            if (error == nil) {
                self.showAlert(message: "Profile updated")
            } else {
                self.showAlert(message: "Error")
            }
        }
    }
    
    func showAlert(message: String) {
        let alert = UIAlertController(title: "", message: message, preferredStyle: UIAlertController.Style.alert)
        alert.addAction(UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: nil))
        self.present(alert, animated: true, completion: nil)
    }

}
