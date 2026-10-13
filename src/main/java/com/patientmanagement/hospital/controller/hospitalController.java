package com.patientmanagement.hospital.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.patientmanagement.hospital.model.Hospital;
import com.patientmanagement.hospital.service.hospitalservice;

//http://localhost:1222/hospital
@CrossOrigin(origins = { "http://localhost:5173" ,"http://localhost:5174"})
@RestController
@RequestMapping("/hospital")
public class hospitalController {
	
	@Autowired
	hospitalservice hospitalservice;
	
//	http://localhost:1222/hospital/hi
	@GetMapping("hi")
	String hi(){
		return "This is the RESTAPI for Hospital Management ..!!";
	}
	
//	http://localhost:1222/hospital/save
	@PostMapping("/save")
	Hospital createhospital(@RequestBody Hospital p) {
		return hospitalservice.savehospital(p);
	}
	
//	http://localhost:1222/hospital/gethospital/1	
	@GetMapping("gethospital/{patientId}")
	Hospital gethospital(@PathVariable Integer patientId) {
		return hospitalservice.gethospital(patientId);

	}
//	http://localhost:1222/hospital/getAll
	@GetMapping("/getAll")
	List<Hospital> getAllhospital(){
		return hospitalservice.getAllhospital();
	}
	
//	http://localhost:1222/hospital/deletehospital/1
	@DeleteMapping("deletehospital/{patientId}")
	String deletehospital(@PathVariable Integer patientId) {
		hospitalservice.deletehospital(patientId);
		return "Deleted Hospital with " + patientId + " Sucessfully !!";
		
	}
	
//	http://localhost:1222/hospital/updatehospital/1
	@PutMapping("updatehospital/{patientId}")
	Hospital updatehospital(@PathVariable Integer patientId,@RequestBody Hospital p) {
		
		Hospital HospFromDB = gethospital(patientId);
		
		HospFromDB.setName(p.getName());
		HospFromDB.setAge(p.getAge());
		HospFromDB.setDisease(p.getDisease());
		
		return hospitalservice.updatehospital(HospFromDB);
	}

}	