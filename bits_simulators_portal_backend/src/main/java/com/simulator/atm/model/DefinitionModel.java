package com.simulator.atm.model;

import com.simulator.entities.*;
import lombok.Data;

@Data
public class DefinitionModel {
    TerminalDefinition terminalDefinition;
    TerminalCash note;
    TerminalPrinter printer;
    TerminalConfiguration configuration;
    TerminalKeys keys;
}
