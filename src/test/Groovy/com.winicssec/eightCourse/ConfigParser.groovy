package com.winicssec.eightCourse

import com.winicssec.fourCourse.file.FileService

class ConfigParser {
    FileService fileService

    ConfigParser(){
        fileService = new FileService()
    }

    def getConfigs(String configFilePath){
        def configs = fileService.getConfigs(configFilePath)
        def sysEnv = System.getenv("ACTIVE_ENV")
        def active = sysEnv ? sysEnv : configs.active
        configs.putAll(configs.get(active))
        //根据active值获取对应环境的配置信息
        configs
    }

    def getGlobalConfig(){
        def configs
        configs = getConfigs("src/test/resources/config/config.yml")
        configs
    }
}

