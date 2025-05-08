package com.yelman.cloudserver.utils.fuzzy;

import com.yelman.cloudserver.api.dto.AlertDto;
import com.yelman.cloudserver.api.dto.AlertRealtimeDto;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;
import org.springframework.stereotype.Service;

@Service
public class HealthCheck {

    public static Double fuzzyLogicGeneral(AlertDto dto) {
        try {
            String file = "C:/Users/cihan/OneDrive/Masaüstü/monitoring-cattle-health/cloud-server/src/main/java/com/yelman/cloudserver/utils/fuzzy/health_check.fcl";
            if (file == null) {
                System.err.println("FCL dosyası kaynaklarda bulunamadı!");
                return null;
            }
            FIS fis = FIS.load(file, true);
            if (fis == null) {
                System.err.println("FCL dosyası yüklenemedi!");
                return null;
            }
           // JFuzzyChart.get().chart(fis);
            fis.setVariable("temperature", dto.getTemp());
            fis.setVariable("heart_rate", dto.getHeart());
            fis.setVariable("rumination", dto.getRumination());
            fis.setVariable("humidity", dto.getHumidity());
            fis.evaluate();

            Variable diseaseRisk = fis.getVariable("disease_risk");
            System.out.println("Disease Risk Score: " + diseaseRisk.getValue());
            return fis.getVariable("disease_risk").getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Double fuzzyLogicRealtime(AlertRealtimeDto dto) {
        try {
            String file = "C:/Users/cihan/OneDrive/Masaüstü/monitoring-cattle-health/cloud-server/src/main/java/com/yelman/cloudserver/utils/fuzzy/realtime_health_check.fcl";
            if (file == null) {
                System.err.println("FCL dosyası kaynaklarda bulunamadı!");
                return null;
            }
            FIS fis = FIS.load(file, true);
            if (fis == null) {
                System.err.println("FCL dosyası yüklenemedi!");
                return null;
            }
          //  JFuzzyChart.get().chart(fis);
            fis.setVariable("temperature", dto.getTemp());
            fis.setVariable("heart_rate", dto.getHeart());
            fis.setVariable("humidity", dto.getHumidity());
            fis.evaluate();

            Variable diseaseRisk = fis.getVariable("disease_risk");
            System.out.println("Disease Risk Score: " + diseaseRisk.getValue());
            return fis.getVariable("disease_risk").getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
