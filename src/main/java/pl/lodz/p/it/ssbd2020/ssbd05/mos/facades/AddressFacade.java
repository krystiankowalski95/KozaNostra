/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.lodz.p.it.ssbd2020.ssbd05.mos.facades;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.lodz.p.it.ssbd2020.ssbd05.AbstractFacade;
import pl.lodz.p.it.ssbd2020.ssbd05.mos.entities.Address;


@Stateless
public class AddressFacade extends AbstractFacade<Address> {

    @PersistenceContext(unitName = "ssbd05mokPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AddressFacade() {
        super(Address.class);
    }
    
}