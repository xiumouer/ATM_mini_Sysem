package com.simple.atm.server;

import com.simple.atm.entity.CommonAtmVO;
import com.simple.atm.entity.CommonResult;

public class StrategyLoginImpl implements IStrategy {
    public CommonResult process(CommonAtmVO vo, ProcessContext ctx) {
        boolean ok = ctx.getLoginService().login(vo.getSourceCardNumber(), vo.getCustomerInfo() == null ? null : vo.getCustomerInfo().getPassword());
        if (ok) return new CommonResult(true, "login success");
        return new CommonResult(false, "login failed");
    }
}
