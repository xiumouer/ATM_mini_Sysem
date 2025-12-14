package com.simple.atm.server;

import com.simple.atm.entity.CommonAtmVO;
import com.simple.atm.entity.CommonResult;

public interface IStrategy {
    CommonResult process(CommonAtmVO vo, ProcessContext ctx);
}
