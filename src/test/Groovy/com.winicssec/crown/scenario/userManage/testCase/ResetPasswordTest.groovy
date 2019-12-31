package com.winicssec.crown.scenario.userManage.testCase

import com.winicssec.crown.scenario.userManage.client.UserClient
import com.winicssec.crown.scenario.userManage.repository.UserRepository
import com.winicssec.crown.scenario.userManage.service.UserService
import com.winicssec.crown.testsuite.CrownTest
import org.junit.experimental.categories.Category
import static org.assertj.core.api.Assertions.assertThat
import spock.lang.Specification
@Category([CrownTest])
class ResetPasswordTest extends Specification{
    UserService userService
    UserRepository userRepository
    UserClient userClient
    def loginName = "userForReset"

    def setup(){
        userService = new UserService()
        userRepository = new UserRepository()
        userClient = new UserClient()
    }

    def cleanup(){
        userService.deleteUser(loginName)
    }

    def "should reset user password successfully"(){
        given: "create a user with password"
        userService.addUser(loginName, password)  //添加一个用户，用户名密码都是abc456
        when: "reset user password"
        def uid = userRepository.getUserInfoByUserName(loginName).uid   //获取用户的uid用于重置密码
        userClient.resetUserPassword(resetPasswordRoleName, uid)        //调用接口重置用户名密码
        then :"should user's password is equal to loginName"
        assertThat(userRepository.getUserInfoByUserName(loginName).password).isEqualTo(userService.getEncryptPassword(loginName,loginName))
        //被测web应用如果重置密码，会把密码设置为登录名称，因为数据库存储密码时进行了加密操作，这里判断数据库存储的密码和登录名加密后的结果一致
        where:
        password | resetPasswordRoleName     //测试系统中的三种角色都可以重置用户密码
        "abc456" | "systemManager"
        "abc456" | "userManager"
        "abc456" | "roleManager"
    }
}
