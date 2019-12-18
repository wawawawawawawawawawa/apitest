package com.winicssec.fourCourse.db

class DataRepository extends DataSource{
    def getUserInfo(){
        def userInfo = sql.rows(ConstantSql.getUserInfo)
        userInfo ? userInfo : '' //这里做了空保护
    }
    def getAddressByUserName(username){
        def address = sql.firstRow(ConstantSql.getAddressInfoByUserName, [username])
        //上面调用的sql语句需要传递一个参数，所以后面带了username参数
        address ? address : ''
    }
    def addUser(username, age){
        sql.execute(ConstantSql.addUser, [username, age])
        //非查询类操作使用execute方法
    }
    def getUser(userName){
        sql.execute(ConstantSql.getUser, [userName])
    }
    def updateAddress(userName, age){
        sql.execute(ConstantSql.updateAge, [age, userName])
    }
}
