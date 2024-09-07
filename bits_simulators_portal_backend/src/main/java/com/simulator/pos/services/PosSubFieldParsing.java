package com.simulator.pos.services;

import com.simulator.dtos.Detail;
import com.simulator.dtos.pos.PosCasesDefinitionDTO;
import com.simulator.dtos.pos.PosFieldValueDTO;
import com.simulator.entities.CountryParam;
import com.simulator.entities.CurrencyParam;
import com.simulator.entities.pos.*;
import com.simulator.repository.CountryRepository;
import com.simulator.repository.CurrencyRepository;
import com.simulator.repository.pos.PosFunctRepository;
import com.simulator.repository.pos.PosMccRepository;
import com.simulator.repository.pos.PosResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PosSubFieldParsing {


    @Autowired
    PosResponseRepository responseRepository;

    @Autowired
    PosFunctRepository functRepository;

    @Autowired
    PosMccRepository mccRepository;

    @Autowired
    CountryRepository countryRepository;

    @Autowired
    CurrencyRepository currencyRepository;
    public List<PosCasesDefinitionDTO> processFields(List<PosCasesDefinition> cases) {
        List<PosCasesDefinitionDTO> casesDTO = new ArrayList<>();
        for (PosCasesDefinition c : cases) {
            List<PosFieldValueDTO> processedFields = new ArrayList<>();
            for (PosFieldValue field : c.getFields()) {
                int fieldCode = field.getFieldDef().getId().getFieldId();
                switch (fieldCode) {
                    case 3:
                        processedFields.add(processFieldCode3(field));
                        break;
                    case 18:
                        processedFields.add(processFieldCode18(field));
                        break;
                    case 19, 21:
                        processedFields.add(processFieldCode19_21(field));
                        break;
                    case 22:
                        processedFields.add(processFieldCode22(field));
                        break;
                    case 24:
                        processedFields.add(processFieldCode24(field));
                        break;
                    case 35:
                        processedFields.add(processFieldCode35(field));
                        break;
                    case 39:
                        processedFields.add(processFieldCode39(field));
                        break;
                    case 49,50,51:
                        processedFields.add(processFieldCode49_50_51(field));
                        break;
                    case 54:
                        processedFields.add(processFieldCode54(field));
                        break;
                    case 53:
                        processedFields.add(processFieldCode53(field));
                        break;
                    /*case 48:
                        processedFields.add(processFieldCode48(field));
                        break;*/
                    case 55:
                        processedFields.add(processFieldCode55(field));
                        break;
                    default:
                        processedFields.add(new PosFieldValueDTO(field.getFieldDef(), field.getValue(), new ArrayList<>()));
                        break;
                }
            }
            PosCasesDefinitionDTO caseDefDTO = new PosCasesDefinitionDTO(
                    c.getId(), c.getCaseDesc(), c.getCaseDirection(), c.getCaseCardRef(),
                    c.getCaseMerchantPrf(), c.getCaseTerminalPrf(), c.getCaseAmount(),
                    c.getCaseHeader(), c.getCaseMti(), processedFields
            );
            casesDTO.add(caseDefDTO);
        }
        return casesDTO;
    }
    private PosFieldValueDTO processFieldCode3(PosFieldValue field) {
        String value = field.getValue();
        if (value != null && value.length() >= 6) {
            return new PosFieldValueDTO(field.getFieldDef(), value, List.of(
                    new Detail("Processing Code", value.substring(0, 2)),
                    new Detail("Source Account", value.substring(2, 4)),
                    new Detail("Destination Account", value.substring(4, 6))
            ));
        }
        return new PosFieldValueDTO(field.getFieldDef(), value, Collections.emptyList());
    }


    private PosFieldValueDTO processFieldCode18(PosFieldValue field) {
        String value = field.getValue();
        System.out.println("value ===>"+value);
        String mccDesc = null;
        if (value != null && value.length() >= 4) {
            List<PosMccDef> mccDefs = mccRepository.findByIdMccCode(value.substring(0, 4));
            if (mccDefs != null && !mccDefs.isEmpty()) {
                // Assuming you want the first result if there are multiple
                mccDesc = mccDefs.get(0).getMccDesc();
            }
        }
        if (value != null && value.length() >= 4) {
            return new PosFieldValueDTO(field.getFieldDef(), value, List.of(
                    new Detail(mccDesc, value.substring(0, 4))

            ));
        }
        return new PosFieldValueDTO(field.getFieldDef(), value, Collections.emptyList());
    }


    private PosFieldValueDTO processFieldCode19_21(PosFieldValue field) {
        String value = field.getValue();
        System.out.println("value ===>"+value);
        String countryDesc = null;
        if (value != null && value.length() >= 3) {
            Optional<CountryParam> countryParam = countryRepository.findById(value.substring(0, 3));
            if (countryParam != null && !countryParam.isEmpty()) {
                // Assuming you want the first result if there are multiple
                countryDesc = countryParam.get().getCountryDesc();
            }
        }
        if (value != null && value.length() >= 3) {
            return new PosFieldValueDTO(field.getFieldDef(), value, List.of(
                    new Detail(countryDesc, value.substring(0, 3))

            ));
        }
        return new PosFieldValueDTO(field.getFieldDef(), value, Collections.emptyList());
    }

    private PosFieldValueDTO processFieldCode49_50_51(PosFieldValue field) {
        String value = field.getValue();
        System.out.println("value ===>"+value);
        String currencyDesc = null;
        if (value != null && value.length() >= 3) {
            Optional<CurrencyParam> currencyParam = currencyRepository.findById(value.substring(0, 3));
            if (currencyParam != null && !currencyParam.isEmpty()) {
                // Assuming you want the first result if there are multiple
                currencyDesc = currencyParam.get().getCcyDesc();
            }
        }
        if (value != null && value.length() >= 3) {
            return new PosFieldValueDTO(field.getFieldDef(), value, List.of(
                    new Detail(currencyDesc, value.substring(0, 3))

            ));
        }
        return new PosFieldValueDTO(field.getFieldDef(), value, Collections.emptyList());
    }

    private PosFieldValueDTO processFieldCode22(PosFieldValue field) {
        String value = field.getValue();
        List<Detail> details = new ArrayList<>();

        if (value != null && value.length() >= 12) {
            details.add(new Detail("Capacité d’entrée de données de carte", String.valueOf(value.charAt(0))));
            details.add(new Detail("Capacité d’authentification du titulaire", String.valueOf(value.charAt(1))));
            details.add(new Detail("Capacité de rétention de carte", String.valueOf(value.charAt(2))));
            details.add(new Detail("Environnement fonctionnel", String.valueOf(value.charAt(3))));
            details.add(new Detail("Présence du titulaire de la carte", String.valueOf(value.charAt(4))));
            details.add(new Detail("Présence de la carte", String.valueOf(value.charAt(5))));
            details.add(new Detail("Mode d’entrée des données de carte", String.valueOf(value.charAt(6))));
            details.add(new Detail("Méthode d’authentification du titulaire", String.valueOf(value.charAt(7))));
            details.add(new Detail("Entité d’authentification du titulaire", String.valueOf(value.charAt(8))));
            details.add(new Detail("Capacité de mise à jour", String.valueOf(value.charAt(9))));
            details.add(new Detail("Capacité de sortie du terminal", String.valueOf(value.charAt(10))));
            details.add(new Detail("Longueur du PIN", String.valueOf(value.charAt(11))));

            return new PosFieldValueDTO(field.getFieldDef(), value, details);
        }

        return new PosFieldValueDTO(field.getFieldDef(), value, Collections.emptyList());
    }

    private PosFieldValueDTO processFieldCode35(PosFieldValue field) {
        String value = field.getValue();
        int separatorIndex = value != null ? (value.indexOf('=') != -1 ? value.indexOf('=') : value.indexOf('d')) : -1;

        if (separatorIndex != -1) {
            return new PosFieldValueDTO(field.getFieldDef(), value, List.of(
                    new Detail("Card Number", value.substring(0, separatorIndex)),
                    new Detail("Expiry date", value.substring(separatorIndex + 1, separatorIndex + 5)),
                    new Detail("SSS", value.substring(separatorIndex + 5, separatorIndex + 8)),
                    new Detail("ADDITIONAL DATA", value.substring(separatorIndex + 8))
            ));
        }

        return new PosFieldValueDTO(field.getFieldDef(), value, Collections.emptyList());
    }

    private PosFieldValueDTO processFieldCode24(PosFieldValue field) {
        String value = field.getValue();
        System.out.println("value ===>" + value);
        String funcDesc = null;
        if (value != null && value.length() >= 3) {
            List<PosFunctDef> functionDefs = functRepository.findByIdFctCode(value.substring(0, 3));
            if (functionDefs != null && !functionDefs.isEmpty()) {
                // Assuming you want the first result if there are multiple
                funcDesc = functionDefs.get(0).getFctDesc();
            }
        }
        if (value != null && value.length() >= 3) {
            return new PosFieldValueDTO(field.getFieldDef(), value, List.of(
                    new Detail(funcDesc, value.substring(0, 3))
            ));
        }
        return new PosFieldValueDTO(field.getFieldDef(), value, Collections.emptyList());
    }

    private PosFieldValueDTO processFieldCode39(PosFieldValue field) {
        String value = field.getValue();
        System.out.println("value ===>"+value);
        String respDesc = null;
        if (value != null && value.length() >= 2) {
            List<PosResponseDef> responseDefs = responseRepository.findByIdRespCode(value.substring(0, 2));
            if (responseDefs != null && !responseDefs.isEmpty()) {
                // Assuming you want the first result if there are multiple
                respDesc = responseDefs.get(0).getRespDesc();
            }
        }
        if (value != null && value.length() >= 2) {
            return new PosFieldValueDTO(field.getFieldDef(), value, List.of(
                    new Detail(respDesc, value.substring(0, 2))

            ));
        }
        return new PosFieldValueDTO(field.getFieldDef(), value, Collections.emptyList());
    }



    private PosFieldValueDTO processFieldCode54(PosFieldValue field) {
        String value = field.getValue();
        List<Detail> details = new ArrayList<>();

        if (value != null && value.length() >= 20) {
            for (int i = 0; i < value.length(); i += 20) {
                details.add(new Detail("Type compte" + (i / 20 + 1), value.substring(i, i + 2)));
                details.add(new Detail("Type montant" + (i / 20 + 1), value.substring(i + 2, i + 4)));
                details.add(new Detail("Code Monnaie" + (i / 20 + 1), value.substring(i + 4, i + 6)));
                details.add(new Detail("Crédit/Débit" + (i / 20 + 1), value.substring(i + 6, i + 7)));
                details.add(new Detail("Montant Balance" + (i / 20 + 1), value.substring(i + 7, i + 19)));
            }

            return new PosFieldValueDTO(field.getFieldDef(), value, details);
        }

        return new PosFieldValueDTO(field.getFieldDef(), value, Collections.emptyList());
    }

    private PosFieldValueDTO processFieldCode53(PosFieldValue field) {
        String value = field.getValue();
        List<Detail> details = new ArrayList<>();

        if (value != null && value.length() >= 6) {
            details.add(new Detail("Format de sécurité (Pin block encryption method)", value.substring(0, 2)));
            details.add(new Detail("Format du pin block", value.substring(2, 4)));
            details.add(new Detail("Index de la clé d'encryption du PIN", value.substring(4, 6)));
        }

        return new PosFieldValueDTO(field.getFieldDef(), value, details);
    }

    private PosFieldValueDTO processFieldCode48(PosFieldValue field) {
        String value = field.getValue();
        List<Detail> details = new ArrayList<>();

        int index = 0;
        while (index + 6 <= value.length()) {
            String tag = value.substring(index, index + 3);
            int length = Integer.parseInt(value.substring(index + 3, index + 6), 10);

            if (index + 6 + length <= value.length()) {
                String tagValue = value.substring(index + 6, index + 6 + length);
                details.add(new Detail(tag, tagValue));
                index += 6 + length;
            } else {
                break;
            }
        }

        return new PosFieldValueDTO(field.getFieldDef(), value, details);
    }

    public PosFieldValueDTO processFieldCode55(PosFieldValue field) {
        String data = field.getValue();
        List<Detail> details = new ArrayList<>();

        for (int i=0; i<data.length();) {
            System.out.println(i);
            String tag = data.substring(i,i+2);
            if (tag.equals("5F") || tag.equals("9F") || tag.substring(0+1,0+1+1).equals("F")) {
                tag = data.substring(i,i+4);
                i+=2;
            }
            i+=2;

            String length = data.substring(i,i+2);
            i+=2;
            if (length.equals("81")) {
                length = data.substring(i,i+2);i+=2;
            }
            else if (length.equals("82")) {
                length = data.substring(i,i+4);i+=4;
            }

            long len = Long.parseLong(length, 16);

            String value = data.substring(i,i+((int) len *2));
            i+=((int) len *2);
            details.add(new Detail(tag, value));
            System.out.println(tag + "\n" + length + "\n" + value);
        }
        return new PosFieldValueDTO(field.getFieldDef(), data, details);
    }
}
