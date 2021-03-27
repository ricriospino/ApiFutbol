package com.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.api.jpa.entity.EquipoJPA;
import com.api.jpa.repository.EquipoRepository;
import com.api.model.entity.EquipoModel;
import com.api.service.EquipoService;
import com.api.util.Convertidor;

@Service("equipoServiceImpl")
public class EquipoServiceImpl implements EquipoService {
	private static final Logger log = LogManager.getLogger(EquipoServiceImpl.class);
	
	@Autowired
	@Qualifier("repositorioequipo")
	private EquipoRepository repositorio;
	
	@Autowired
	@Qualifier("convertidor")
	private Convertidor convertidor;

	@Override
	public boolean insertar(EquipoModel equipo) {
		
		try {
			
			repositorio.save(new EquipoJPA (equipo));
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean actualizar(EquipoModel equipo) {
		
		try {
			
			repositorio.save(new EquipoJPA (equipo));
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean borrar(int id) {
		
		try {
			log.info("Borrar");
			Optional<EquipoJPA> equipo = repositorio.findById(id);
			
			if(equipo.isPresent()) {
				repositorio.delete(equipo.get());
				
			}else {
				log.error("No existe data para ese Id");
				return false;
			}
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<EquipoModel> obtenerEquipos() {
		
		return convertidor.convertirListaEquipo(repositorio.findAll());
	}

	@Override
	public EquipoModel obtenerEquipoPorNombre(String nombre) {
		
		return convertidor.convertirEquipoModel(repositorio.findByNombreEquipo(nombre));
	}

}