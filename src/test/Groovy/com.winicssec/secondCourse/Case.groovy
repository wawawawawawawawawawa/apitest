package com.winicssec.secondCourse

import groovy.json.JsonSlurper
import org.junit.Assert
import spock.lang.Specification
import static io.restassured.RestAssured.given
class Case extends Specification{
    //测试场景：期望调用接口获取到名字为三国演义且判断价格是否等于20
    JsonSlurper jsonSlurper = new JsonSlurper()
    //接口返回的是json字符串，jsonSlurper的作用是将json字符串转换为Groovy的集合对象
    def "should book's price correct"(){
        given:""
        when: "get books response"
        def response = given().baseUri("http://localhost:9090")
                .when()
                .get("api/getAllBooks")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response().getBody().asString()
        then: "bookos price is correct"
        def bookPrice = jsonSlurper.parseText(response).books.find{it -> it.name == bookName}.price
        Assert.assertEquals("bookPrice:${bookPrice} is not correct", bookPrice, price)
        where:
        bookName | price
        "三国演绎" | 20
    }
}
