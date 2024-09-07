package com.simulator.entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "cases_definition")
public class   CasesDefinition {
    @EmbeddedId
    private CasesDefinitionId id;

    @Column(name = "CASE_DESC",length = 50)
    private String caseDesc;

    @Column(name = "CASE_DIRECTION",length = 3)
    private String caseDirection;

    @Column(name = "CASE_CARDREF",length = 50)
    private String caseCardRef;

    @Column(name = "CASE_MERCHANTPRF",length = 50)
    private String caseMerchantPrf;

    @Column(name = "CASE_TERMINALPRF",length = 50)
    private String caseTerminalPrf;

    @Column(name = "CASE_AMOUNT")
    private int caseAmount;

    @Column(name = "CASE_HEADER",length = 50)
    private String caseHeader;

    @Column(name = "CASE_MTI",length = 50)
    private String caseMti;

    @Lob
    @Column(name = "CASE_FIELD_1",length = 150)
    private String caseField001;

    @Lob
    @Column(name = "CASE_FIELD_2",length = 150)
    private String caseField002;

    @Lob
    @Column(name = "CASE_FIELD_3",length = 150)
    private String caseField003;

    @Lob
    @Column(name = "CASE_FIELD_4",length = 150)
    private String caseField004;

    @Lob
    @Column(name = "CASE_FIELD_5",length = 150)
    private String caseField005;

    @Lob
    @Column(name = "CASE_FIELD_6",length = 150)
    private String caseField006;

    @Lob
    @Column(name = "CASE_FIELD_7",length = 150)
    private String caseField007;

    @Lob
    @Column(name = "CASE_FIELD_8",length = 150)
    private String caseField008;

    @Lob
    @Column(name = "CASE_FIELD_9",length = 150)
    private String caseField009;

    @Lob
    @Column(name = "CASE_FIELD_10",length = 150)
    private String caseField010;

    @Lob
    @Column(name = "CASE_FIELD_11",length = 150)
    private String caseField011;

    @Lob
    @Column(name = "CASE_FIELD_12",length = 150)
    private String caseField012;

    @Lob
    @Column(name = "CASE_FIELD_13",length = 150)
    private String caseField013;

    @Lob
    @Column(name = "CASE_FIELD_14",length = 150)
    private String caseField014;

    @Lob
    @Column(name = "CASE_FIELD_15",length = 150)
    private String caseField015;

    @Lob
    @Column(name = "CASE_FIELD_16",length = 150)
    private String caseField016;

    @Lob
    @Column(name = "CASE_FIELD_17",length = 150)
    private String caseField017;

    @Lob
    @Column(name = "CASE_FIELD_18",length = 150)
    private String caseField018;

    @Lob
    @Column(name = "CASE_FIELD_19",length = 150)
    private String caseField019;

    @Lob
    @Column(name = "CASE_FIELD_20",length = 150)
    private String caseField020;

    @Lob
    @Column(name = "CASE_FIELD_21",length = 150)
    private String caseField021;

    @Lob
    @Column(name = "CASE_FIELD_22",length = 150)
    private String caseField022;

    @Lob
    @Column(name = "CASE_FIELD_23",length = 150)
    private String caseField023;

    @Lob
    @Column(name = "CASE_FIELD_24",length = 150)
    private String caseField024;

    @Lob
    @Column(name = "CASE_FIELD_25",length = 150)
    private String caseField025;

    @Lob
    @Column(name = "CASE_FIELD_26",length = 150)
    private String caseField026;

    @Lob
    @Column(name = "CASE_FIELD_27",length = 150)
    private String caseField027;

    @Lob
    @Column(name = "CASE_FIELD_28",length = 150)
    private String caseField028;

    @Lob
    @Column(name = "CASE_FIELD_29",length = 150)
    private String caseField029;

    @Lob
    @Column(name = "CASE_FIELD_30",length = 150)
    private String caseField030;

    @Lob
    @Column(name = "CASE_FIELD_31",length = 150)
    private String caseField031;

    @Lob
    @Column(name = "CASE_FIELD_32",length = 150)
    private String caseField032;

    @Lob
    @Column(name = "CASE_FIELD_33",length = 150)
    private String caseField033;

    @Lob
    @Column(name = "CASE_FIELD_34",length = 150)
    private String caseField034;

    @Lob
    @Column(name = "CASE_FIELD_35",length = 150)
    private String caseField035;

    @Lob
    @Column(name = "CASE_FIELD_36",length = 150)
    private String caseField036;

    @Lob
    @Column(name = "CASE_FIELD_37",length = 150)
    private String caseField037;

    @Lob
    @Column(name = "CASE_FIELD_38",length = 150)
    private String caseField038;

    @Lob
    @Column(name = "CASE_FIELD_39",length = 150)
    private String caseField039;

    @Lob
    @Column(name = "CASE_FIELD_40",length = 150)
    private String caseField040;

    @Lob
    @Column(name = "CASE_FIELD_41",length = 150)
    private String caseField041;

    @Lob
    @Column(name = "CASE_FIELD_42",length = 150)
    private String caseField042;

    @Lob
    @Column(name = "CASE_FIELD_43",length = 150)
    private String caseField043;

    @Lob
    @Column(name = "CASE_FIELD_44",length = 150)
    private String caseField044;

    @Lob
    @Column(name = "CASE_FIELD_45",length = 150)
    private String caseField045;

    @Lob
    @Column(name = "CASE_FIELD_46",length = 150)
    private String caseField046;

    @Lob
    @Column(name = "CASE_FIELD_47",length = 150)
    private String caseField047;

    @Lob
    @Column(name = "CASE_FIELD_48",length = 150)
    private String caseField048;

    @Lob
    @Column(name = "CASE_FIELD_49",length = 150)
    private String caseField049;

    @Lob
    @Column(name = "CASE_FIELD_50",length = 150)
    private String caseField050;

    @Lob
    @Column(name = "CASE_FIELD_51",length = 150)
    private String caseField051;

    @Lob
    @Column(name = "CASE_FIELD_52",length = 150)
    private String caseField052;

    @Lob
    @Column(name = "CASE_FIELD_53",length = 150)
    private String caseField053;

    @Lob
    @Column(name = "CASE_FIELD_54",length = 150)
    private String caseField054;

    @Lob
    @Column(name = "CASE_FIELD_55",length = 150)
    private String caseField055;

    @Lob
    @Column(name = "CASE_FIELD_56",length = 150)
    private String caseField056;

    @Lob
    @Column(name = "CASE_FIELD_57",length = 150)
    private String caseField057;

    @Lob
    @Column(name = "CASE_FIELD_58",length = 150)
    private String caseField058;

    @Lob
    @Column(name = "CASE_FIELD_59",length = 150)
    private String caseField059;

    @Lob
    @Column(name = "CASE_FIELD_60",length = 150)
    private String caseField060;

    @Lob
    @Column(name = "CASE_FIELD_61",length = 150)
    private String caseField061;

    @Lob
    @Column(name = "CASE_FIELD_62",length = 150)
    private String caseField062;

    @Lob
    @Column(name = "CASE_FIELD_63",length = 150)
    private String caseField063;

    @Lob
    @Column(name = "CASE_FIELD_64",length = 150)
    private String caseField064;

    @Lob
    @Column(name = "CASE_FIELD_65",length = 150)
    private String caseField065;

    @Lob
    @Column(name = "CASE_FIELD_66",length = 150)
    private String caseField066;

    @Lob
    @Column(name = "CASE_FIELD_67",length = 150)
    private String caseField067;

    @Lob
    @Column(name = "CASE_FIELD_68",length = 150)
    private String caseField068;

    @Lob
    @Column(name = "CASE_FIELD_69",length = 150)
    private String caseField069;

    @Lob
    @Column(name = "CASE_FIELD_70",length = 150)
    private String caseField070;

    @Lob
    @Column(name = "CASE_FIELD_71",length = 150)
    private String caseField071;

    @Lob
    @Column(name = "CASE_FIELD_72",length = 150)
    private String caseField072;

    @Lob
    @Column(name = "CASE_FIELD_73",length = 150)
    private String caseField073;

    @Lob
    @Column(name = "CASE_FIELD_74",length = 150)
    private String caseField074;

    @Lob
    @Column(name = "CASE_FIELD_75",length = 150)
    private String caseField075;

    @Lob
    @Column(name = "CASE_FIELD_76",length = 150)
    private String caseField076;

    @Lob
    @Column(name = "CASE_FIELD_77",length = 150)
    private String caseField077;

    @Lob
    @Column(name = "CASE_FIELD_78",length = 150)
    private String caseField078;

    @Lob
    @Column(name = "CASE_FIELD_79",length = 150)
    private String caseField079;

    @Lob
    @Column(name = "CASE_FIELD_80",length = 150)
    private String caseField080;

    @Lob
    @Column(name = "CASE_FIELD_81",length = 150)
    private String caseField081;

    @Lob
    @Column(name = "CASE_FIELD_82",length = 150)
    private String caseField082;

    @Lob
    @Column(name = "CASE_FIELD_83",length = 150)
    private String caseField083;

    @Lob
    @Column(name = "CASE_FIELD_84",length = 150)
    private String caseField084;

    @Lob
    @Column(name = "CASE_FIELD_85",length = 150)
    private String caseField085;

    @Lob
    @Column(name = "CASE_FIELD_86",length = 150)
    private String caseField086;

    @Lob
    @Column(name = "CASE_FIELD_87",length = 150)
    private String caseField087;

    @Lob
    @Column(name = "CASE_FIELD_88",length = 150)
    private String caseField088;

    @Lob
    @Column(name = "CASE_FIELD_89",length = 150)
    private String caseField089;

    @Lob
    @Column(name = "CASE_FIELD_90",length = 150)
    private String caseField090;

    @Lob
    @Column(name = "CASE_FIELD_91",length = 150)
    private String caseField091;

    @Lob
    @Column(name = "CASE_FIELD_92",length = 150)
    private String caseField092;

    @Lob
    @Column(name = "CASE_FIELD_93",length = 150)
    private String caseField093;

    @Lob
    @Column(name = "CASE_FIELD_94",length = 150)
    private String caseField094;

    @Lob
    @Column(name = "CASE_FIELD_95",length = 150)
    private String caseField095;

    @Lob
    @Column(name = "CASE_FIELD_96",length = 150)
    private String caseField096;

    @Lob
    @Column(name = "CASE_FIELD_97",length = 150)
    private String caseField097;

    @Lob
    @Column(name = "CASE_FIELD_98",length = 150)
    private String caseField098;

    @Lob
    @Column(name = "CASE_FIELD_99",length = 150)
    private String caseField099;

    @Lob
    @Column(name = "CASE_FIELD_100",length = 150)
    private String caseField100;

    @Lob
    @Column(name = "CASE_FIELD_101",length = 150)
    private String caseField101;

    @Lob
    @Column(name = "CASE_FIELD_102",length = 150)
    private String caseField102;

    @Lob
    @Column(name = "CASE_FIELD_103",length = 150)
    private String caseField103;

    @Lob
    @Column(name = "CASE_FIELD_104",length = 150)
    private String caseField104;

    @Lob
    @Column(name = "CASE_FIELD_105",length = 150)
    private String caseField105;

    @Lob
    @Column(name = "CASE_FIELD_106",length = 150)
    private String caseField106;

    @Lob
    @Column(name = "CASE_FIELD_107",length = 150)
    private String caseField107;

    @Lob
    @Column(name = "CASE_FIELD_108",length = 150)
    private String caseField108;

    @Lob
    @Column(name = "CASE_FIELD_109",length = 150)
    private String caseField109;

    @Lob
    @Column(name = "CASE_FIELD_110",length = 150)
    private String caseField110;

    @Lob
    @Column(name = "CASE_FIELD_111",length = 150)
    private String caseField111;

    @Lob
    @Column(name = "CASE_FIELD_112",length = 150)
    private String caseField112;

    @Lob
    @Column(name = "CASE_FIELD_113",length = 150)
    private String caseField113;

    @Lob
    @Column(name = "CASE_FIELD_114",length = 150)
    private String caseField114;

    @Lob
    @Column(name = "CASE_FIELD_115",length = 150)
    private String caseField115;

    @Lob
    @Column(name = "CASE_FIELD_116",length = 150)
    private String caseField116;

    @Lob
    @Column(name = "CASE_FIELD_117",length = 150)
    private String caseField117;

    @Lob
    @Column(name = "CASE_FIELD_118",length = 150)
    private String caseField118;

    @Lob
    @Column(name = "CASE_FIELD_119",length = 150)
    private String caseField119;

    @Lob
    @Column(name = "CASE_FIELD_120",length = 150)
    private String caseField120;

    @Lob
    @Column(name = "CASE_FIELD_121",length = 150)
    private String caseField121;

    @Lob
    @Column(name = "CASE_FIELD_122",length = 150)
    private String caseField122;

    @Lob
    @Column(name = "CASE_FIELD_123",length = 150)
    private String caseField123;

    @Lob
    @Column(name = "CASE_FIELD_124",length = 150)
    private String caseField124;

    @Lob
    @Column(name = "CASE_FIELD_125",length = 150)
    private String caseField125;

    @Lob
    @Column(name = "CASE_FIELD_126",length = 150)
    private String caseField126;

    @Lob
    @Column(name = "CASE_FIELD_127",length = 150)
    private String caseField127;

    @Lob
    @Column(name = "CASE_FIELD_128",length = 150)
    private String caseField128;




    public String getCaseDesc() {
        return caseDesc;
    }

    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc;
    }

    public String getCaseCardRef() {
        return caseCardRef;
    }

    public void setCaseCardRef(String caseCardRef) {
        this.caseCardRef = caseCardRef;
    }

    public String getCaseMerchantPrf() {
        return caseMerchantPrf;
    }

    public void setCaseMerchantPrf(String caseMerchantPrf) {
        this.caseMerchantPrf = caseMerchantPrf;
    }

    public String getCaseTerminalPrf() {
        return caseTerminalPrf;
    }

    public void setCaseTerminalPrf(String caseTerminalPrf) {
        this.caseTerminalPrf = caseTerminalPrf;
    }

    public int getCaseAmount() {
        return caseAmount;
    }

    public void setCaseAmount(int caseAmount) {
        this.caseAmount = caseAmount;
    }

    public String getCaseHeader() {
        return caseHeader;
    }

    public void setCaseHeader(String caseHeader) {
        this.caseHeader = caseHeader;
    }

    public String getCaseMti() {
        return caseMti;
    }

    public void setCaseMti(String caseMti) {
        this.caseMti = caseMti;
    }

    public CasesDefinitionId getId() {
        return id;
    }

    public void setId(CasesDefinitionId id) {
        this.id = id;
    }

    public String getCaseField001() {
        return caseField001;
    }

    public void setCaseField001(String caseField001) {
        this.caseField001 = caseField001;
    }

    public String getCaseField002() {
        return caseField002;
    }

    public void setCaseField002(String caseField002) {
        this.caseField002 = caseField002;
    }

    public String getCaseField003() {
        return caseField003;
    }

    public void setCaseField003(String caseField003) {
        this.caseField003 = caseField003;
    }

    public String getCaseField004() {
        return caseField004;
    }

    public void setCaseField004(String caseField004) {
        this.caseField004 = caseField004;
    }

    public String getCaseField005() {
        return caseField005;
    }

    public void setCaseField005(String caseField005) {
        this.caseField005 = caseField005;
    }

    public String getCaseField006() {
        return caseField006;
    }

    public void setCaseField006(String caseField006) {
        this.caseField006 = caseField006;
    }

    public String getCaseField007() {
        return caseField007;
    }

    public void setCaseField007(String caseField007) {
        this.caseField007 = caseField007;
    }

    public String getCaseField008() {
        return caseField008;
    }

    public void setCaseField008(String caseField008) {
        this.caseField008 = caseField008;
    }

    public String getCaseField009() {
        return caseField009;
    }

    public void setCaseField009(String caseField009) {
        this.caseField009 = caseField009;
    }

    public String getCaseField010() {
        return caseField010;
    }

    public void setCaseField010(String caseField010) {
        this.caseField010 = caseField010;
    }

    public String getCaseField011() {
        return caseField011;
    }

    public void setCaseField011(String caseField011) {
        this.caseField011 = caseField011;
    }

    public String getCaseField012() {
        return caseField012;
    }

    public void setCaseField012(String caseField012) {
        this.caseField012 = caseField012;
    }

    public String getCaseField013() {
        return caseField013;
    }

    public void setCaseField013(String caseField013) {
        this.caseField013 = caseField013;
    }

    public String getCaseField014() {
        return caseField014;
    }

    public void setCaseField014(String caseField014) {
        this.caseField014 = caseField014;
    }

    public String getCaseField015() {
        return caseField015;
    }

    public void setCaseField015(String caseField015) {
        this.caseField015 = caseField015;
    }

    public String getCaseField016() {
        return caseField016;
    }

    public void setCaseField016(String caseField016) {
        this.caseField016 = caseField016;
    }

    public String getCaseField017() {
        return caseField017;
    }

    public void setCaseField017(String caseField017) {
        this.caseField017 = caseField017;
    }

    public String getCaseField018() {
        return caseField018;
    }

    public void setCaseField018(String caseField018) {
        this.caseField018 = caseField018;
    }

    public String getCaseField019() {
        return caseField019;
    }

    public void setCaseField019(String caseField019) {
        this.caseField019 = caseField019;
    }

    public String getCaseField020() {
        return caseField020;
    }

    public void setCaseField020(String caseField020) {
        this.caseField020 = caseField020;
    }

    public String getCaseField021() {
        return caseField021;
    }

    public void setCaseField021(String caseField021) {
        this.caseField021 = caseField021;
    }

    public String getCaseField022() {
        return caseField022;
    }

    public void setCaseField022(String caseField022) {
        this.caseField022 = caseField022;
    }

    public String getCaseField023() {
        return caseField023;
    }

    public void setCaseField023(String caseField023) {
        this.caseField023 = caseField023;
    }

    public String getCaseField024() {
        return caseField024;
    }

    public void setCaseField024(String caseField024) {
        this.caseField024 = caseField024;
    }

    public String getCaseField025() {
        return caseField025;
    }

    public void setCaseField025(String caseField025) {
        this.caseField025 = caseField025;
    }

    public String getCaseField026() {
        return caseField026;
    }

    public void setCaseField026(String caseField026) {
        this.caseField026 = caseField026;
    }

    public String getCaseField027() {
        return caseField027;
    }

    public void setCaseField027(String caseField027) {
        this.caseField027 = caseField027;
    }

    public String getCaseField028() {
        return caseField028;
    }

    public void setCaseField028(String caseField028) {
        this.caseField028 = caseField028;
    }

    public String getCaseField029() {
        return caseField029;
    }

    public void setCaseField029(String caseField029) {
        this.caseField029 = caseField029;
    }

    public String getCaseField030() {
        return caseField030;
    }

    public void setCaseField030(String caseField030) {
        this.caseField030 = caseField030;
    }

    public String getCaseField031() {
        return caseField031;
    }

    public void setCaseField031(String caseField031) {
        this.caseField031 = caseField031;
    }

    public String getCaseField032() {
        return caseField032;
    }

    public void setCaseField032(String caseField032) {
        this.caseField032 = caseField032;
    }

    public String getCaseField033() {
        return caseField033;
    }

    public void setCaseField033(String caseField033) {
        this.caseField033 = caseField033;
    }

    public String getCaseField034() {
        return caseField034;
    }

    public void setCaseField034(String caseField034) {
        this.caseField034 = caseField034;
    }

    public String getCaseField035() {
        return caseField035;
    }

    public void setCaseField035(String caseField035) {
        this.caseField035 = caseField035;
    }

    public String getCaseField036() {
        return caseField036;
    }

    public void setCaseField036(String caseField036) {
        this.caseField036 = caseField036;
    }

    public String getCaseField037() {
        return caseField037;
    }

    public void setCaseField037(String caseField037) {
        this.caseField037 = caseField037;
    }

    public String getCaseField038() {
        return caseField038;
    }

    public void setCaseField038(String caseField038) {
        this.caseField038 = caseField038;
    }

    public String getCaseField039() {
        return caseField039;
    }

    public void setCaseField039(String caseField039) {
        this.caseField039 = caseField039;
    }

    public String getCaseField040() {
        return caseField040;
    }

    public void setCaseField040(String caseField040) {
        this.caseField040 = caseField040;
    }

    public String getCaseField041() {
        return caseField041;
    }

    public void setCaseField041(String caseField041) {
        this.caseField041 = caseField041;
    }

    public String getCaseField042() {
        return caseField042;
    }

    public void setCaseField042(String caseField042) {
        this.caseField042 = caseField042;
    }

    public String getCaseField043() {
        return caseField043;
    }

    public void setCaseField043(String caseField043) {
        this.caseField043 = caseField043;
    }

    public String getCaseField044() {
        return caseField044;
    }

    public void setCaseField044(String caseField044) {
        this.caseField044 = caseField044;
    }

    public String getCaseField045() {
        return caseField045;
    }

    public void setCaseField045(String caseField045) {
        this.caseField045 = caseField045;
    }

    public String getCaseField046() {
        return caseField046;
    }

    public void setCaseField046(String caseField046) {
        this.caseField046 = caseField046;
    }

    public String getCaseField047() {
        return caseField047;
    }

    public void setCaseField047(String caseField047) {
        this.caseField047 = caseField047;
    }

    public String getCaseField048() {
        return caseField048;
    }

    public void setCaseField048(String caseField048) {
        this.caseField048 = caseField048;
    }

    public String getCaseField049() {
        return caseField049;
    }

    public void setCaseField049(String caseField049) {
        this.caseField049 = caseField049;
    }

    public String getCaseField050() {
        return caseField050;
    }

    public void setCaseField050(String caseField050) {
        this.caseField050 = caseField050;
    }

    public String getCaseField051() {
        return caseField051;
    }

    public void setCaseField051(String caseField051) {
        this.caseField051 = caseField051;
    }

    public String getCaseField052() {
        return caseField052;
    }

    public void setCaseField052(String caseField052) {
        this.caseField052 = caseField052;
    }

    public String getCaseField053() {
        return caseField053;
    }

    public void setCaseField053(String caseField053) {
        this.caseField053 = caseField053;
    }

    public String getCaseField054() {
        return caseField054;
    }

    public void setCaseField054(String caseField054) {
        this.caseField054 = caseField054;
    }

    public String getCaseField055() {
        return caseField055;
    }

    public void setCaseField055(String caseField055) {
        this.caseField055 = caseField055;
    }

    public String getCaseField056() {
        return caseField056;
    }

    public void setCaseField056(String caseField056) {
        this.caseField056 = caseField056;
    }

    public String getCaseField057() {
        return caseField057;
    }

    public void setCaseField057(String caseField057) {
        this.caseField057 = caseField057;
    }

    public String getCaseField058() {
        return caseField058;
    }

    public void setCaseField058(String caseField058) {
        this.caseField058 = caseField058;
    }

    public String getCaseField059() {
        return caseField059;
    }

    public void setCaseField059(String caseField059) {
        this.caseField059 = caseField059;
    }

    public String getCaseField060() {
        return caseField060;
    }

    public void setCaseField060(String caseField060) {
        this.caseField060 = caseField060;
    }

    public String getCaseField061() {
        return caseField061;
    }

    public void setCaseField061(String caseField061) {
        this.caseField061 = caseField061;
    }

    public String getCaseField062() {
        return caseField062;
    }

    public void setCaseField062(String caseField062) {
        this.caseField062 = caseField062;
    }

    public String getCaseField063() {
        return caseField063;
    }

    public void setCaseField063(String caseField063) {
        this.caseField063 = caseField063;
    }

    public String getCaseField064() {
        return caseField064;
    }

    public void setCaseField064(String caseField064) {
        this.caseField064 = caseField064;
    }

    public String getCaseField065() {
        return caseField065;
    }

    public void setCaseField065(String caseField065) {
        this.caseField065 = caseField065;
    }

    public String getCaseField066() {
        return caseField066;
    }

    public void setCaseField066(String caseField066) {
        this.caseField066 = caseField066;
    }

    public String getCaseField067() {
        return caseField067;
    }

    public void setCaseField067(String caseField067) {
        this.caseField067 = caseField067;
    }

    public String getCaseField068() {
        return caseField068;
    }

    public void setCaseField068(String caseField068) {
        this.caseField068 = caseField068;
    }

    public String getCaseField069() {
        return caseField069;
    }

    public void setCaseField069(String caseField069) {
        this.caseField069 = caseField069;
    }

    public String getCaseField070() {
        return caseField070;
    }

    public void setCaseField070(String caseField070) {
        this.caseField070 = caseField070;
    }

    public String getCaseField071() {
        return caseField071;
    }

    public void setCaseField071(String caseField071) {
        this.caseField071 = caseField071;
    }

    public String getCaseField072() {
        return caseField072;
    }

    public void setCaseField072(String caseField072) {
        this.caseField072 = caseField072;
    }

    public String getCaseField073() {
        return caseField073;
    }

    public void setCaseField073(String caseField073) {
        this.caseField073 = caseField073;
    }

    public String getCaseField074() {
        return caseField074;
    }

    public void setCaseField074(String caseField074) {
        this.caseField074 = caseField074;
    }

    public String getCaseField075() {
        return caseField075;
    }

    public void setCaseField075(String caseField075) {
        this.caseField075 = caseField075;
    }

    public String getCaseField076() {
        return caseField076;
    }

    public void setCaseField076(String caseField076) {
        this.caseField076 = caseField076;
    }

    public String getCaseField077() {
        return caseField077;
    }

    public void setCaseField077(String caseField077) {
        this.caseField077 = caseField077;
    }

    public String getCaseField078() {
        return caseField078;
    }

    public void setCaseField078(String caseField078) {
        this.caseField078 = caseField078;
    }

    public String getCaseField079() {
        return caseField079;
    }

    public void setCaseField079(String caseField079) {
        this.caseField079 = caseField079;
    }

    public String getCaseField080() {
        return caseField080;
    }

    public void setCaseField080(String caseField080) {
        this.caseField080 = caseField080;
    }

    public String getCaseField081() {
        return caseField081;
    }

    public void setCaseField081(String caseField081) {
        this.caseField081 = caseField081;
    }

    public String getCaseField082() {
        return caseField082;
    }

    public void setCaseField082(String caseField082) {
        this.caseField082 = caseField082;
    }

    public String getCaseField083() {
        return caseField083;
    }

    public void setCaseField083(String caseField083) {
        this.caseField083 = caseField083;
    }

    public String getCaseField084() {
        return caseField084;
    }

    public void setCaseField084(String caseField084) {
        this.caseField084 = caseField084;
    }

    public String getCaseField085() {
        return caseField085;
    }

    public void setCaseField085(String caseField085) {
        this.caseField085 = caseField085;
    }

    public String getCaseField086() {
        return caseField086;
    }

    public void setCaseField086(String caseField086) {
        this.caseField086 = caseField086;
    }

    public String getCaseField087() {
        return caseField087;
    }

    public void setCaseField087(String caseField087) {
        this.caseField087 = caseField087;
    }

    public String getCaseField088() {
        return caseField088;
    }

    public void setCaseField088(String caseField088) {
        this.caseField088 = caseField088;
    }

    public String getCaseField089() {
        return caseField089;
    }

    public void setCaseField089(String caseField089) {
        this.caseField089 = caseField089;
    }

    public String getCaseField090() {
        return caseField090;
    }

    public void setCaseField090(String caseField090) {
        this.caseField090 = caseField090;
    }

    public String getCaseField091() {
        return caseField091;
    }

    public void setCaseField091(String caseField091) {
        this.caseField091 = caseField091;
    }

    public String getCaseField092() {
        return caseField092;
    }

    public void setCaseField092(String caseField092) {
        this.caseField092 = caseField092;
    }

    public String getCaseField093() {
        return caseField093;
    }

    public void setCaseField093(String caseField093) {
        this.caseField093 = caseField093;
    }

    public String getCaseField094() {
        return caseField094;
    }

    public void setCaseField094(String caseField094) {
        this.caseField094 = caseField094;
    }

    public String getCaseField095() {
        return caseField095;
    }

    public void setCaseField095(String caseField095) {
        this.caseField095 = caseField095;
    }

    public String getCaseField096() {
        return caseField096;
    }

    public void setCaseField096(String caseField096) {
        this.caseField096 = caseField096;
    }

    public String getCaseField097() {
        return caseField097;
    }

    public void setCaseField097(String caseField097) {
        this.caseField097 = caseField097;
    }

    public String getCaseField098() {
        return caseField098;
    }

    public void setCaseField098(String caseField098) {
        this.caseField098 = caseField098;
    }

    public String getCaseField099() {
        return caseField099;
    }

    public void setCaseField099(String caseField099) {
        this.caseField099 = caseField099;
    }

    public String getCaseField100() {
        return caseField100;
    }

    public void setCaseField100(String caseField100) {
        this.caseField100 = caseField100;
    }

    public String getCaseField101() {
        return caseField101;
    }

    public void setCaseField101(String caseField101) {
        this.caseField101 = caseField101;
    }

    public String getCaseField102() {
        return caseField102;
    }

    public void setCaseField102(String caseField102) {
        this.caseField102 = caseField102;
    }

    public String getCaseField103() {
        return caseField103;
    }

    public void setCaseField103(String caseField103) {
        this.caseField103 = caseField103;
    }

    public String getCaseField104() {
        return caseField104;
    }

    public void setCaseField104(String caseField104) {
        this.caseField104 = caseField104;
    }

    public String getCaseField105() {
        return caseField105;
    }

    public void setCaseField105(String caseField105) {
        this.caseField105 = caseField105;
    }

    public String getCaseField106() {
        return caseField106;
    }

    public void setCaseField106(String caseField106) {
        this.caseField106 = caseField106;
    }

    public String getCaseField107() {
        return caseField107;
    }

    public void setCaseField107(String caseField107) {
        this.caseField107 = caseField107;
    }

    public String getCaseField108() {
        return caseField108;
    }

    public void setCaseField108(String caseField108) {
        this.caseField108 = caseField108;
    }

    public String getCaseField109() {
        return caseField109;
    }

    public void setCaseField109(String caseField109) {
        this.caseField109 = caseField109;
    }

    public String getCaseField110() {
        return caseField110;
    }

    public void setCaseField110(String caseField110) {
        this.caseField110 = caseField110;
    }

    public String getCaseField111() {
        return caseField111;
    }

    public void setCaseField111(String caseField111) {
        this.caseField111 = caseField111;
    }

    public String getCaseField112() {
        return caseField112;
    }

    public void setCaseField112(String caseField112) {
        this.caseField112 = caseField112;
    }

    public String getCaseField113() {
        return caseField113;
    }

    public void setCaseField113(String caseField113) {
        this.caseField113 = caseField113;
    }

    public String getCaseField114() {
        return caseField114;
    }

    public void setCaseField114(String caseField114) {
        this.caseField114 = caseField114;
    }

    public String getCaseField115() {
        return caseField115;
    }

    public void setCaseField115(String caseField115) {
        this.caseField115 = caseField115;
    }

    public String getCaseField116() {
        return caseField116;
    }

    public void setCaseField116(String caseField116) {
        this.caseField116 = caseField116;
    }

    public String getCaseField117() {
        return caseField117;
    }

    public void setCaseField117(String caseField117) {
        this.caseField117 = caseField117;
    }

    public String getCaseField118() {
        return caseField118;
    }

    public void setCaseField118(String caseField118) {
        this.caseField118 = caseField118;
    }

    public String getCaseField119() {
        return caseField119;
    }

    public void setCaseField119(String caseField119) {
        this.caseField119 = caseField119;
    }

    public String getCaseField120() {
        return caseField120;
    }

    public void setCaseField120(String caseField120) {
        this.caseField120 = caseField120;
    }

    public String getCaseField121() {
        return caseField121;
    }

    public void setCaseField121(String caseField121) {
        this.caseField121 = caseField121;
    }

    public String getCaseField122() {
        return caseField122;
    }

    public void setCaseField122(String caseField122) {
        this.caseField122 = caseField122;
    }

    public String getCaseField123() {
        return caseField123;
    }

    public void setCaseField123(String caseField123) {
        this.caseField123 = caseField123;
    }

    public String getCaseField124() {
        return caseField124;
    }

    public void setCaseField124(String caseField124) {
        this.caseField124 = caseField124;
    }

    public String getCaseField125() {
        return caseField125;
    }

    public void setCaseField125(String caseField125) {
        this.caseField125 = caseField125;
    }

    public String getCaseField126() {
        return caseField126;
    }

    public void setCaseField126(String caseField126) {
        this.caseField126 = caseField126;
    }

    public String getCaseField127() {
        return caseField127;
    }

    public void setCaseField127(String caseField127) {
        this.caseField127 = caseField127;
    }

    public String getCaseField128() {
        return caseField128;
    }

    public void setCaseField128(String caseField128) {
        this.caseField128 = caseField128;
    }

    public String getCaseDirection() {
        return caseDirection;
    }

    public void setCaseDirection(String caseDirection) {
        this.caseDirection = caseDirection;
    }

    public CasesDefinition() {
    }

    public CasesDefinition(CasesDefinitionId id, String caseDesc, String caseDirection, String caseCardRef, String caseMerchantPrf, String caseTerminalPrf, int caseAmount, String caseHeader, String caseMti, String caseField001, String caseField002, String caseField003, String caseField004, String caseField005, String caseField006, String caseField007, String caseField008, String caseField009, String caseField010, String caseField011, String caseField012, String caseField013, String caseField014, String caseField015, String caseField016, String caseField017, String caseField018, String caseField019, String caseField020, String caseField021, String caseField022, String caseField023, String caseField024, String caseField025, String caseField026, String caseField027, String caseField028, String caseField029, String caseField030, String caseField031, String caseField032, String caseField033, String caseField034, String caseField035, String caseField036, String caseField037, String caseField038, String caseField039, String caseField040, String caseField041, String caseField042, String caseField043, String caseField044, String caseField045, String caseField046, String caseField047, String caseField048, String caseField049, String caseField050, String caseField051, String caseField052, String caseField053, String caseField054, String caseField055, String caseField056, String caseField057, String caseField058, String caseField059, String caseField060, String caseField061, String caseField062, String caseField063, String caseField064, String caseField065, String caseField066, String caseField067, String caseField068, String caseField069, String caseField070, String caseField071, String caseField072, String caseField073, String caseField074, String caseField075, String caseField076, String caseField077, String caseField078, String caseField079, String caseField080, String caseField081, String caseField082, String caseField083, String caseField084, String caseField085, String caseField086, String caseField087, String caseField088, String caseField089, String caseField090, String caseField091, String caseField092, String caseField093, String caseField094, String caseField095, String caseField096, String caseField097, String caseField098, String caseField099, String caseField100, String caseField101, String caseField102, String caseField103, String caseField104, String caseField105, String caseField106, String caseField107, String caseField108, String caseField109, String caseField110, String caseField111, String caseField112, String caseField113, String caseField114, String caseField115, String caseField116, String caseField117, String caseField118, String caseField119, String caseField120, String caseField121, String caseField122, String caseField123, String caseField124, String caseField125, String caseField126, String caseField127, String caseField128) {
        this.id = id;
        this.caseDesc = caseDesc;
        this.caseDirection = caseDirection;
        this.caseCardRef = caseCardRef;
        this.caseMerchantPrf = caseMerchantPrf;
        this.caseTerminalPrf = caseTerminalPrf;
        this.caseAmount = caseAmount;
        this.caseHeader = caseHeader;
        this.caseMti = caseMti;
        this.caseField001 = caseField001;
        this.caseField002 = caseField002;
        this.caseField003 = caseField003;
        this.caseField004 = caseField004;
        this.caseField005 = caseField005;
        this.caseField006 = caseField006;
        this.caseField007 = caseField007;
        this.caseField008 = caseField008;
        this.caseField009 = caseField009;
        this.caseField010 = caseField010;
        this.caseField011 = caseField011;
        this.caseField012 = caseField012;
        this.caseField013 = caseField013;
        this.caseField014 = caseField014;
        this.caseField015 = caseField015;
        this.caseField016 = caseField016;
        this.caseField017 = caseField017;
        this.caseField018 = caseField018;
        this.caseField019 = caseField019;
        this.caseField020 = caseField020;
        this.caseField021 = caseField021;
        this.caseField022 = caseField022;
        this.caseField023 = caseField023;
        this.caseField024 = caseField024;
        this.caseField025 = caseField025;
        this.caseField026 = caseField026;
        this.caseField027 = caseField027;
        this.caseField028 = caseField028;
        this.caseField029 = caseField029;
        this.caseField030 = caseField030;
        this.caseField031 = caseField031;
        this.caseField032 = caseField032;
        this.caseField033 = caseField033;
        this.caseField034 = caseField034;
        this.caseField035 = caseField035;
        this.caseField036 = caseField036;
        this.caseField037 = caseField037;
        this.caseField038 = caseField038;
        this.caseField039 = caseField039;
        this.caseField040 = caseField040;
        this.caseField041 = caseField041;
        this.caseField042 = caseField042;
        this.caseField043 = caseField043;
        this.caseField044 = caseField044;
        this.caseField045 = caseField045;
        this.caseField046 = caseField046;
        this.caseField047 = caseField047;
        this.caseField048 = caseField048;
        this.caseField049 = caseField049;
        this.caseField050 = caseField050;
        this.caseField051 = caseField051;
        this.caseField052 = caseField052;
        this.caseField053 = caseField053;
        this.caseField054 = caseField054;
        this.caseField055 = caseField055;
        this.caseField056 = caseField056;
        this.caseField057 = caseField057;
        this.caseField058 = caseField058;
        this.caseField059 = caseField059;
        this.caseField060 = caseField060;
        this.caseField061 = caseField061;
        this.caseField062 = caseField062;
        this.caseField063 = caseField063;
        this.caseField064 = caseField064;
        this.caseField065 = caseField065;
        this.caseField066 = caseField066;
        this.caseField067 = caseField067;
        this.caseField068 = caseField068;
        this.caseField069 = caseField069;
        this.caseField070 = caseField070;
        this.caseField071 = caseField071;
        this.caseField072 = caseField072;
        this.caseField073 = caseField073;
        this.caseField074 = caseField074;
        this.caseField075 = caseField075;
        this.caseField076 = caseField076;
        this.caseField077 = caseField077;
        this.caseField078 = caseField078;
        this.caseField079 = caseField079;
        this.caseField080 = caseField080;
        this.caseField081 = caseField081;
        this.caseField082 = caseField082;
        this.caseField083 = caseField083;
        this.caseField084 = caseField084;
        this.caseField085 = caseField085;
        this.caseField086 = caseField086;
        this.caseField087 = caseField087;
        this.caseField088 = caseField088;
        this.caseField089 = caseField089;
        this.caseField090 = caseField090;
        this.caseField091 = caseField091;
        this.caseField092 = caseField092;
        this.caseField093 = caseField093;
        this.caseField094 = caseField094;
        this.caseField095 = caseField095;
        this.caseField096 = caseField096;
        this.caseField097 = caseField097;
        this.caseField098 = caseField098;
        this.caseField099 = caseField099;
        this.caseField100 = caseField100;
        this.caseField101 = caseField101;
        this.caseField102 = caseField102;
        this.caseField103 = caseField103;
        this.caseField104 = caseField104;
        this.caseField105 = caseField105;
        this.caseField106 = caseField106;
        this.caseField107 = caseField107;
        this.caseField108 = caseField108;
        this.caseField109 = caseField109;
        this.caseField110 = caseField110;
        this.caseField111 = caseField111;
        this.caseField112 = caseField112;
        this.caseField113 = caseField113;
        this.caseField114 = caseField114;
        this.caseField115 = caseField115;
        this.caseField116 = caseField116;
        this.caseField117 = caseField117;
        this.caseField118 = caseField118;
        this.caseField119 = caseField119;
        this.caseField120 = caseField120;
        this.caseField121 = caseField121;
        this.caseField122 = caseField122;
        this.caseField123 = caseField123;
        this.caseField124 = caseField124;
        this.caseField125 = caseField125;
        this.caseField126 = caseField126;
        this.caseField127 = caseField127;
        this.caseField128 = caseField128;
    }

    public CasesDefinition(CasesDefinitionId id, String caseDesc, String caseCardRef, String caseMerchantPrf, String caseTerminalPrf, int caseAmount, String caseHeader, String caseMti) {
        this.id = id;
        this.caseDesc = caseDesc;
        this.caseCardRef = caseCardRef;
        this.caseMerchantPrf = caseMerchantPrf;
        this.caseTerminalPrf = caseTerminalPrf;
        this.caseAmount = caseAmount;
        this.caseHeader = caseHeader;
        this.caseMti = caseMti;
    }

    @Override
    public String toString() {
        return "CasesDefinition{" +
                "id=" + id +
                ", caseDesc='" + caseDesc + '\'' +
                ", caseDirection='" + caseDirection + '\'' +
                ", caseCardRef='" + caseCardRef + '\'' +
                ", caseMerchantPrf='" + caseMerchantPrf + '\'' +
                ", caseTerminalPrf='" + caseTerminalPrf + '\'' +
                ", caseAmount=" + caseAmount +
                ", caseHeader='" + caseHeader + '\'' +
                ", caseMti='" + caseMti + '\'' +
                ", caseField001='" + caseField001 + '\'' +
                ", caseField002='" + caseField002 + '\'' +
                ", caseField003='" + caseField003 + '\'' +
                ", caseField004='" + caseField004 + '\'' +
                ", caseField005='" + caseField005 + '\'' +
                ", caseField006='" + caseField006 + '\'' +
                ", caseField007='" + caseField007 + '\'' +
                ", caseField008='" + caseField008 + '\'' +
                ", caseField009='" + caseField009 + '\'' +
                ", caseField010='" + caseField010 + '\'' +
                ", caseField011='" + caseField011 + '\'' +
                ", caseField012='" + caseField012 + '\'' +
                ", caseField013='" + caseField013 + '\'' +
                ", caseField014='" + caseField014 + '\'' +
                ", caseField015='" + caseField015 + '\'' +
                ", caseField016='" + caseField016 + '\'' +
                ", caseField017='" + caseField017 + '\'' +
                ", caseField018='" + caseField018 + '\'' +
                ", caseField019='" + caseField019 + '\'' +
                ", caseField020='" + caseField020 + '\'' +
                ", caseField021='" + caseField021 + '\'' +
                ", caseField022='" + caseField022 + '\'' +
                ", caseField023='" + caseField023 + '\'' +
                ", caseField024='" + caseField024 + '\'' +
                ", caseField025='" + caseField025 + '\'' +
                ", caseField026='" + caseField026 + '\'' +
                ", caseField027='" + caseField027 + '\'' +
                ", caseField028='" + caseField028 + '\'' +
                ", caseField029='" + caseField029 + '\'' +
                ", caseField030='" + caseField030 + '\'' +
                ", caseField031='" + caseField031 + '\'' +
                ", caseField032='" + caseField032 + '\'' +
                ", caseField033='" + caseField033 + '\'' +
                ", caseField034='" + caseField034 + '\'' +
                ", caseField035='" + caseField035 + '\'' +
                ", caseField036='" + caseField036 + '\'' +
                ", caseField037='" + caseField037 + '\'' +
                ", caseField038='" + caseField038 + '\'' +
                ", caseField039='" + caseField039 + '\'' +
                ", caseField040='" + caseField040 + '\'' +
                ", caseField041='" + caseField041 + '\'' +
                ", caseField042='" + caseField042 + '\'' +
                ", caseField043='" + caseField043 + '\'' +
                ", caseField044='" + caseField044 + '\'' +
                ", caseField045='" + caseField045 + '\'' +
                ", caseField046='" + caseField046 + '\'' +
                ", caseField047='" + caseField047 + '\'' +
                ", caseField048='" + caseField048 + '\'' +
                ", caseField049='" + caseField049 + '\'' +
                ", caseField050='" + caseField050 + '\'' +
                ", caseField051='" + caseField051 + '\'' +
                ", caseField052='" + caseField052 + '\'' +
                ", caseField053='" + caseField053 + '\'' +
                ", caseField054='" + caseField054 + '\'' +
                ", caseField055='" + caseField055 + '\'' +
                ", caseField056='" + caseField056 + '\'' +
                ", caseField057='" + caseField057 + '\'' +
                ", caseField058='" + caseField058 + '\'' +
                ", caseField059='" + caseField059 + '\'' +
                ", caseField060='" + caseField060 + '\'' +
                ", caseField061='" + caseField061 + '\'' +
                ", caseField062='" + caseField062 + '\'' +
                ", caseField063='" + caseField063 + '\'' +
                ", caseField064='" + caseField064 + '\'' +
                ", caseField065='" + caseField065 + '\'' +
                ", caseField066='" + caseField066 + '\'' +
                ", caseField067='" + caseField067 + '\'' +
                ", caseField068='" + caseField068 + '\'' +
                ", caseField069='" + caseField069 + '\'' +
                ", caseField070='" + caseField070 + '\'' +
                ", caseField071='" + caseField071 + '\'' +
                ", caseField072='" + caseField072 + '\'' +
                ", caseField073='" + caseField073 + '\'' +
                ", caseField074='" + caseField074 + '\'' +
                ", caseField075='" + caseField075 + '\'' +
                ", caseField076='" + caseField076 + '\'' +
                ", caseField077='" + caseField077 + '\'' +
                ", caseField078='" + caseField078 + '\'' +
                ", caseField079='" + caseField079 + '\'' +
                ", caseField080='" + caseField080 + '\'' +
                ", caseField081='" + caseField081 + '\'' +
                ", caseField082='" + caseField082 + '\'' +
                ", caseField083='" + caseField083 + '\'' +
                ", caseField084='" + caseField084 + '\'' +
                ", caseField085='" + caseField085 + '\'' +
                ", caseField086='" + caseField086 + '\'' +
                ", caseField087='" + caseField087 + '\'' +
                ", caseField088='" + caseField088 + '\'' +
                ", caseField089='" + caseField089 + '\'' +
                ", caseField090='" + caseField090 + '\'' +
                ", caseField091='" + caseField091 + '\'' +
                ", caseField092='" + caseField092 + '\'' +
                ", caseField093='" + caseField093 + '\'' +
                ", caseField094='" + caseField094 + '\'' +
                ", caseField095='" + caseField095 + '\'' +
                ", caseField096='" + caseField096 + '\'' +
                ", caseField097='" + caseField097 + '\'' +
                ", caseField098='" + caseField098 + '\'' +
                ", caseField099='" + caseField099 + '\'' +
                ", caseField100='" + caseField100 + '\'' +
                ", caseField101='" + caseField101 + '\'' +
                ", caseField102='" + caseField102 + '\'' +
                ", caseField103='" + caseField103 + '\'' +
                ", caseField104='" + caseField104 + '\'' +
                ", caseField105='" + caseField105 + '\'' +
                ", caseField106='" + caseField106 + '\'' +
                ", caseField107='" + caseField107 + '\'' +
                ", caseField108='" + caseField108 + '\'' +
                ", caseField109='" + caseField109 + '\'' +
                ", caseField110='" + caseField110 + '\'' +
                ", caseField111='" + caseField111 + '\'' +
                ", caseField112='" + caseField112 + '\'' +
                ", caseField113='" + caseField113 + '\'' +
                ", caseField114='" + caseField114 + '\'' +
                ", caseField115='" + caseField115 + '\'' +
                ", caseField116='" + caseField116 + '\'' +
                ", caseField117='" + caseField117 + '\'' +
                ", caseField118='" + caseField118 + '\'' +
                ", caseField119='" + caseField119 + '\'' +
                ", caseField120='" + caseField120 + '\'' +
                ", caseField121='" + caseField121 + '\'' +
                ", caseField122='" + caseField122 + '\'' +
                ", caseField123='" + caseField123 + '\'' +
                ", caseField124='" + caseField124 + '\'' +
                ", caseField125='" + caseField125 + '\'' +
                ", caseField126='" + caseField126 + '\'' +
                ", caseField127='" + caseField127 + '\'' +
                ", caseField128='" + caseField128 + '\'' +
                '}';
    }
}
