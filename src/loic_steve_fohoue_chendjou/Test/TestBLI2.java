package loic_steve_fohoue_chendjou.Test;

import loic_steve_fohoue_chendjou.Services.PatientBLService;
import loic_steve_fohoue_chendjou.Services.PatientReader;
import loic_steve_fohoue_chendjou.datamodel.Patient;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class TestBLI2 {
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        PatientReader patientCSVReader = new PatientReader();
        List<Patient> patientList = patientCSVReader.readAll();
        System.out.println(PatientBLService.computeSeniorityByPatient(patientList));
    }
}
