import java.util.Objects;

public class Planet {
    private String designation;
    private double massKg;
    private double orbitEarthYears;
    private PlanetType type;

    public Planet(String designation, PlanetType type){
        this.designation = designation;
        this.type = type;
    }

    public Planet(String designation, double massKg, double orbitEarthYears, PlanetType type) {
        this.designation = designation;
        this.massKg = massKg;
        this.orbitEarthYears = orbitEarthYears;
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        // Check if the passed object is an instance of Planet
        if(obj instanceof Planet) {
            // Cast the object to a Planet type
            Planet other = (Planet) obj;

            // Compare the designation, mass, orbit duration, and type
            // If all properties match, return true, indicating the objects are equal
            if(this.designation.equals(other.designation)
                    & this.massKg == other.massKg
                    & this.orbitEarthYears == other.orbitEarthYears
                    & this.type.equals(other.type)) {
                return true;
            }
        }
        // If the passed object is not an instance of Planet or any property doesn't match, return false
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(designation, massKg, orbitEarthYears, type);
    }

    @Override
    public String toString() {
        return designation;
    }
}