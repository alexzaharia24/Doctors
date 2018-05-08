import controller.DoctorController;
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

public class AddPatientBBT {
    private String filePatients = "TestFilePatients.txt";
    private String fileConsultations = "TestFileConsultations.txt";

    private Repository repo;
    private DoctorController ctrl;
    private List<Consultation> consultations;
    private List<Patient> patients;

    public void initialize() {
        repo = mock(Repository.class);

        consultations = new ArrayList<Consultation>();
        Consultation c1 = new Consultation("3", "1234567890123", "Flu", Collections.singletonList("Tea"), "10-04-2018");
        Consultation c2 = new Consultation("4", "2234567890123", "Flu", Collections.singletonList("Tea"), "25-04-2018");
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
    public void test_tc_1_AllValid() {
        initialize();

        int initialSize = patients.size();
        Patient p = new Patient("Tom", "1234567890123", "Home");
        try {
            ctrl.addPatient(p);
        } catch (PatientException e) {
        }

        Assert.assertEquals(patients.size(), initialSize + 1);
    }

    @Test
    public void test_tc_2_CNPOnlyDigitsTooShort() {
        initialize();
        int initialSize = patients.size();
        Patient p = new Patient("Tom", "100", "Home");
        try {
            ctrl.addPatient(p);
        } catch (PatientException e) {
        }

        Assert.assertEquals(patients.size(), initialSize);
    }

    @Test
    public void test_tc_3_CNPDigitsAndChars() {
        initialize();
        int initialSize = patients.size();
        Patient p = new Patient("Tom", "100a", "Home");
        try {
            ctrl.addPatient(p);
        } catch (PatientException e) {
        }

        Assert.assertEquals(patients.size(), initialSize);
    }

    @Test
    public void test_tc_4_NameInvalidChars() {
        initialize();
        int initialSize = patients.size();
        try {
            Patient p = new Patient("Tom$", "1990825060078", "Home");
            ctrl.addPatient(p);
        } catch (PatientException e) {
        }

        Assert.assertEquals(patients.size(), initialSize);
    }

    @Test
    public void test_tc_5_AddressInvalidChars() {
        initialize();
        int initialSize = patients.size();
        try {
            Patient p = new Patient("Tom", "1990825060078", "Home$");
            ctrl.addPatient(p);
        } catch (PatientException e) {
        }

        Assert.assertEquals(patients.size(), initialSize);
    }

    @Test
    public void test_tc_6_NameEmpty() {
        initialize();
        int initialSize = patients.size();
        try {
            Patient p = new Patient("", "1990825060078", "Home");
            ctrl.addPatient(p);
        } catch (PatientException e) {
        }

        Assert.assertEquals(patients.size(), initialSize);
    }

    @Test
    public void test_tc_7_AddressEmpty() {
        initialize();
        int initialSize = patients.size();
        try {
            Patient p = new Patient("Tom", "1990825060078", "");
            ctrl.addPatient(p);
        } catch (PatientException e) {
        }

        Assert.assertEquals(patients.size(), initialSize);
    }

    @Test
    public void test_tc_8_CNP12Digits() {
        initialize();
        int initialSize = patients.size();
        try {
            Patient p = new Patient("Tom", "123456789012", "Home");
            ctrl.addPatient(p);
        } catch (PatientException e) {
        }

        Assert.assertEquals(patients.size(), initialSize);
    }

    @Test
    public void test_tc_9_CNP14Digits() {
        initialize();
        int initialSize = patients.size();
        try {
            Patient p = new Patient("Tom", "12345678901234", "Home");
            ctrl.addPatient(p);
        } catch (PatientException e) {
        }

        Assert.assertEquals(patients.size(), initialSize);
    }

    @Test
    public void test_tc_10_Name1ValidChar() {
        initialize();
        int initialSize = patients.size();
        try {
            Patient p = new Patient("T", "1234567890123", "Home");
            ctrl.addPatient(p);
        } catch (PatientException e) {
        }

        Assert.assertEquals(patients.size(), initialSize + 1);
    }
}
