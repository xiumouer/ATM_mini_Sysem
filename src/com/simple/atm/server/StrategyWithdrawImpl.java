package com.simple.atm.server;

import com.simple.atm.entity.CommonAtmVO;
import com.simple.atm.entity.CommonResult;

public class StrategyWithdrawImpl implements IStrategy {
    public CommonResult process(CommonAtmVO vo, ProcessContext ctx) {
        boolean ok = ctx.getAtmService().withdraw(vo.getSourceCardNumber(), vo.getOperatorMoney());
        if (ok) return new CommonResult(true, "withdraw success");
        return new CommonResult(false, "withdraw failed");
    }
}
