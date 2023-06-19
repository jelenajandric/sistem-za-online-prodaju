package org.unibl.etf.adminapp.beans;

import org.unibl.etf.adminapp.dao.CategoryDAO;
import org.unibl.etf.adminapp.dao.StatisticDAO;
import org.unibl.etf.adminapp.dto.Category;
import org.unibl.etf.adminapp.dto.Statistic;

import java.util.ArrayList;

public class StatisticBean {

    public ArrayList<Statistic> getAll() { //da li da u ovoj klasi napravim kao atribut listu categorija
        return StatisticDAO.getAll();
    }
}
