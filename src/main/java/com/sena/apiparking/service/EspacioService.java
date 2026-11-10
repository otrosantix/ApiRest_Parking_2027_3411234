package com.sena.apiparking.service;

import org.springframework.stereotype.Service;

import com.sena.apiparking.model.RegistroParqueo;
import com.sena.apiparking.repository.EspacioParqueoRepository;
import com.sena.apiparking.repository.RegistroPaqueoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EspacioService {
	
	private static final int MAX_LOTE = 500;
	
	private final EspacioParqueoRepository espacioRepo;
	private final RegistroPaqueoRepository registroRepo;
	
	public EspacioResponse crear() {
		
	}

}
