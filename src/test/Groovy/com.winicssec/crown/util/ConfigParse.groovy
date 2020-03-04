package com.winicssec.crown.util
/**
 * 此部分用于的密码加密和读取文件
 */
class ConfigParse {
    CrownFileService crownFileService

    ConfigParse(){
        crownFileService = new CrownFileService()
    }

    private def getConfigs(String configFilePath){
        def configs = crownFileService.getConfigs(configFilePath) //传入yaml文件的路径，然后获取整个yaml文件的内容
        def sysEnv = System.getenv("ACTIVE_ENV") //读取名称为“ACTIVE_ENV”的环境变量
        def active = sysEnv ? sysEnv : configs.active //如果环境变量存在，则读取环境变量的值，如果环境变量不存在，则读取yaml文件中active值
        configs.putAll(configs.get(active))
        //通过active值获取对应环境的配置信息，例如如果active的值是“active“，那么自动化脚本在运行时就会获取active下面配置的db连接信息和ServerUrl
        configs
    }

    private def decryptConfig(Map<String,Object>configs,secretKey){
        configs.each {
            it ->
                if(it.getValue() instanceof String){
                    if(it.getValue().startsWith("[ENC")){
                        it.setValue Secret.decrypt(secretKey, it.getValue().replace("[ENC]",""))
                    }
                    //调用secret中的加解密方法
                } else if(it.getValue() instanceof Map){
                    decryptConfig(it.getValue(),secretKey)
                }
        }
        configs
    }
    def getGlobalConfig(){
        def configs
        configs = getConfigs("src/test/resources/crown/config/crownConfig.yml")
        decryptConfig(configs,configs.decryptKey)
    }
}
