package org.unibl.etf.adminapp.beans;

import org.unibl.etf.adminapp.dao.ClientDAO;
import org.unibl.etf.adminapp.dto.Client;

import java.io.Serializable;
import java.util.ArrayList;

public class ClientBean implements Serializable {
    private static final long serialVersionUID = 1L;

    public ArrayList<Client> getAllClients() {
        return ClientDAO.getAllClients();
    }

    public boolean createNewClient(Client client) {
        return ClientDAO.createNewClient(client);
    }

    public boolean deleteClient(String username) {
        return ClientDAO.deleteClient(username);
    }

    public boolean updateClient(Client client) {
        return ClientDAO.updateClient(client);
    }

    public Client getClientWithUsername(String username) {
        return ClientDAO.getClientWithUsername(username);
    }
}
