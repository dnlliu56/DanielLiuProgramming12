public class Main {
    public static void main(String[] args) {
        //example objects and method calling
        Planet earth = new Planet("Earth", PlanetType.TERRESTRIAL);
        Planet neptune = new Planet("Neptune", PlanetType.ICE_GIANT);
        Planet uranus = new Planet("Uranus", PlanetType.ICE_GIANT);
        Planet earthSpecified = new Planet("Earth", 500, 1, PlanetType.TERRESTRIAL);
        Planet neptuneDuplicate = new Planet("Neptune", PlanetType.ICE_GIANT);
        System.out.println(earth.equals(earthSpecified));
        System.out.println(neptune.equals(neptuneDuplicate));
        System.out.println(neptune.equals(uranus));
        System.out.println(earth.hashCode());
        System.out.println(earthSpecified.hashCode());
        System.out.println(neptune.hashCode());
        System.out.println(uranus.hashCode());
        System.out.println(earth);
        System.out.println(uranus);
    }
}
