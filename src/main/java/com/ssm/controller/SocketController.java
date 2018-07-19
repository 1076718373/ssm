package com.ssm.controller;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;


@ServerEndpoint("/soc")
public class SocketController {

    private Session session;

    public static CopyOnWriteArraySet<SocketController> controllers =
            new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        controllers.add(this);
    }

    @OnMessage
    public void onMessage(String message) {
//        for (SocketController items:controllers) {
//            try {
//                items.onSendMessage(message);
//            } catch (IOException e) {
//                e.printStackTrace();
//                continue;
//            }
//        }
    }

    @OnClose
    public void onClose() {
        controllers.remove(this);
    }

    public void onSendMessage(String message) throws  IOException {
        this.session.getBasicRemote().sendText(message);
    }

}
