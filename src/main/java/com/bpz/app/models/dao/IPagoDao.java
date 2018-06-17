package com.bpz.app.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bpz.app.models.entity.Pago;

@Repository
public interface IPagoDao extends JpaRepository<Pago, Long> {

}
