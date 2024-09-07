package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_DISPLAY_LAYOUT")
public class NdcDisplayLayout {

	@EmbeddedId
	private NdcDisplayLayoutId id;
	@Column(name = "SCREEN_NAME")
	private String screenName;
	@Column(name = "SCREEN_LANGUAGE")
	private String screenLanguage;
	@Column(name = "VOICE_NUMBER")
	private String voiceNumber;
	@Column(name = "NUMBER_CONCATENATION")
	private String numberConcatenation;
	@Column(name = "TRACK1_NAME")
	private boolean track1Name;
	@Column(name = "LINE_NUMBER")
	private int lineNumber;
	@Column(name = "COLUMN_NUMBER")
	private int columnNumber;
	@Column(name = "FG_COLOUR")
	private String fgColour;
	@Column(name = "BG_COLOUR")
	private String bgColour;
	@Column(name = "BLINKING")
	private String blinking;
	@Column(name = "POLICE_NUMBER")
	private String policeNumber;
	@Column(name = "FORM_FEED_FLAG")
	private String formFeedFlag;
	@Column(name = "CURRENT_POSITION_X")
	private String currentPositionX;
	@Column(name = "CURRENT_POSITION_Y")
	private String currentPositionY;
	@Column(name = "CURRENT_FG_COLOUR")   ///
	private String currentFgColour;
	@Column(name = "CURRENT_BG_COLOUR")
	private String currentBgColour;
	@Column(name = "CURRENT_BLINKING")    ///
	private String currentBlinking;
	@Column(name = "CURRENT_POLICE")
	private String currentPolice;

	public NdcDisplayLayoutId getId() {
		return id;
	}

	public void setId(NdcDisplayLayoutId id) {
		this.id = id;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public String getScreenLanguage() {
		return screenLanguage;
	}

	public void setScreenLanguage(String screenLanguage) {
		this.screenLanguage = screenLanguage;
	}

	public String getVoiceNumber() {
		return voiceNumber;
	}

	public void setVoiceNumber(String voiceNumber) {
		this.voiceNumber = voiceNumber;
	}

	public String getNumberConcatenation() {
		return numberConcatenation;
	}

	public void setNumberConcatenation(String numberConcatenation) {
		this.numberConcatenation = numberConcatenation;
	}

	public boolean isTrack1Name() {
		return track1Name;
	}

	public void setTrack1Name(boolean track1Name) {
		this.track1Name = track1Name;
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

	public String getFormFeedFlag() {
		return formFeedFlag;
	}

	public void setFormFeedFlag(String formFeedFlag) {
		this.formFeedFlag = formFeedFlag;
	}

	public String getCurrentPositionX() {
		return currentPositionX;
	}

	public void setCurrentPositionX(String currentPositionX) {
		this.currentPositionX = currentPositionX;
	}

	public String getCurrentPositionY() {
		return currentPositionY;
	}

	public void setCurrentPositionY(String currentPositionY) {
		this.currentPositionY = currentPositionY;
	}

	public String getCurrentFgColour() {
		return currentFgColour;
	}

	public void setCurrentFgColour(String currentFgColour) {
		this.currentFgColour = currentFgColour;
	}

	public String getCurrentBgColour() {
		return currentBgColour;
	}

	public void setCurrentBgColour(String currentBgColour) {
		this.currentBgColour = currentBgColour;
	}

	public String getCurrentBlinking() {
		return currentBlinking;
	}

	public void setCurrentBlinking(String currentBlinking) {
		this.currentBlinking = currentBlinking;
	}

	public String getCurrentPolice() {
		return currentPolice;
	}

	public void setCurrentPolice(String currentPolice) {
		this.currentPolice = currentPolice;
	}
}