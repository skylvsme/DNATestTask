package tech.dna.task.offer;

import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import tech.dna.task.common.RestResponse;

import java.util.List;

@RestController
@RequestMapping("offer")
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    @PostMapping
    public RestResponse<Offer> createOffer(@RequestBody OfferDTO offerDTO) {
        return RestResponse.ok(offerService.createOffer(offerDTO));
    }

    @GetMapping
    public RestResponse<List<Offer>> findOffers(
            @Param("category") Long category,
            @Param("employerName") String employerName
    ) {
        return RestResponse.ok(offerService.findOffers(category, employerName));
    }

}
