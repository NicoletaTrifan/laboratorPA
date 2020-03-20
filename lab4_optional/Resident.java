import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Collections;

public class Resident {

    private String name;
    private List<Hospital> hospitalList;

    public Resident() {
    }

    public Resident(String name) {
        this.name = name;
        hospitalList = new ArrayList<Hospital>();
    }

    public String getName() {
        return name;
    }

    public List<Hospital> getHospitalList() {
        return hospitalList;
    }

    public void setHospitalList(List<Hospital> hospitalList) {
        this.hospitalList = hospitalList;
    }

    public void setHospitalList(Hospital... hospitalList) {
        Collections.addAll(this.hospitalList, hospitalList);
        Arrays.stream(hospitalList).forEach(item -> item.addResident(this));
    }

    public static int compareByName(Resident resident1, Resident resident2) {
        return resident1.getName().compareTo(resident2.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Resident)) return false;
        Resident residents = (Resident) o;
        return name.equals(residents.name);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Residents : ");
        sb.append(this.getName());
        sb.append(": ");
        sb.append("(");
        for (Hospital hospital : hospitalList) {
            sb.append(hospital.getName());
            sb.append(";");
        }
        sb.append(")");
        return sb.toString();

    }
}
