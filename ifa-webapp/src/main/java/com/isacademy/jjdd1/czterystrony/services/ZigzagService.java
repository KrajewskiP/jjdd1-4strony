package com.isacademy.jjdd1.czterystrony.services;

import isacademy.jjdd1.czterystrony.webapp.persistance.model.InvestFund;
import isacademy.jjdd1.czterystrony.webapp.persistance.model.Rating;
import isacademy.jjdd1.czterystrony.webapp.persistance.repositories.InvestFundRepository;
import isacademy.jjdd1.czterystrony.webapp.persistance.repositories.RatingRepository;
import com.isacademy.jjdd1.czterystrony.restparameters.RestDateParam;
import com.isacademy.jjdd1.czterystrony.restparameters.RestIntegerParam;
import com.isacademy.jjdd1.czterystrony.technicalanalysis.AnalysisAudit;
import com.isacademy.jjdd1.czterystrony.technicalanalysis.LocalExtremaProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/investfunds")
public class ZigzagService {

    private static Logger log = LoggerFactory.getLogger(RatingsService.class);

    @Inject
    InvestFundRepository investFundRepository;

    @Inject
    RatingRepository ratingRepository;

    @Inject
    LocalExtremaProvider localExtremaProvider;

    @GET
    @Path("/{id}/zigzag")
    @Produces(MediaType.APPLICATION_JSON)
    @Interceptors(AnalysisAudit.class)
    public List<Rating> getZigZag(
            @PathParam("id") String id,
            @QueryParam("value") RestIntegerParam zigZag,
            @QueryParam("startDate") RestDateParam start,
            @QueryParam("endDate") RestDateParam end) {

        List<Rating> ratings = ratingRepository.getAllByFund(getFund(id));
        log.info("Provided all local extrema for {}", id);
        return localExtremaProvider.findExtrema(ratings, zigZag.getNumber());
    }

    private InvestFund getFund(String id) {
        return investFundRepository.getById(id);
    }
}
