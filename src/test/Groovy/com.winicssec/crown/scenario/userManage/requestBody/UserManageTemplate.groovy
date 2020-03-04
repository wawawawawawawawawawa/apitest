package com.winicssec.crown.scenario.userManage.requestBody

import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine

class UserManageTemplate {
    VelocityEngine velocityEngine = new VelocityEngine()
    VelocityContext velocityContext = new VelocityContext()
    StringWriter stringWriter = new StringWriter()

    def getAddUserRequestBody(UserBody addUserBody){
        velocityContext.put("userInfo",addUserBody.userInfo)
        velocityContext.put("roleIdList",addUserBody.roleIdList)
        velocityEngine.getTemplate("src/test/resources/crown/body/userManage/addUserBodyTemplate.json").merge(velocityContext, stringWriter)
        stringWriter.toString()
        // 上面四行属于固定写法，目的是把数据对象UserBody和模版文件进行merge
    }
}
