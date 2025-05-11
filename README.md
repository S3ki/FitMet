# Fitmet 🏃


**Fitmet** is a motivational step-tracking app designed to **encourage daily movement** through a positive, visually engaging experience. It tracks your steps in real time and boosts your motivation with:


- **Encouraging messages**
- **Clean and uplifting UI**
- **Interactive graph visualizations** of your progress

Whether you're just starting your fitness journey or aiming to stay consistent, **Fitmet** makes every step count—literally.

## Requirements
- **Minimum Android Version**: Android 4.4 (API level 19 – KitKat)
- **Hardware Requirements**:
  - Device must include a **Step Counter Sensor** (`Sensor.TYPE_STEP_COUNTER`)
    - Not all devices running Android 4.4+ support this sensor
    - The app checks for sensor availability at runtime
- **Permissions**:
  - Access to physical activity (required on Android 10 and above)

    
## Installation

To install and run the app from the source code:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/S3ki/FitMet/


## Architecture

The application is structured using the **MVVM (Model-View-ViewModel)** architecture to ensure a clear separation between the user interface and business logic.

### Key Technologies and Components
![Platform](https://img.shields.io/badge/platform-Android-blue)
![Language](https://img.shields.io/badge/language-Kotlin-orange)
![UI](https://img.shields.io/badge/UI-Jetpack%20Compose-7950F2?logo=android)
![WorkManager](https://img.shields.io/badge/WorkManager-Task%20Scheduling-00C853?logo=android)
![MPAndroidChart](https://img.shields.io/badge/MPAndroidChart-Charting-00796B?logo=chart)
![Room](https://img.shields.io/badge/Room-Local%20Storage-FF7043?logo=android)

- **Jetpack Compose**: Used to build a modern, declarative UI.
- **SensorManager & Sensor Service**: Accesses and processes real-time data from device sensors.
- **WorkManager**: Handles background and periodic tasks reliably.
- **ViewModel**: Manages and preserves UI-related state across configuration changes.
- **Kotlin Coroutines**: Facilitates efficient asynchronous programming.
- **Room**: Provides local data persistence with an abstraction layer over SQLite.
- **MPAndroidChart**: Utilized for displaying interactive, customizable charts (e.g., step progress graphs) to visualize user activity data.


This architecture ensures responsiveness, scalability, and a maintainable code

## 🚀 Future Improvements / Roadmap

Planned enhancements for upcoming versions of **Fitmet** include:

- **🏆 Leaderboard for Daily Steps**
  - Introduce a competitive element by showing top step counts among users
  - Encourage consistency and friendly competition

- **⌚ Wearable Device Connectivity**
  - Support for smartwatches and fitness trackers (e.g., Wear OS)
  - Sync step data from wearables for improved accuracy and convenience

- **📈 Advanced Analytics**
  - Weekly and monthly trends with insights
  - Personalized goal suggestions based on user activity

- **📱 Widget Support**
  - Home screen widget showing daily step progress at a glance
