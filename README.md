Codebase Overview

This repository is a small Maven project whose purpose is to benchmark different techniques for copying a file in Java.

The README explains in French that it is “A simple project to save time when copying local files.”

Project structure

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

The build configuration in pom.xml specifies Java 11 and depends on Apache Commons IO 2.7

.
Core Components

    Copier Interface

    Defines the methods every strategy must implement—initialize resources, read, write, close, and cleanup—plus a description of the strategy

.

Main Class

Generates a 1 GiB file in the system temp directory, then runs a benchmark for each copying strategy. Each strategy is wrapped in a CopierBenchMark instance that times the steps and prints a summary

.

CopierBenchMark

Measures duration for each stage (initialize, read, write, close) using java.time and prints a human‑readable summary

.

Copy Implementations

Several classes provide different file-copy techniques:

    CopierJournalDevSolution1: uses FileInputStream/FileOutputStream with a configurable byte buffer

.

CopierJournalDevSolution2: uses NIO FileChannel.transferFrom

.

CopierJournalDevSolution3: delegates to FileUtils.copyFile from Commons IO

.

CopierNewIO: uses FileChannel.transferTo to copy directly between channels

.

CopierNewIOBufferRead: memory‑maps the file into MappedByteBuffer and writes from the mapped buffer

.

CopierNewIOBufferRead2: reads chunks with a direct ByteBuffer in a loop, writing them out with NIO channels

    .

Every implementation cleans up by deleting the destination file in cleanup().

IOFacility

A small helper to close resources and log any errors

    .

Important Notes

    Benchmark Execution – Running Main will create a large temp file (1 GiB) and then invoke each strategy sequentially. This may consume disk space and take time depending on your environment.

    No Unit Tests – The repo doesn’t contain automated tests, so verification relies on manual runs.

    Dependencies – Only Apache Commons IO is required. Everything else is standard Java 11.

Suggestions for Learning Next

    Explore Java NIO – Understanding memory-mapped files (MappedByteBuffer), FileChannel, and direct byte buffers will help you grasp why certain strategies might outperform others.

    Benchmarking Techniques – Review how java.time is used to measure durations and consider more advanced benchmarking (e.g., JMH) for precise measurements.

    Error Handling and Resource Management – Some strategies have minimal error handling. Investigate try-with-resources and better exception logging.

    More Strategies – Try implementing additional copy methods such as asynchronous or multi-threaded copy, or benchmarking on different file sizes and file systems.

    Build/Packaging – Since it’s a Maven project, learning about running mvn package or creating a runnable JAR will make it easier to execute outside the IDE.

This codebase is straightforward and focused on demonstrating file-copy approaches. Understanding these classes provides a good foundation for experimenting with Java I/O performance and learning how different libraries and APIs affect throughput.
