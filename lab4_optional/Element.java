public class Element {
    private Hospital hospital;
    private Resident resident;

    public Element(Hospital hospital, Resident resident) {
        this.hospital = hospital;
        this.resident = resident;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Resident getResident() {
        return resident;
    }

    public void setResident(Resident resident) {
        this.resident = resident;
    }

    @Override
    public String toString() {
        return "Element {" +
                "resident=" + resident.getName() +
                ", hospital=" + hospital.getName() +
                ", resident preferences= " + resident.getHospitalList() +
                "}\n";
    }
}
