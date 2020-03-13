import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Residents {

    private String name;
    private List<Hospital> hospitalList;

    public Residents(String name) {
        this.name = name;
        hospitalList = new ArrayList<Hospital>();
    }

    public String getName() {
        return name;
    }

    public List<Hospital> getHospitalList() {
        return hospitalList;
    }

    public void setHospitalList(Hospital... hospitalList) {
        for (Hospital hospital : hospitalList) {
            this.hospitalList.add(hospital);
        }
        Arrays.stream(hospitalList).forEach(hospitalR -> hospitalR.addResident(this));
    }

    public static int compareByName(Residents resident1, Residents resident2) {
        return resident1.getName().compareTo(resident2.getName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Residents)) return false;
        Residents residents = (Residents) o;
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
