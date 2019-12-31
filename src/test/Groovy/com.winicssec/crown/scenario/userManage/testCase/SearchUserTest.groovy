package com.winicssec.crown.scenario.userManage.testCase

import com.winicssec.crown.scenario.userManage.client.UserClient
import com.winicssec.crown.scenario.userManage.service.UserService
import com.winicssec.crown.testsuite.CrownTest
import org.junit.experimental.categories.Category
import static org.assertj.core.api.Assertions.assertThat
import spock.lang.Specification
@Category([CrownTest])
class SearchUserTest extends Specification{    //检索用户
    UserClient userClient
    UserService userService

    def setup(){
        userClient = new UserClient()
        userService = new UserService()
    }

    def "should search user successfully"(){
        given: "no given"
        when: "search user"
        def response = userClient.searchUser(searchUserRoleName, loginName, nickName, status)
        //调用检索接口
        then: "should get all vaild user"
        assertThat(response.result.total).isEqualTo(userService.getUserNumbersForSearch(loginName, nickName,status))
        //调用检索接口返回的response中的total个数与数据库中查询的数量与一致说明检索接口正确
        where:
        searchUserRoleName | loginName | nickName | status
        "systemManager"    | "name"    | ""       | ""
        "systemManager"    | ""        | "abc"    | ""
        "systemManager"    | ""        | ""       | 0
        "roleManager"    | ""        | ""       | 1       //这里使用了roleManger与userManager进行检索，主要是顺便覆盖三种角色都有检索用户接口权限
        "userManager"    | "crown"   | "crown"  | 0
        "systemManager"    | ""        | ""       | ""
    }
    def "should search user with limit successfully"(){
        given:"no given"
        when: "search user with limit 20"
        def response = userClient.searchUserWithLimit(searchUserRoleName,limit)
        then: "should get valid user"
        assertThat(response.result.size).isEqualTo(limit)
        where:
        searchUserRoleName|limit
        "systemManager"|20
    }
}
