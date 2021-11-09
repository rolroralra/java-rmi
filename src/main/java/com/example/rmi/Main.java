package com.example.rmi;

import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws RemoteException, AlreadyBoundException, NotBoundException {
        // RMI Server
        RmiServer rmiServer = new RmiServer();
        rmiServer.init();


        RmiClient rmiClient = new RmiClient(rmiServer);
        RmiClient rmiClient2 = new RmiClient(rmiServer);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() -> test_add_function_for_rmi(rmiClient));
        executorService.submit(() -> test_poll_function_for_rmi(rmiClient2));


        rmiServer.shutDown();
    }

    private static void test_add_function_for_rmi(RmiClient rmiClient) {
        for (int i = 0; i < 10; i++) {
            try {
                rmiClient.add(i);
                Thread.sleep(200);
            } catch (RemoteException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void test_poll_function_for_rmi(RmiClient rmiClient) {
        while(true) {
            try {
                rmiClient.rebind();
                if (rmiClient.isEmpty()) continue;

                rmiClient.poll();
                Thread.sleep(100);
            } catch (RemoteException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
