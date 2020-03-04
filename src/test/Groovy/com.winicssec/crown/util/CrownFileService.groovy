package com.winicssec.crown.util

import com.xlson.groovycsv.CsvParser
import groovy.json.JsonSlurper
import org.yaml.snakeyaml.Yaml

/**
 * 此类用于导入各类的配置文件
 */

class CrownFileService {
    JsonSlurper jsonSlurper
    XmlSlurper xmlSlurper
    CsvParser csvParser

    CrownFileService(){
        jsonSlurper = new JsonSlurper()
        xmlSlurper = new XmlSlurper()
        xmlSlurper.setFeature("http://apache.org/xml/features/disallow-doctype-decl",false)
        csvParser = new CsvParser()
    }

    def createFile(path){
        new File(path)
    }

    private def yml(String text){  //yml类型文件的导入
        new Yaml().load(text)
    }

    def getConfigs(String ymlFilePath){
        def configs = yml(createFile(ymlFilePath).text)
        configs
    }

    def getCollectionFromXMLFile(String xmlFilePath) {   //XML文件的导入
        xmlSlurper.parse(createFile(xmlFilePath))
    }
    def getCollectionFromJsonFile(String jsonFilePath) {  //Json类型文件的导入
        jsonSlurper.parse(createFile(jsonFilePath))
    }
    def getCsvFileContent(String csvFilePath, separator) {  //CSV类型文件的导入
        csvParser.parse(new FileReader(createFile(csvFilePath)), separator: separator)
    }
}
