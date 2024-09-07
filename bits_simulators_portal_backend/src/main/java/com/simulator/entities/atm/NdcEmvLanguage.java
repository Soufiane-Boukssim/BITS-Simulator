package com.simulator.entities.atm;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "NDC_EMV_LANGUAGE")
public class NdcEmvLanguage {

	@EmbeddedId
	private NdcEmvLanguageId id;
	@Column(name = "SCREEN_BASE")
	private String screenBase;
	@Column(name = "AUDIO_BASE")
	private String audioBase;
	@Column(name = "OPCODE_BUFF_POS")
	private String opcodeBuffPos;
	@Column(name = "OPCODE_BUFF_VAL")
	private String opcodeBuffVal;

	public NdcEmvLanguageId getId() {
		return id;
	}

	public void setId(NdcEmvLanguageId id) {
		this.id = id;
	}

	public String getScreenBase() {
		return screenBase;
	}

	public void setScreenBase(String screenBase) {
		this.screenBase = screenBase;
	}

	public String getAudioBase() {
		return audioBase;
	}

	public void setAudioBase(String audioBase) {
		this.audioBase = audioBase;
	}

	public String getOpcodeBuffPos() {
		return opcodeBuffPos;
	}

	public void setOpcodeBuffPos(String opcodeBuffPos) {
		this.opcodeBuffPos = opcodeBuffPos;
	}

	public String getOpcodeBuffVal() {
		return opcodeBuffVal;
	}

	public void setOpcodeBuffVal(String opcodeBuffVal) {
		this.opcodeBuffVal = opcodeBuffVal;
	}
}