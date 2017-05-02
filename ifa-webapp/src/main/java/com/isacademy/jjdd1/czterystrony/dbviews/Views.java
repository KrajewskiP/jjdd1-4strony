package com.isacademy.jjdd1.czterystrony.dbviews;

import com.isacademy.jjdd1.czterystrony.model.InvestFundDetails;
import com.isacademy.jjdd1.czterystrony.repositories.InvestFundRepository;

import javax.annotation.PostConstruct;
import javax.ejb.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Startup
@Singleton
@DependsOn({"DatabaseInitializer", "InvestFundsInitializer", "RatingsInitializer"})
public class Views {
    private List<InvestFundDetails> allFunds;
    private List<InvestFundDetails> promotedFunds;
    private List<InvestFundDetails> notPromotedFunds;

    @EJB
    private InvestFundRepository investFundRepository;

    @PostConstruct
    public void updateViews() {
        this.allFunds = investFundRepository.getAllWithDetails().stream()
                .sorted(Comparator.comparing(InvestFundDetails::getName))
                .collect(Collectors.toList());

        this.promotedFunds = allFunds.stream()
                .filter(f -> f.getPriority() > 0)
                .sorted(Comparator.comparing(InvestFundDetails::getPriority).reversed())
                .collect(Collectors.toList());

        this.notPromotedFunds = allFunds.stream()
                .filter(f -> f.getPriority() == 0)
                .collect(Collectors.toList());
    }

    public List<InvestFundDetails> getAllFunds() {
        return allFunds;
    }

    public List<InvestFundDetails> getPromotedFunds() {
        return promotedFunds;
    }

    public List<InvestFundDetails> getNotPromotedFunds() {
        return notPromotedFunds;
    }
}
