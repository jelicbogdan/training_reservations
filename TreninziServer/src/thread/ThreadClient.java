/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import domain.Administrator;
import domain.Klijent;
import domain.Trening;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.Request;
import transfer.Response;
import transfer.util.ResponseStatus;
import transfer.util.Operation;

/**
 *
 * @author PC
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!isInterrupted()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                Request request = (Request) in.readObject();
                Response response = handleRequest(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Response handleRequest(Request request) {
        Response response = new Response(null, null, ResponseStatus.Success);
        try {
            switch (request.getOperation()) {
                case Operation.ADD_KLIJENT:
                    ServerController.getInstance().addKlijent((Klijent) request.getData());
                    break;
                case Operation.ADD_TRENING:
                    ServerController.getInstance().addTrening((Trening) request.getData());
                    break;
                case Operation.DELETE_KLIJENT:
                    ServerController.getInstance().deleteKlijent((Klijent) request.getData());
                    break;
                case Operation.DELETE_TRENING:
                    ServerController.getInstance().deleteTrening((Trening) request.getData());
                    break;
                case Operation.UPDATE_TRENING:
                    ServerController.getInstance().updateTrening((Trening) request.getData());
                    break;
                case Operation.UPDATE_KLIJENT:
                    ServerController.getInstance().updateKlijent((Klijent) request.getData());
                    break;
                case Operation.GET_ALL_ADMINISTRATOR:
                    response.setData(ServerController.getInstance().getAllAdministrator());
                    break;
                case Operation.GET_ALL_KLIJENT:
                    response.setData(ServerController.getInstance().getAllKlijent());
                    break;
                case Operation.GET_ALL_TRENER:
                    response.setData(ServerController.getInstance().getAllTrener());
                    break;
                case Operation.GET_ALL_SALA:
                    response.setData(ServerController.getInstance().getAllSala());
                    break;
                case Operation.GET_ALL_VEZBAC:
                    response.setData(ServerController.getInstance().getAllVezbac((Trening) request.getData()));
                    break;
                case Operation.GET_ALL_TRENING:
                    response.setData(ServerController.getInstance().getAllTrening());
                    break;
                case Operation.GET_ALL_VRSTA_TRENINGA:
                    response.setData(ServerController.getInstance().getAllVrstaTreninga());
                    break;
                case Operation.LOGIN:
                    Administrator administrator = (Administrator) request.getData();
                    Administrator ulogovani = ServerController.getInstance().login(administrator);
                    response.setData(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception e) {
            response.setExc(e);
            response.setResponseStatus(ResponseStatus.Error);
        }
        return response;
    }

}
