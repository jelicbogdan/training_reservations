/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.vrstaTreninga;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.VrstaTreninga;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllVrstaTreninga extends AbstractSO {

    private ArrayList<VrstaTreninga> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof VrstaTreninga)) {
            throw new Exception("Prosledjeni objekat nije instanca klase VrstaTreninga!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> vrsteTreninga =  DBBroker.getInstance().select(ado);
        lista = (ArrayList<VrstaTreninga>) (ArrayList<?>) vrsteTreninga;
    }

    public ArrayList<VrstaTreninga> getLista() {
        return lista;
    }

}
