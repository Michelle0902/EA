package redisvectorstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemanticSearchService {
    VectorStoreRepository vectorStoreRepository;

    @Autowired
    public SemanticSearchService(VectorStoreRepository vectorStoreRepository) {
        this.vectorStoreRepository = vectorStoreRepository;
        fillVectorStore();
    }

    private void fillVectorStore() {
        List<String> rentalData = List.of(
                "2-bedroom apartment in downtown with balcony and parking",
                "Cozy studio near university, pet-friendly",
                "Modern 3-bedroom house with garden and garage",
                "1-bedroom condo in city center, near metro",
                "Spacious 4-bedroom home in suburbs, includes backyard and fireplace",
                "Luxury penthouse with rooftop terrace and skyline views",
                "Charming cottage near the lake with private dock",
                "Renovated loft in historic district with exposed brick walls",
                "Townhouse with 3 bedrooms, 2 bathrooms, and private driveway",
                "Beachfront villa with swimming pool and large patio"
        );
        vectorStoreRepository.add(rentalData);
    }

    public List<String> findClosestMatches(String query, int count) {
        return vectorStoreRepository.findClosestMatches(query,count);
    }
}
