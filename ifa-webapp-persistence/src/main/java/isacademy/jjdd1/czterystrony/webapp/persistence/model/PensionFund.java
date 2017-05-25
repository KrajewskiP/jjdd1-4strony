package isacademy.jjdd1.czterystrony.webapp.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import isacademy.jjdd1.czterystrony.webapp.persistence.queries.PensionFundNamedNativeQueries;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(indexes = {@Index(
        name = "UX_PensionFund_id_name",
        columnList = "id,name")
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name = "PensionFundDetailsMapping",
                classes = {
                        @ConstructorResult(
                                targetClass = PensionFundDetails.class,
                                columns = {
                                        @ColumnResult(name = "name", type = String.class),
                                        @ColumnResult(name = "id", type = String.class),
                                        @ColumnResult(name = "date", type = LocalDate.class),
                                        @ColumnResult(name = "close", type = BigDecimal.class),
                                        @ColumnResult(name = "diff", type = BigDecimal.class)
                                })})
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "PensionFund.getAllWithDetails",
                query = PensionFundNamedNativeQueries.allWithDetails,
                resultSetMapping = "PensionFundDetailsMapping"),
})
public class PensionFund extends FinancialInstrument {

    @JsonIgnore
    @OneToMany(mappedBy = "pensionFund", cascade = CascadeType.PERSIST)
    private List<PensionFundRating> pensionFundRatings = new ArrayList<>();

    public PensionFund() {
    }

    public static class Builder extends FinancialInstrument.Builder<PensionFund, Builder> {

        @Override
        PensionFund createInstrument() {
            return new PensionFund();
        }

        @Override
        Builder createBuilder() {
            return this;
        }
    }

    public List<PensionFundRating> getPensionFundRatings() {
        return pensionFundRatings;
    }

    @Override
    public String toString() {
        return "PensionFund{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastRatingDate=" + lastRatingDate +
                ", ratings=" + pensionFundRatings +
                '}';
    }
}
