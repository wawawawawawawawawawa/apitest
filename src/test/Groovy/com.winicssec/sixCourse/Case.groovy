package com.winicssec.sixCourse

import spock.lang.Specification
import static org.hamcrest.MatcherAssert.assertThat
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath
class Case extends Specification{
    ResumeClient resumeClient
    ResumeService resumeService

    def setup(){
        resumeService = new ResumeService()
        resumeClient = new ResumeClient()
    }

    def "get person from different country"(){
        given: "no given"
        when: "call the get resume api"
        def res = resumeClient.getResumeDetails()
        then: "println out the person name from different country"
        println resumeService.getPersonByCountry(res, country)
        where:
        country | placeHolder
        "China" | ""
        "USA" | ""
    }
    def "get contact from resume"(){
        given: "no given"
        when: "call the get resume api"
        def res = resumeClient.getResumeDetails()
        then: "println out contacts info"
        println resumeService.getContactPhone(res)
    }

    def "get all working experience"(){
        given:"no given"
        when: "call the get resume api"
        def res = resumeClient.getResumeDetails()
        then: "println out contacts info"
        resumeService.printWorkingDetails(res)
    }
    def "println language skill if person with it"(){
        given: "no given"
        when :"call the get resume api"
        def res = resumeClient.getResumeDetails()
        then:"println out contacts info "
        resumeService.printIfPersonWithSpecialSkill(res,language)
        where:
        language | placeHolder
        "Java"   | ""
    }

    def "call the api to validate schema with second schema file"(){
        given: "no given"
        when :"call the get resume api"
        println resumeClient.getResumeSchemaValidate(filePath)
        then:"no then"
        where:
        filePath|placeHolder
        "com/winicssec/schema/getResumeSchema2.json"|""
    }

    def "validate schema of getResume2 api"(){
        given: "no given"
        when: "call the get resume2 api"
        def payLoad = resumeClient.getResume2()
        then: "check the schema"
        assertThat(payLoad, matchesJsonSchemaInClasspath(filePath))
        where:
        filePath | palceHolder
        "com/winicssec/schema/getResumeSchema2.json" | ""
    }

}
