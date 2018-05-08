import controller.DoctorController;
import exceptions.ConsultationException;
import model.Consultation;
import model.Patient;
import org.junit.Assert;
import org.junit.Test;
import repository.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.when;

public class AddInvalidConsultation {
    private Repository repo;
    private DoctorController ctrl;
    private List<Consultation> consultations;
    private List<Patient> patients;

    public void initialize() {
        repo = mock(Repository.class);

        consultations = new ArrayList<Consultation>();
        Consultation c1 = new Consultation("6", "1234567890123", "Flu", Collections.singletonList("Tea"), "10-04-2018");
        Consultation c2 = new Consultation("7", "2234567890123", "Flu", Collections.singletonList("Tea"), "25-04-2018");
        consultations.add(c1);
        consultations.add(c2);

        patients = new ArrayList<Patient>();
        Patient p1 = new Patient("James", "1234567890123", "Broadway Str.");
        Patient p2 = new Patient("Sally", "2234567890123", "Rainbow Avenue");
        patients.add(p1);
        patients.add(p2);

        when(repo.getConsultationList()).thenReturn(consultations);
        when(repo.getPatientList()).thenReturn(patients);

        ctrl = new DoctorController(repo);
    }

    @Test
    public void addConsultation() {
        initialize();

        int nrConsultations = consultations.size();
        int idConsultation = Integer.parseInt(ctrl.getConsultationList().get(nrConsultations-1).getConsID()) + 1;
        List<String> meds = new ArrayList<String>();
        meds.add("No med");
        try {
            ctrl.addConsultation(String.valueOf(idConsultation), "000", "Should not work", meds, "04.04.2018");

        } catch (ConsultationException e) {

        }

        Assert.assertEquals(consultations.size(), nrConsultations );

    }
}
