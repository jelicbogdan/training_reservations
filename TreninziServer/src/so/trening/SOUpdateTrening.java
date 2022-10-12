/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.trening;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Trening;
import domain.Vezbac;
import java.util.Date;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOUpdateTrening extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Trening)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Trening!");
        }
        
        Trening t = (Trening) ado;
        
        if(!t.getDatumVremePocetka().after(new Date())){
            throw new Exception("Datum i vreme pocetka treninga mora biti u buducnosti!");
        }
        
        if(t.getVezbaci().size() < 2 || t.getVezbaci().size() > 10){
            throw new Exception("Trening mora imati izmedju 2 i 10 vezbaca!");
        }
        
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        DBBroker.getInstance().update(ado);
        
        Trening t = (Trening) ado;
        DBBroker.getInstance().delete(t.getVezbaci().get(0));
        
        for (Vezbac vezbac : t.getVezbaci()) {
            DBBroker.getInstance().insert(vezbac);
        }
        
    }

}
