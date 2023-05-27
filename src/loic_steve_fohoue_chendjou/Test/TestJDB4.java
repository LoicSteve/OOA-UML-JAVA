package loic_steve_fohoue_chendjou.Test;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import loic_steve_fohoue_chendjou.Services.*;
import loic_steve_fohoue_chendjou.datamodel.*;
public class TestJDB4 {


    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");

            //Create DB Tables
            List<String> inputFiles = Arrays.asList("insurances", "medications", "patients", "prescriptions");
            for (String inputFileName : inputFiles) {
                String pathName = "resources/create-" + inputFileName + ".sql";
                String query = Files.readString(Path.of(pathName));

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.execute();
            }
            // Create Service Instances
            MedicationDAO medicationDAO = new MedicationDAO(connection);
            PrescriptionDAO prescriptionDAO = new PrescriptionDAO(connection);
            PatientDAO patientDAOForTestJDB2 = new PatientDAO(connection);
            InsuranceDAO insuranceDAO = new InsuranceDAO(connection);

            // read and InsertFiles Files
            PatientReader patientCSVReader = new PatientReader();
            List<Patient> patientList = patientCSVReader.readAll();

            PrescriptionReader prescriptionCSVReader = new PrescriptionReader();
            List<Prescription> prescriptionList = prescriptionCSVReader.readAll();

            MedicationReader medicationCSVReader = new MedicationReader();
            List<Medication> medicationList = medicationCSVReader.readAll();

            InsuranceReader insuranceCSVReader = new InsuranceReader();
            List<Insurances> insuranceList = insuranceCSVReader.readAll();

            // Insert Record to DB
            for (Patient patient : patientList){
                patientDAOForTestJDB2.create(patient);
            }
            for (Prescription prescription : prescriptionList){
                prescriptionDAO.create(prescription);
            }
            for (Medication medication : medicationList){
                medicationDAO.create(medication);
            }
            for (Insurances insurance : insuranceList){
                insuranceDAO.create(insurance);
            }


            // our main code
            List<Prescription> prescriptions = prescriptionDAO.readAll();

            for (Prescription prescription : prescriptions) {
                Medication medication = medicationDAO.read(prescription.getPrescCode());
                System.out.println("Patient Name: " + prescription.getPrescRefPat());
                System.out.println("Medication Name: " + medication.getMedicationName());
                System.out.println("Prescription Days: " + prescription.getPrescDays());
                System.out.println();
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}
