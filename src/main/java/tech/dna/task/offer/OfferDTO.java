package tech.dna.task.offer;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OfferDTO {

    private Long categoryId;
    private Long employerId;
    private LocalDate startDate;
    private LocalDate endDate;

    public static final String[] fieldNames = {
            "categoryId",
            "employerId",
            "startDate",
            "endDate"
    };

}
