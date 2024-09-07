export const dataCase =
[
  {
    TEST_CASE: "C_GR_CERT_001",
    SUB_CASE: 1,
    EXEC_SEQ: 0,
    WORDING: "EMV CPA certification",
    DESCRIPTION: "EMV CPA certification checking"
  },
  {
    TEST_CASE: "C_CC_Mag_001",
    SUB_CASE: 1,
    EXEC_SEQ: 1,
    WORDING: "Magstripe presence",
    DESCRIPTION: "Check Magstripe presence and personalization"
  },
  {
    TEST_CASE: "C_DE_Mag_001",
    SUB_CASE: 1,
    EXEC_SEQ: 2,
    WORDING: "Magstripe Data Checking",
    DESCRIPTION: "Check Magstripe data and consistency with information on card plastic"
  },
  {
    TEST_CASE: "C_CC_PSE_001",
    SUB_CASE: 1,
    EXEC_SEQ: 3,
    WORDING: "PSE Selection Response",
    DESCRIPTION: "Check PSE Selection Response"
  },
  {
    TEST_CASE: "C_CC_PSE_002",
    SUB_CASE: 1,
    EXEC_SEQ: 4,
    WORDING: "PSE DIR File contents",
    DESCRIPTION: "Check PSE DIR File Contents"
  },
//   {
//     TEST_CASE: "C_DE_PSE_001",
//     SUB_CASE: 1,
//     EXEC_SEQ: 5,
//     WORDING: "PSE DIR File contents",
//     DESCRIPTION: "Check PSE DIR File Contents"
//   },
  {
    TEST_CASE: "C_TP_DPA_001",
    SUB_CASE: 1,
    EXEC_SEQ: 6,
    WORDING: "Test Preparation",
    DESCRIPTION: "Collect all the information required for application profile contents analysis"
  },
  {
    TEST_CASE: "C_CC_DPA_001",
    SUB_CASE: 1,
    EXEC_SEQ: 7,
    WORDING: "Application Selection Contents",
    DESCRIPTION: "Check Application Selection Response"
  },
  {
    TEST_CASE: "C_CC_DPA_002",
    SUB_CASE: 1,
    EXEC_SEQ: 8,
    WORDING: "Application Readable records",
    DESCRIPTION: "Check readable data: SFI 1 record 1"
  },
  {
    TEST_CASE: "C_CC_DPA_002",
    SUB_CASE: 2,
    EXEC_SEQ: 9,
    WORDING: "Application Readable records",
    DESCRIPTION: "Check readable data: SFI 2 record 1"
  },
  {
    TEST_CASE: "C_CC_DPA_002",
    SUB_CASE: 3,
    EXEC_SEQ: 10,
    WORDING: "Application Readable records",
    DESCRIPTION: "Check readable data: SFI 2 record 2"
  },
  {
    TEST_CASE: "C_CC_DPA_002",
    SUB_CASE: 4,
    EXEC_SEQ: 11,
    WORDING: "Application Readable records",
    DESCRIPTION: "Check readable data: SFI 2 record 3"
  },
  {
    TEST_CASE: "C_CC_DPA_002",
    SUB_CASE: 5,
    EXEC_SEQ: 12,
    WORDING: "Application Readable records",
    DESCRIPTION: "Check readable data: SFI 2 record 4"
  },
  {
    TEST_CASE: "C_CC_DPA_002",
    SUB_CASE: 6,
    EXEC_SEQ: 13,
    WORDING: "Application Readable records",
    DESCRIPTION: "Check readable data: SFI 3 record 1"
  },
  {
    TEST_CASE: "C_CC_DPA_002",
    SUB_CASE: 7,
    EXEC_SEQ: 14,
    WORDING: "Application Readable records",
    DESCRIPTION: "Check readable data: SFI 3 record 2"
  },
  {
    TEST_CASE: "C_CC_DPA_002",
    SUB_CASE: 8,
    EXEC_SEQ: 15,
    WORDING: "Application Readable records",
    DESCRIPTION: "Check readable data: SFI 3 record 3"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 1,
    EXEC_SEQ: 16,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of GPO Parameter tag BF3E"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 2,
    EXEC_SEQ: 17,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of Profile Selection Table"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 3,
    EXEC_SEQ: 18,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of Profile Selection File Entry Tag C2"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 4,
    EXEC_SEQ: 19,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of Profile Controls Tag BF3F"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 5,
    EXEC_SEQ: 20,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of AIP/AFL Table Tag BF41"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 6,
    EXEC_SEQ: 21,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of CIAC Table Tag BF34"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 7,
    EXEC_SEQ: 22,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of Accumulator Table Tag BF30"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 8,
    EXEC_SEQ: 23,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of Accumulator Control Table Tag BF32"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 9,
    EXEC_SEQ: 24,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of Accumulator Profile Control Table Tag BF31"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 10,
    EXEC_SEQ: 25,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of Counter Table Tag BF35"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 11,
    EXEC_SEQ: 26,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of Counter Control Table Tag BF37"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 12,
    EXEC_SEQ: 27,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of Counter Profile Control Table Tag BF36"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 13,
    EXEC_SEQ: 28,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of Cyclic Accumulator Table Tag BF42"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 14,
    EXEC_SEQ: 29,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of Cyclic Accumulator Control Table Tag BF3A"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 15,
    EXEC_SEQ: 30,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of Cyclic Accumulator Profile Control Table Tag BF39"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 16,
    EXEC_SEQ: 31,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of MTA Control Table Tag BF3D"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 17,
    EXEC_SEQ: 32,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of MTA Control Table Tag BF3C"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 18,
    EXEC_SEQ: 33,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of Application Control Tag C1"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 19,
    EXEC_SEQ: 34,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of MTA Control Table Tag BF3B"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 20,
    EXEC_SEQ: 35,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of Number of Days Offline Limit Tag C3"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 21,
    EXEC_SEQ: 36,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of Application Life Cycle Tag 9F7E"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 22,
    EXEC_SEQ: 37,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of ATC Tag 9F36"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 23,
    EXEC_SEQ: 38,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of Log Format Tag 9F4F"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 24,
    EXEC_SEQ: 39,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of GEN AC Log Data Tag BF40"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 25,
    EXEC_SEQ: 40,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of Offline Balance Currency Code Tag C9"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 26,
    EXEC_SEQ: 41,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of Offline Balance Tag 9F50"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 27,
    EXEC_SEQ: 42,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of Security Limit Status Tag C4"
  },
  {
    TEST_CASE: "C_CC_DPA_003",
    SUB_CASE: 28,
    EXEC_SEQ: 43,
    WORDING: "Application Risk Management Parameters",
    DESCRIPTION: "Check presence of Security Limit Status Tag BF33"
  },
  {
    TEST_CASE: "C_DE_DPA_001",
    SUB_CASE: 1,
    EXEC_SEQ: 44,
    WORDING: "Data Elements read by the terminal",
    DESCRIPTION: "Check readable data value in Payment profile"
  },
//   {
//     TEST_CASE: "C_DE_DPA_002",
//     SUB_CASE: 1,
//     EXEC_SEQ: 45,
//     WORDING: "Data Elements read by the terminal",
//     DESCRIPTION: "Check readable data value in Payment profile"
//   },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 1,
    EXEC_SEQ: 46,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check content of GPO Parameter tag BF3E"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 2,
    EXEC_SEQ: 47,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Profile Selection Table"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 3,
    EXEC_SEQ: 48,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Profile Selection File Entry Tag C2"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 4,
    EXEC_SEQ: 49,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Profile Controls Tag BF3F"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 5,
    EXEC_SEQ: 50,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of AIP/AFL Table Tag BF41"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 6,
    EXEC_SEQ: 51,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of CIAC Table Tag BF34"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 7,
    EXEC_SEQ: 52,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Accumulator Table Tag BF30"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 8,
    EXEC_SEQ: 53,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Accumulator Control Table Tag BF32"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 9,
    EXEC_SEQ: 54,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Accumulator Profile Control Table Tag BF31"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 10,
    EXEC_SEQ: 55,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Counter Table Tag BF35"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 11,
    EXEC_SEQ: 56,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Counter Control Table Tag BF37"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 12,
    EXEC_SEQ: 57,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Counter Profile Control Table Tag BF36"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 13,
    EXEC_SEQ: 58,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Cyclic Accumulator Table Tag BF42"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 14,
    EXEC_SEQ: 59,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Cyclic Accumulator Control Table Tag BF3A"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 15,
    EXEC_SEQ: 60,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Cyclic Accumulator Profile Control Table Tag BF39"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 16,
    EXEC_SEQ: 61,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of MTA Control Table Tag BF3D"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 17,
    EXEC_SEQ: 62,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of MTA Control Table Tag BF3C"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 18,
    EXEC_SEQ: 63,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Application Control Tag C1"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 19,
    EXEC_SEQ: 64,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of MTA Control Table Tag BF3B"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 20,
    EXEC_SEQ: 65,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check presence of Number of Days Offline Limit Tag C3"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 21,
    EXEC_SEQ: 66,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Application Life Cycle Tag 9F7E"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 22,
    EXEC_SEQ: 67,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Log Format Tag 9F4F"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 23,
    EXEC_SEQ: 68,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of GEN AC Log Data Tag BF40"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 24,
    EXEC_SEQ: 69,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Offline Balance Currency Code Tag C9"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 25,
    EXEC_SEQ: 70,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Offline Balance Currency Code Tag BF33"
  },
//   {
//     TEST_CASE: "C_TP_DPA_002",
//     SUB_CASE: 1,
//     EXEC_SEQ: 71,
//     WORDING: "Test Preparation",
//     DESCRIPTION: "Collect all the information required for application profile contents analysis"
//   },
  {
    TEST_CASE: "C_CC_DPA_004",
    SUB_CASE: 1,
    EXEC_SEQ: 72,
    WORDING: "Application Readable records",
    DESCRIPTION: "Check readable data: SFI 4 record 1"
  },
  {
    TEST_CASE: "C_DE_DPA_004",
    SUB_CASE: 1,
    EXEC_SEQ: 73,
    WORDING: "Data Element involved in authentication token generation",
    DESCRIPTION: "Check Data Elements values required for the Authentication Token Generation profile"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 16,
    EXEC_SEQ: 61,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of MTA Control Table Tag BF3D"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 17,
    EXEC_SEQ: 62,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of MTA Control Table Tag BF3C"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 18,
    EXEC_SEQ: 63,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Application Control Tag C1"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 19,
    EXEC_SEQ: 64,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of MTA Control Table Tag BF3B"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 20,
    EXEC_SEQ: 65,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check presence of Number of Days Offline Limit Tag C3"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 21,
    EXEC_SEQ: 66,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Application Life Cycle Tag 9F7E"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 22,
    EXEC_SEQ: 67,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Log Format Tag 9F4F"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 23,
    EXEC_SEQ: 68,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of GEN AC Log Data Tag BF40"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 24,
    EXEC_SEQ: 69,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Offline Balance Currency Code Tag C9"
  },
  {
    TEST_CASE: "C_DE_DPA_003",
    SUB_CASE: 25,
    EXEC_SEQ: 70,
    WORDING: "Data Element involved in the Card risk management",
    DESCRIPTION: "Check value of Offline Balance Currency Code Tag BF33"
  },
//   {
//     TEST_CASE: "C_TP_DPA_002",
//     SUB_CASE: 1,
//     EXEC_SEQ: 71,
//     WORDING: "Test Preparation",
//     DESCRIPTION: "Collect all the information required for application profile contents analysis"
//   },
//   {
//     TEST_CASE: "C_CC_DPA_004",
//     SUB_CASE: 1,
//     EXEC_SEQ: 72,
//     WORDING: "Application Readable records",
//     DESCRIPTION: "Check readable data: SFI 4 record 1"
//   },
//   {
//     TEST_CASE: "C_DE_DPA_004",
//     SUB_CASE: 1,
//     EXEC_SEQ: 73,
//     WORDING: "Data Element involved in authentication token generation",
//     DESCRIPTION: "Check Data Elements values required for the Authentication Token Generation profile"
//   },
  {
    TEST_CASE: "C_CC_DPA_005",
    SUB_CASE: 1,
    EXEC_SEQ: 74,
    WORDING: "Transaction Log File",
    DESCRIPTION: "Check presence and cyclic management of the Transaction Log file"
  },
  {
    TEST_CASE: "C_CC_DPA_006",
    SUB_CASE: 1,
    EXEC_SEQ: 75,
    WORDING: "Offline PIN",
    DESCRIPTION: "Check presence of PIN Try Counter Tag 9F17"
  },
  {
    TEST_CASE: "C_SD_DPA_001",
    SUB_CASE: 1,
    EXEC_SEQ: 76,
    WORDING: "PIN value test",
    DESCRIPTION: "Check Offline PIN"
  },
  {
    TEST_CASE: "C_SD_DPA_002",
    SUB_CASE: 1,
    EXEC_SEQ: 77,
    WORDING: "TDES keys value test",
    DESCRIPTION: "Check UDK key value"
  },
  {
    TEST_CASE: "C_SD_DPA_002",
    SUB_CASE: 2,
    EXEC_SEQ: 78,
    WORDING: "TDES keys value test",
    DESCRIPTION: "Check MAC UDK key value"
  },
  {
  "TEST_CASE": "C_SD_DPA_002",
  SUB_CASE: 3,
  EXEC_SEQ: 79,
  WORDING: "TDES keys value test",
  DESCRIPTION: "Check ENC UDK key value"
}

]
