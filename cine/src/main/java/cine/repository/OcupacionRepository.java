package cine.repository;

import cine.domain.Butaca;
import cine.domain.Funcion;
import cine.domain.Ocupacion;
import cine.domain.Ticket;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


/**
 * Spring Data  repository for the Ocupacion entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OcupacionRepository extends JpaRepository<Ocupacion, Long> {
	 List<Ocupacion> findAllByFuncionAndButacaNotNull(Funcion funcion);
	    List<Ocupacion> findAllByTicketNotNullAndEntradaNull();
	    List<Ocupacion> findAllByButaca(Iterable<Long> longs);

	    Ocupacion findByTicket(Ticket ticket);
	    Ocupacion findByButaca(Butaca butaca);

	    Ocupacion findByButaca(Optional<Butaca> butaca);
	    Ocupacion findByTicket(Optional<Ticket> ticket);
	    List<Ocupacion> findAllByTicket(Ticket ticket);
}
