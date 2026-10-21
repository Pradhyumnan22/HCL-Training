package com.hcl.training;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class SubscriptionCalculator {
    private static final ZoneId IST = ZoneId.of("Asia/Kolkata");
    private static final ZoneId UTC = ZoneOffset.UTC;
    private static final DateTimeFormatter DISPLAY_FORMAT = DateTimeFormatter.ofPattern("dd MMM uuuu HH:mm z");
    private final Map<Long, Subscription> subscriptions = new HashMap<>();

    public void save(Subscription subscription) { subscriptions.put(subscription.id(), subscription); }

    // Every find-by-id method returns Optional rather than null.
    public Optional<Subscription> findById(long id) { return Optional.ofNullable(subscriptions.get(id)); }

    public ZonedDateTime expiryDate(long id) {
        Subscription subscription = findById(id)
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found: " + id));
        return subscription.startedAt().plusMonths(subscription.durationMonths());
    }

    public long daysRemaining(long id, ZonedDateTime now) {
        return Math.max(0, Duration.between(now, expiryDate(id)).toDays());
    }

    public ZonedDateTime expiryWithGracePeriod(long id, Period gracePeriod) {
        return expiryDate(id).plus(gracePeriod);
    }

    public static String convertIstToUtc(ZonedDateTime istDateTime) {
        return istDateTime.withZoneSameInstant(UTC).format(DISPLAY_FORMAT);
    }

    public static String convertUtcToIst(Instant utcInstant) {
        return utcInstant.atZone(IST).format(DISPLAY_FORMAT);
    }

    public static void main(String[] args) {
        SubscriptionCalculator calculator = new SubscriptionCalculator();
        ZonedDateTime start = LocalDateTime.of(2026, 10, 1, 10, 30).atZone(IST);
        calculator.save(new Subscription(101, "Meera", start, 3));

        ZonedDateTime expiry = calculator.expiryDate(101);
        System.out.println("Expiry: " + expiry.format(DISPLAY_FORMAT));
        System.out.println("Days remaining: " + calculator.daysRemaining(101, ZonedDateTime.of(2026, 11, 1, 10, 30, 0, 0, IST)));
        System.out.println("Expiry with 7-day grace: " + calculator.expiryWithGracePeriod(101, Period.ofDays(7)).format(DISPLAY_FORMAT));
        System.out.println("IST to UTC: " + convertIstToUtc(start));
        System.out.println("UTC to IST: " + convertUtcToIst(start.toInstant()));
        System.out.println("Missing lookup present: " + calculator.findById(999).isPresent());

        try {
            calculator.expiryDate(999);
        } catch (SubscriptionNotFoundException e) {
            System.out.println("Custom exception: " + e.getMessage());
        }
        System.out.println("LocalDate example: " + LocalDate.now(IST));
    }
}
