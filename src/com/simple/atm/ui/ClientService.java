package com.simple.atm.ui;

import com.simple.atm.client.ClientSocket;
import com.simple.atm.entity.CommonAtmVO;
import com.simple.atm.entity.CommonResult;
import com.simple.atm.entity.CustomerInfo;
import com.simple.atm.entity.OperateType;
import com.simple.atm.entity.AccountQueryResult;

public class ClientService {
    private final ClientSocket socket = new ClientSocket();

    public CommonResult login(String card, String password) throws Exception {
        CommonAtmVO vo = new CommonAtmVO();
        vo.setType(OperateType.LOGIN.name());
        vo.setSourceCardNumber(card);
        CustomerInfo ci = new CustomerInfo();
        ci.setPassword(password);
        vo.setCustomerInfo(ci);
        return socket.send(vo);
    }

    public CommonResult logout(String card) throws Exception {
        CommonAtmVO vo = new CommonAtmVO();
        vo.setType(OperateType.LOGOUT.name());
        vo.setSourceCardNumber(card);
        return socket.send(vo);
    }

    public CommonResult deposit(String card, Double amount) throws Exception {
        CommonAtmVO vo = new CommonAtmVO();
        vo.setType(OperateType.DEPOSIT.name());
        vo.setSourceCardNumber(card);
        vo.setOperatorMoney(amount);
        return socket.send(vo);
    }

    public CommonResult withdraw(String card, Double amount) throws Exception {
        CommonAtmVO vo = new CommonAtmVO();
        vo.setType(OperateType.WITHDRAW.name());
        vo.setSourceCardNumber(card);
        vo.setOperatorMoney(amount);
        return socket.send(vo);
    }

    public CommonResult transfer(String sourceCard, String targetCard, Double amount) throws Exception {
        CommonAtmVO vo = new CommonAtmVO();
        vo.setType(OperateType.TRANSFER.name());
        vo.setSourceCardNumber(sourceCard);
        vo.setTargetCardNumber(targetCard);
        vo.setOperatorMoney(amount);
        return socket.send(vo);
    }

    public AccountQueryResult query(String card) throws Exception {
        CommonAtmVO vo = new CommonAtmVO();
        vo.setType(OperateType.QUERY.name());
        vo.setSourceCardNumber(card);
        Object obj = socket.sendObject(vo);
        if (obj instanceof AccountQueryResult) {
            return (AccountQueryResult) obj;
        }
        AccountQueryResult fail = new AccountQueryResult();
        fail.setStatus(false);
        fail.setMessage("invalid response");
        return fail;
    }
}
