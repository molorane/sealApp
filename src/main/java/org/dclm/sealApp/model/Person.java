package org.dclm.sealApp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.dclm.sealApp.model.enums.Gender;
import org.dclm.sealApp.model.enums.MaritalStatus;
import org.dclm.sealApp.model.enums.Race;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "account_info")
public class Person implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "id")
	private Account account;

	@NotEmpty(message = "last name Required.")
	private String lastName;

	@NotEmpty(message = "first name Required.")
	private String firstName;

	private String otherName;

	@Past
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate dob;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Enumerated(EnumType.STRING)
	private MaritalStatus maritalStatus;

	@Enumerated(EnumType.STRING)
	private Race race;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="title_id", nullable=false)
	private Title title;

	private String about;

	@OneToOne
	@JoinColumn(name = "recorded_by")
	private Account recordedBy;

}
