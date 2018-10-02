//
//  Datasource.swift
//
//  Copyright © 2018. All rights reserved.
//

import UIKit

class TableViewDataSource<Cell: UITableViewCell, Model: Any>: DataSource<Model>, UITableViewDataSource {
    
    var configureCell: ((Cell, Model) -> ())?
    var configureCellWithIndex: ((Cell, Model, IndexPath) -> ())?

    func numberOfSections(in tableView: UITableView) -> Int {
        return dataProvider.numberOfSections()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return dataProvider.numberOfRows(in: section)
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueResuableCell(for: indexPath) as Cell
        let model = dataProvider.item(at: indexPath)
        if configureCell != nil {
            configureCell!(cell, model)
        } else if configureCellWithIndex != nil {
            configureCellWithIndex!(cell, model, indexPath)
        }
        return cell
    }
    
}

class TableViewDataSource2<Cell: UITableViewCell, Model: Any>: DataSourceForSection<Model>, UITableViewDataSource {
    
    var configureCell: ((Cell, Model) -> ())?
    
    func numberOfSections(in tableView: UITableView) -> Int {
        return dataProvider.numberOfSections()
    }
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return dataProvider.numberOfRows(in: section)
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueResuableCell(for: indexPath) as Cell
        let model = dataProvider.item(at: indexPath)
        if configureCell != nil { configureCell!(cell, model) }
        return cell
    }
    
}
