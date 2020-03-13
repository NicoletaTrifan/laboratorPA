import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Hospital implements Comparable<Hospital> {
    private List<Residents> hospitalR;
    private String name;
    private int capacity;

    public Hospital(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        hospitalR = new ArrayList<Residents>();
    }

    public List<Residents> getHospitalR() {
        return this.hospitalR;
    }

    public String getName() {
        return name;
    }

    public void setHospitalR(Residents... hospitalR) {
        if (hospitalR.length <= this.capacity) {
            this.hospitalR.addAll(Arrays.asList(hospitalR));
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public void addResident(Residents resident) {
        if (hospitalR.size() + 1 <= this.capacity) {
            hospitalR.add(resident);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Hospital)) return false;
        Hospital hospital = (Hospital) o;
        return capacity == hospital.capacity &&
                name.equals(hospital.name);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Hospitals : ");
        sb.append(this.getName());
        sb.append(": ");
        sb.append("(");
        for (Residents resident : hospitalR) {
            sb.append(resident.getName());
            sb.append(";");
        }
        sb.append(")");
        sb.append("  Hospital capacity :");
        sb.append(this.capacity);
        return sb.toString();

    }

    @Override
    public int compareTo(Hospital hospital) {
        int comparator = this.getName().compareTo(hospital.getName());
        if (comparator == 0) {
            if (this.getCapacity() > hospital.getCapacity()) {
                return 1;
            } else {
                return -1;
            }
        }
        return comparator;
    }
}
