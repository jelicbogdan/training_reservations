/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Administrator;
import domain.Klijent;
import domain.Sala;
import domain.Trener;
import domain.Trening;
import domain.Vezbac;
import domain.VrstaTreninga;
import java.util.ArrayList;
import so.administrator.SOGetAllAdministrator;
import so.klijent.SOAddKlijent;
import so.klijent.SODeleteKlijent;
import so.klijent.SOGetAllKlijent;
import so.klijent.SOUpdateKlijent;
import so.login.SOLogin;
import so.sala.SOGetAllSala;
import so.trener.SOGetAllTrener;
import so.trening.SOAddTrening;
import so.trening.SODeleteTrening;
import so.trening.SOGetAllTrening;
import so.trening.SOUpdateTrening;
import so.vezbac.SOGetAllVezbac;
import so.vrstaTreninga.SOGetAllVrstaTreninga;

/**
 *
 * @author PC
 */
public class ServerController {

    private static ServerController instance;

    public ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(administrator);
        return so.getAdmin();
    }

    public void addKlijent(Klijent klijent) throws Exception {
        (new SOAddKlijent()).templateExecute(klijent);
    }
    
    public void addTrening(Trening trening) throws Exception {
        (new SOAddTrening()).templateExecute(trening);
    }

    public void deleteKlijent(Klijent klijent) throws Exception {
        (new SODeleteKlijent()).templateExecute(klijent);
    }

    public void deleteTrening(Trening trening) throws Exception {
        (new SODeleteTrening()).templateExecute(trening);
    }

    public void updateKlijent(Klijent klijent) throws Exception {
        (new SOUpdateKlijent()).templateExecute(klijent);
    }

    public void updateTrening(Trening trening) throws Exception {
        (new SOUpdateTrening()).templateExecute(trening);
    }

    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        SOGetAllAdministrator so = new SOGetAllAdministrator();
        so.templateExecute(new Administrator());
        return so.getLista();
    }

    public ArrayList<Klijent> getAllKlijent() throws Exception {
        SOGetAllKlijent so = new SOGetAllKlijent();
        so.templateExecute(new Klijent());
        return so.getLista();
    }

    public ArrayList<Trening> getAllTrening() throws Exception {
        SOGetAllTrening so = new SOGetAllTrening();
        so.templateExecute(new Trening());
        return so.getLista();
    }

    public ArrayList<Vezbac> getAllVezbac(Trening t) throws Exception {
        SOGetAllVezbac so = new SOGetAllVezbac();
        
        Vezbac v = new Vezbac();
        v.setTrening(t);
        
        so.templateExecute(v);
        return so.getLista();
    }

    public ArrayList<VrstaTreninga> getAllVrstaTreninga() throws Exception {
        SOGetAllVrstaTreninga so = new SOGetAllVrstaTreninga();
        so.templateExecute(new VrstaTreninga());
        return so.getLista();
    }

    public ArrayList<Sala> getAllSala() throws Exception {
        SOGetAllSala so = new SOGetAllSala();
        so.templateExecute(new Sala());
        return so.getLista();
    }

    public ArrayList<Trener> getAllTrener() throws Exception {
        SOGetAllTrener so = new SOGetAllTrener();
        so.templateExecute(new Trener());
        return so.getLista();
    }

}
