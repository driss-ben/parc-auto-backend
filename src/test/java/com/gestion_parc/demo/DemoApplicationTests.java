package com.gestion_parc.demo;

import com.gestion_parc.demo.beans.Assurance;
import com.gestion_parc.demo.beans.Statistique;
import com.gestion_parc.demo.beans.Vehicule;
import com.gestion_parc.demo.services.implementations.StatistiqueService;
import com.gestion_parc.demo.services.implementations.VehiculeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	VehiculeService vehiculeService;

	@Autowired
	StatistiqueService statistiqueService;

	@Test
	void contextLoads() {
	}

	@Test
	void showMe(){
		List<Statistique> statistiques= statistiqueService.findByVehicule(Long.valueOf(1));

		System.out.println("------------------------------------------------------");
		for (Statistique statistique:statistiques) {
			System.out.println(statistique.getMonth() +"  "+statistique.getCost());
		}
	}
	@Test
	public void addVehicule(){
		Vehicule vehicule1=Vehicule.builder()
				.immatriculation("imm1")
				.build();

		Vehicule vehicule2=Vehicule.builder()
				.immatriculation("imm2")
				.build();

		Vehicule vehicule3=Vehicule.builder()
				.immatriculation("imm3")
				.build();

		vehiculeService.add(vehicule1);
		vehiculeService.add(vehicule2);
		vehiculeService.add(vehicule3);
	}


}
