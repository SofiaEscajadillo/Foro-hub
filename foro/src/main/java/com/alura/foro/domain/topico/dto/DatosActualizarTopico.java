package com.alura.foro.domain.topico.dto;

import com.alura.foro.domain.topico.Curso;
import com.alura.foro.domain.topico.Status;

public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        Curso curso,
        Status status) {
}
