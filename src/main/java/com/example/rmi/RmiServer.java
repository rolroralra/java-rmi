package com.example.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiServer extends UnicastRemoteObject implements RemoteObject {
    private static final long serialVersionUID = 1L;
    public static final String BIND_NAME = "rolroralra";
    public static final Integer RMI_SERVER_PORT = 2021;

    public RmiServer() throws RemoteException {
    }

    public void init() throws RemoteException, AlreadyBoundException {
        Registry registry = LocateRegistry.createRegistry(RMI_SERVER_PORT);
        registry.bind(BIND_NAME, this);
    }

    @Override
    public void println(String message) throws RemoteException {
        System.out.printf("[%s] %s%n", this.getClass().getSimpleName(), message);
    }
}
