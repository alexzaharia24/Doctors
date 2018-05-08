import controller.DoctorController;
import exceptions.PatientException;
import org.junit.Assert;
import org.junit.Test;
import repository.Repository;

public class GetPatientsWithCommonDiseaseBBT {
    private String filePatients = "TestFilePatients.txt";
    private String fileConsultations = "TestFileConsultations.txt";

    @Test
    public void tc_1_GetOnePatientWithValidDisease(String desease) {
        Repository repo = new Repository(filePatients, fileConsultations);
        DoctorController ctrl = new DoctorController(repo);

        try {
            Assert.assertEquals(ctrl.getPatientsWithDisease(desease).size(), 1);
        } catch (PatientException e) {
            e.printStackTrace();
        }
    }
}
