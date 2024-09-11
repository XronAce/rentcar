package rentcar.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import rentcar.domain.*;

import java.util.Optional;

//<<< PoEAA / Repository
@RepositoryRestResource(collectionResourceRel = "points", path = "points")
public interface PointRepository
    extends PagingAndSortingRepository<Point, Long> {
    Optional<Point> findByUserId(Long userId);
}
