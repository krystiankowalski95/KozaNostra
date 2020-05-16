package pl.lodz.p.it.ssbd2020.ssbd05.mos.facades;

import pl.lodz.p.it.ssbd2020.ssbd05.abstraction.AbstractFacade;
import pl.lodz.p.it.ssbd2020.ssbd05.entities.mos.EventType;
import pl.lodz.p.it.ssbd2020.ssbd05.interceptors.TrackerInterceptor;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@TransactionAttribute(TransactionAttributeType.MANDATORY)
@Stateless(name = "EventTypesFacadeMOS")
@LocalBean
@Interceptors(TrackerInterceptor.class)
public class EventTypesFacade extends AbstractFacade<EventType> {

    @PersistenceContext(unitName = "ssbd05mosPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventTypesFacade() {
        super(EventType.class);
    }

    @Override
    //    @RolesAllowed()
    public Optional<EventType> find(Object id) {
        return super.find(id);
    }

    @Override
    //    @RolesAllowed()
    public List<EventType> findAll() {
        return super.findAll();
    }
}
