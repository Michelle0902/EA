package app;

import app.domain.*;
import app.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "app.repositories")
@EntityScan(basePackages = "app.domain")
public class Application implements CommandLineRunner {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private DepartmentRepository departmentRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private GradeRepository gradeRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Sample data setup
		Department dept1 = new Department();
		dept1.setName("Engineering");
		departmentRepository.save(dept1);

		Course course1 = new Course();
		course1.setName("Algorithms");
		courseRepository.save(course1);

		Student student1 = new Student();
		student1.setName("Alice");
		student1.setStudentNumber("S001");
		student1.setDepartment(dept1);
		studentRepository.save(student1);

		Student student2 = new Student();
		student2.setName("Bob");
		student2.setStudentNumber("S002");
		student2.setDepartment(dept1);
		studentRepository.save(student2);

		Grade grade1 = new Grade();
		grade1.setGrade("A");
		grade1.setStudent(student1);
		grade1.setCourse(course1);
		gradeRepository.save(grade1);

		Grade grade2 = new Grade();
		grade2.setGrade("B");
		grade2.setStudent(student2);
		grade2.setCourse(course1);
		gradeRepository.save(grade2);

		System.out.println("\n[Method] Students from Engineering:");
		studentRepository.findByDepartmentName("Engineering")
				.forEach(s -> System.out.println(s.getName()));

		System.out.println("\n[Method] Students who took Algorithms:");
		studentRepository.findByGradesCourseName("Algorithms")
				.forEach(s -> System.out.println(s.getName()));

		System.out.println("\n[@Query] Students from Engineering:");
		studentRepository.findStudentsByDepartmentName("Engineering")
				.forEach(s -> System.out.println(s.getName()));

		System.out.println("\n[@Query] Students who took Algorithms:");
		studentRepository.findStudentsByCourseName("Algorithms")
				.forEach(s -> System.out.println(s.getName()));
	}
}
