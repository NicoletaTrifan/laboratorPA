import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Matching {
    private Problem generatedProbleminstance;
    private List<Element> resultedMatch;
    private List<Hospital> hospitalGeneratedList;
    private List<Resident> residentGeneratedList;
    private Map<Hospital, List<Resident>> hospitalPreferencesMap;
    private Map<Resident, List<Hospital>> residentPreferencesMap;
    private List<Hospital> temporaryHospital;

    public Matching() {
        generatedProbleminstance = new Problem();
        generatedProbleminstance.generateRandomNames();
        resultedMatch = new ArrayList<>();
        hospitalGeneratedList = new ArrayList<>();
        residentGeneratedList = new ArrayList<>();
        hospitalPreferencesMap = new HashMap<>();
        residentPreferencesMap = new HashMap<>();
        this.hospitalGeneratedList = generatedProbleminstance.getHospitalGeneratedList();
        this.residentGeneratedList = generatedProbleminstance.getResidentGeneratedList();
        this.residentPreferencesMap = generatedProbleminstance.getResidentPreferencesMap();
        temporaryHospital = new ArrayList<>();
    }

    public void matchAlgorithm() {
        // for (Map.Entry<Resident, List<Hospital>> entry : residentPreferencesMap.entrySet()) {
        //   System.out.println(entry.getKey() + ":" + "(" + entry.getValue() + ")");
        // }
        for (Resident resident : residentPreferencesMap.keySet()) {
            temporaryHospital = resident.getHospitalList();
            //initializez un vector cu capacitatile spitalelor din lista curenta
            int[] capacityHospital = new int[temporaryHospital.size()];
            for (int i = 0; i < temporaryHospital.size(); i++) {
                capacityHospital[i] = temporaryHospital.get(i).getCapacity();
            }
            int contor = 0;
            //verific pt lista spitalelor rezidentului curent si il asignez la primul spital cu capacitate disponibila conform
            //preferintelor indicate anterior
            while (contor < temporaryHospital.size() - 1) {
                if (temporaryHospital.get(contor).getCapacity() <= 0) {
                    contor++;
                } else {
                    temporaryHospital.get(contor).addResident(resident);
                    capacityHospital[contor]--;
                    Hospital newHospital = new Hospital(temporaryHospital.get(contor).getName(), temporaryHospital.get(contor).getCapacity());
                    Element newElement = new Element(newHospital, resident);
                    resultedMatch.add(newElement);
                    contor = temporaryHospital.size();
                }
            }
        }
    }

    public List<Element> getResultedMatch() {
        return resultedMatch;
    }
}
