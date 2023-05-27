package loic_steve_fohoue_chendjou.Services;
import java.time.LocalDate;
import java.time.Period;
import loic_steve_fohoue_chendjou.datamodel.Patient;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PatientBLService {
    public static Integer computeSeniority(Patient patient) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date subscriptionDate = dateFormat.parse(dateFormat.format(patient.getPat_sub_date()));
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        Integer seniority = 0;
        if (subscriptionDate.before(currentDate)) {
            calendar.setTime(subscriptionDate);
            int subscriptionMonth = calendar.get(Calendar.MONTH);
            int subscriptionYear = calendar.get(Calendar.YEAR);
            calendar.setTime(currentDate);
            int currentMonth = calendar.get(Calendar.MONTH);
            int currentYear = calendar.get(Calendar.YEAR);
            seniority = (currentYear - subscriptionYear) * 12 + (currentMonth - subscriptionMonth);
            if (calendar.get(Calendar.DATE) > 15) {
                seniority++; // round up to the next month if there are more than 15 days
            }
        }
        return seniority;
    }

    public static Map<String, Integer> computeSeniorityByPatient(List<Patient> patients) throws ParseException {
        Map<String, Integer> seniorityByPatient = new HashMap<>();
        for (Patient patient : patients) {
            seniorityByPatient.put(patient.getPat_num_HC(), computeSeniority(patient));
        }

        return seniorityByPatient;
    }
}
