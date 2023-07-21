package live.zeballos.reserva.service;

import live.zeballos.reserva.error.ReservaAlreadyExistsException;
import live.zeballos.reserva.model.Reserva;
import live.zeballos.reserva.query.ReservaQueryParams;
import live.zeballos.reserva.repository.ReservaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static live.zeballos.reserva.util.Parser.parseIdList;

@Service
@RequiredArgsConstructor
public class ReservaService implements IReservaService {

    private final ReservaRepository repository;
    private final EstadoService estadoService;

    @Override
    public Page<Reserva> getAll(Pageable pageable, Integer duracion, String comentario, String clienteId, String motivoReserva, String estadoId, String motivoRechazo, String espacioFisicoId) {
        return repository.findByParams(
                ReservaQueryParams.builder()
                        .duracion(duracion)
                        .comentario(comentario)
                        .clienteId(parseIdList(clienteId))
                        .motivoReserva(motivoReserva)
                        .estadoId(parseIdList(estadoId))
                        .motivoRechazo(motivoRechazo)
                        .espacioFisicoId(parseIdList(espacioFisicoId))
                        .build(),
                pageable
        );
    }

    @Override
    public List<Reserva> getAll() {
        return repository.findAll();
    }

    @Override
    public Page<Reserva> getAll(Pageable page) {
        return repository.findAll(page);
    }

    @Override
    public Reserva get(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Reserva create(Reserva reserva) {
        // Validar que no exista una reserva para el mismo espacio físico en el mismo horario
        if (repository.existsByEspacioFisicoAndFechaHoraInicioLessThanAndFechaHoraFinGreaterThan(reserva.getEspacioFisico(), reserva.getFechaHoraFin(), reserva.getFechaHoraInicio())) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            throw new ReservaAlreadyExistsException("Ya existe una reserva para ese espacio físico en el horario " + reserva.getFechaHoraInicio().format(formatter) + " - " + reserva.getFechaHoraFin().format(formatter));
        }

        // Validar que la fecha de inicio sea menor a la fecha de fin
        if (reserva.getFechaHoraInicio().isAfter(reserva.getFechaHoraFin())) {
            throw new IllegalArgumentException("La fecha de inicio debe ser menor a la fecha de fin");
        }

        // Validar que la fecha de inicio sea mayor a la fecha actual
        if (reserva.getFechaHoraInicio().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("La fecha de inicio debe ser mayor a la fecha actual");
        }

        // Finalmente, crear la reserva
        reserva.setFechaHoraCreacion(LocalDateTime.now());
        reserva.setEstado(estadoService.get("Creado"));
        return repository.saveAndFlush(reserva);
    }

    @Override
    public Reserva update(Long id, Reserva reserva) {
        // Validar que no exista una reserva para el mismo espacio físico en el mismo horario
        if (repository.existsByEspacioFisicoAndFechaHoraInicioLessThanAndFechaHoraFinGreaterThanAndIdNot(
                reserva.getEspacioFisico(),
                reserva.getFechaHoraFin(),
                reserva.getFechaHoraInicio(),
                id
        )) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            throw new ReservaAlreadyExistsException("Ya existe una reserva para ese espacio físico en el horario " + reserva.getFechaHoraInicio().format(formatter) + " - " + reserva.getFechaHoraFin().format(formatter));
        }

        // Validar que la fecha de inicio sea menor a la fecha de fin
        if (reserva.getFechaHoraInicio().isAfter(reserva.getFechaHoraFin())) {
            throw new IllegalArgumentException("La fecha de inicio debe ser menor a la fecha de fin");
        }

        // Finalmente, actualizar la reserva
        reserva.setId(id);
        return repository.saveAndFlush(reserva);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
