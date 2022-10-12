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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Session;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author PC
 */
public class ClientController {

    private static ClientController instance;

    public ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        return (Administrator) sendRequest(Operation.LOGIN, administrator);
    }

    public void addKlijent(Klijent klijent) throws Exception {
        sendRequest(Operation.ADD_KLIJENT, klijent);
    }

    public void addTrening(Trening trening) throws Exception {
        sendRequest(Operation.ADD_TRENING, trening);
    }

    public void deleteKlijent(Klijent klijent) throws Exception {
        sendRequest(Operation.DELETE_KLIJENT, klijent);
    }

    public void deleteTrening(Trening trening) throws Exception {
        sendRequest(Operation.DELETE_TRENING, trening);
    }

    public void updateKlijent(Klijent klijent) throws Exception {
        sendRequest(Operation.UPDATE_KLIJENT, klijent);
    }

    public void updateTrening(Trening trening) throws Exception {
        sendRequest(Operation.UPDATE_TRENING, trening);
    }

    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        return (ArrayList<Administrator>) sendRequest(Operation.GET_ALL_ADMINISTRATOR, null);
    }

    public ArrayList<Klijent> getAllKlijent() throws Exception {
        return (ArrayList<Klijent>) sendRequest(Operation.GET_ALL_KLIJENT, null);
    }

    public ArrayList<Trening> getAllTrening() throws Exception {
        return (ArrayList<Trening>) sendRequest(Operation.GET_ALL_TRENING, null);
    }

    public ArrayList<Vezbac> getAllVezbac(Trening t) throws Exception {
        return (ArrayList<Vezbac>) sendRequest(Operation.GET_ALL_VEZBAC, t);
    }

    public ArrayList<VrstaTreninga> getAllVrstaTreninga() throws Exception {
        return (ArrayList<VrstaTreninga>) sendRequest(Operation.GET_ALL_VRSTA_TRENINGA, null);
    }

    public ArrayList<Sala> getAllSala() throws Exception {
        return (ArrayList<Sala>) sendRequest(Operation.GET_ALL_SALA, null);
    }

    public ArrayList<Trener> getAllTrener() throws Exception {
        return (ArrayList<Trener>) sendRequest(Operation.GET_ALL_TRENER, null);
    }

    private Object sendRequest(int operation, Object data) throws Exception {
        Request request = new Request(operation, data);
        
        ObjectOutputStream out = new ObjectOutputStream(Session.getInstance().getSocket().getOutputStream());
        out.writeObject(request);
        
        ObjectInputStream in = new ObjectInputStream(Session.getInstance().getSocket().getInputStream());
        Response response = (Response) in.readObject();
        
        if (response.getResponseStatus().equals(ResponseStatus.Error)) {
            throw response.getExc();
        } else {
            return response.getData();
        }
    }
}
