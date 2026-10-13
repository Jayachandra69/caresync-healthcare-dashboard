package com.patientmanagement.hospital.service;

import java.util.List;

import com.patientmanagement.hospital.model.Hospital;


public interface hospitalservice {

	Hospital savehospital(Hospital p);
	
	Hospital gethospital(Integer id);
	
	List<Hospital> getAllhospital();
	
	void deletehospital(Integer id);
	
	Hospital updatehospital(Hospital p);
}
