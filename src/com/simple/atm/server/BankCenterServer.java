package com.simple.atm.server;

import com.simple.atm.dao.ChargeDao;
import com.simple.atm.dao.LogDao;
import com.simple.atm.dao.LoginDao;
import com.simple.atm.entity.CommonAtmVO;
import com.simple.atm.entity.CommonResult;
import com.simple.atm.entity.CustomerInfo;
import com.simple.atm.entity.AccountQueryResult;
import com.simple.atm.entity.OperateType;
import com.simple.atm.util.Config;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BankCenterServer {
    private final ProcessContext context;

    public BankCenterServer() {
        LoginDao loginDao = new LoginDao();
        ChargeDao chargeDao = new ChargeDao();
        LogDao logDao = new LogDao();
        this.context = new ProcessContext(loginDao, chargeDao, logDao);
        seed(loginDao);
    }

    private void seed(LoginDao loginDao) {
        loginDao.save(new CustomerInfo("10001", "123456", "Alice", Double.valueOf(1000)));
        loginDao.save(new CustomerInfo("10002", "123456", "Bob", Double.valueOf(500)));
    }

    public void start() throws Exception {
        ServerSocket server = new ServerSocket(Config.PORT);
        while (true) {
            final Socket socket = server.accept();
            Thread t = new Thread(new Runnable() {
                public void run() {
                    handle(socket);
                }
            });
            t.start();
        }
    }

    private void handle(Socket socket) {
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Object obj = in.readObject();
            CommonResult result;
            if (obj instanceof CommonAtmVO) {
                CommonAtmVO vo = (CommonAtmVO) obj;
                IStrategy strategy = ServerProcessIOUtil.getStrategy(vo.getType());
                if (strategy != null) {
                    result = strategy.process(vo, context);
                } else {
                    result = new CommonResult(false, "unknown type");
                }
            } else {
                result = new CommonResult(false, "invalid request");
            }
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            if (obj instanceof CommonAtmVO && OperateType.QUERY.name().equalsIgnoreCase(((CommonAtmVO) obj).getType())) {
                AccountQueryResult qr = buildQuery((CommonAtmVO) obj);
                out.writeObject(qr);
            } else {
                out.writeObject(result);
            }
            out.flush();
            socket.close();
        } catch (Exception e) {
            try {
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(new CommonResult(false, "server error"));
                out.flush();
                socket.close();
            } catch (Exception ignored) {}
        }
    }

    public static void main(String[] args) throws Exception {
        new BankCenterServer().start();
    }

    private AccountQueryResult buildQuery(CommonAtmVO vo) {
        StrategyQueryImpl query = new StrategyQueryImpl();
        // reuse strategy internals by calling process and then manually build result again for data
        // Here we directly construct via context
        CustomerInfo info = context.getLoginDao().findByCard(vo.getSourceCardNumber());
        AccountQueryResult result = new AccountQueryResult();
        if (info == null) {
            result.setStatus(false);
            result.setMessage("account not found");
            return result;
        }
        result.setStatus(true);
        result.setMessage("query success");
        result.setCardNumber(info.getCardNumber());
        result.setName(info.getName());
        result.setBalance(info.getBalance());
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.ArrayList<String> logs = new java.util.ArrayList<String>();
        for (com.simple.atm.entity.ChargeRecorder r : context.getChargeDao().all()) {
            if (info.getCardNumber().equals(r.getCardNumber())) {
                logs.add("用户在" + sdf.format(r.getTime()) + ("deposit".equals(r.getType()) ? "存入" : ("withdraw".equals(r.getType()) ? "取出" : "转账")) + r.getAmount() + "元");
            }
        }
        result.setLogs(logs);
        return result;
    }
}
