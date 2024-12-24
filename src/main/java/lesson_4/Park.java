package lesson_4;

public class Park {
    public class Attraction {
        private String name;
        private String workingHours;
        private int cost;

        public Attraction(String name, String workingHours, int cost) {
            this.name = name;
            this.workingHours = workingHours;
            this.cost = cost;
        }

        public void printInfo() {
            System.out.println("Name: " + name + ", Working Hours: " + workingHours + ", Cost: " + cost);
        }
    }
}