//
//  APIKeywords.swift
//
//  Copyright © 2018 Apps Cyclone. All rights reserved.


struct Header {
    
    static let authorization                     = "Authorization"
    static let contentType                       = "Content-Type"
    
    struct Content {
        static let basic                         = "Basic"
    }
    
}

struct APIKeywords {
    struct Music {
        static let objectId                      = "objectId"
        static let itemID                        = "itemID"
        static let ContentTypeID                 = "contentType"
    }
    
    struct CreditCardRD {
        static let mid                           = "mid"
        static let orderId                       = "order_id"
        static let cardNo                        = "card_no"
        static let expDate                       = "exp_date"
        static let payerId                       = "payer_id"
        static let payerName                     = "payer_name"
        static let payerEmail                    = "payer_email"
        static let cvv2                          = "cvv2"
        static let ccy                           = "ccy"
        static let transactionType               = "transaction_type"
        static let apiMode                       = "api_mode"
        static let billToAddressCity             = "bill_to_address_city"
        static let billToAddressCountry          = "bill_to_address_country"
        static let billToAddressLine1            = "bill_to_address_line1"
        static let billToAddressPostalCode       = "bill_to_address_postal_code"
        
        static let signature                     = "signature"
        
        static let secretKey                     = "4ViGXfYVf4IcK3fpWyPPNuiJm0lLeWDmFb50h7PyKPtI7qYGEDk3Tl6qYqhAfnjgaNbiROasaWTPwVaVIM8IJ23nxb2qWAb6OZRoivmVUaYzG867vlkW7joa1QewYRE5"
    }
    
    struct CreditCardCHC {
        static let name                          = "name"
        static let expiry                        = "expiry"
        static let token                         = "token"
        static let cardType                      = "cardType"
    }
}














