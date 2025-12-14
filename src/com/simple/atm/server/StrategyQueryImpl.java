package com.simple.atm.server;

import com.simple.atm.entity.AccountQueryResult;
import com.simple.atm.entity.ChargeRecorder;
import com.simple.atm.entity.CommonAtmVO;
import com.simple.atm.entity.CommonResult;
import com.simple.atm.entity.CustomerInfo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class StrategyQueryImpl implements IStrategy {
    public CommonResult process(CommonAtmVO vo, ProcessContext ctx) {
        CustomerInfo info = ctx.getLoginDao().findByCard(vo.getSourceCardNumber());
        AccountQueryResult result = new AccountQueryResult();
        if (info == null) {
            result.setStatus(false);
            result.setMessage("account not found");
            return wrap(result);
        }
        result.setStatus(true);
        result.setMessage("query success");
        result.setCardNumber(info.getCardNumber());
        result.setName(info.getName());
        result.setBalance(info.getBalance());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<String> logs = new ArrayList<String>();
        for (ChargeRecorder r : ctx.getChargeDao().all()) {
            if (info.getCardNumber().equals(r.getCardNumber())) {
                logs.add("用户在" + sdf.format(r.getTime()) + ("deposit".equals(r.getType()) ? "存入" : ("withdraw".equals(r.getType()) ? "取出" : "转账")) + r.getAmount() + "元");
            }
        }
        result.setLogs(logs);
        return wrap(result);
    }

    private CommonResult wrap(AccountQueryResult qr) {
        CommonResult cr = new CommonResult(qr.isStatus(), qr.getMessage());
        return cr;
    }
}
