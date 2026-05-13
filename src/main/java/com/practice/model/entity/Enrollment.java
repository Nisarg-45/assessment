package com.practice.model.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table
@Entity
public class Enrollment {
	
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer id;

	    private LocalDate enrollmentDate;

	    private String status;
	    
	    @ManyToOne
	    @JoinColumn(name = "student_id")
	    private Student student;
	    
	    @ManyToOne
	    @JoinColumn(name = "course_id")
	    private Course course;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public LocalDate getEnrollmentDate() {
			return enrollmentDate;
		}

		public void setEnrollmentDate(LocalDate enrollmentDate) {
			this.enrollmentDate = enrollmentDate;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Student getStudent() {
			return student;
		}

		public void setStudent(Student student) {
			this.student = student;
		}

		public Course getCourse() {
			return course;
		}

		public void setCourse(Course course) {
			this.course = course;
		}

		public Enrollment(Integer id, LocalDate enrollmentDate, String status, Student student, Course course) {
			super();
			this.id = id;
			this.enrollmentDate = enrollmentDate;
			this.status = status;
			this.student = student;
			this.course = course;
		}

		public Enrollment() {
			super();
		}
	    
	    

}
