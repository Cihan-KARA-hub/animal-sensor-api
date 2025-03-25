package com.yelman.cloudserver.utils.fuzzy;

import net.sourceforge.jFuzzyLogic.FIS;

public class HourlyHealthCheck {

    public static Double hourlyHealthCheck(double temp, int heart, int rumination, int humidity) {
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
            //JFuzzyChart.get().chart(fis);
            fis.setVariable("temperature", temp);
            fis.setVariable("heart_rate", heart);
            fis.setVariable("humidity", humidity);
            fis.evaluate();

            System.out.println("Hastalık Riski: " + fis.getVariable("disease_risk").getValue() + "%"
                    + fis.getVariable("disease_risk"));
            return fis.getVariable("disease_risk").getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
