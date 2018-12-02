package cine.repository;

import cine.domain.Butaca;
import cine.domain.Sala;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Butaca entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ButacaRepository extends JpaRepository<Butaca, Long> {
	List<Butaca> findAllBySala(Sala sala_desc);
    List<Butaca> findAllByIdNotIn(Iterable<Long> longs);
    List<Butaca> findAllByIdNotInAndSala(Iterable<Long> longs,Sala sala);
}
