package live.zeballos.reserva.repository;

import jakarta.persistence.criteria.Predicate;
import live.zeballos.reserva.model.EspacioFisico;
import live.zeballos.reserva.model.Reserva;
import live.zeballos.reserva.query.ReservaQueryParams;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long>, JpaSpecificationExecutor<Reserva> {
    default Page<Reserva> findByParams(ReservaQueryParams queryParams, Pageable pageable) {
        return findAll((root, query, builder) -> {
            List<Predicate> reservaPredicates = new ArrayList<>();
            if (queryParams.clienteId() != null && !queryParams.clienteId().isEmpty()) {
                reservaPredicates.add(root.join("cliente").get("id").in(queryParams.clienteId()));
            }
            if (queryParams.motivoReserva() != null) {
                reservaPredicates.add(builder.like(builder.lower(root.get("motivoReserva")), "%" + queryParams.motivoReserva().toLowerCase() + "%"));
            }
            if (queryParams.estadoId() != null && !queryParams.estadoId().isEmpty()) {
                reservaPredicates.add(root.join("estado").get("id").in(queryParams.estadoId()));
            }
            if (queryParams.espacioFisicoId() != null && !queryParams.espacioFisicoId().isEmpty()) {
                reservaPredicates.add(root.join("espacioFisico").get("id").in(queryParams.espacioFisicoId()));
            }
            return builder.and(reservaPredicates.toArray(new Predicate[0]));
        }, pageable);
    }

    /**
     * ¿Existe una reserva que se superponga con la que se quiere crear?
     * Se superpone si:
     * - El espacio físico es el mismo
     * - La fecha de inicio de la reserva es menor a la fecha de fin de la reserva existente
     * - La fecha de fin de la reserva es mayor a la fecha de inicio de la reserva existente
     *
     * @param espacioFisico   espacio físico de la reserva
     * @param fechaHoraInicio fecha y hora de inicio de la reserva
     * @param fechaHoraFin    fecha y hora de fin de la reserva
     * @return true si existe una reserva que se superpone con la que se quiere crear, false en caso contrario
     */
    boolean existsByEspacioFisicoAndFechaHoraInicioLessThanAndFechaHoraFinGreaterThan(EspacioFisico espacioFisico, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin);

    /**
     * ¿Existe una reserva que se superponga con la que se quiere crear?
     * Se superpone si:
     * - El espacio físico es el mismo
     * - La fecha de inicio de la reserva es menor a la fecha de fin de la reserva existente
     * - La fecha de fin de la reserva es mayor a la fecha de inicio de la reserva existente
     * - El id de la reserva no es el mismo (para el caso de actualizar una reserva)
     *
     * @param espacioFisico   espacio físico de la reserva
     * @param fechaHoraInicio fecha y hora de inicio de la reserva
     * @param fechaHoraFin    fecha y hora de fin de la reserva
     * @param id              id de la reserva
     * @return true si existe una reserva que se superpone con la que se quiere crear, false en caso contrario
     */
    boolean existsByEspacioFisicoAndFechaHoraInicioLessThanAndFechaHoraFinGreaterThanAndIdNot(EspacioFisico espacioFisico, LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin, Long id);
}
