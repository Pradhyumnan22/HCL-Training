package com.hcl.training;

import com.hcl.training.model.TravelPackage;
import com.hcl.training.model.WaitlistRequest;
import com.hcl.training.service.WaitlistService;

import java.util.Set;

public final class Day7Demo {
    private Day7Demo() { }

    public static void main(String[] args) {
        WaitlistService service = new WaitlistService();

        // FR1: store and search package entities in the ArrayList catalogue.
        service.addPackage(new TravelPackage("OOTY-01", "Ooty", 0));
        service.addPackage(new TravelPackage("GOA-01", "Goa", 10));
        service.addPackage(new TravelPackage("OOTY-02", "Ooty", 4));
        System.out.println("FR1 packages for Ooty: " + service.findPackages("Ooty"));

        // FR2: add a FIFO waitlist request, while HashSet rejects duplicates.
        System.out.println("FR2 first request accepted: " +
                service.addToWaitlist(new WaitlistRequest("T-101", "OOTY-01", 1)));
        System.out.println("FR2 duplicate rejected: " +
                service.addToWaitlist(new WaitlistRequest("T-101", "OOTY-01", 1)));
        service.addToWaitlist(new WaitlistRequest("T-102", "OOTY-01", 5));
        System.out.println("FR2 priority request: " + service.nextPriorityRequest());

        // FR3: Iterator.remove safely removes overdue entries without CME.
        int removed = service.removeOverdueRequests(Set.of("T-101"));
        System.out.println("FR3 overdue requests removed: " + removed);

        System.out.println("Event queue: " + service.nextEvent());
        System.out.println("Catalogue size: " + service.catalogueSize());
        System.out.println("Remaining waitlist size: " + service.waitlistSize());
        printTimingTable();
    }

    private static void printTimingTable() {
        System.out.println("\nCollection timing/choice guide");
        System.out.println("ArrayList   | get(i) O(1) | package catalogue and indexed reads");
        System.out.println("LinkedList  | end add/remove O(1) | FIFO waitlist operations");
        System.out.println("HashSet     | add/contains O(1) average | duplicate request prevention");
        System.out.println("ArrayDeque  | offer/poll O(1) | event queue");
        System.out.println("PriorityQueue| offer/poll O(log n) | highest-priority traveller first");
    }
}
