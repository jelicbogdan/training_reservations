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
public class Vezbac extends AbstractDomainObject implements Serializable {

    private Trening trening;
    private int rbVezbaca;
    private String napomena;
    private Klijent klijent;

    public Vezbac(Trening trening, int rbVezbaca, String napomena, Klijent klijent) {
        this.trening = trening;
        this.rbVezbaca = rbVezbaca;
        this.napomena = napomena;
        this.klijent = klijent;
    }

    public Vezbac() {
    }

    @Override
    public String nazivTabele() {
        return " vezbac ";
    }

    @Override
    public String alijas() {
        return " v ";
    }

    @Override
    public String join() {
        return " JOIN KLIJENT K USING (KLIJENTID) "
                + "JOIN TRENING T USING (TRENINGID) "
                + "JOIN TRENER TR ON (TR.TRENERID = T.TRENERID) "
                + "JOIN SALA S ON (S.SALAID = T.SALAID) "
                + "JOIN VRSTATRENINGA VT ON (VT.VRSTATRENINGAID = T.VRSTATRENINGAID) "
                + "JOIN ADMINISTRATOR A ON (A.ADMINISTRATORID = T.ADMINISTRATORID) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Administrator a = new Administrator(rs.getLong("AdministratorID"),
                    rs.getString("Ime"), rs.getString("Prezime"),
                    rs.getString("Username"), rs.getString("Password"));
            
            Trener tr = new Trener(rs.getLong("trenerID"), rs.getString("imeTrenera"),
                    rs.getString("prezimeTrenera"), rs.getInt("godineIskustva"),
                    rs.getString("opisIskustva"));
            
            Sala s = new Sala(rs.getLong("salaID"), rs.getString("nazivSale"));
            
            VrstaTreninga vt = new VrstaTreninga(rs.getLong("vrstaTreningaID"), 
                    rs.getString("nazivVrsteTreninga"));
            
            Trening t = new Trening(rs.getLong("treningID"), rs.getTimestamp("datumVremePocetka"), 
                    rs.getString("opisTreninga"), tr, s, vt, a, null);
            
            Klijent k = new Klijent(rs.getLong("klijentID"), 
                    rs.getString("imeKlijenta"), rs.getString("prezimeKlijenta"), 
                    rs.getString("email"), rs.getString("telefon"));
            
            Vezbac v = new Vezbac(t, rs.getInt("rbVezbaca"), rs.getString("napomena"), k);

            lista.add(v);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (treningID, rbVezbaca, napomena, klijentID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " treningID = " + trening.getTreningID();
    }

    @Override
    public String vrednostiZaInsert() {
        return trening.getTreningID() + ", " + rbVezbaca + ", "
                + "'" + napomena + "', " + klijent.getKlijentID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return " WHERE T.TRENINGID = " + trening.getTreningID();
    }

    public Trening getTrening() {
        return trening;
    }

    public void setTrening(Trening trening) {
        this.trening = trening;
    }

    public int getRbVezbaca() {
        return rbVezbaca;
    }

    public void setRbVezbaca(int rbVezbaca) {
        this.rbVezbaca = rbVezbaca;
    }

    public String getNapomena() {
        return napomena;
    }

    public void setNapomena(String napomena) {
        this.napomena = napomena;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

}
