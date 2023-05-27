package loic_steve_fohoue_chendjou.Test;

import loic_steve_fohoue_chendjou.Services.PatientBLService;
import loic_steve_fohoue_chendjou.Services.PatientDAO;
import loic_steve_fohoue_chendjou.Services.PatientReader;
import loic_steve_fohoue_chendjou.datamodel.Patient;

import java.io.IOException;
import java.util.List;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.List;

public class TestBLI1 {
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        PatientReader patientCSVReader = new PatientReader();
        List<Patient> patientList = patientCSVReader.readAll();

        Integer i = 0;
        for (Patient patient : patientList) {
            if (i == 5) {
                System.out.println(PatientBLService.computeSeniority(patient));
                break;
            }
            i++;
        }
    }
}
