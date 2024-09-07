package com.simulator.atm.controllers.controlleratm;

import com.simulator.atm.dto.OperationAtmDto;

import java.util.List;

public class ScenarioRequest {
    private OperationAtmDto singleOperation;
    private List<OperationAtmDto> operations;

    // Getters and Setters
    public OperationAtmDto getSingleOperation() {
        return singleOperation;
    }

    public void setSingleOperation(OperationAtmDto singleOperation) {
        this.singleOperation = singleOperation;
    }

    public List<OperationAtmDto> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationAtmDto> operations) {
        this.operations = operations;
    }

    // Utility method to check if it's a single operation request
    public boolean isSingle() {
        return singleOperation != null && operations == null;
    }
}
