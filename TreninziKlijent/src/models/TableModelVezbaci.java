/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import domain.Klijent;
import domain.Trening;
import domain.Vezbac;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author PC
 */
public class TableModelVezbaci extends AbstractTableModel {

    private ArrayList<Vezbac> lista;
    private String[] kolone = {"Rb", "Klijent", "Napomena"};
    private int rb = 0;

    public TableModelVezbaci() {
        lista = new ArrayList<>();
    }

    public TableModelVezbaci(Trening t) {
        try {
            lista = ClientController.getInstance().getAllVezbac(t);
        } catch (Exception ex) {
            Logger.getLogger(TableModelVezbaci.class.getName()).log(Level.SEVERE, null, ex);
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
        Vezbac v = lista.get(row);

        switch (column) {
            case 0:
                return v.getRbVezbaca();
            case 1:
                return v.getKlijent();
            case 2:
                return v.getNapomena();

            default:
                return null;
        }
    }

    public void dodajVezbaca(Vezbac v) {
        rb = lista.size();
        v.setRbVezbaca(++rb);
        lista.add(v);
        fireTableDataChanged();
    }

    public void obrisiVezbaca(int row) {
        lista.remove(row);
        
        rb = 0;
        for (Vezbac vezbac : lista) {
            vezbac.setRbVezbaca(++rb);
        }
        
        fireTableDataChanged();
    }

    public boolean postojiVezbac(Klijent klijent) {
        for (Vezbac vezbac : lista) {
            if(vezbac.getKlijent().getKlijentID().equals(klijent.getKlijentID())){
                return true;
            }
        }
        return false;
    }

    public ArrayList<Vezbac> getLista() {
        return lista;
    }

}
