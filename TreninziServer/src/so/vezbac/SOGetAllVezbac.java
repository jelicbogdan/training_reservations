/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.vezbac;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Vezbac;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOGetAllVezbac extends AbstractSO {

    private ArrayList<Vezbac> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Vezbac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Vezbac!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> vezbaci = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Vezbac>) (ArrayList<?>) vezbaci;
    }

    public ArrayList<Vezbac> getLista() {
        return lista;
    }

}
