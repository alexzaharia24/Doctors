import model.Patient;
import org.junit.Assert;
import org.junit.Test;
import repository.Repository;

public class PatientTests {
    @Test
    public void testPatient() {
        Patient p1 = new Patient("P1", "1234567890123", "A1");
        Assert.assertEquals(p1.getName(), "P1");
        Assert.assertEquals(p1.getSSN(), "1234567890123");
        Assert.assertEquals(p1.getAddress(), "A1");
    }
}
