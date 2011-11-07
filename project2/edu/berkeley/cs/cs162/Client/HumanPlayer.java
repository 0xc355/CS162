package edu.berkeley.cs.cs162.Client;

import edu.berkeley.cs.cs162.Writable.ClientInfo;
import edu.berkeley.cs.cs162.Writable.Message;
import edu.berkeley.cs.cs162.Writable.MessageFactory;
import edu.berkeley.cs.cs162.Writable.MessageProtocol;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;

public class HumanPlayer extends Player {

    public HumanPlayer(String name) {
        super(name, MessageProtocol.TYPE_HUMAN);
    }

    private void connectTo(String address, Integer port) {
        try {
            Socket c1 = new Socket(address, port);
            Socket c2 = new Socket(address, port);

            ServerConnection con = new ServerConnection(c1, c2);
            System.out.println(con.initiate3WayHandshake(new Random()));
            Message connectMessage = MessageFactory.createConnectMessage(clientInfo);

            Message ok = con.sendSyncToServer(connectMessage);

            if (ok.getMsgType() == MessageProtocol.OP_STATUS_OK) {
                System.out.println("Status OK, connected");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void handleMessage(Message m) {
        switch (m.getMsgType()) {
            case MessageProtocol.OP_TYPE_GAMESTART:
                break;
            case MessageProtocol.OP_TYPE_GAMEOVER:
                break;
            case MessageProtocol.OP_TYPE_MAKEMOVE:
                break;
            case MessageProtocol.OP_TYPE_GETMOVE:
                break;
            default:
                break;
        }
    }
}
