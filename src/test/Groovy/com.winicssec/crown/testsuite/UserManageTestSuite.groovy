package com.winicssec.crown.testsuite

import com.winicssec.crown.scenario.userManage.testCase.AddUserTest

import com.winicssec.crown.scenario.userManage.testCase.ResetPasswordTest
import com.winicssec.crown.scenario.userManage.testCase.SearchUserTest
import com.winicssec.crown.scenario.userManage.testCase.UpdateUserStatusTest
import org.junit.experimental.categories.Categories
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Categories.class)
@Categories.IncludeCategory(CrownTest.class)
@Suite.SuiteClasses([AddUserTest, EditUserTest, ResetPasswordTest, SearchUserTest, UpdateUserStatusTest])
class UserManageTestSuite {
}
