public final class TravelConstants {

    private TravelConstants() {
        // Prevent instantiation
    }

    // Booking
    public static final int MIN_BOOKING_SEATS = 1;
    public static final int MAX_CO_TRAVELLERS = 5;

    // Payment
    public static final double INSTALLMENT_PERCENTAGE = 25.0;

    // Booking status
    public static final String BOOKING_CONFIRMED = "CONFIRMED";
    public static final String BOOKING_CANCELLED = "CANCELLED";
    public static final String BOOKING_PENDING = "PENDING";

    // User roles
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_AGENT = "AGENT";
    public static final String ROLE_TRAVELLER = "TRAVELLER";
}