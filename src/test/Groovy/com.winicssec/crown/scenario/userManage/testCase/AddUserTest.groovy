package com.winicssec.crown.scenario.userManage.testCase

import com.winicssec.crown.scenario.userManage.client.UserClient
import com.winicssec.crown.scenario.userManage.repository.UserRepository
import com.winicssec.crown.scenario.userManage.requestBody.UserBody
import com.winicssec.crown.scenario.userManage.service.UserService
import com.winicssec.crown.testsuite.CrownTest
import org.junit.Assert
import org.junit.experimental.categories.Category
import spock.lang.Specification
@Category(CrownTest)
class AddUserTest extends Specification{
    UserClient userClient
    UserService userService
    UserRepository userRepository
    def loginName = ""

    def setup(){    //每个case都会先执行setup中的内容
        userClient = new UserClient()
        userService = new UserService()
        userRepository = new UserRepository()
    }

    def cleanup(){  //每个case执行后都会执行cleanup中的内容,这里每次case执行完成后，删除添加的user，达到清理数据的效果
        userService.deleteUser(loginName)
    }

    def "should add user successfully when filling all required information"(){
        def roleIdList = userService.generateUserRoleList(addedUserRoleName)
        //根据被添加的用户角色生成对应的角色ID List，页面上创建一个user，选中对应角色是看到的是角色名称，而实际插入数据库的是角色对应的ID值
        given:"generate add user api request body"
        loginName = userService.generateUniqueLoginName()
        //这里是一个生成不重复登录名称的方法
        def body = new UserBody()
                .setUserLoginName(loginName)
                .setUserNickName("abc123NickName")
                .setUserPhone("18181971234")
                .setUserEmail("test@163.com")
                .setRoleIdList(roleIdList)
                .getAddUserBody()   //采用build模式构造接口的request body
        when: "call add user api interface"
        userClient.addUser(addUserRoleName,body)  //这里是addUser()方法实现调用添加user的方法
                .statusCode(201)
        then: "should add user in db successfully"
        Assert.assertTrue(userService.ifUserExist(loginName))
        where:
        addUserRoleName | addedUserRoleName      //这里通过data-driven的方式覆盖了系统中三个角色都能添加user的测试场景
        "systemManager" | "roleManager"
        "userManager"   | "roleManager"
        "roleManager"   | "roleManager"
    }

    def "should add user failed when not filling all required information"() {
        def roleIdList = userService.generateUserRoleList(addedUserRoleName)
        given: "generate add user api request body"
        loginName = userService.generateUniqueLoginName()
        def body = new UserBody()
                .setUserLoginName(loginName)
                .setUserNickName(nickName)
                .setUserPhone(phone)
                .setUserEmail(email)
                .setRoleIdList(roleIdList)
                .getAddUserBody()
        when: "call add user api interface"
        userClient.addUser(addUserRoleName, body)
                .statusCode(400)
        then: "should add user in db successfully"
        Assert.assertFalse(userService.ifUserExist(loginName))
        where:
        nickName   | phone         | email          | addedUserRoleName | addUserRoleName
        ""         | "18181971234" | "test@163.com" | "systemManager"   | "systemManager"
        "nickName" | ""            | "test@163.com" | "systemManager"   | "systemManager"
//        "nickName" | "18181971234" | ""             | "systemManager"  | "systemManager"

    }
}
