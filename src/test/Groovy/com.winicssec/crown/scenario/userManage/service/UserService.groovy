package com.winicssec.crown.scenario.userManage.service

import com.winicssec.crown.scenario.userManage.repository.UserRepository
import org.apache.commons.codec.digest.Md5Crypt

class UserService {
    UserRepository userRepository

    UserService(){
        userRepository = new UserRepository()  //用于查询数据
    }

    boolean ifUserExist(def loginName){   //判断用户是否已经存在，大部分系统中不允许重复用户存在
        userRepository.getUserInfoByUserName(loginName) ? true : false
    }

    def getEncryptPassword(password, loginName){  //用户名密码加密
        Md5Crypt.apr1Crypt(password, loginName)
    }

    void addUser(def loginName, def password = "abc123", def roleName = "systemManager"){
        if(!ifUserExist(loginName)){
            userRepository.insertSysUserTable(loginName,loginName,getEncryptPassword(password,loginName),"test@163.com", "18181971234", 0)
            userRepository.insertSysUserRoleTable(userRepository.getUserInfoByUserName(loginName).uid, userRepository.getRoleInfoByRoleName(roleName).id)
        }
    }

    void deleteUser(def loginName){  //删除用户
        userRepository.deleteUserRoleTableByUserName(loginName)
        userRepository.deleteUserTableByUserName(loginName)
    }

    def generateUniqueLoginName(){   //产生不重复用户名
        boolean ifContinue
        def loginName
        ifContinue = true
        while(ifContinue){
            loginName = "name" + new Random().nextInt()
            if(!ifUserExist(loginName)){
                ifContinue = false
            }
        }
        loginName
    }
    def generateUserRoleList(roleName){  //生成角色列表
        def roleList = []
        roleList[0] = (userRepository.getRoleInfoByRoleName(roleName)).id
        roleList
    }

    def getUserNumbersForSearch(loginName = "", nickName = "" , status = ""){
        userRepository.searchUserCount(convertSearchKeyWord(loginName), convertSearchKeyWord(nickName), convertSearchKeyWord(status))
    }

    private def convertSearchKeyWord(searchKeyWord){
        searchKeyWord + '%'
    }
}
