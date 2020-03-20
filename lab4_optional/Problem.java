import com.github.javafaker.Faker;

import java.util.*;

public class Problem {
    private int numberOfHospitals;
    private int numberOfResidents;
    private List<Hospital> hospitalGeneratedList;
    private Map<Hospital, List<Resident>> hospitalPreferencesMap;
    private List<Resident> residentGeneratedList;
    private Map<Resident, List<Hospital>> residentPreferencesMap;
    private Hospital temporaryHospital;
    private Resident temporaryResident;

    public Problem() {
        numberOfHospitals = (int) (Math.random() * 20 + 1);
        numberOfResidents = (int) (Math.random() * 20 + 1);
        hospitalGeneratedList = new ArrayList<>();
        residentGeneratedList = new ArrayList<>();
        hospitalPreferencesMap = new HashMap<>();
        residentPreferencesMap = new HashMap<>();
        temporaryHospital = new Hospital();
        temporaryResident = new Resident();
    }

    public void generateRandomNames() {
        Faker faker = new Faker();
        //creez lista de spitale

        for (int i = 0; i < numberOfHospitals; i++) {
            int hospitalCapacity = (int) (Math.random() * 10 + 1);
            Hospital hospital = new Hospital(faker.name().fullName(), hospitalCapacity);
            hospitalGeneratedList.add(hospital);
        }
        //creez lista de rezidenti
        for (int i = 0; i < numberOfResidents; i++) {
            Resident resident = new Resident(faker.name().fullName());
            residentGeneratedList.add(resident);
        }
        Random rand = new Random();

        //creez listele de preferinte pentru rezidenti
        for (int i = 0; i < numberOfResidents; i++) {
            List<Hospital> hospitalList = new ArrayList<>();
            int numberOfPreferences = (int) (Math.random() * 5 + 1);
            for (int j = 0; j < numberOfPreferences; j++) {
                temporaryHospital = hospitalGeneratedList.get(rand.nextInt(hospitalGeneratedList.size()));
                while (residentPreferencesMap.containsKey(temporaryHospital)) {
                    temporaryHospital = hospitalGeneratedList.get(rand.nextInt(hospitalGeneratedList.size()));
                }
                hospitalList.add(temporaryHospital);
            }
            residentGeneratedList.get(i).setHospitalList(hospitalList);
            residentPreferencesMap.put(residentGeneratedList.get(i), hospitalList);
        }

        //creez listele de preferinte pentru spitale
//       for (int i = 0; i < numberOfResidents; i++) {
////            List<Resident> residentList = new ArrayList<>();
////            int capacity= hospitalGeneratedList.get(i).getCapacity();
////            for (int j = 0; j < capacity; j++) {
////                temporaryResident = residentGeneratedList.get(rand.nextInt(residentGeneratedList.size()));
////                while (hospitalPreferencesMap.containsKey(temporaryResident)) {
////                    temporaryResident = residentGeneratedList.get(rand.nextInt(residentGeneratedList.size()));
////                }
////                residentList.add(temporaryResident);
////            }
////            hospitalPreferencesMap.put(hospitalGeneratedList.get(i), residentList);
////        }
    }

    public List<Hospital> getHospitalGeneratedList() {
        return hospitalGeneratedList;
    }

    public List<Resident> getResidentGeneratedList() {
        return residentGeneratedList;
    }

    public Map<Resident, List<Hospital>> getResidentPreferencesMap() {
        return residentPreferencesMap;
    }

    public int getNumberOfHospitals() {
        return numberOfHospitals;
    }

    public int getNumberOfResidents() {
        return numberOfResidents;
    }
}