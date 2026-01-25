package com.example.demo.utils;

import com.example.demo.model.AssetDetail;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CsvParser {

    public static List<AssetDetail> parse(String csv) {
        List<AssetDetail> list = new ArrayList<>();
        if (csv == null || csv.isEmpty()) return list;

        String[] lines = csv.split("\\r?\\n");

        // first line = header → skip
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i].trim();
            if (line.isEmpty()) continue;

            String[] cols = line.split(",");
            if (cols.length < 5) continue;

            AssetDetail d = new AssetDetail();
            d.setProductNum(cols[0].trim());
            d.setProductName(cols[1].trim());
            d.setProductType(cols[2].trim());
            d.setDateOfIssue(LocalDate.parse(cols[3].trim()));
            d.setTotalAssetAllocated(Integer.parseInt(cols[4].trim()));

            list.add(d);
        }
        return list;
    }
}
