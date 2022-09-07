package acme.entities.pimpam;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.URL;

import acme.entities.item.Item;
import acme.framework.datatypes.Money;
import acme.framework.entities.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Pimpam extends AbstractEntity {

	// Serialisation identifier -----------------------------------------------

	protected static final long	serialVersionUID	= 1L;

	// Attributes -------------------------------------------------------------
	
	@Pattern(regexp = "^([A-Z]{2}:)?[A-Z]{3}-[0-9]{3}$")
	@Column(unique=true)
	@NotBlank
	protected String					acode;

	
	@NotBlank
	@Length(max = 100)
	protected String					atitle;

	
	@NotBlank
	@Length(max = 255)
	protected String					adescription;
	
	
	@NotNull
	@Past
	@Temporal(TemporalType.TIMESTAMP)
	protected Date 						ainstantationMoment;

	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date 						astartDate;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	protected Date 						aendDate;
	
	
	@Valid
	@NotNull
	protected Money 			abudget;
	

	@URL
	protected String			alink;
	
	
	// Derived attributes -----------------------------------------------------
	
	// Relationships ----------------------------------------------------------

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	protected Item item;

}