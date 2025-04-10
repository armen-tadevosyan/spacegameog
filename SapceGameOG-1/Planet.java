import java.util.ArrayList;
import java.util.List;

public class Planet {
    public String name;
    public double mass;
    public double radius;

    public int temp;

    public double age;

    public double speed;

    public Point orbitalDistortion;


    public Planet(String name, double mass, double radius, int temp, double age, double speed, Point orbitalDistortion) {
        this.name = name;
        this.mass = mass;
        this.radius = radius;
        this.temp = temp;
        this.age = age;
        this.speed = speed;
        this.orbitalDistortion = orbitalDistortion;

    }

    public double getVolume() {
        double planetRadius = this.radius;
        double volume = (4.0 / 3.0) * Math.PI * Math.pow(planetRadius, 3);
        return volume;
    }
    public double getDensity() {
        return this.mass/this.getVolume();
    }
    public String getHabitable() {
        if (this.temp < 233 || this.temp > 323) {
            return "Inhabitable";
        }
        else {
            return "Habitable";
        }
    }
    public Planet collide(Planet planet) {
        Planet newPlanet = new Planet("New Planet", this.mass + 0.6* planet.mass, this.radius + 0.2 * planet.radius, 5000, 0.0, this.speed, this.point2D());
        return newPlanet;
    }
    public String announcePlanet() {
        return "Name:" + this.name + " Mass: " + this.mass + " Radius: " + this.radius + " Temperature: " + this.temp + " Age: " + this.age;
    }
    public Point point2D() {
        double input = (this.age * this.speed)/100;
        double xcord = this.orbitalDistortion.x * Math.cos(input);
        double ycord = this.orbitalDistortion.y * Math.sin(input);
        Point point = new Point(xcord, ycord);
        return point;
    }
    public double distanceTo(Planet planet) {
        double thisx = this.point2D().x;
        double thisy = this.point2D().y;
        double thatx = planet.point2D().x;
        double thaty = planet.point2D().y;
        double distance = Math.sqrt(Math.pow((thatx - thisx), 2) + Math.pow((thaty - thisy),2));
        return distance;
    }
    public Planet progress(double time) {
        // Update the age of the current planet
        this.age += time;

        // Return the current instance after modification
        return this;
    }





    // main method inside the Planet class but outside any other methods
    public static void main(String[] args) {
        Point newPoint = new Point(3, 0.5);
        Point newPoint1 = new Point(1,1);
        Planet earth = new Planet("Earth", 500, 10, 293, 2010, 3, newPoint);
        Planet thea = new Planet("Thea", 300, 4, 142, 9050, 20, newPoint1);
        List<Planet> planets = new ArrayList<>();
        planets.add(earth);
        planets.add(thea);
        double volume = earth.getVolume();
        double density = earth.getDensity();
        String habitable = earth.getHabitable();
        System.out.println("The volume of the planet is: " + volume);
        System.out.println("the Density of the planet is: " + density);
        System.out.println("this planet is " + habitable);
        System.out.println(earth.collide(thea).announcePlanet());
        System.out.println("this planet is located at " + earth.point2D().x + ", " + earth.point2D().y);
        System.out.println("the distance between the two planets is " + earth.distanceTo(thea));
        earth.progress(1000);
        thea.progress(100);
        System.out.println("this planet is located at " + earth.point2D().x + ", " + earth.point2D().y);
        System.out.println("the distance between the two planets is " + earth.distanceTo(thea));


    }
} // This closing brace ends the Planet class

