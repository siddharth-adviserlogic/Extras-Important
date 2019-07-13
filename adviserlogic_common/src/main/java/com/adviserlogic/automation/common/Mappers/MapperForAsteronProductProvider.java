package com.adviserlogic.automation.common.Mappers;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class MapperForAsteronProductProvider {

    public LinkedHashMap<String, String> mapperForAsteron(LinkedList<Map<String,String>> excelContant) {

        Map excelKeys = excelContant.get(1);
        LinkedHashMap<String, String> mapper = new LinkedHashMap<>();

        for (Object entry : excelKeys.keySet()) {

            if (entry.toString().equals("Rep Name")) {
                mapper.put("Rep Name", "Adviser Name");
            } else if (entry.toString().equals("Rep No")) {
                mapper.put("Rep No", "Adviser Code");
            } else if (entry.toString().equals("Life Insured")) {
                mapper.put("Life Insured", "Client Name");
            } else if (entry.toString().equals("Policy No")) {
                mapper.put("Policy No", "Client Account Code");
            } else if (entry.toString().equals("Policy Type")) {
                mapper.put("Policy Type", "Sub Product Code");
            } else if (entry.toString().equals("Comm Type")) {
                mapper.put("Comm Type", "Fee Type Name");
            } else if (entry.toString().equals("Comm Amt")) {
                mapper.put("Comm Amt", "Fee Amount($)");
            } else if (entry.toString().equals("Comm GST")) {
                mapper.put("Comm GST", "GST($)");
            } else if (entry.toString().equals("GST Adj")) {
                mapper.put("GST Adj", "GST($)");
            }
        }
        return mapper;
    }
}
