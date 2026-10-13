# Day 8 Collections Framework Part 2

`EmployeeDirectory` demonstrates:

- `HashMap<Integer, Employee>` for employee ID lookup.
- `HashMap.merge` for frequency counting.
- `TreeMap` for a department report sorted by department name.
- `Comparator.comparingDouble(...).reversed().thenComparing(...)` for the top-five salary report.
- `equals`/`hashCode` based on employee ID, demonstrated with a `HashSet`.

The directory methods cover FR4–FR6 and use parameterized collections throughout; no raw types or unchecked conversions are used.
