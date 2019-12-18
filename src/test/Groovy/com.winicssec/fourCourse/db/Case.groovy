package com.winicssec.fourCourse.db

import org.junit.Assert
import spock.lang.Specification
class Case extends Specification{
    DataRepository dataRepository

    void setup() {
        dataRepository = new DataRepository()
    }

    def "should get user info successfully"(){
        given: "no given"
        when : "query user table to get info"
        def userinfo = dataRepository.getUserInfo()
        then:"should get user info"
        userinfo.each {it -> println it.username + ":" + it.age + ":" + it.create_date }

    }
    def "should get user address successfully"(){
        given:"no given"
        when:"query user and address table"
        def adderssInfo = dataRepository.getAddressByUserName(userName)
        then:"should get correct user address info"
        Assert.assertEquals(adderssInfo.address, address)
        where:
        userName | address
        "TOM"    | "chengdu"
        "DONE"    | "beijing"
        "ECHO"    | "shanghai"
        "MARY"    | "hangzhou"
    }
    def "should add user successfully"(){
        given: "no given"
        when: "add user"
        dataRepository.addUser(userName, age)
        then: "should get added user successfully"
        Assert.assertEquals(dataRepository.getUser(userName).username, userName)
        where:
        userName | age
        "MARY"   | 55
    }


}
