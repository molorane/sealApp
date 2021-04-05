package org.dclm.sealApp.model.converter;

import org.dclm.sealApp.model.Account;
import org.dclm.sealApp.model.Role;
import org.dclm.sealApp.model.res.AccountRes;
import org.dclm.sealApp.model.res.RoleRes;
import org.springframework.beans.BeanUtils;

public class AccountConverter {

    public static AccountRes convertAccountToAccountRes(Account account){
        AccountRes accountRes = new AccountRes();
        BeanUtils.copyProperties(account, accountRes);
        return accountRes;
    }

    public static RoleRes convertRoleToRoleRes(Role role){
        RoleRes roleRes = new RoleRes();
        BeanUtils.copyProperties(role, roleRes);
        return roleRes;
    }
}
