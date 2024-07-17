package com.alura.foro.domain.topico;

import com.alura.foro.domain.topico.dto.DatosActualizarTopico;
import com.alura.foro.domain.topico.dto.DatosRegistroTopico;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "topicos")
@Entity(name = "Topico")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaDeCreacion;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String autor;
    @Enumerated(EnumType.STRING)
    private Curso curso;

    public Topico(DatosRegistroTopico datos) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaDeCreacion = LocalDateTime.now();
        this.status = Status.SIN_RESPUESTA;
        this.autor = datos.idusuario();
        this.curso = datos.curso();
    }

    public void actualizarDatos(DatosActualizarTopico datos){
        if(datos.titulo() != null){
            this.titulo = datos.titulo();}
        if(datos.mensaje() != null){
            this.mensaje = datos.mensaje();}
        if (datos.curso() != null){
            this.curso = datos.curso();
        }
        if (datos.status() != null){
            this.status = datos.status();
        }
        this.fechaDeCreacion = LocalDateTime.now();
    }
}
