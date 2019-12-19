package com.winicssec.fiveCourse

import org.apache.velocity.VelocityContext
import org.apache.velocity.app.VelocityEngine

class TemplateService {
    VelocityEngine velocityEngine = new VelocityEngine()
    VelocityContext velocityContext = new VelocityContext()
    StringWriter stringWriter = new StringWriter()

    def getAddUserRequestBody(addUserBody) {
        velocityContext.put("addUserBody",addUserBody)
        velocityEngine.getTemplate("src/test/resources/addUserTemplate.json").merge(velocityContext,stringWriter)
        stringWriter.toString()
    }
}
