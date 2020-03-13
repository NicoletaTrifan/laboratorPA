import com.sun.security.jgss.GSSUtil;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        var residents = IntStream.rangeClosed(0, 3)
                .mapToObj(i -> new Residents("R" + i))
                .toArray(Residents[]::new);

        // Hospital hospital0 = new Hospital("H0", 1);
        //Hospital hospital1 = new Hospital("H1", 2);
        //Hospital hospital2 = new Hospital("H2", 2);
        int[] capacity = new int[3];
        capacity[0] = 1;
        capacity[1] = 2;
        capacity[2] = 2;
        var hospitals = IntStream.rangeClosed(0, 2)
                .mapToObj(i -> new Hospital("H" + i, capacity[i]))
                .toArray(Hospital[]::new);

        residents[0].setHospitalList(hospitals[0], hospitals[1], hospitals[2]);
        residents[1].setHospitalList(hospitals[0], hospitals[1], hospitals[2]);
        residents[2].setHospitalList(hospitals[0], hospitals[1]);
        residents[3].setHospitalList(hospitals[0], hospitals[2]);

        List<Residents> residentList = new ArrayList<>();
        residentList.addAll(Arrays.asList(residents));


        List<Residents> newSortedList = residentList.stream().sorted(Comparator.comparing(Residents::getName)).collect(Collectors.toList());
        System.out.println(newSortedList.toString());

        Set<Hospital> hospitalSet = new TreeSet<>();
        hospitalSet.add(hospitals[0]);
        hospitalSet.add(hospitals[1]);
        hospitalSet.add(hospitals[2]);
        for (Hospital hospital : hospitalSet) {
            System.out.print(hospital + ",");
        }
        System.out.println();

        Map<Residents, List<Hospital>> residentsPreferencesMap = new HashMap<>();
        residentsPreferencesMap.put(residents[0], Arrays.asList(hospitals[0], hospitals[1], hospitals[2]));
        residentsPreferencesMap.put(residents[1], Arrays.asList(hospitals[0], hospitals[1], hospitals[2]));
        residentsPreferencesMap.put(residents[2], Arrays.asList(hospitals[0], hospitals[1]));
        residentsPreferencesMap.put(residents[3], Arrays.asList(hospitals[0], hospitals[2]));

        Map<Hospital, List<Residents>> hospitalsPreferencesMap = new TreeMap<>();

        hospitalsPreferencesMap.put(hospitals[0], Arrays.asList(residents[3], residents[0], residents[1], residents[2]));
        hospitalsPreferencesMap.put(hospitals[1], Arrays.asList(residents[0], residents[2], residents[1]));
        hospitalsPreferencesMap.put(hospitals[2], Arrays.asList(residents[0], residents[1], residents[3]));
        System.out.println();
        for (Map.Entry<Residents, List<Hospital>> entry : residentsPreferencesMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + "(" + entry.getValue() + ")");
        }
        System.out.println();
        for (Map.Entry<Hospital, List<Residents>> entry : hospitalsPreferencesMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + "(" + entry.getValue() + ")");
        }
        System.out.println("The residents who accept H0");
        residentList.stream()
                .filter(res -> residentsPreferencesMap.get(res).contains(hospitals[0]))
                .filter(res -> residentsPreferencesMap.get(res).contains(hospitals[2]))
                .forEach(System.out::println);
        System.out.println("The residents who accept H2");
        residentList.stream()
                .filter(res -> residentsPreferencesMap.get(res).contains(hospitals[2]))
                .forEach(System.out::println);
        System.out.println("The Hospitals that have the R0 as their top reference");

        // hospitalSet.stream().filter(res -> hospitalsPreferencesMap.get(res).indexOf(residentList.get(0))==0).forEach(System.out::println);
        hospitalSet.stream()
                .filter(hos -> hos.getHospitalR().size() > 0 && hos.getHospitalR().get(0).equals(residents[0]))
                .forEach(System.out::println);


    }
}
