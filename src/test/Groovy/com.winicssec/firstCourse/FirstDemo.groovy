package com.winicssec.firstCourse

import spock.lang.Specification
import static io.restassured.RestAssured.given

class FirstDemo extends Specification{
    def "should call mock api successfully"(){  //spock框架(BDD框架)语法，所有case都是def开头，def后面是case 的描述信息
        given:"no given"    //spock框架语法，given-when-then三段式写法，given/when/then后是描述信息
        when:"call mock api api"
        given().baseUri("http://localhost:9090") //固定写法，REST Assured自身也是三段式写法
               .when()
               .get("api/getUserDetails")   //固定写法支持get，post，delete等，括号里为接口路径
               .then().log().all()
               .assertThat().statusCode(200)
        then: "no then"
    }
}
