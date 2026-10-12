package com.hcl.training.service;

import com.hcl.training.model.TravelPackage;
import com.hcl.training.model.WaitlistRequest;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public final class WaitlistService {
    // ArrayList: fast indexed catalogue reads for package search/display.
    private final List<TravelPackage> catalogue = new ArrayList<>();
    // LinkedList: efficient FIFO insertion/removal at the waitlist ends.
    private final LinkedList<WaitlistRequest> waitlist = new LinkedList<>();
    // HashSet: prevents duplicate traveller/package requests in O(1) average time.
    private final Set<String> requestKeys = new HashSet<>();
    // ArrayDeque: lightweight FIFO queue for package-management events.
    private final ArrayDeque<String> events = new ArrayDeque<>();
    // PriorityQueue: urgent requests are served before normal requests.
    private final PriorityQueue<WaitlistRequest> priorityRequests = new PriorityQueue<>(
            (left, right) -> Integer.compare(right.priority(), left.priority()));

    public void addPackage(TravelPackage travelPackage) {
        catalogue.add(travelPackage);
        events.offer("Added package " + travelPackage.code());
    }

    public List<TravelPackage> findPackages(String destination) {
        List<TravelPackage> matches = new ArrayList<>();
        for (TravelPackage travelPackage : catalogue) {
            if (travelPackage.destination().equalsIgnoreCase(destination)) {
                matches.add(travelPackage);
            }
        }
        return matches;
    }

    public boolean addToWaitlist(WaitlistRequest request) {
        String key = request.travellerId() + ":" + request.packageCode();
        if (!requestKeys.add(key)) {
            return false;
        }
        waitlist.addLast(request);
        priorityRequests.offer(request);
        events.offer("Waitlisted traveller " + request.travellerId());
        return true;
    }

    public WaitlistRequest nextPriorityRequest() {
        return priorityRequests.poll();
    }

    public int removeOverdueRequests(Set<String> overdueTravellerIds) {
        int removed = 0;
        Iterator<WaitlistRequest> iterator = waitlist.iterator();
        while (iterator.hasNext()) {
            WaitlistRequest request = iterator.next();
            if (overdueTravellerIds.contains(request.travellerId())) {
                iterator.remove();
                requestKeys.remove(request.travellerId() + ":" + request.packageCode());
                removed++;
            }
        }
        return removed;
    }

    public String nextEvent() {
        return events.poll();
    }

    public int catalogueSize() { return catalogue.size(); }
    public int waitlistSize() { return waitlist.size(); }
}
