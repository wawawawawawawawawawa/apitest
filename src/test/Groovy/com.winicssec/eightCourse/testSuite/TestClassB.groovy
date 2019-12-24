package com.winicssec.eightCourse.testSuite

import org.junit.Test
import org.junit.experimental.categories.Category

class TestClassB {

    @Category([FirstCategory])
    @Test
    void firstMethod(){
        println("this is first method from testB")
    }
    @Category([SecondCategory])
    @Test
    void secondMethod(){
        println("this is second method from testB")
    }
}
