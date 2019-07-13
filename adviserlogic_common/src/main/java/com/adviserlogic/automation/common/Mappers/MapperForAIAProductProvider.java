package com.adviserlogic.automation.common.Mappers;

import java.util.*;

public class MapperForAIAProductProvider {

    public int mapperForAIA(LinkedList<Map<String,String>> excelContant, List<LinkedHashMap<String,String>> uploadTableData) {

        Map excelKeys = excelContant.get(1);
        LinkedHashMap<String, String> mapper = new LinkedHashMap<>();

        for (Object entry : excelKeys.keySet()) {

            if (entry.toString().equals("Advisor Name")) {
                mapper.put("Advisor Name", "Adviser Name");
            } else if (entry.toString().equals("Advisor Id")) {
                mapper.put("Advisor Id", "Adviser Code");
            } else if (entry.toString().equals("Insured Name")) {
                mapper.put("Insured Name", "Client Name");
            } else if (entry.toString().equals("Policy Number")) {
                mapper.put("Policy Number", "Client Account Code");
            } else if (entry.toString().equals("Product Name")) {
                mapper.put("Product Name", "Sub Product Code");
            } else if (entry.toString().equals("Type")) {
                mapper.put("Type", "Fee Type Name");
            } else if (entry.toString().equals("Commission")) {
                mapper.put("Commission", "Fee Amount($)");
            } else if (entry.toString().equals("Gst")) {
                mapper.put("Gst", "GST($)");
            } else if (entry.toString().equals("Statement\n" +
                    "Date")) {
                mapper.put("Statement\n" +
                        "Date", "DatePaid");
            }
        }
       // return mapper;
        int rowMatchcounter = 0;
        for (int j = 0; j <excelContant.size(); j++) {
            Map<String, String> excelTableRows = excelContant.get(j);
            for (int i = 0; i < uploadTableData.size(); i++) {
                LinkedHashMap<String, String> uploadedTableRows = uploadTableData.get(i);
                if (excelTableRows.get("Advisor Name").replace(":", "").equals(uploadedTableRows.get(mapper.get("Advisor Name")))
                        && excelTableRows.get("Advisor Id").equals(uploadedTableRows.get(mapper.get("Advisor Id")))
                        && excelTableRows.get("Insured Name").replace(",", "").replace("'", "").trim()
                        .equals(uploadedTableRows.get(mapper.get("Insured Name")).trim())
                        && excelTableRows.get("Policy Number").equals(uploadedTableRows.get(mapper.get("Policy Number")))
                        && excelTableRows.get("Product Name").equals(uploadedTableRows.get(mapper.get("Product Name")))
                        && excelTableRows.get("Type").equals(uploadedTableRows.get(mapper.get("Type")))
                        && String.format("%.2f", (Float.valueOf(excelTableRows.get("Commission").replaceAll("^\\d+\\.\\d{1}$+",
                        excelTableRows.get("Commission") + "0")))).equals(uploadedTableRows.get(mapper.get("Commission")))
                        && String.format("%.2f", (Float.valueOf(excelTableRows.get("Gst").replaceAll("^\\d+\\.\\d{1}$+",
                        excelTableRows.get("Gst") + "0")))).equals(uploadedTableRows.get(mapper.get("Gst"))))

                {
                    ++rowMatchcounter;
                    break;
                }

            }
        }
        return rowMatchcounter;
    }

}
