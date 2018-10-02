//
//  DataSource.swift
//
//  Copyright © 2018. All rights reserved.
//

import UIKit

class DataSource<Model: Any>: NSObject {
    
    var dataProvider: DataProvider<Model>
    
    init(dataProvider: DataProvider<Model>) {
        self.dataProvider = dataProvider
    }
}

class DataSourceForSection<Model: Any>: NSObject {
    
    var dataProvider: DataProviderForSection<Model>
    
    init(dataProvider: DataProviderForSection<Model>) {
        self.dataProvider = dataProvider
    }
}
