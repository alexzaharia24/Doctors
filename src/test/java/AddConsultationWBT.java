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

public class AddConsultationWBT {
    private String filePatients = "TestFilePatients.txt";
    private String fileConsultations = "TestFileConsultations.txt";
    private Repository repo;
    private DoctorController ctrl;
    private List<Consultation> consultations;
    private List<Patient> patients;

    public void initialize() {
        repo = mock(Repository.class);

        consultations = new ArrayList<Consultation>();
        Consultation c1 = new Consultation("1", "1234567890123", "Flu", Collections.singletonList("Tea"), "10-04-2018");
        Consultation c2 = new Consultation("2", "2234567890123", "Flu", Collections.singletonList("Tea"), "25-04-2018");
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
    public void tc_1() {
        initialize();

        try {
            ctrl.addConsultation("3", "1234567890123", "Healthy", null, "21-05-2018");
        } catch (ConsultationException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(ctrl.getConsultationList().size(), 2);
    }

    @Test
    public void tc_2() {
        initialize();

        try {
            ctrl.addConsultation("3", "1234567890123", null, new ArrayList<String>(), "21-05-2018");
        } catch (ConsultationException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(ctrl.getConsultationList().size(), 2);
    }

    @Test
    public void tc_3() {
        Repository repo = new Repository(filePatients, fileConsultations);
        DoctorController ctrl = new DoctorController(repo);

        try {
            ctrl.addConsultation("3", "1234567890123", "Healthy", new ArrayList<String>(), "21-05-2018");
        } catch (ConsultationException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(ctrl.getConsultationList().size(), 1);
    }

    @Test
    public void tc_4() {
        initialize();

        try {
            ctrl.addConsultation("3", "1234567890123", "Cold", Collections.singletonList("Parasinus"), "21-05-2018");
        } catch (ConsultationException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(ctrl.getConsultationList().size(), 3);
    }

}
