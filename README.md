# 🔍 Secure Log Analyzer

A high-performance, command-line utility for secure log processing and system diagnostics.

---

## 📌 Overview

Secure Log Analyzer is a robust Java-based tool built for efficient, safe, and reliable log file analysis. It is designed to handle large-scale system logs with minimal memory footprint while ensuring data integrity through low-level file system synchronization.

The system is engineered with a modular architecture that enforces clean separation of concerns, robust error handling, and OS-level security checks.

---

## 🚀 Core Features

### 🛡️ Security & Integrity
- **Permission Verification:** Pre-flight checks on both input log files and output directory access.
- **Data Persistence:** Implements `FileDescriptor.sync()` to force physical disk writes, ensuring critical error logs are never lost in the event of system crashes.
- **Fail-Fast Mechanism:** Immediate termination upon encountering unrecoverable I/O errors or permission violations.

### ⚙️ Performance
- **Streaming Processing:** Uses `BufferedReader` to process log files line-by-line, maintaining constant $O(1)$ memory usage regardless of input file size.
- **Automatic Provisioning:** Self-healing directory creation for reports.

### 📊 Analysis Engine
- **Classification:** Categorizes logs into `INFO`, `WARNING`, `ERROR`, and `UNCLASSIFIED` metrics.
- **Critical Reporting:** Immediate extraction and reporting of critical errors with hardware-level synchronization.
- **Summary Generation:** Detailed final analysis output with processing statistics.

---

## 🏗️ Architecture

The project follows a clean layered design:

- **`LogAnalyzer` (Orchestrator):** Main entry point, handles CLI arguments and system workflow.
- **`PermissionChecker` (Security):** Validates file access rights before operations begin.
- **`LogParser` (Logic Engine):** Performs line-by-line streaming and classification.
- **`ReportWriter` (I/O Layer):** Manages secure buffered output and hardware disk synchronization.

---

## 🛠️ Technical Principles

- **Resource Safety:** Utilizes `try-with-resources` for automatic stream management and `AutoCloseable` interface.
- **Memory Efficiency:** Avoids loading full logs into memory, enabling the analysis of gigabyte-scale files.
- **Robustness:** Handles `IOException` and `SyncFailedException` to prevent partial data corruption.
- **Modular Design:** Highly decoupled components for easy maintainability and testing.

---

## 📝 Usage

Compile the project and run the utility via the terminal:

```bash
java LogAnalyzer <input_path> <output_path>