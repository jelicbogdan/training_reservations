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
public class Trener extends AbstractDomainObject implements Serializable {

    private Long trenerID;
    private String imeTrenera;
    private String prezimeTrenera;
    private int godineIskustva;
    private String opisIskustva;

    @Override
    public String toString() {
        return imeTrenera + " " + prezimeTrenera + " (Godine iskustva: " + godineIskustva + ")";
    }

    public Trener(Long trenerID, String imeTrenera, String prezimeTrenera, int godineIskustva, String opisIskustva) {
        this.trenerID = trenerID;
        this.imeTrenera = imeTrenera;
        this.prezimeTrenera = prezimeTrenera;
        this.godineIskustva = godineIskustva;
        this.opisIskustva = opisIskustva;
    }

    public Trener() {
    }

    @Override
    public String nazivTabele() {
        return " trener ";
    }

    @Override
    public String alijas() {
        return " tr ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Trener tr = new Trener(rs.getLong("trenerID"), rs.getString("imeTrenera"),
                    rs.getString("prezimeTrenera"), rs.getInt("godineIskustva"),
                    rs.getString("opisIskustva"));

            lista.add(tr);
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
        return " trenerID = " + trenerID;
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

    public Long getTrenerID() {
        return trenerID;
    }

    public void setTrenerID(Long trenerID) {
        this.trenerID = trenerID;
    }

    public String getImeTrenera() {
        return imeTrenera;
    }

    public void setImeTrenera(String imeTrenera) {
        this.imeTrenera = imeTrenera;
    }

    public String getPrezimeTrenera() {
        return prezimeTrenera;
    }

    public void setPrezimeTrenera(String prezimeTrenera) {
        this.prezimeTrenera = prezimeTrenera;
    }

    public int getGodineIskustva() {
        return godineIskustva;
    }

    public void setGodineIskustva(int godineIskustva) {
        this.godineIskustva = godineIskustva;
    }

    public String getOpisIskustva() {
        return opisIskustva;
    }

    public void setOpisIskustva(String opisIskustva) {
        this.opisIskustva = opisIskustva;
    }

}
