# Day 10 Memory Lab Results

| Experiment | Command/configuration | Observed result | Explanation |
|---|---|---|---|
| Stack overflow | `java -Xss256k ... MemoryLab stack` | `StackOverflowError` after depth 2,000 was printed | Each recursive call adds a stack frame; the bounded thread stack is exhausted. |
| Heap exhaustion | `java -Xmx64m ... MemoryLab oom` | `OutOfMemoryError` after about 31 MiB of arrays | The live byte arrays fill the configured maximum heap; JVM overhead uses the remainder. |
| String concatenation | `MemoryLab strings` | `+=` 581,709,400 ns; `StringBuilder` 2,988,300 ns; both length 113,890 | `String +=` creates many intermediate immutable strings; the builder mutates one buffer. |
| Garbage collection | `java -Xlog:gc ... MemoryLab gc` | G1 selected; `GC(0) Pause Full (System.gc()) 14M->0M(8M)` | Short-lived arrays become unreachable; the collector reclaims eligible heap memory. |
| Integer cache | `MemoryLab cache` | `127 == 127` true; `1000 == 1000` false; `.equals` true | Small boxed integers are commonly cached; `equals` compares values and `==` compares references. |

The timing values are runtime-dependent. The comparison is valid because both concatenation loops use the same iteration count and output length.
