
import repository.Repository;
import ui.DoctorUI;
import controller.DoctorController;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class App {

    public static void doSmth() throws Exception {
        FileReader fr = new FileReader("file.fff");
        System.out.println("Trece");

    }

    public static void main(String[] args) {
//		String patients = "FilePatients.txt";
//		String consultations = "FileConsultations.txt";
//		Repository repo = new Repository(patients, consultations);
//		DoctorController ctrl = new DoctorController(repo);
//
//		DoctorUI console = new DoctorUI(ctrl);
//		console.Run();

        try {
            App.doSmth();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
