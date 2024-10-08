package com.SafetyNet.Projet5;

import com.SafetyNet.Projet5.model.DataJson;
import com.SafetyNet.Projet5.util.DataReaderUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Projet5Application {
	public static void main(String[] args) throws IOException {
		DataReaderUtil readerUtil = new DataReaderUtil();
		readerUtil.getPersons();
		readerUtil.getFirestations();
		readerUtil.getMedicalrecords();
		SpringApplication.run(Projet5Application.class, args);
	}

}
