package com.wvkia.springsource.Container.Dependencies_4;

public class ClientService {
    private static ClientService clientService = new ClientService();
    private ClientService(){}

    public static ClientService createInstance() {
        return clientService;
    }
}
