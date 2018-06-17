package com.bpz.app.service;

import java.util.List;


import com.bpz.app.models.entity.PersonaContacto;

public interface IPersonaContactoService {

	public List<PersonaContacto> findAll();
	
	public void save(PersonaContacto personaContacto);
	
	public PersonaContacto findById(Long term);
	public void eliminarPorId(Long id);
	
	public PersonaContacto findByDni(Long term);
}
