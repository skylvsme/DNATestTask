package tech.dna.task.offer;

import com.turkraft.springfilter.FilterBuilder;
import com.turkraft.springfilter.boot.FilterSpecification;
import com.turkraft.springfilter.parser.Filter;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tech.dna.task.category.CategoryRepository;
import tech.dna.task.user.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OfferService {

    private final OfferRepository offerRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public Offer createOffer(OfferDTO offerDTO) {
        val offer = new Offer();

        val category = categoryRepository.findById(offerDTO.getCategoryId())
                .orElseThrow(() -> new EntityNotFoundException("Category with specified id not found"));

        val user = userRepository.findById(offerDTO.getEmployerId())
                .orElseThrow(() -> new EntityNotFoundException("User with specified id not found"));

        offer.setCategory(category);
        offer.setEmployer(user);
        offer.setStartDate(offerDTO.getStartDate());
        offer.setEndDate(offerDTO.getEndDate());

        return offerRepository.save(offer);
    }

    public List<Offer> findOffers(Long categoryId, String employerSearch) {
        // I use turkraft's Spring Filter library for fast specification creation
        val filtersList = new ArrayList<Filter>();

        if (categoryId != null) {
            filtersList.add(FilterBuilder.equal("category.id", categoryId));
        }
        if (employerSearch != null) {
            filtersList.add(FilterBuilder.like("employer.name", String.format("%%%s%%", employerSearch)));
        }

        // Show only valid offers
        filtersList.add(FilterBuilder.lessThanOrEqual("startDate", FilterBuilder.currentDate()));
        filtersList.add(FilterBuilder.greaterThanOrEqual("endDate", FilterBuilder.currentDate()));

        val filter = FilterBuilder.and(filtersList);
        val specification = new FilterSpecification<Offer>(filter.generate());
        return offerRepository.findAll(specification);
    }

}
