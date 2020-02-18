package be.belfius.readtag.repository;

import be.belfius.readtag.domain.JsonLine;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
//public interface JsonLineRepository extends JpaRepository<JsonLine, Long> {
 public interface JsonLineRepository extends CrudRepository<JsonLine, Long> {
}
