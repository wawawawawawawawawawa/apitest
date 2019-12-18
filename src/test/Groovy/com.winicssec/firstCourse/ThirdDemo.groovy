package com.winicssec.firstCourse

import spock.lang.Specification
import static io.restassured.RestAssured.given

class ThirdDemo extends Specification{
    def "should call get book by name and price successfully"(){
        given: "no given"
        when: "call mock api api"
        given().baseUri("http://localhost:9090").log().all()
            .queryParam("name","sanguo")
            .queryParam("price",1)
            .when()
            .get("api/getBookByPathPatter/test")
            .then()
            .assertThat().statusCode(200)
        then: "no then"
    }
}
