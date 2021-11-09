package com.example.rmi;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.Queue;

@EqualsAndHashCode(callSuper = true)
@Data
public class RmiServer extends UnicastRemoteObject implements RemoteObject {
    private static final long serialVersionUID = 1L;

    private final String bindName;
    private final Integer rmiServerPort;
    private Registry registry;
    private Queue<Integer> queue = new LinkedList<>();

    public RmiServer() throws RemoteException {
        this.bindName = "rolroralra";
        rmiServerPort = 2021;
    }



    public void init() throws RemoteException, AlreadyBoundException {
        registry = LocateRegistry.createRegistry(rmiServerPort);
        registry.bind(bindName, this);
    }

    public void shutDown() throws NotBoundException, RemoteException {
        registry.unbind(bindName);
    }

    @Override
    public void println(String message) throws RemoteException {
        System.out.printf("[%s - %s] %s%n", this.getClass().getSimpleName(), Thread.currentThread(), message);
    }

    @Override
    public Integer poll() {
        if (!queue.isEmpty()) {
            System.out.printf("[%s - %s] poll %d%n", this.getClass().getSimpleName(), Thread.currentThread(), queue.peek());
        }

        return queue.poll();
    }

    @Override
    public void add(Integer input) {
        System.out.printf("[%s - %s] add %d%n", this.getClass().getSimpleName(), Thread.currentThread(), input);

        queue.add(input);
    }

    @Override
    public boolean isEmpty() throws RemoteException {
        return queue.isEmpty();
    }

}
