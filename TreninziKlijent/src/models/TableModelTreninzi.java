/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Trening;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelTreninzi extends AbstractTableModel implements Runnable {

    private ArrayList<Trening> lista;
    private String[] kolone = {"ID", "Vrsta treninga", "Trener", "Sala", "Datum i vreme pocetka"};
    private String parametar = "";

    public TableModelTreninzi() {
        try {
            lista = ClientController.getInstance().getAllTrening();
        } catch (Exception ex) {
            Logger.getLogger(TableModelTreninzi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Trening t = lista.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        
        switch (column) {
            case 0:
                return t.getTreningID();
            case 1:
                return t.getVrstaTreninga();
            case 2:
                return t.getTrener();
            case 3:
                return t.getSala();
            case 4:
                return sdf.format(t.getDatumVremePocetka());

            default:
                return null;
        }
    }

    public Trening getSelectedTrening(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelTreninzi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllTrening();
            if (!parametar.equals("")) {
                ArrayList<Trening> novaLista = new ArrayList<>();
                for (Trening t : lista) {
                    if (t.getVrstaTreninga().getNazivVrsteTreninga().toLowerCase().contains(parametar.toLowerCase())
                            || t.getTrener().getImeTrenera().toLowerCase().contains(parametar.toLowerCase())
                            || t.getTrener().getPrezimeTrenera().toLowerCase().contains(parametar.toLowerCase())
                            || t.getSala().getNazivSale().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(t);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
