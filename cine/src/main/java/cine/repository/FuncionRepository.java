package cine.repository;

import cine.domain.Funcion;
import cine.domain.Pelicula;
import cine.domain.Sala;

import java.util.List;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Funcion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FuncionRepository extends JpaRepository<Funcion, Long> {
	List<Funcion> findAllByPelicula(Pelicula peliculas);
    Funcion findBySala(Sala sala);
    Funcion findBySalaAndId(Sala sala,Long id_funcion);

}
