package com.alura.foro.domain.topico.service;

import com.alura.foro.domain.topico.Topico;
import com.alura.foro.domain.topico.TopicoRepository;
import com.alura.foro.domain.topico.dto.DatosActualizarTopico;
import com.alura.foro.domain.topico.dto.DatosDetalleTopico;
import com.alura.foro.domain.topico.dto.DatosListadoTopico;
import com.alura.foro.domain.topico.dto.DatosRegistroTopico;
import com.alura.foro.infra.errores.ValidacionDeIntegridad;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;

    public Topico agregarTopico(DatosRegistroTopico datos){
        var response = topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
        if(response){
            throw new ValidacionDeIntegridad("No se permite el registro de tópicos duplicados");
        }
        Topico topico = topicoRepository.save(new Topico(datos));
        topicoRepository.save(topico);
        return topico;
    }

    public DatosDetalleTopico obtenerTopico (Long id){
        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico = new DatosDetalleTopico(topico);
        return  datosTopico;
    }

    public Page<DatosListadoTopico> listadoTopicos (Pageable paginacion){
        var response = topicoRepository.findAll(paginacion).map(DatosListadoTopico::new);
        return response;
    }

    public DatosDetalleTopico actualizarTopico (Long id, DatosActualizarTopico datos){
        Optional <Topico> topico = topicoRepository.findById(id);
        if(topico.isPresent()){

            var response = topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje());
            if(response) {
                throw new ValidacionDeIntegridad("No se permite el registro de tópicos duplicados");
            }
            topico.get().actualizarDatos(datos);
            return new DatosDetalleTopico(topico.get());
        }else {
                throw new ValidacionDeIntegridad("No se encuentra trópico con el ID ingresado");
            }
    }

    public void eliminarTopico(Long id){
        Optional <Topico> topico = topicoRepository.findById(id);
        if(topico.isPresent()){

            topicoRepository.deleteById(id);

        }else {
            throw new ValidacionDeIntegridad("No se encuentra trópico con el ID ingresado");
        }
    }
}
