package org.vaadin.artur.potus.clara.data;

import java.time.LocalDate;

public class President {
	private String firstName, lastName;
	private Party party;
	private LocalDate tookOffice, leftOffice;

	public President(String firstName, String lastName, Party party,
			LocalDate tookOffice, LocalDate leftOffice) {
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

	public LocalDate getTookOffice() {
		return tookOffice;
	}

	public void setTookOffice(LocalDate tookOffice) {
		this.tookOffice = tookOffice;
	}

	public LocalDate getLeftOffice() {
		return leftOffice;
	}

	public void setLeftOffice(LocalDate leftOffice) {
		this.leftOffice = leftOffice;
	}

}
