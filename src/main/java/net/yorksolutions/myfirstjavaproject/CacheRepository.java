package net.yorksolutions.myfirstjavaproject;

import net.yorksolutions.myfirstjavaproject.json.Cache;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CacheRepository extends CrudRepository<Cache, Long> {
    Optional<Cache> findByInput(String input);

}
