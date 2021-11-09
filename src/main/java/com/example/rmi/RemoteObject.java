package com.example.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteObject extends Remote {
    void println(String message) throws RemoteException;
    Integer poll() throws RemoteException;
    void add(Integer input) throws RemoteException;
    boolean isEmpty() throws RemoteException;
}
