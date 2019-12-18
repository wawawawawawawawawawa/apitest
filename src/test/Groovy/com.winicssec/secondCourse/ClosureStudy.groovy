package com.winicssec.secondCourse

import org.junit.Test

class ClosureStudy {
    def firstClosure = {println "hello world"}
    def secondClosure = {a, b -> a+b}
    @Test
    void testClosure(){
        firstClosure()  //运行闭包
        println secondClosure(1, 2)
    }
    //闭包作为方法的参数
    def myFunction(name, closure){
        closure(name)
    }
    @Test
    void testMyFunction(){
        myFunction("Dave", {name->println "my name is ${name}"})
        myFunction("Dave", {it->println "my name is ${it}"})
        myFunction("Dave", {println "my name is ${it}"})
    }
    //闭包是方法的唯一参数
    def function(closure){
        def a = "hello"
        closure(a)
    }
    @Test
    void testFunction(){
        function({it -> println it})
        function{it -> println it}
    }
    @Test
    void testEach(){
        def firstList = ["zhangsan", 1, 2]
        firstList.each {println it}

        //采用each计算数组的和
        def secondList = [1, 3, 5, 7]
        def c = 0
        secondList.each {c = c + it}
        println c

        //map对象的each方法
        def myMap = ["name": "tom", "age": 100]
        myMap.each {key, value -> println key + "---" +value}
    }
    @Test
    void testFind(){
        def firstList = [1, 3, 5, 7, 9]
        def result = firstList.find{it -> it > 5}
        println result
        result = firstList.findAll({it -> it > 5})
        println result
    }
}
