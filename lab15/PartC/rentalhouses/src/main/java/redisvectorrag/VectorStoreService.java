package redisvectorrag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VectorStoreService {
    VectorStoreRepository vectorStoreRepository;

    @Autowired
    public VectorStoreService(VectorStoreRepository vectorStoreRepository) {
        this.vectorStoreRepository = vectorStoreRepository;
        fillVectorStore();
    }

    private void fillVectorStore() {
        vectorStoreRepository.cleanUpStore();
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


}
