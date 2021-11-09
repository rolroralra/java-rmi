package com.example.rmi;

import java.rmi.registry.LocateRegistry;

public class RmiClient {
    public static void main(String[] args) throws Exception {
        RemoteObject remoteObject = (RemoteObject) LocateRegistry.getRegistry(RmiServer.RMI_SERVER_PORT).lookup(RmiServer.BIND_NAME);
        for (int i = 0; i < 10; i++) {
            remoteObject.println(String.format("%d called", i));
            Thread.sleep(1000);
        }
    }
}
