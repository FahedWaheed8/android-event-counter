# Android Event Counter

A customizable Android application developed in Java and XML for tracking and managing multiple event counters. Created as part of COEN 390 at Concordia University.

## Features

- **Event Tracking:** Three independent counters with a combined total count.
- **Customizable Settings:** Rename individual counters and configure a maximum count between 5 and 200.
- **Persistent Storage:** Save counter values, preferences, and event history using Android SharedPreferences.
- **Event History:** View recorded events and filter them by counter category.
- **Display Options:** Switch between event names and numerical identifiers.
- **Input Validation:** Validate counter names and counting limits before saving settings.

## Technologies Used

- Java
- XML
- Android Studio
- Android SDK
- Gradle
- Android SharedPreferences

## Application Structure

The application consists of three primary screens:

**Main Screen:** Displays three event buttons and the total count, with navigation to the settings and data screens.

**Settings Screen:** Allows users to customize counter names and configure the maximum number of events.

**Data Screen:** Displays the recorded event history, with category filtering and display options.

## Getting Started

1. Clone the repository.
2. Open the project in Android Studio.
3. Allow Gradle to synchronize.
4. Run the application using an Android emulator or compatible Android device.

## Project Information

**Course:** COEN 390 – Mini-Capstone  
**Institution:** Concordia University  
**Date:** October 2026  
**Project Type:** Individual Academic Assignment

## Future Improvements

- Add options to reset individual counters and event history.
- Export recorded events to CSV.
- Improve the user interface and data visualization.
- Add automated tests for input validation and counting logic.
