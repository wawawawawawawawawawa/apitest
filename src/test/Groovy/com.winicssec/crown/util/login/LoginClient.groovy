package com.winicssec.crown.util.login

import com.winicssec.crown.testdata.service.GlobalUserService
import com.winicssec.crown.util.ConfigParse

import static io.restassured.RestAssured.given

/**
 * 首先使用REST Assured调通被测接口
 */
class LoginClient {
    GlobalUserService globalUserService   //从各种格式的配置文件中读取数据用于初始化
    ConfigParse configParse         //这里调用Configparse，因为所有环境相关的配置信息都放到了yaml文件中

    LoginClient() {
        globalUserService = new GlobalUserService()
        configParse = new ConfigParse()
    }

    def clientWithSpecialRole(roleName) {
        def userInfo = globalUserService.getUserInfoByRole(roleName) //根据角色获取用户信息
        setDefaultUriAndContentType()  //该方法和getTokenByUser方法都需要设置baseUri，header等，所以把公共的内容抽取了一个“setDefaultUriAndContentType”方法，减少冗余
                .header("Authorization", getTokenByUser(userInfo.userName, userInfo.password)) //查看web应用可以看到所有接口调用都需要在header中设置token
    }

    private def getTokenByUser(userName, password) {
        HashMap userMap = new HashMap()  //这里是根据用户名称和密码生成token的方法
        userMap.put("loginName", userName)
        userMap.put("password", password)
        def token = setDefaultUriAndContentType()
                .body(userMap)
                .post("/account/token")
                .then().assertThat().statusCode(200)
                .extract().response().path("result.token")
        token = "Bearer " + token
        token
    }

    private def setDefaultUriAndContentType(contentType = "application/json") {
        given().baseUri((String) configParse.getGlobalConfig().baseUrl)
                .header("Content-Type", contentType)
    }
}
