package com.yelman.cloudserver.utils.fuzzy;

import net.sourceforge.jFuzzyLogic.FIS;

public class FuzzyLogic {
    
    public  static void logic(){
        try {
            String file = "C:/Users/cihan/OneDrive/Masaüstü/monitoring-cattle-health/cloud-server/src/main/java/com/yelman/cloudserver/utils/fuzzy/cattle_health.fcl";
            if (file == null) {
                System.err.println("FCL dosyası kaynaklarda bulunamadı!");
                return;
            }

            FIS fis = FIS.load(file, true);
            if (fis == null) {
                System.err.println("FCL dosyası yüklenemedi!");
                return;
            }

            //JFuzzyChart.get().chart(fis);

            fis.setVariable("temperature", 37.8);
            fis.setVariable("heart_rate", 80);
            fis.setVariable("rumination", 400);
            fis.setVariable("humidity", 75);
            fis.evaluate();

            System.out.println("Hastalık Riski: " + fis.getVariable("disease_risk").getValue() + "%"
                    + fis.getVariable("disease_risk"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
