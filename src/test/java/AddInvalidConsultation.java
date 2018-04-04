import controller.DoctorController;
import exceptions.ConsultationException;
import model.Patient;
import org.junit.Assert;
import org.junit.Test;
import repository.Repository;

import java.util.ArrayList;
import java.util.List;

public class AddInvalidConsultation {
    @Test
    public void addConsultation() {
        String patients = "TestFilePatients.txt";
        String consultations = "TestFileConsultations.txt";
        Repository repo = new Repository(patients, consultations);
        DoctorController ctrl = new DoctorController(repo);

        int nrConsultations = ctrl.getConsultationList().size();

        int idConsultation = Integer.parseInt(ctrl.getConsultationList().get(nrConsultations-1).getConsID()) + 1;
        List<String> meds = new ArrayList<String>();
        meds.add("No med");
        try {
            ctrl.addConsultation(String.valueOf(idConsultation), "000", "Should not work", meds, "04.04.2018");

        } catch (ConsultationException e) {

        }

        Assert.assertEquals(ctrl.getConsultationList().size(), nrConsultations );

    }
}
