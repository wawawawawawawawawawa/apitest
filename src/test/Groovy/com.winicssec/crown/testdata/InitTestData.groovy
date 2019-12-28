package com.winicssec.crown.testdata

import com.winicssec.crown.testdata.service.GlobalUserService
import org.junit.Test

class InitTestData {
   GlobalUserService globalUserService

    InitTestData(){
        globalUserService = new GlobalUserService()
    }

    @Test
    void InitGlobalTestData(){
        globalUserService.initGlobalUser()
    }
}
