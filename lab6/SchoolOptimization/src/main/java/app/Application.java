package app;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.List;

@EnableTransactionManagement
@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	SchoolRepository schoolRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {
		insertSchools();
		retrieveOnlySchools();
		retrieveSchoolsWithStudents();
	}

	@Transactional
	public void insertSchools() {
		List<School> schools = new ArrayList<>();
		for (int i = 0; i < 1000; i++) {
			School school = new School();
			school.setName("School " + i);
			for (int j = 0; j < 200; j++) {
				Student student = new Student("First" + j, "Last" + j, "student" + j + "@school.com");
				student.setSchool(school);
				school.getStudents().add(student);
			}
			schools.add(school);
		}

		long start = System.currentTimeMillis();
		schoolRepository.saveAll(schools);
		long end = System.currentTimeMillis();
		System.out.println("Inserted 1000 schools with students in " + (end - start) + " ms");
	}

	public void retrieveOnlySchools() {
		long start = System.currentTimeMillis();

		List<School> schools = schoolRepository.findAllWithoutStudents();
		schools.forEach(s -> System.out.println("Sch" + s.getName()));

		long end = System.currentTimeMillis();
		System.out.println("Retrieve only schools took: " + (end - start) + " ms");
	}

	public void retrieveSchoolsWithStudents() {
		long start = System.currentTimeMillis();

		List<School> schools = schoolRepository.findAllWithStudents();
		for (School s : schools) {
			System.out.println("Sch" + s.getName());
			for (Student student : s.getStudents()) {
				System.out.println("Student" + student.getFirstName() + " " + student.getLastName());
			}
		}

		long end = System.currentTimeMillis();
		System.out.println("Retrieve schools with students took: " + (end - start) + " ms");
	}
}