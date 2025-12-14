package com.simple.atm.server;

import com.simple.atm.entity.CommonAtmVO;
import com.simple.atm.entity.CommonResult;

public class StrategyLogoutImpl implements IStrategy {
    public CommonResult process(CommonAtmVO vo, ProcessContext ctx) {
        ctx.getLoginService().logout(vo.getSourceCardNumber());
        return new CommonResult(true, "logout success");
    }
}
