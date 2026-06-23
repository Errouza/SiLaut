# siLaut (Sistem Informasi Layanan Utama)

**siLaut** is a modern Android application designed as a maritime information and reporting system. It allows users to report maritime incidents (such as pollution, weather anomalies, or security issues) and provides real-time sea condition updates. The application also features a live chat system for direct communication between users and maritime authorities.

## 🚀 Key Features

### 👤 User Features
*   **Wavy Dashboard:** A visually appealing oceanic-themed dashboard with real-time sea status.
*   **Sea Condition Updates:** Searchable maritime data including wave heights, wind direction, and wind speed across various locations.
*   **Report Management:** Easily submit maritime reports with categories and detailed descriptions.
*   **Live Chat Support:** Direct messaging with maritime agents featuring an automated response system.
*   **Track Reports:** Monitor the progress of your reports (Pending, Processed, Completed).

### 🛠️ Admin Features (Role-Based)
*   **Admin Panel:** A dedicated management dashboard accessible only to accounts with `@admin.silaut.com` emails.
*   **Global Report Management:** View all reports submitted by every user in the system.
*   **Status Control:** Update the status of any report with a single click.
*   **Admin Live Chat:** Manage multiple chat sessions and reply to users manually from a unified inbox.

## 🛠 Tech Stack
*   **Language:** Kotlin
*   **Architecture:** MVVM (Model-View-ViewModel)
*   **UI Framework:** Material Design 3 (Material You) with ViewBinding
*   **Local Database:** Room Database (SQLite) with KSP
*   **Concurrency:** Kotlin Coroutines & Flow for real-time UI updates
*   **Preferences:** SharedPreferences for session management

## 📂 Project Structure (Architecture _018)
The project follows a clean architectural pattern with customized identifiers:
*   `data/model/`: Room Entities (User_018, Pengaduan_018, Chat_018)
*   `data/local/`: Database configuration (AppDatabase_018) and DAOs
*   `ui/auth/`: Login and Registration logic
*   `ui/pengaduan/`: Reporting and status tracking activities
*   `ui/chat/`: Real-time messaging implementation
*   `ui/adapter/`: RecyclerView adapters for dynamic data listing

## 🔧 Installation & Setup
1.  **Clone the repository:**
    ```bash
    git clone https://github.com/Errouza/SiLaut.git
    ```
2.  **Open in Android Studio:**
    Use Android Studio Ladybug or newer.
3.  **Sync Gradle:**
    The project uses Version Catalog (`libs.versions.toml`). Ensure KSP and Room 2.7.0-alpha11 are correctly synced.
4.  **Run Application:**
    Target SDK 36.

## 🔑 Role-Based Access
*   **User:** Register with any valid email.
*   **Admin:** Register with an email and get verified to get in.

## 👤 Developer
Developed by **Dhia** (Custom Identifier: **_018**)

---
*Developed with ❤️ for the Maritime Community.*
