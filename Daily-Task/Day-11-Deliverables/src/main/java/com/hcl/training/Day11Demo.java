package com.hcl.training;

import com.hcl.training.model.Booking;
import com.hcl.training.model.Student;
import com.hcl.training.repository.BookingRepository;
import com.hcl.training.repository.StudentRepository;
import com.hcl.training.service.BookingService;
import com.hcl.training.service.StudentService;

public final class Day11Demo {
    private Day11Demo() { }
    public static void main(String[] args) {
        StudentService students = new StudentService(new StudentRepository());
        students.register(new Student(1, "Meera", "meera@example.com"));
        students.register(new Student(2, "Ravi", "ravi@example.com"));
        System.out.println("StudentService: " + students.listStudents());

        BookingService bookings = new BookingService(new BookingRepository());
        bookings.add(new Booking(101, "Meera", "DEP-OOTY-01", 4500));
        bookings.add(new Booking(102, "Ravi", "DEP-OOTY-01", 3000));
        bookings.add(new Booking(103, "Asha", "DEP-GOA-01", 5000));
        System.out.println("FR7 refund: " + bookings.cancel(101, 20));
        System.out.println("FR8 departure report: " + bookings.bookingsByDeparture());
    }
}
