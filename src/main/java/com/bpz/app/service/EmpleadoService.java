package com.bpz.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bpz.app.models.dao.IEmpleadoDao;
import com.bpz.app.models.entity.Empleado;

@Service
public class EmpleadoService implements IEmpleadoService {

	@Autowired
	private IEmpleadoDao empleadoDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Empleado> finAll() {
		
		return empleadoDao.findAll();
	}

	@Override
	@Transactional
	public void save(Empleado empleado) {
		empleadoDao.save(empleado);
		
	}

}
