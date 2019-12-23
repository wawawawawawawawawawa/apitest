package com.winicssec.sevenCourse

import org.junit.Test

class XmlDataService {
    XmlSlurper xmlSlurper
    XmlClient xmlClient

    XmlDataService(){
        xmlSlurper = new XmlSlurper()
        xmlClient = new XmlClient()
    }
    @Test
    void getMatchLevel(){
        def result = xmlSlurper.parseText(xmlClient.getXmlData())
        //將sring类型的response body通过xmlSlurper转换为数据对象
        println result.View.Result.MatchLevel.text()
    }
}
