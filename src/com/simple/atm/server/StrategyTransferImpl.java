package com.simple.atm.server;

import com.simple.atm.entity.CommonAtmVO;
import com.simple.atm.entity.CommonResult;

public class StrategyTransferImpl implements IStrategy {
    public CommonResult process(CommonAtmVO vo, ProcessContext ctx) {
        boolean ok = ctx.getAtmService().transfer(vo.getSourceCardNumber(), vo.getTargetCardNumber(), vo.getOperatorMoney());
        if (ok) return new CommonResult(true, "transfer success");
        return new CommonResult(false, "transfer failed");
    }
}
