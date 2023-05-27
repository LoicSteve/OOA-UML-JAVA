package loic_steve_fohoue_chendjou.Test;

import loic_steve_fohoue_chendjou.Services.InsuranceReader;
import loic_steve_fohoue_chendjou.Services.PatientReader;
import loic_steve_fohoue_chendjou.datamodel.Insurances;
import loic_steve_fohoue_chendjou.datamodel.Patient;
import loic_steve_fohoue_chendjou.Services.PatientReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TestOOP2 {
        public static void main (String[]args) throws FileNotFoundException {
            InsuranceReader insuranceCSVReader = new InsuranceReader();
            List<Insurances> insuranceList = insuranceCSVReader.readAll();
            System.out.println(insuranceList);

            PatientReader patientCSVReader = new PatientReader();
            List<Patient> patientList = patientCSVReader.readAll();
            System.out.println(patientList);
        }

    }
