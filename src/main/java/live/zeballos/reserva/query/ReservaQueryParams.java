package live.zeballos.reserva.query;

import java.util.List;

@lombok.Builder
public record ReservaQueryParams(
        List<Long> clienteId,
        String motivoReserva,
        List<Long> estadoId,
        List<Long> espacioFisicoId
) {
}
