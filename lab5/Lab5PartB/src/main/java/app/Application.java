package app;

import app.domain.Appointment;
import app.repositories.AppointmentRepository;
import app.repositories.DoctorRepository;
import app.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "app.repositories")
@EntityScan(basePackages = "app.domain")
public class Application implements CommandLineRunner{

	@Autowired
	private AppointmentRepository appointmentRepository;

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		app.domain.Doctor doctor1 = new app.domain.Doctor("Chirurg", "Frank", "Brown");
		doctorRepository.save(doctor1);
		app.domain.Doctor doctor2 = new app.domain.Doctor("Nurse", "Mary", "Jones");
		doctorRepository.save(doctor2);

		app.domain.Payment payment1 = new app.domain.Payment("10-10-2008", 12.50);
		app.domain.Payment payment2 = new app.domain.Payment("11-10-2008", 45.00);
		app.domain.Payment payment3 = new app.domain.Payment("12-10-2008", 99.60);
		app.domain.Payment payment4 = new app.domain.Payment("13-10-2008", 55.80);

		app.domain.Patient patient1 = new app.domain.Patient("Jerry Lewis");
		app.domain.Address address1 = new app.domain.Address("34 4th avenue", "New York", "13221");
		patient1.setAddress(address1);
		patientRepository.save(patient1);

		app.domain.Patient patient2 = new app.domain.Patient("Frank Moore");
		app.domain.Address address2 = new app.domain.Address("34 Mainstreet", "New York", "13221");
		patient2.setAddress(address2);
		patientRepository.save(patient2);

		app.domain.Patient patient3 = new app.domain.Patient("Sam Ruby");
		app.domain.Address address3 = new app.domain.Address("105 N Street", "New York", "13221");
		patient3.setAddress(address3);
		patientRepository.save(patient3);

		Appointment appointment1 = new Appointment("11-11-2008", patient1, payment1, doctor1);
		Appointment appointment2 = new Appointment("12-11-2008", patient2, payment2, doctor2);
		Appointment appointment3 = new Appointment("13-11-2008", patient3, payment3, doctor2);
		Appointment appointment4 = new Appointment("14-11-2008", patient1, payment4, doctor1);

		appointmentRepository.save(appointment1);
		appointmentRepository.save(appointment2);
		appointmentRepository.save(appointment3);
		appointmentRepository.save(appointment4);
	}


}
