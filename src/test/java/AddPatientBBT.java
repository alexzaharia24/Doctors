import controller.DoctorController;
import exceptions.PatientException;
import model.Patient;
import org.junit.Assert;
import org.junit.Test;
import repository.Repository;

import java.util.List;

public class AddPatientBBT {
    private String filePatients = "TestFilePatients.txt";
    private String fileConsultations = "TestFileConsultations.txt";

    @Test
    public void test_tc_1_AllValid() {
        Repository repo = new Repository(filePatients, fileConsultations);
        DoctorController ctrl = new DoctorController(repo);

        List<Patient> pList = ctrl.getPatientList();
        int initialSize = pList.size();
        Patient p = new Patient("Tom", "1234567890123", "Home");
        try {
            ctrl.addPatient(p);
        } catch (PatientException e) {
        }

        Assert.assertEquals(pList.size(), initialSize+1);
    }

    @Test
    public void test_tc_2_CNPOnlyDigitsTooShort() {
        Repository repo = new Repository(filePatients, fileConsultations);
        DoctorController ctrl = new DoctorController(repo);

        List<Patient> pList = ctrl.getPatientList();
        int initialSize = pList.size();
        Patient p = new Patient("Tom", "100", "Home");
        try {
            ctrl.addPatient(p);
        } catch (PatientException e) {
        }

        Assert.assertEquals(pList.size(), initialSize);
    }

    @Test
    public void test_tc_3_CNPDigitsAndChars() {
        Repository repo = new Repository(filePatients, fileConsultations);
        DoctorController ctrl = new DoctorController(repo);

        List<Patient> pList = ctrl.getPatientList();
        int initialSize = pList.size();
        Patient p = new Patient("Tom", "100a", "Home");
        try {
            ctrl.addPatient(p);
        } catch (PatientException e) {
        }

        Assert.assertEquals(pList.size(), initialSize);
    }

    @Test
    public void test_tc_4_NameInvalidChars() {
        Repository repo = new Repository(filePatients, fileConsultations);
        DoctorController ctrl = new DoctorController(repo);

        List<Patient> pList = ctrl.getPatientList();
        int initialSize = pList.size();
        try {
            Patient p = new Patient("Tom$", "1990825060078", "Home");
            ctrl.addPatient(p);
        } catch (PatientException e) {
        }

        Assert.assertEquals(pList.size(), initialSize);
    }

    @Test
    public void test_tc_5_AddressInvalidChars() {
        Repository repo = new Repository(filePatients, fileConsultations);
        DoctorController ctrl = new DoctorController(repo);

        List<Patient> pList = ctrl.getPatientList();
        int initialSize = pList.size();
        try {
            Patient p = new Patient("Tom", "1990825060078", "Home$");
            ctrl.addPatient(p);
        } catch (PatientException e) {
        }

        Assert.assertEquals(pList.size(), initialSize);
    }

    @Test
    public void test_tc_6_NameEmpty() {
        Repository repo = new Repository(filePatients, fileConsultations);
        DoctorController ctrl = new DoctorController(repo);

        List<Patient> pList = ctrl.getPatientList();
        int initialSize = pList.size();
        try {
            Patient p = new Patient("", "1990825060078", "Home");
            ctrl.addPatient(p);
        } catch (PatientException e) {
        }

        Assert.assertEquals(pList.size(), initialSize);
    }

    @Test
    public void test_tc_7_AddressEmpty() {
        Repository repo = new Repository(filePatients, fileConsultations);
        DoctorController ctrl = new DoctorController(repo);

        List<Patient> pList = ctrl.getPatientList();
        int initialSize = pList.size();
        try {
            Patient p = new Patient("Tom", "1990825060078", "");
            ctrl.addPatient(p);
        } catch (PatientException e) {
        }

        Assert.assertEquals(pList.size(), initialSize);
    }

    @Test
    public void test_tc_8_CNP12Digits() {
        Repository repo = new Repository(filePatients, fileConsultations);
        DoctorController ctrl = new DoctorController(repo);

        List<Patient> pList = ctrl.getPatientList();
        int initialSize = pList.size();
        try {
            Patient p = new Patient("Tom", "123456789012", "Home");
            ctrl.addPatient(p);
        } catch (PatientException e) {
        }

        Assert.assertEquals(pList.size(), initialSize);
    }

    @Test
    public void test_tc_9_CNP14Digits() {
        Repository repo = new Repository(filePatients, fileConsultations);
        DoctorController ctrl = new DoctorController(repo);

        List<Patient> pList = ctrl.getPatientList();
        int initialSize = pList.size();
        try {
            Patient p = new Patient("Tom", "12345678901234", "Home");
            ctrl.addPatient(p);
        } catch (PatientException e) {
        }

        Assert.assertEquals(pList.size(), initialSize);
    }

    @Test
    public void test_tc_10_Name1ValidChar() {
        Repository repo = new Repository(filePatients, fileConsultations);
        DoctorController ctrl = new DoctorController(repo);

        List<Patient> pList = ctrl.getPatientList();
        int initialSize = pList.size();
        try {
            Patient p = new Patient("T", "1234567890123", "Home");
            ctrl.addPatient(p);
        } catch (PatientException e) {
        }

        Assert.assertEquals(pList.size(), initialSize+1);
    }
}
