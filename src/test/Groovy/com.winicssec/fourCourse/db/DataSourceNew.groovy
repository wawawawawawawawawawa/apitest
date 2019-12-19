package com.winicssec.fourCourse.db

import com.winicssec.fourCourse.file.FileService
import groovy.sql.Sql

class DataSourceNew {
    Sql sql
    FileService fileService
    def configs


    DataSourceNew(){
        fileService = new FileService()
        configs = fileService.getConfigs('./src/test/resources/config/config.yml')
    }
    Sql getSql(){
        if(!sql){
            def mysqlDB = [
                    driver  : 'com.mysql.jdbc.Driver',
                    url     : configs.dev.db.url,
                    user    : configs.dev.db.user,
                    password: configs.dev.db.password
            ]
            sql = Sql.newInstance(mysqlDB.url, mysqlDB.user, mysqlDB.password, mysqlDB.driver)
        }
        sql
    }
}
