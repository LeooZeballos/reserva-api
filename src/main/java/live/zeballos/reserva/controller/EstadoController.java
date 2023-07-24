package live.zeballos.reserva.controller;

import live.zeballos.reserva.model.Estado;
import live.zeballos.reserva.service.IEstadoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/estado")
@CrossOrigin(origins = "*", maxAge = 3600)
public class EstadoController {

    private final IEstadoService estadoService;

    public EstadoController(IEstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping
    public Page<Estado> getAll(
            Pageable page,
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String descripcion,
            @RequestParam(required = false) String color
    ) {
        return estadoService.getAll(page, nombre, descripcion, color);
    }

    @GetMapping("/{id}")
    public Estado get(@PathVariable Long id) {
        return estadoService.get(id);
    }

    @PostMapping
    public ResponseEntity<Estado> create(@RequestBody Estado estado) {
        return ResponseEntity.ok(estadoService.create(estado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estado> update(@PathVariable Long id, @RequestBody Estado estado) {
        return ResponseEntity.ok(estadoService.update(id, estado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        estadoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
