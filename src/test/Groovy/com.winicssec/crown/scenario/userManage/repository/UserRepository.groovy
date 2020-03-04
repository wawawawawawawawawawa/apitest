package com.winicssec.crown.scenario.userManage.repository

/**
 * 此类用于操作数据库获取需要的数据
 */
class UserRepository extends com.winicssec.crown.util.CrownDataSource{
    def getUserInfoByUserName(loginName) {
        def getUserInfoByLoginNameSql = "select * from sys_user where login_name=?"
        def userInfo = crownDB.firstRow(getUserInfoByLoginNameSql, [loginName])
        userInfo ? userInfo : ""
    }

    def insertSysUserTable(loginName, nickName, password, email, phone, status) {  //插入用户
        def insertUserSql = "insert into sys_user(login_name,nickName,password,email,phone,status,create_uid,create_time,update_time) values (?,?,?,?,?,?,1,'2019-07-01 10:00:00','2019-07-01 10:00:00')"
        crownDB.execute(insertUserSql, [loginName, nickName, password, email, phone, status])
    }

    def insertSysRoleTable(roleName) {  //插入角色
        def insertRoleSql = "insert into sys_role(role_name,remark,create_time,update_time,create_uid,update_uid) values (?,'addUserByApiTest','2019-07-01 10:00:00','2019-07-01 10:00:00',1,1)"
        crownDB.execute(insertRoleSql, [roleName])
    }

    void deleteUserTableByUserName(userName) {  //根据用户名删除用户
        def deleteSysUserRoleSql = "delete from sys_user where login_name=?"
        crownDB.execute(deleteSysUserRoleSql, [userName])
    }

    void deleteUserRoleTableByUserName(userName) {  //根据角色名删除角色
        def deleteSysUserRoleSql = "delete from sys_user_role where uid in (select uid from sys_user where login_name=?)"
        crownDB.execute(deleteSysUserRoleSql, [userName])
    }

    def insertSysUserRoleTable(uid, roleId) {   //插入角色
        def insertSysUserRoleSql = "insert into sys_user_role(uid,role_id) values(?,?)"
        crownDB.execute(insertSysUserRoleSql, [uid, roleId])
    }

    def getRoleInfoByRoleName(roleName) {  //根据角色名获取角色信息
        def getRoleInfoSql = "select * from sys_role where role_name=?"
        def roleInfo = crownDB.firstRow(getRoleInfoSql, [roleName])
        roleInfo ? roleInfo : ""
    }

    def getRoleMenuRel(roleId, menuId) {
        def roleIdMenuInfoSql = "select * from sys_role_menu where role_id=? and menu_id=?"
        def roleMenuInfo = crownDB.firstRow(roleIdMenuInfoSql, [roleId, menuId])
        roleMenuInfo ? roleMenuInfo : ""
    }

    def insertSysRoleMenuTable(roleId, menuId) {
        def insertRoleMenuSql = "insert sys_role_menu(role_id,menu_id) values (?,?)"
        crownDB.execute(insertRoleMenuSql, [roleId, menuId])
    }

    def searchUserCount(loginName, nickName, status) {
        def searchUserSql = "select count(*) as numbers from sys_user where login_name like ? and nickname like ? and status like ?"
        crownDB.firstRow(searchUserSql, [loginName, nickName, status]).numbers
    }

    def getUserRole(loginName) {
        def getUserRoleSql="select * from sys_user_role where uid in (select uid from sys_user where login_name =?)"
        crownDB.firstRow(getUserRoleSql,[loginName])

    }
}
