package pl.lodz.p.it.ssbd2020.ssbd05.exceptions.mos;

import pl.lodz.p.it.ssbd2020.ssbd05.exceptions.AppBaseException;

public class HallHasReservationsException extends AppBaseException {
    public static final String KEY_HALL_PROBLEM = "page.hall.details.reservation.exists";

    public HallHasReservationsException() {
        super(KEY_HALL_PROBLEM);
    }

    public HallHasReservationsException(String message) {
        super(message);
    }

    public HallHasReservationsException(Throwable cause) {
        super(KEY_HALL_PROBLEM, cause);
    }
}
