package com.alura.foro.domain.topico.dto;

import com.alura.foro.domain.topico.Topico;
import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String idUsuario,
        String mensaje,
        String titulo,
        LocalDateTime fecha) {

    public DatosDetalleTopico(Topico topico) {
        this(topico.getId(), topico.getAutor(), topico.getMensaje(),
            topico.getTitulo(), topico.getFechaDeCreacion());
    }
}
