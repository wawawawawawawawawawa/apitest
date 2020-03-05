package com.winicssec.eightCourse

import org.junit.Test

import static io.restassured.RestAssured.given
class GetDataClient {
    ConfigParser configParser
    TestDataService testDataService
    def configs
    def users

    GetDataClient(){
        configParser = new ConfigParser()
        configs = configParser.getGlobalConfig()
        testDataService = new TestDataService()
        users = testDataService.getUserDataByRole("ForGetDataApi")
    }

    void getData(){
        def res = given().baseUri((String) configs.mockServerUrl)
                .auth().preemptive().basic("apiUsername", "apiPassword")
                .when()
                .get("/api/getData")
                .then().assertThat().statusCode(200)
                .extract().response().getBody().asString()
        println res
    }

    @Test
    void callGetData(){
        getData()
    }

    void getDataWithCsvUser(){
        def res = given().baseUri((String) configs.mockServerUrl)
                .auth().preemptive().basic(users.username, users.password)
                .when()
                .get("/api/getData")
                .then().assertThat().statusCode(404)
                .extract().response().getBody().asString()
        println res
    }
    @Test
    void callGetDataWithCsvUser(){
        getDataWithCsvUser()
    }

    void GetDataWithEncrypyPassword()
    {
        def res = given().baseUri((String) configs.mockServerUrl)
                        .auth().preemptive().basic(users.username, testDataService.getPasswordByUserName(users.username))
                        .when()
                        .get("/api/getData")
                        .then().assertThat().statusCode(200)
                        .extract().response().getBody().asString()
        println res
    }

    @Test
    void callGetDataWithEncrypyPassword(){
        GetDataWithEncrypyPassword()
    }
}
