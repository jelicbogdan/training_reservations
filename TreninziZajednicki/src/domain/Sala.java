/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Korisnik
 */
public class Sala extends AbstractDomainObject implements Serializable {
    
    private Long salaID;
    private String nazivSale;

    @Override
    public String toString() {
        return nazivSale;
    }

    public Sala(Long salaID, String nazivSale) {
        this.salaID = salaID;
        this.nazivSale = nazivSale;
    }

    public Sala() {
    }
    
    @Override
    public String nazivTabele() {
        return " sala ";
    }

    @Override
    public String alijas() {
        return " s ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Sala s = new Sala(rs.getLong("salaID"), rs.getString("nazivSale"));
            
            lista.add(s);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return "";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " salaID = " + salaID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return "";
    }

    public Long getSalaID() {
        return salaID;
    }

    public void setSalaID(Long salaID) {
        this.salaID = salaID;
    }

    public String getNazivSale() {
        return nazivSale;
    }

    public void setNazivSale(String nazivSale) {
        this.nazivSale = nazivSale;
    }
    
}
