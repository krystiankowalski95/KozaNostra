package pl.lodz.p.it.ssbd2020.ssbd05.mor.endpoints.interfaces;

import pl.lodz.p.it.ssbd2020.ssbd05.dto.mor.ReviewDTO;
import pl.lodz.p.it.ssbd2020.ssbd05.exceptions.AppBaseException;

import javax.ejb.Local;

@Local
public interface EditReviewEndpointLocal {
    ReviewDTO getReviewByReviewNumber(String number) throws AppBaseException;

    void editReview(ReviewDTO reviewDTO) throws AppBaseException;
}
