# Benchmark Copy File

This repository is a small Maven project that benchmarks different techniques for copying a file in Java. The original README described it in French as:

> Un projet simple pour gagner du temps de copie de fichiers locaux.

## Project structure

```
benchmarkcopyfile/
├── pom.xml
└── src
    └── main
        └── java
            └── info/boaventura/bench
                ├── Copier.java
                ├── CopierBenchMark.java
                ├── IOFacility.java
                ├── Main.java
                ├── CopierJournalDevSolution1.java
                ├── CopierJournalDevSolution2.java
                ├── CopierJournalDevSolution3.java
                ├── CopierNewIO.java
                ├── CopierNewIOBufferRead.java
                └── CopierNewIOBufferRead2.java
```

The build configuration in `pom.xml` uses **Java 11** and depends on **Apache Commons IO 2.7**.

## Core Components

### Copier Interface
Defines the methods every strategy must implement—initialize resources, read, write, close, cleanup—and provide a human readable description.

### Main Class
Generates a 1 GiB file in the system temp directory and runs a benchmark for each copying strategy. Each strategy is wrapped in `CopierBenchMark` which times the steps and prints a summary.

### CopierBenchMark
Measures duration for each stage (initialize, read, write, close) using `java.time` and prints a human-readable summary.

### Copy Implementations
Several classes provide different file-copy techniques:

- `CopierJournalDevSolution1`: uses `FileInputStream`/`FileOutputStream` with a configurable byte buffer.
- `CopierJournalDevSolution2`: uses NIO `FileChannel.transferFrom`.
- `CopierJournalDevSolution3`: delegates to `FileUtils.copyFile` from Commons IO.
- `CopierNewIO`: uses `FileChannel.transferTo` to copy directly between channels.
- `CopierNewIOBufferRead`: memory‑maps the file with `MappedByteBuffer` and writes from the mapped buffer.
- `CopierNewIOBufferRead2`: reads chunks with a direct `ByteBuffer` and writes them out with NIO channels.

Every implementation cleans up by deleting the destination file in `cleanup()`.

### IOFacility
A helper to close resources and log any errors.

## Important Notes
1. **Benchmark Execution** – running `Main` creates a large (1 GiB) temp file and sequentially runs each strategy. It may take time and disk space.
2. **No Unit Tests** – the repository doesn’t contain automated tests, so verification is manual.
3. **Dependencies** – only Apache Commons IO is required. Everything else is standard Java 11.

## Suggestions for Learning Next
- **Explore Java NIO** – understand memory-mapped files, `FileChannel`, and direct buffers to see why some strategies are faster.
- **Benchmarking Techniques** – look into more precise tools such as JMH.
- **Error Handling and Resource Management** – consider `try-with-resources` and better exception logging.
- **More Strategies** – try implementing asynchronous or multi-threaded copy, or vary file sizes.
- **Build/Packaging** – learn how to run `mvn package` or create a runnable JAR.

This codebase is straightforward and focused on demonstrating file-copy approaches. Understanding these classes provides a good foundation for experimenting with Java I/O performance and learning how different libraries and APIs affect throughput.
