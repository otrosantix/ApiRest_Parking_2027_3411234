package com.sena.apiparking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sena.apiparking.model.EspacioParqueo;
import com.sena.apiparking.model.RegistroParqueo;

import jakarta.persistence.LockModeType;

public interface RegistroPaqueoRepository extends JpaRepository<RegistroParqueo, Long> {
	
	@Query("select count(r) > 0 from RegistroParqueo r join r.vehiculo v where v.placa = placa and r.fechaSalida is null ")
	
	boolean existByVehiculoPlacaAndFechaSalidaIsNull(@Param("placa")String placa);
	
	@Query("select count (r) > 0 from RegistroParqueo r where r.espacio = :espacio")
	boolean existByEspacio(@Param("espacio") EspacioParqueo espacio);
	
	Optional<RegistroParqueo> findByVehiculoPlacaAndFechaSalidaIsNull(String placa);
	
	List<RegistroParqueo> findByFechaSalidaIsNullOrderByFechaEntradaDesc();
	
	List<RegistroParqueo> findByFechaSalidaIsNotNullOrderByFechaEntradaDesc();
	
	List<RegistroParqueo> findByVehiculoPlacaOrderByFechaEntradaDesc (String placa);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select r from RegistroParqueo r where r.id_registro = id")
	Optional<RegistroParqueo> bloquearPorId(@Param("id")Long id);
	
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("select r from RegistroParqueo r join r.vehiculo where v.placa = :placa and r.fechaSalida is null")
	Optional<RegistroParqueo> bloquearActivoPorPlaca(@Param("placa") String placa);
	
}
