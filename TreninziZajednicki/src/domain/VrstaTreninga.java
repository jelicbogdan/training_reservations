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
public class VrstaTreninga extends AbstractDomainObject implements Serializable {
    
    private Long vrstaTreningaID;
    private String nazivVrsteTreninga;

    @Override
    public String toString() {
        return nazivVrsteTreninga;
    }

    public VrstaTreninga(Long vrstaTreningaID, String nazivVrsteTreninga) {
        this.vrstaTreningaID = vrstaTreningaID;
        this.nazivVrsteTreninga = nazivVrsteTreninga;
    }

    public VrstaTreninga() {
    }
    
    @Override
    public String nazivTabele() {
        return " vrstaTreninga ";
    }

    @Override
    public String alijas() {
        return " vt ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            VrstaTreninga vt = new VrstaTreninga(rs.getLong("vrstaTreningaID"), 
                    rs.getString("nazivVrsteTreninga"));
            
            lista.add(vt);
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
        return " vrstaTreningaID = " + vrstaTreningaID;
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

    public Long getVrstaTreningaID() {
        return vrstaTreningaID;
    }

    public void setVrstaTreningaID(Long vrstaTreningaID) {
        this.vrstaTreningaID = vrstaTreningaID;
    }

    public String getNazivVrsteTreninga() {
        return nazivVrsteTreninga;
    }

    public void setNazivVrsteTreninga(String nazivVrsteTreninga) {
        this.nazivVrsteTreninga = nazivVrsteTreninga;
    }
    
}
