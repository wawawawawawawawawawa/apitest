package com.winicssec.eightCourse.testSuite

import org.junit.Test
import org.junit.experimental.categories.Category

class TestClassA {

    @Category([FirstCategory])
    @Test
    void firstMethod(){
        println("this is first method from testA")
    }
    @Category([SecondCategory])
    @Test
    void secondMethod(){
        println("this is second method from testA")
    }
}
