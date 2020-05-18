package pl.lodz.p.it.ssbd2020.ssbd05.mor.endpoints;

import pl.lodz.p.it.ssbd2020.ssbd05.dto.mor.ReservationDTO;
import pl.lodz.p.it.ssbd2020.ssbd05.exceptions.AppBaseException;
import pl.lodz.p.it.ssbd2020.ssbd05.mor.endpoints.interfaces.ListUserReservationsEndpointLocal;
import pl.lodz.p.it.ssbd2020.ssbd05.mor.managers.ReservationManager;

import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateful
@TransactionAttribute(TransactionAttributeType.NEVER)
public class ListUserReservationsEndpoint implements Serializable, ListUserReservationsEndpointLocal {

    @Inject
    private ReservationManager reservationManager;

    @Override
    public List<ReservationDTO> getAllUsersReservations(String login) throws AppBaseException {
        return new ArrayList<>();
    }
}