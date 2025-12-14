package com.simple.atm.server;

import com.simple.atm.entity.CommonAtmVO;
import com.simple.atm.entity.CommonResult;

public class StrategyDepositImpl implements IStrategy {
    public CommonResult process(CommonAtmVO vo, ProcessContext ctx) {
        boolean ok = ctx.getAtmService().deposit(vo.getSourceCardNumber(), vo.getOperatorMoney());
        if (ok) return new CommonResult(true, "deposit success");
        return new CommonResult(false, "deposit failed");
    }
}
