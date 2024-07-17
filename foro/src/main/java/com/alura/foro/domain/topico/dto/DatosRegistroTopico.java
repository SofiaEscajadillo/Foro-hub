package com.alura.foro.domain.topico.dto;

import com.alura.foro.domain.topico.Curso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroTopico(
        @NotBlank
        String idusuario,
        @NotBlank
        String mensaje,
        @NotNull
        Curso curso,
        @NotBlank
        String titulo) {
}
