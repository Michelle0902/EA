package app;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class School {
	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Student> students = new ArrayList<>();

	public Long getId() { return id; }

	public void setId(Long id) { this.id = id; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public List<Student> getStudents() { return students; }

	public void setStudents(List<Student> students) { this.students = students; }

	@Override
	public String toString() {
		return "School{id=" + id + ", name='" + name + '\'' + '}';
	}
}