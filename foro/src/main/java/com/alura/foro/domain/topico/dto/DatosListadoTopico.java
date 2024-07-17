package com.alura.foro.domain.topico.dto;

import com.alura.foro.domain.topico.Curso;
import com.alura.foro.domain.topico.Status;
import com.alura.foro.domain.topico.Topico;
import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String autor,
        String mensaje,
        String titulo,
        LocalDateTime fecha,
        Status status,
        Curso curso) {

    public DatosListadoTopico (Topico topico){
        this(topico.getId(), topico.getAutor(), topico.getMensaje(), 
                topico.getTitulo(), topico.getFechaDeCreacion(),
                topico.getStatus(), topico.getCurso());
    }
}
