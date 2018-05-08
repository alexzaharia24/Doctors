import controller.DoctorController;
import exceptions.ConsultationException;
import exceptions.PatientException;
import model.Consultation;
import model.Patient;
import org.junit.Assert;
import org.junit.Test;
import repository.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class IncrementalIntegrationTopDown {
    private String filePatients = "TestFilePatients.txt";
    private String fileConsultations = "TestNonexistentFileConsultations.txt";
    private Repository repo;
    private DoctorController ctrl;
    private List<Consultation> consultations;
    private List<Patient> patients;

    public void initialize() {
        repo = mock(Repository.class);

        consultations = new ArrayList<Consultation>();
        Consultation c1 = new Consultation("2", "1234567890123", "Flu", Collections.singletonList("Tea"), "10-04-2018");
        Consultation c2 = new Consultation("3", "2234567890123", "Flu", Collections.singletonList("Tea"), "25-04-2018");
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
    public void test_topDown_1() {
        initialize();
        // a
        int initialSize = patients.size();
        Patient p = new Patient("Tom", "1234567890123", "Home");
        try {
            ctrl.addPatient(p);
        } catch (PatientException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(patients.size(), initialSize + 1);
    }

    @Test
    public void test_topDown_2() {
        // a & b
        test_topDown_1();

        int nrConsultations = consultations.size();

        Patient p = patients.get(0);
        int idConsultation = Integer.parseInt(consultations.get(nrConsultations - 1).getConsID()) + 1;
        List<String> meds = new ArrayList<String>();
        meds.add("No med");
        try {
            ctrl.addConsultation(String.valueOf(idConsultation), p.getSSN(), "Flu", meds, "04.04.2018");

        } catch (ConsultationException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(ctrl.getConsultationList().size(), nrConsultations + 1);
    }

    @Test
    public void test_topDown_3() {
        // a & b & c
        test_topDown_2();

        try {
            Assert.assertEquals(ctrl.getPatientsWithDisease("Flu").size(), 3);
        } catch (PatientException e) {
            e.printStackTrace();
        }
    }
}
