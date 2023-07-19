package live.zeballos.reserva.controller;

import live.zeballos.reserva.error.ReservaAlreadyExistsException;
import live.zeballos.reserva.model.Reserva;
import live.zeballos.reserva.service.IReservaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reserva")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ReservaController {

    private final IReservaService reservaService;

    public ReservaController(IReservaService reservaService) {
        this.reservaService = reservaService;
    }

    @GetMapping
    public Page<Reserva> getAll(
            Pageable pageable,
            @RequestParam(required = false) Integer duracion,
            @RequestParam(required = false) String comentario,
            @RequestParam(required = false) String clienteId,
            @RequestParam(required = false) String motivoReserva,
            @RequestParam(required = false) String estadoId,
            @RequestParam(required = false) String motivoRechazo,
            @RequestParam(required = false) String espacioFisicoId
    ) {
        return reservaService.getAll(pageable, duracion, comentario, clienteId, motivoReserva, estadoId, motivoRechazo, espacioFisicoId);
    }

    @GetMapping("/{id}")
    public Reserva get(@PathVariable Long id) {
        return reservaService.get(id);
    }

    @PostMapping
    public ResponseEntity<Reserva> create(@RequestBody Reserva reserva) {
        try {
            return ResponseEntity.ok(reservaService.create(reserva));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (ReservaAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> update(@PathVariable Long id, @RequestBody Reserva reserva) {
        try {
            return ResponseEntity.ok(reservaService.update(id, reserva));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (ReservaAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        reservaService.delete(id);
        return ResponseEntity.ok("Reserva con id " + id + " eliminada correctamente.");
    }

}
