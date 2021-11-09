package com.example.rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class RmiClient implements RemoteObject{
    private final RmiServer rmiServer;

    public RmiClient(RmiServer rmiServer) throws RemoteException {
        this.rmiServer = rmiServer;
        rebind();
    }

    public RmiClient(String bindName) throws NotBoundException, RemoteException {
        this.rmiServer = bind(bindName);
    }

    public void rebind() throws RemoteException {
        LocateRegistry.getRegistry(rmiServer.getRmiServerPort()).rebind(rmiServer.getBindName(), rmiServer);
    }

    public RmiServer bind(String bindName) throws RemoteException, NotBoundException {
        return (RmiServer)LocateRegistry.getRegistry().lookup(bindName);
    }

    @Override
    public void println(String message) throws RemoteException {
        rmiServer.println(message);
    }

    @Override
    public Integer poll() throws RemoteException {
        return rmiServer.poll();
    }

    @Override
    public void add(Integer input) throws RemoteException {
        rmiServer.add(input);
    }

    @Override
    public boolean isEmpty() throws RemoteException {
        return rmiServer.isEmpty();
    }
}
