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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import so.AbstractSO;

/**
 *
 * @author PC
 */
public class SOAddTrening extends AbstractSO {

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
        
        PreparedStatement ps = DBBroker.getInstance().insert(ado);
        
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long treningID = tableKeys.getLong(1);
        
        Trening t = (Trening) ado;
        t.setTreningID(treningID);
        
        for (Vezbac vezbac : t.getVezbaci()) {
            vezbac.setTrening(t);
            DBBroker.getInstance().insert(vezbac);
        }
        
    }

}
