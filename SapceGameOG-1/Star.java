import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Star {
    public String name;
    public int mass;
    public int radius;

    public int temp;

    public double luminosity;

    public List<Planet> planets = new ArrayList<>();




    public Star(String name, int mass, int radius, int temp, double luminosity, List<Planet> planets) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.temp = temp;
        this.luminosity = luminosity;
        this.planets = planets;

    }

    public double getVolume() {
        int planetRadius = this.radius;
        double volume = (4.0 / 3.0) * Math.PI * Math.pow(planetRadius, 3);
        return volume;
    }
    public double getDensity() {
        return this.mass/this.getVolume();
    }
    public String getColor() {
        if (this.temp < 2000) {
            return "red";
        }
        if (this.temp > 2000 && this.temp < 3000) {
            return "orange";
        }
        if (this.temp > 3000 && this.temp < 4000) {
            return "yellow";
        }
        if (this.temp > 4000 && this.temp < 7000) {
            return "white";
        }
        if (this.temp > 7000 && this.temp < 20000) {
            return "blue";
        }
        else {
            return "purple";
        }
    }
    public String getHabitableZone() {
        double innerRadius = Math.sqrt((double)(this.luminosity / 1.1));
        double outerRadius = Math.sqrt((double)(this.luminosity / 0.53));
        return "the habitable zone of this star is in between " + innerRadius + " and " + outerRadius + " AU.";
    }
    public String announceStar() {
        String planetList = "";
        for (Planet i : this.planets) {
            planetList += i.name.toString() + ", " ;
        }
        if (!planetList.isEmpty()) {
            planetList = planetList.substring(0, planetList.length() - 2);
        }
            return "Name: " + this.name +
                    ", Mass: " + this.mass +
                    ", Radius: " + this.radius +
                    ", Temperature: " + this.temp +
                    ", Planets in orbit: " + planetList + ".";
    }
    public static void main(String[] args) {
        Point newPoint = new Point(3, 1);
        Point newPoint1 = new Point(1,1);
        Planet earth = new Planet("Earth", 500, 10, 293, 0, 30, newPoint);
        Planet thea = new Planet("Thea", 300, 4, 142, 10, 10, newPoint1);
        // Create an instance of Star
        List<Planet> sunPlanets = new ArrayList<>(Arrays.asList(earth, thea));
        Star star = new Star("Sun", 100000000, 100, 5778, 3, sunPlanets);

        // Call getVolume method and print the result
        double volume = star.getVolume();
        System.out.println("Volume of the star: " + volume + " cubic meters");

        // Call getDensity method and print the result
        double density = star.getDensity();
        System.out.println("Density of the star: " + density + " kg per cubic meter");

        // Call getColor method and print the result
        String color = star.getColor();
        System.out.println("Color of the star: " + color);

        // Call getHabitableZone method and print the result
        String habitableZone = star.getHabitableZone();
        System.out.println(habitableZone);
        System.out.println(star.announceStar());
    }

}
