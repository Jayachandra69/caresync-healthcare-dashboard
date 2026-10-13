package com.patientmanagement.hospital.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.patientmanagement.hospital.model.Hospital;
import com.patientmanagement.hospital.repo.HospitalRepository;

@Service
public class hospitalserviceImpl implements hospitalservice{
	
	@Autowired
	HospitalRepository HospitalRepository;

	@Override
	public Hospital savehospital(Hospital p) {
		return HospitalRepository.save(p);
	}

	@Override
	public Hospital gethospital(Integer id) {
		return HospitalRepository.findById(id).orElseThrow();
	}

	@Override
	public List<Hospital> getAllhospital() {
		return HospitalRepository.findAll();
	}

	@Override
	public void deletehospital(Integer id) {
		HospitalRepository.deleteById(id);
	}

	@Override
	public Hospital updatehospital(Hospital p) {
		return HospitalRepository.save(p);
	}

}
