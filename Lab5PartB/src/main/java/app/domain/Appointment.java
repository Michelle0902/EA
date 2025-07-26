package app.domain;

import app.domain.Doctor;
import app.domain.Patient;
import app.domain.Payment;
import jakarta.persistence.*;

@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String appdate;

	@ManyToOne
	@JoinColumn(name="patient_id")
	private Patient patient;

	@ManyToOne
	@JoinColumn(name="doctor_id")
	private Doctor doctor;

	@Embedded
	private Payment payment;

	public Appointment() {
	}

	public Appointment(String appdate, app.domain.Patient patient, app.domain.Payment payment,
					   app.domain.Doctor doctor) {
		this.appdate = appdate;
		this.patient = patient;
		this.payment = payment;
		this.doctor = doctor;
	}


	public String getAppdate() {
		return appdate;
	}

	public void setAppdate(String appdate) {
		this.appdate = appdate;
	}

	public app.domain.Patient getPatient() {
		return patient;
	}

	public void setPatient(app.domain.Patient patient) {
		this.patient = patient;
	}

	public app.domain.Payment getPayment() {
		return payment;
	}

	public void setPayment(app.domain.Payment payment) {
		this.payment = payment;
	}

	public app.domain.Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(app.domain.Doctor doctor) {
		this.doctor = doctor;
	}

}
