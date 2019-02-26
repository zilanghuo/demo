package com.zilanghuo.java8.rmi.mode1;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class UserHandlerImpl extends UnicastRemoteObject implements UserHandler {


    protected UserHandlerImpl() throws RemoteException {
    }

    @Override
    public String getUserName(int id) throws RemoteException {
        return "serverBegin--";
    }

    @Override
    public int getUserCount() throws RemoteException {
        return 1;
    }

    @Override
    public User getUserByName(String name) throws RemoteException {
        return new User();
    }
}
