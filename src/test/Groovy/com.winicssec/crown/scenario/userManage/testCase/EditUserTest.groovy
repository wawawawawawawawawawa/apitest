package com.winicssec.crown.scenario.userManage.testCase

import org.junit.experimental.categories.Category
import com.winicssec.crown.scenario.userManage.client.UserClient
import com.winicssec.crown.scenario.userManage.repository.UserRepository
import com.winicssec.crown.scenario.userManage.requestBody.UserBody
import com.winicssec.crown.scenario.userManage.service.UserService
import com.winicssec.crown.testsuite.CrownTest
import spock.lang.Specification
import static org.assertj.core.api.Assertions.assertThat

@Category([CrownTest])
class EditUserTest extends Specification{
    UserRepository userRepository
    UserClient userClient
    UserService userService
    def loginName = "userForEdit"

    def setup(){
        userService = new UserService()
        userRepository = new UserRepository()
        userClient = new UserClient()
    }

    def cleanup(){
        userService.deleteUser(loginName)
    }

    def "should update user details successfully"(){
        given: "create a exist user"
        userService.addUser(loginName, "abc123", "systemManage")
        //为了测试编辑用户，应该先创建一个用户，这里是采用数据库直接添加
        def userInfo = userRepository.getUserInfoByUserName(loginName)
        and:"generate edit user body"
        def reqBody = new UserBody()
                .setUserIdForEditUser(userInfo.uid)  //编辑用户的接口里面需要传递被修改用户的uid
                .setUserLoginName(loginName)
                .setUserNickName(newNickName)
                .setUserPhone(newPhone)
                .setUserEmail(newEmail)
                .setRoleIdList(userService.generateUserRoleList("roleManager"))
                .getAddUserBody()
        when: "edit user details"
        userClient.editUser(updateUserRoleName,userInfo.uid, reqBody).statusCode(200)
        then: "should edit successfully"
        def newUserInfo = userRepository.getUserInfoByUserName(loginName)
        assertThat(newUserInfo.nickName).isEqualTo(newNickName)
        assertThat(newUserInfo.phone).isEqualTo(newPhone)
        assertThat(newUserInfo.email).isEqualTo(newEmail)
        assertThat(userRepository.getUserRole(loginName).role_id).isEqualTo(userRepository.getRoleInfoByRoleName("roleManager").id)
        where:
        updateUserRoleName|newNickName|newPhone|newEmail
        "systemManager"| "newNickName"|"18181991122"|"newEmail@163.com"
        "userManager"| "newNickName"|"18181991122"|"newEmail@163.com"
        "roleManager"| "newNickName"|"18181991122"|"newEmail@163.com"
    }
}
