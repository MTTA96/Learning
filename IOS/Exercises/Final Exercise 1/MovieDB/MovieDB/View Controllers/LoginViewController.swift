//
//  ViewController.swift
//  MovieDB
//
//  Created by Anh Tran on 3/26/18.
//  Copyright © 2018 Anh Tran. All rights reserved.
//

import UIKit
import SafariServices

class LoginViewController: UIViewController {
    
    @IBOutlet weak var contentView: UIView!
    @IBOutlet weak var userNameTextField: UITextField!
    @IBOutlet weak var passwordTextField: UITextField!
    @IBOutlet weak var signUpLabel: UILabel!
    @IBOutlet weak var loginButton: UIButton!
    
    // Constraints
    @IBOutlet weak var scrollView: UIScrollView!
    @IBOutlet weak var titleContraintTop: NSLayoutConstraint!
    @IBOutlet weak var userInfoHeight: NSLayoutConstraint!
    @IBOutlet weak var loginButtonHeight: NSLayoutConstraint!
    @IBOutlet weak var contentHeight: NSLayoutConstraint!
    @IBOutlet weak var pbeLogoLeft: NSLayoutConstraint!
    
    let loginPresenter: LoginPresenter = LoginPresenter()
    var activeField: UITextField?
    var lastOffset: CGPoint?
    var keyboardHeight: CGFloat!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        userNameTextField.delegate = self
        passwordTextField.delegate = self
        
        setUp()
        loadData()
    }

    func setUp() {
        
        //Set up navigation controller
        self.navigationController?.makeBlackNavigationbar()
        self.navigationController?.navigationBar.makeBlackNavigationBar()
        
        //Set action for signUp
        let gestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(self.signUp))
        signUpLabel.addGestureRecognizer(gestureRecognizer)
        
        //Set up view constant
        let screenWidth = UIScreen.main.bounds.width
        if screenWidth < 375 {
            titleContraintTop.constant = 110
            userInfoHeight.constant = 80
            loginButtonHeight.constant = 45
            pbeLogoLeft.constant = 60
        }
        
        //Set up keyboard
        // Observe keyboard change
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillShow(notification:)), name: NSNotification.Name.UIKeyboardWillShow, object: nil)
        NotificationCenter.default.addObserver(self, selector: #selector(keyboardWillHide(notification:)), name: NSNotification.Name.UIKeyboardWillHide, object: nil)
        
        // Add touch gesture for contentView
        self.contentView.addGestureRecognizer(UITapGestureRecognizer(target: self, action: #selector(returnTextView(gesture:))))
    }
    
    func loadData() {
        let attributedString = NSMutableAttributedString(string: "OR \nSign up via website\nthemoviedb.org\n", attributes: [
            .font: UIFont(name: "AvenirNext-Regular", size: 17.0)!,
            .foregroundColor: UIColor(white: 1.0, alpha: 1.0)
            ])
        
        attributedString.addAttribute(.font, value: UIFont(name: "AvenirNext-Medium", size: 17.0)!, range: NSRange(location: 0, length: 2))
        
        signUpLabel.attributedText = attributedString
    }
    
    @IBAction func returnTextView(gesture: UIGestureRecognizer) {
        guard activeField != nil else {
            return
        }
        
        activeField?.resignFirstResponder()
        activeField = nil
    }
    
    @IBAction func signUp() {
        if let url = URL(string: ServerUrl.signUpUrl) {
            let safariViewController = SFSafariViewController(url: url)
                present(safariViewController, animated: true,
                    completion: nil)
        }
    }
    
    @IBAction func loginButtonTapped(_ sender: UIButton) {
        activeField?.resignFirstResponder()
        guard let username = userNameTextField.text, let password = passwordTextField.text else { return }
        let loadingAlert = LoadingAlert().createView()
        present(loadingAlert, animated: true, completion: nil)
        
        loginPresenter.login(userName: username, passWord: password) { msg in

            if msg == "Login Success!" {
                loadingAlert.dismiss(animated: true, completion: nil)
                self.present((self.storyboard?.instantiateViewController(withIdentifier: "MainController"))!, animated: true, completion: nil)
                NotificationCenter.default.removeObserver(self)
            } else {
                loadingAlert.dismiss(animated: true, completion: nil)
                
                let alert: UIAlertController = UIAlertController(title: nil, message: msg, preferredStyle: UIAlertControllerStyle.alert)
                let action = UIAlertAction(title: "Ok", style: .default, handler: { handler in
                    self.dismiss(animated: true , completion: nil)
                })
                alert.addAction(action)
                self.present(alert, animated: true, completion: nil)
            }
        }
        
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

}

// MARK: - UITextFieldDelegate
extension LoginViewController: UITextFieldDelegate {
    
    func textFieldShouldBeginEditing(_ textField: UITextField) -> Bool {
        activeField = textField
        lastOffset = self.scrollView.contentOffset
        return true
    }
    
    func textFieldShouldReturn(_ textField: UITextField) -> Bool {
        activeField?.resignFirstResponder()
        activeField = nil
        return true
    }
    
}

// MARK: Keyboard Handling
extension LoginViewController {
    @IBAction func keyboardWillShow(notification: NSNotification) {
        if keyboardHeight != nil {
            return
        }
        
        if let keyboardSize = (notification.userInfo?[UIKeyboardFrameBeginUserInfoKey] as? NSValue)?.cgRectValue {
            keyboardHeight = keyboardSize.height
            
            // Increase contentView's height by keyboard height
            UIView.animate(withDuration: 0.5, animations: {
                self.contentHeight.constant += self.keyboardHeight
            })
            
            // Move if keyboard hide input field
            let distanceToBottom = self.scrollView.frame.size.height - (loginButton.frame.origin.y) - (loginButton?.frame.size.height)!
            let collapseSpace = keyboardHeight - distanceToBottom
            
            if collapseSpace < -4 {
                return
            }
            
            // Set new offset for scroll view
            UIView.animate(withDuration: 0.5, animations: {
                // scroll to the position above keyboard 10 points
                self.scrollView.contentOffset = CGPoint(x: (self.lastOffset?.x)!, y: collapseSpace + 10)
            })
        }
    }
    
    @IBAction func keyboardWillHide(notification: NSNotification) {
        UIView.animate(withDuration: 0.5) {
            if self.keyboardHeight != nil {
                self.contentHeight.constant -= self.keyboardHeight
                
                if self.lastOffset != nil {
                    self.scrollView.contentOffset = self.lastOffset!
                }
            }
        }
        
        keyboardHeight = nil
    }
}

