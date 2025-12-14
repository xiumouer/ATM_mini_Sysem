package com.simple.atm.client;

import com.simple.atm.entity.CommonAtmVO;
import com.simple.atm.entity.CommonResult;
import com.simple.atm.entity.CustomerInfo;
import com.simple.atm.entity.OperateType;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ClientApp {
    private final ClientSocket clientSocket = new ClientSocket();
    private String card;

    public static void main(String[] args) throws Exception {
        new ClientApp().run();
    }

    private void run() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        System.out.println("ATM 客户端");
        System.out.print("卡号:");
        card = br.readLine();
        System.out.print("密码:");
        String pwd = br.readLine();
        CommonAtmVO loginVo = new CommonAtmVO();
        loginVo.setType(OperateType.LOGIN.name());
        loginVo.setSourceCardNumber(card);
        CustomerInfo ci = new CustomerInfo();
        ci.setPassword(pwd);
        loginVo.setCustomerInfo(ci);
        CommonResult login = clientSocket.send(loginVo);
        System.out.println(login.getMessage());
        if (!login.isStatus()) return;
        while (true) {
            System.out.println("1 存款 2 取款 3 转账 4 退出");
            String op = br.readLine();
            if ("1".equals(op)) doDeposit(br);
            else if ("2".equals(op)) doWithdraw(br);
            else if ("3".equals(op)) doTransfer(br);
            else if ("4".equals(op)) { doLogout(); break; }
        }
    }

    private void doDeposit(BufferedReader br) throws Exception {
        System.out.print("金额:");
        Double amt = Double.valueOf(br.readLine());
        CommonAtmVO vo = new CommonAtmVO();
        vo.setType(OperateType.DEPOSIT.name());
        vo.setSourceCardNumber(card);
        vo.setOperatorMoney(amt);
        CommonResult r = clientSocket.send(vo);
        System.out.println(r.getMessage());
    }

    private void doWithdraw(BufferedReader br) throws Exception {
        System.out.print("金额:");
        Double amt = Double.valueOf(br.readLine());
        CommonAtmVO vo = new CommonAtmVO();
        vo.setType(OperateType.WITHDRAW.name());
        vo.setSourceCardNumber(card);
        vo.setOperatorMoney(amt);
        CommonResult r = clientSocket.send(vo);
        System.out.println(r.getMessage());
    }

    private void doTransfer(BufferedReader br) throws Exception {
        System.out.print("目标卡号:");
        String target = br.readLine();
        System.out.print("金额:");
        Double amt = Double.valueOf(br.readLine());
        CommonAtmVO vo = new CommonAtmVO();
        vo.setType(OperateType.TRANSFER.name());
        vo.setSourceCardNumber(card);
        vo.setTargetCardNumber(target);
        vo.setOperatorMoney(amt);
        CommonResult r = clientSocket.send(vo);
        System.out.println(r.getMessage());
    }

    private void doLogout() throws Exception {
        CommonAtmVO vo = new CommonAtmVO();
        vo.setType(OperateType.LOGOUT.name());
        vo.setSourceCardNumber(card);
        CommonResult r = clientSocket.send(vo);
        System.out.println(r.getMessage());
    }
}
