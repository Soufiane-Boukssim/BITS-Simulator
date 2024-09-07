package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_DISPLAY_OBJECT")
public class NdcDisplayObject {

	@EmbeddedId
	private NdcDisplayObjectId id;
	@Column(name = "DISPLAYED_OBJECT")
	private String displayedObject;
	@Column(name = "OBJECT_TYPE")
	private int objectType;
	@Column(name = "LINE_NUMBER")
	private int lineNumber;
	@Column(name = "COLUMN_NUMBER")
	private int columnNumber;
	@Column(name = "OBJECT_NUMBER")
	private String objectNumber;

	public NdcDisplayObjectId getId() {
		return id;
	}

	public void setId(NdcDisplayObjectId id) {
		this.id = id;
	}

	public String getDisplayedObject() {
		return displayedObject;
	}

	public void setDisplayedObject(String displayedObject) {
		this.displayedObject = displayedObject;
	}

	public int getObjectType() {
		return objectType;
	}

	public void setObjectType(int objectType) {
		this.objectType = objectType;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getColumnNumber() {
		return columnNumber;
	}

	public void setColumnNumber(int columnNumber) {
		this.columnNumber = columnNumber;
	}

	public String getObjectNumber() {
		return objectNumber;
	}

	public void setObjectNumber(String objectNumber) {
		this.objectNumber = objectNumber;
	}
}