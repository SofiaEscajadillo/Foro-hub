package com.alura.foro.controller;

import com.alura.foro.domain.topico.dto.DatosActualizarTopico;
import com.alura.foro.domain.topico.dto.DatosDetalleTopico;
import com.alura.foro.domain.topico.dto.DatosListadoTopico;
import com.alura.foro.domain.topico.dto.DatosRegistroTopico;
import com.alura.foro.domain.topico.service.TopicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService servicio;

    @PostMapping
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datos,
                                          UriComponentsBuilder uriComponentsBuilder){
        var response = servicio.agregarTopico(datos);
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(response.getId()).toUri();
        DatosDetalleTopico datosDetalleTopico = new DatosDetalleTopico(response);
        return ResponseEntity.created(url).body(datosDetalleTopico);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosDetalleTopico> obtenerTopico(@PathVariable Long id){
        var response = servicio.obtenerTopico(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listaDeMedicos(@PageableDefault(size = 2) Pageable paginacion){
        var response = servicio.listadoTopicos(paginacion);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosDetalleTopico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico, @PathVariable Long id){
        var response = servicio.actualizarTopico(id,datosActualizarTopico);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseEntity.BodyBuilder> eliminarTopico(@PathVariable Long id){
        servicio.eliminarTopico(id);
        return  ResponseEntity.noContent().build();
    }

}
