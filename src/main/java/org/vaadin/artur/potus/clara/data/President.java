package org.vaadin.artur.potus.clara.data;

import java.util.Date;

public class President {
	private String firstName, lastName;
	private Party party;
	private Date tookOffice, leftOffice;

	public President(String firstName, String lastName, Party party,
			Date tookOffice, Date leftOffice) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.party = party;
		this.tookOffice = tookOffice;
		this.leftOffice = leftOffice;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Party getParty() {
		return party;
	}

	public void setParty(Party party) {
		this.party = party;
	}

	public Date getTookOffice() {
		return tookOffice;
	}

	public void setTookOffice(Date tookOffice) {
		this.tookOffice = tookOffice;
	}

	public Date getLeftOffice() {
		return leftOffice;
	}

	public void setLeftOffice(Date leftOffice) {
		this.leftOffice = leftOffice;
	}

}
