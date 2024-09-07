package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_DISPLAY_TEXT")
public class NdcDisplayText {

	@EmbeddedId
	private NdcDisplayTextId id;
	@Column(name = "DISPLAYED_TEXT")
	private String displayedText;
	@Column(name = "LINE_NUMBER")
	private int lineNumber;
	@Column(name = "COLUMN_NUMBER")
	private int columnNumber;
	@Column(name = "TEXT_LENGTH")
	private int textLength;
	@Column(name = "TEXT")
	private String text;
	@Column(name = "FG_COLOUR")
	private String fgColour;
	@Column(name = "BG_COLOUR")
	private String bgColour;
	@Column(name = "BLINKING")
	private String blinking;
	@Column(name = "POLICE_NUMBER")
	private String policeNumber;

	public NdcDisplayTextId getId() {
		return id;
	}

	public void setId(NdcDisplayTextId id) {
		this.id = id;
	}

	public String getDisplayedText() {
		return displayedText;
	}

	public void setDisplayedText(String displayedText) {
		this.displayedText = displayedText;
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

	public int getTextLength() {
		return textLength;
	}

	public void setTextLength(int textLength) {
		this.textLength = textLength;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFgColour() {
		return fgColour;
	}

	public void setFgColour(String fgColour) {
		this.fgColour = fgColour;
	}

	public String getBgColour() {
		return bgColour;
	}

	public void setBgColour(String bgColour) {
		this.bgColour = bgColour;
	}

	public String getBlinking() {
		return blinking;
	}

	public void setBlinking(String blinking) {
		this.blinking = blinking;
	}

	public String getPoliceNumber() {
		return policeNumber;
	}

	public void setPoliceNumber(String policeNumber) {
		this.policeNumber = policeNumber;
	}
}