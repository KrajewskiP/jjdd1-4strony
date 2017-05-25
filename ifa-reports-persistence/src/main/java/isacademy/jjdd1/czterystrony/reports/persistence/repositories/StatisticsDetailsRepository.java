package isacademy.jjdd1.czterystrony.reports.persistence.repositories;

import isacademy.jjdd1.czterystrony.reports.persistence.model.StatisticsDetails;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class StatisticsDetailsRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<StatisticsDetails> getAll() {
        Query query = entityManager.createNamedQuery("StatisticsDetails.getAll");
        return query.getResultList();
    }
}