package com.simple.atm.server;

import com.simple.atm.entity.OperateType;

public class ServerProcessIOUtil {
    public static IStrategy getStrategy(String type) {
        if (type == null) return null;
        if (OperateType.LOGIN.name().equalsIgnoreCase(type)) return new StrategyLoginImpl();
        if (OperateType.LOGOUT.name().equalsIgnoreCase(type)) return new StrategyLogoutImpl();
        if (OperateType.DEPOSIT.name().equalsIgnoreCase(type)) return new StrategyDepositImpl();
        if (OperateType.WITHDRAW.name().equalsIgnoreCase(type)) return new StrategyWithdrawImpl();
        if (OperateType.TRANSFER.name().equalsIgnoreCase(type)) return new StrategyTransferImpl();
        if (OperateType.QUERY.name().equalsIgnoreCase(type)) return new StrategyQueryImpl();
        return null;
    }
}
