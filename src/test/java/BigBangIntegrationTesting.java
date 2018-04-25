import controller.DoctorController;
import exceptions.PatientException;
import model.Consultation;
import model.Patient;
import org.junit.Assert;
import org.junit.Test;
import repository.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BigBangIntegrationTesting {
    private String filePatients = "TestFilePatients.txt";
    private String fileConsultations = "TestFileConsultations.txt";

    public void prepareRepo() {
        Repository repo = mock(Repository.class);
        DoctorController ctrl = new DoctorController(repo);

    }

    @Test
    public void tc_c_1_GetTwoPatientWithCommonValidDisease() {
        Repository repo = mock(Repository.class);


        List<Consultation> consultations = new ArrayList<Consultation>();
        Consultation c1 = new Consultation("1", "1234567890123", "Flu", Collections.singletonList("Tea"),"10-04-2018");
        Consultation c2 = new Consultation("2", "2234567890123", "Flu", Collections.singletonList("Tea"),"25-04-2018");
        consultations.add(c1);
        consultations.add(c2);

        List<Patient> patients = new ArrayList<Patient>();
        Patient p1 = new Patient("James", "1234567890123", "Broadway Str.");
        Patient p2 = new Patient("Sally", "2234567890123", "Rainbow Avenue");
        patients.add(p1);
        patients.add(p2);

        when(repo.getConsultationList()).thenReturn(consultations);
        when(repo.getPatientList()).thenReturn(patients);

        DoctorController ctrl = new DoctorController(repo);

        try {
            Assert.assertEquals(ctrl.getPatientsWithDisease("Flu").size(), 2);
        } catch (PatientException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_BigBang() {
        // a
        AddPatientBBT a = new AddPatientBBT();
        // b
        AddValidConsultation b = new AddValidConsultation();

        a.test_tc_1_AllValid();
        b.addConsultation();
        this.tc_c_1_GetTwoPatientWithCommonValidDisease();
    }
}
