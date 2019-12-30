package com.winicssec.crown.scenario.userManage.requestBody

class UserBody {
    def userInfo = [:]
    def roleIdList = [:]
    UserManageTemplate userManageTemplate

    UserBody(){
        userManageTemplate = new UserManageTemplate()
    }

    def setUserLoginName(loginName){
        this.userInfo.LoginName = loginName
        this
    }

    def setUserNickName(nickName){
        this.userInfo.nickName = nickName
        this
    }
    def setUserPhone(phone){
        this.userInfo.phone=phone
        this
    }

    def setUserEmail(email) {
        this.userInfo.email=email
        this
    }

    def setRoleIdList(roleIdList) {
        this.roleIdList = roleIdList
        this
    }

    def setUserIdForEditUser(id)
    {   this.userInfo.isEditUser=true
        this.userInfo.id=id
        this
    }

    def getAddUserBody() {
        userManageTemplate.getAddUserRequestBody(this)
    }

}
