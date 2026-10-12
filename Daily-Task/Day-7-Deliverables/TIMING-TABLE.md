# Day 7 Collection Timing Table

| Collection | Operation | Typical complexity | Travel-booking choice |
|---|---|---:|---|
| `ArrayList` | `get(i)` | O(1) | Package catalogue with indexed reads |
| `LinkedList` | add/remove at an end | O(1) | FIFO waitlist insertion/removal |
| `HashSet` | `add` / `contains` | O(1) average | Duplicate traveller/package detection |
| `ArrayDeque` | `offer` / `poll` | O(1) | Package-management event queue |
| `PriorityQueue` | `offer` / `poll` | O(log n) | Urgent waitlist requests first |

`ArrayList` is preferred over `LinkedList` for package lookup because indexed reads are constant time. `LinkedList` is used for the waitlist because the workflow is end-oriented. `HashSet` is used beside the list because membership checks and duplicate prevention are the primary operation. `Iterator.remove()` is used for overdue entries so removal is safe during traversal and does not cause `ConcurrentModificationException`.
