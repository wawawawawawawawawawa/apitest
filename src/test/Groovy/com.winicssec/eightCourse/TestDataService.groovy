package com.winicssec.eightCourse

import com.winicssec.fourCourse.file.FileService

class TestDataService {
    FileService fileService

    TestDataService(){
        fileService = new FileService()
    }

    def getUserFileData(){
        def userData = fileService.getCsvFileContent("src/test/resources/date/user.csv", ",")
        //读取csv文件，以 ， 作为分隔符
        userData
    }
    def getUserDataByRole(roleName){
        getUserFileData().find{ it -> it.roleName == roleName}
    }
    def getPasswordByUserName(userName){
        //这个方法是通过用户名称获取解密后的用户密码
        Secret.decrypt( "apiTestStudy",(String)getUserFileData().find{it -> it.username == userName}.password)   //Secret中都是静态方法，所以可以直接类名.方法名进行调用
    }
}
