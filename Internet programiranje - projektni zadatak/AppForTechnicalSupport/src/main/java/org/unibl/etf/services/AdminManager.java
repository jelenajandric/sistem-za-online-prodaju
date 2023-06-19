package org.unibl.etf.services;

import java.io.Serializable;

import org.unibl.etf.beans.AdminBean;
import org.unibl.etf.dao.AdminDAO;

public class AdminManager implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AdminManager() {
		// TODO Auto-generated constructor stub
	}
	
	public AdminBean login(String username, String password) {
		if(username==null || password == null) {
			return null;
		}
		AdminBean adminBean = AdminDAO.getAdminByUsername(username);
		if(adminBean!=null) {
			if(!adminBean.getPassword().equals(password)) {
				return null;
			}
		}
		return adminBean;
	}
}
