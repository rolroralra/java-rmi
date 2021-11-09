package com.example.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Main {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, InterruptedException, NotBoundException {
        // RMI Server
        RmiServer rmiServer = new RmiServer();
        rmiServer.init();


        // RMI Client
        RemoteObject remoteObject = (RemoteObject) LocateRegistry.getRegistry(RmiServer.RMI_SERVER_PORT).lookup(RmiServer.BIND_NAME);
        for (int i = 0; i < 10; i++) {
            remoteObject.println(String.format("%d called", i));
            Thread.sleep(1000);
        }
    }
}
