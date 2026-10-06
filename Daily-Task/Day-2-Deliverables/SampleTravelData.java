public class SampleTravelData {

    public static void main(String[] args) {

        String[] days = {
                "Day 1",
                "Day 2",
                "Day 3",
                "Day 4",
                "Day 5",
                "Day 6",
                "Day 7"
        };

        String[] destinations = {
                "Chennai",
                "Pondicherry",
                "Coimbatore",
                "Ooty",
                "Mysore",
                "Bangalore",
                "Chennai"
        };

        String[] activities = {
                "Arrival and city tour",
                "Beach and heritage sightseeing",
                "Local sightseeing",
                "Ooty sightseeing and hill station tour",
                "Palace and city tour",
                "City sightseeing and shopping",
                "Return journey"
        };

        double[] packagePrices = {
                2500.00,
                3500.00,
                3000.00,
                4500.00,
                4000.00,
                3500.00,
                2500.00
        };

        int[] availableSeats = {
                20,
                18,
                15,
                12,
                20,
                15,
                20
        };

        System.out.println("Tour & Travel Sample Data");
        System.out.println("=========================");

        for (int i = 0; i < days.length; i++) {
            System.out.println();
            System.out.println(days[i]);
            System.out.println("Destination    : " + destinations[i]);
            System.out.println("Activity       : " + activities[i]);
            System.out.println("Package Price  : ₹" + packagePrices[i]);
            System.out.println("Available Seats: " + availableSeats[i]);
        }
    }
}