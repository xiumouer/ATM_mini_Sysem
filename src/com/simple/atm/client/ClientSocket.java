package com.simple.atm.client;

import com.simple.atm.entity.CommonAtmVO;
import com.simple.atm.entity.CommonResult;
import com.simple.atm.util.Config;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocket {
    public CommonResult send(CommonAtmVO vo) throws Exception {
        Socket socket = new Socket(Config.HOST, Config.PORT);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(vo);
        out.flush();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Object obj = in.readObject();
        socket.close();
        if (obj instanceof CommonResult) {
            return (CommonResult) obj;
        }
        return new CommonResult(false, "invalid response");
    }

    public Object sendObject(CommonAtmVO vo) throws Exception {
        Socket socket = new Socket(Config.HOST, Config.PORT);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        out.writeObject(vo);
        out.flush();
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        Object obj = in.readObject();
        socket.close();
        return obj;
    }
}
