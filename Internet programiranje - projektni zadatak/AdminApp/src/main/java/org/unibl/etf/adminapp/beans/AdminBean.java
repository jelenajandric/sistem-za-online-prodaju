package org.unibl.etf.adminapp.beans;

import org.unibl.etf.adminapp.dao.AdminDAO;
import org.unibl.etf.adminapp.dto.Admin;

import java.io.Serializable;

public class AdminBean implements Serializable {
    private Admin admin = new Admin();
    private boolean isLoggedIn = false;

    public Admin getAdmin() {
        return admin;
    }

    public boolean checkUsernameAndPassword(String username, String password) {
        admin = AdminDAO.getAdminByUsername(username);
        if (admin == null) {
            return false;
        } else {
            if (password.equals(admin.getPassword())) {
                isLoggedIn = true;
                return true;
            }
        }
        admin = new Admin();
        return false;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void logout() {
        admin = new Admin();
        isLoggedIn = false;
    }
}
