//
//  LoginViewController.swift
//  iosApp
//
//  Created by Zuheir on 16/03/2021.
//  Copyright © 2021 orgName. All rights reserved.
//

import UIKit
import shared

class LoginViewController: UIViewController {
//    @ObservedObject private(set) var viewModel: ViewModel
    @IBOutlet var fieldUsername: UITextField?
    @IBOutlet var fieldPassword: UITextField?
    let engine = ApiEngine()

    override func viewDidLoad() {
        super.viewDidLoad()
    }


    @IBAction func onLoginClicked() {
        login(_userName: self.fieldUsername!.text!, _password: self.fieldPassword!.text!)
    }
        
    func login(_userName: String, _password: String) {
            engine.login(userName: _userName, password: _password) { (auth, error) in
                if (auth != nil) {
                    if (auth!.id != nil) {
                        self.proceedToMain()
                    } else {
                        self.showError(_message: "Incorrect username or password")
                    }
                } else {
                    self.showError(_message: "Error occurred while logging in")
                }
                
            }
    }
    
    func proceedToMain() {
        let mainViewController = storyboard?.instantiateViewController(identifier: "main") as! MainViewController
        show(mainViewController, sender: self)
    }
    
    func showError(_message: String?) {
        let alert = UIAlertController(title: "", message: _message, preferredStyle: UIAlertController.Style.alert)
                alert.addAction(UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler: nil))

                self.present(alert, animated: true, completion: nil)
    }
}
