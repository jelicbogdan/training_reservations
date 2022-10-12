/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Korisnik
 */
public class Trening extends AbstractDomainObject implements Serializable {

    private Long treningID;
    private Date datumVremePocetka;
    private String opisTreninga;
    private Trener trener;
    private Sala sala;
    private VrstaTreninga vrstaTreninga;
    private Administrator administrator;
    private ArrayList<Vezbac> vezbaci;

    public Trening(Long treningID, Date datumVremePocetka, String opisTreninga, Trener trener, Sala sala, VrstaTreninga vrstaTreninga, Administrator administrator, ArrayList<Vezbac> vezbaci) {
        this.treningID = treningID;
        this.datumVremePocetka = datumVremePocetka;
        this.opisTreninga = opisTreninga;
        this.trener = trener;
        this.sala = sala;
        this.vrstaTreninga = vrstaTreninga;
        this.administrator = administrator;
        this.vezbaci = vezbaci;
    }

    public Trening() {
    }

    @Override
    public String nazivTabele() {
        return " trening ";
    }

    @Override
    public String alijas() {
        return " t ";
    }

    @Override
    public String join() {
        return " JOIN TRENER TR ON (TR.TRENERID = T.TRENERID) "
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

            lista.add(t);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumVremePocetka, opisTreninga, trenerID, salaID, vrstaTreningaID, administratorID) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " treningID = " + treningID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + new Timestamp(datumVremePocetka.getTime()) + "', "
                + "'" + opisTreninga + "', "
                + trener.getTrenerID() + ", " + sala.getSalaID() + ", "
                + vrstaTreninga.getVrstaTreningaID() + ", " + administrator.getAdministratorID() + " ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " datumVremePocetka = '" + new Timestamp(datumVremePocetka.getTime()) + "', "
                + "opisTreninga = '" + opisTreninga + "', "
                + "TrenerID = " + trener.getTrenerID() + ", SalaID = " + sala.getSalaID() + " ";
    }

    @Override
    public String uslov() {
        return "";
    }

    public Long getTreningID() {
        return treningID;
    }

    public void setTreningID(Long treningID) {
        this.treningID = treningID;
    }

    public Date getDatumVremePocetka() {
        return datumVremePocetka;
    }

    public void setDatumVremePocetka(Date datumVremePocetka) {
        this.datumVremePocetka = datumVremePocetka;
    }

    public String getOpisTreninga() {
        return opisTreninga;
    }

    public void setOpisTreninga(String opisTreninga) {
        this.opisTreninga = opisTreninga;
    }

    public Trener getTrener() {
        return trener;
    }

    public void setTrener(Trener trener) {
        this.trener = trener;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public VrstaTreninga getVrstaTreninga() {
        return vrstaTreninga;
    }

    public void setVrstaTreninga(VrstaTreninga vrstaTreninga) {
        this.vrstaTreninga = vrstaTreninga;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    public ArrayList<Vezbac> getVezbaci() {
        return vezbaci;
    }

    public void setVezbaci(ArrayList<Vezbac> vezbaci) {
        this.vezbaci = vezbaci;
    }

}
