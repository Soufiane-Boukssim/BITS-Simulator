package com.simulator;


class TerminalMessNonsoliciteServiceTest {
//
//    @Mock
//    private TerminalMessNonsoliciteRepository repository;
//
//    @InjectMocks
//    private TerminalMessNonsoliciteService service;
//
//    private TerminalMessNonsolicite terminalMessNonsolicite;
//    private TerminalMessNonSoliciteId id;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        // Setup data for TerminalMessNonsolicite
//        id = new TerminalMessNonSoliciteId("11", "00100");
//        terminalMessNonsolicite = new TerminalMessNonsolicite();
//        terminalMessNonsolicite.setId(id);
//        terminalMessNonsolicite.setTerminalNumber("1234567890");
//        terminalMessNonsolicite.setTrxDeviceStatus("OK");
//        terminalMessNonsolicite.setErrorSeverity("Low");
//        terminalMessNonsolicite.setDiagnostique("No issues");
//        terminalMessNonsolicite.setSupplyStatus("Full");
//        terminalMessNonsolicite.setMessageUnsolicited("Test message");
//        repository.save(terminalMessNonsolicite);
//    }
//
//    @Test
//    void addTerminalMessNonsolicite_Success() {
//        when(repository.findById(any(TerminalMessNonSoliciteId.class))).thenReturn(Optional.empty());
//        when(repository.save(any(TerminalMessNonsolicite.class))).thenReturn(terminalMessNonsolicite);
//
//        ResponseApiJson<?> response = service.addTerminalMessNonsolicite(terminalMessNonsolicite);
//
//        assertEquals(GlobalVars.SUCCESS, response.getRespCode());
//        verify(repository, times(1)).save(terminalMessNonsolicite);
//    }
//
//    @Test
//    void getTerminalMessNonsolicite_Success() {
//        when(repository.findById(any(TerminalMessNonSoliciteId.class))).thenReturn(Optional.of(terminalMessNonsolicite));
//
//        ResponseApiJson<?> response = service.getTerminalMessNonsolicite(id);
//
//        assertEquals(GlobalVars.SUCCESS, response.getRespCode());
//        assertEquals(terminalMessNonsolicite, response.getResult());
//    }
//
//    // Additional test methods for updateTerminalMessNonsolicite and deleteTerminalMessNonsolicite
//
//    @Test
//    void updateTerminalMessNonsolicite_Success() {
//        when(repository.findById(id)).thenReturn(Optional.of(terminalMessNonsolicite));
//        when(repository.save(terminalMessNonsolicite)).thenReturn(terminalMessNonsolicite);
//
//        terminalMessNonsolicite.setTrxDeviceStatus("Updated Status");
//        ResponseApiJson<?> response = service.updateTerminalMessNonsolicite(terminalMessNonsolicite);
//
//        assertEquals(GlobalVars.SUCCESS, response.getRespCode());
//        verify(repository, times(1)).save(terminalMessNonsolicite);
//    }
//
//    @Test
//    void deleteTerminalMessNonsolicite_Success() {
//        when(repository.findById(any(TerminalMessNonSoliciteId.class))).thenReturn(Optional.of(terminalMessNonsolicite));
//
//        ResponseApiJson<?> response = service.deleteTerminalMessNonsolicite(id);
//
//        assertEquals(GlobalVars.SUCCESS, response.getRespCode());
//        verify(repository, times(1)).delete(terminalMessNonsolicite);
//    }
//
//    @Test
//    void updateTerminalMessNonsolicite_NotFound() {
//        when(repository.findById(any(TerminalMessNonSoliciteId.class))).thenReturn(Optional.empty());
//
//        ResponseApiJson<?> response = service.updateTerminalMessNonsolicite(terminalMessNonsolicite);
//
//        assertEquals(GlobalVars.NOTEXIST, response.getRespCode());
//        verify(repository, never()).save(any(TerminalMessNonsolicite.class));
//    }
//
//    @Test
//    void deleteTerminalMessNonsolicite_NotFound() {
//        when(repository.findById(any(TerminalMessNonSoliciteId.class))).thenReturn(Optional.empty());
//
//        ResponseApiJson<?> response = service.deleteTerminalMessNonsolicite(id);
//
//        assertEquals(GlobalVars.NOTEXIST, response.getRespCode());
//        verify(repository, never()).delete(any(TerminalMessNonsolicite.class));
//    }

}

