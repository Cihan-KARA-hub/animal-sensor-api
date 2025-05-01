package com.yelman.cloudserver.utils.fuzzy;

import com.yelman.cloudserver.api.dto.AlertDto;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;

public class HourlyHealthCheck {

    public static Double hourlyHealthCheck(AlertDto dto) {
        try {
            String file = "C:/Users/cihan/OneDrive/Masaüstü/monitoring-cattle-health/cloud-server/src/main/java/com/yelman/cloudserver/utils/fuzzy/hourly_health_check.fcl";
            if (file == null) {
                System.err.println("FCL dosyası kaynaklarda bulunamadı!");
                return null;
            }
            FIS fis = FIS.load(file, true);
            if (fis == null) {
                System.err.println("FCL dosyası yüklenemedi!");
                return null;
            }
            JFuzzyChart.get().chart(fis);
            fis.setVariable("temperature", dto.getTemp());
            fis.setVariable("heart_rate", dto.getHeart());
            fis.setVariable("humidity", dto.getHumidity());
            fis.evaluate();

            System.out.println("Hastalık Riski: " + fis.getVariable("disease_risk").getValue() + "%"
                    + fis.getVariable("disease_risk"));
            return fis.getVariable("disease_risk").getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {
        AlertDto dto = new AlertDto();
        dto.setTemp(38.5);
        dto.setHeart(95);
        dto.setHumidity(85.0);
        hourlyHealthCheck(dto);

    }
}
