# Habit Tracker - Java Servlet Web Application

A lightweight, beginner-friendly web application designed to help users track daily habits. Built strictly using core Java, Java Servlets, HTML, CSS, and Apache Tomcat without high-level frameworks or external database dependencies.

---

## Table of Contents

1. [Overview](https://www.google.com/search?q=%23overview)
2. [Project Architecture](https://www.google.com/search?q=%23project-architecture)
3. [Technology Stack](https://www.google.com/search?q=%23technology-stack)
4. [Key Features](https://www.google.com/search?q=%23key-features)
5. [Directory Structure](https://www.google.com/search?q=%23directory-structure)
6. [Prerequisites](https://www.google.com/search?q=%23prerequisites)
7. [Step-by-Step Implementation & Execution](https://www.google.com/search?q=%23step-by-step-implementation--execution)
8. [Usage Guide](https://www.google.com/search?q=%23usage-guide)
9. [Limitations](https://www.google.com/search?q=%23limitations)
10. [Future Enhancements](https://www.google.com/search?q=%23future-enhancements)

---

## Overview

The **Habit Tracker** application demonstrates key web development concepts in Java, including the HTTP request-response lifecycle, servlet routing via annotations, in-memory state management using collections (`ArrayList`), and dynamic HTML rendering.

---

## Project Architecture

```text
[ Browser (HTML / CSS UI) ]
            │
            │  1. HTTP GET / POST Request (Form Submission)
            ▼
   [ Apache Tomcat Server ]
            │
            │  2. Routes request to servlet endpoint (@WebServlet("/habit"))
            ▼
    [ HabitServlet.java ]
            │
            │  3. Modifies in-memory collection & processes business logic
            ▼
    [ Habit.java Model ] ◄── stored in ──► static ArrayList<Habit>
            │
            │  4. Generates dynamic HTML via PrintWriter
            ▼
 [ HTTP Response to Browser ]

```

---

## Technology Stack

* **Programming Language:** Java (JDK 17+)
* **Web Technology:** Java Servlet API (`jakarta.servlet`)
* **Application Server:** Apache Tomcat (v10.x)
* **Data Storage:** In-memory `ArrayList` collection
* **Frontend:** HTML5, CSS3

---

## Key Features

* **Add Habit:** Create new habits through an HTML form interface.
* **View Habits:** Display all current habits dynamically.
* **Toggle Completion Status:** Click to switch habits between complete and incomplete states.
* **Delete Habit:** Remove habits from the tracking list.
* **Progress Tracking:** Automatically recalculate and display completion progress (e.g., `2 / 4 completed`).

---

## Directory Structure

```text
HabitTracker/
├── index.html                  # Static landing preview UI
├── style.css                   # Custom UI stylesheet
├── README.md                   # Project documentation
├── .gitignore                  # Git ignore rules
│
├── src/                        # Java source files
│   ├── Habit.java              # Habit domain model
│   └── HabitServlet.java       # Controller handling HTTP requests & HTML rendering
│
└── WEB-INF/                    # Tomcat system folder
    └── classes/                # Compiled Java bytecode (.class files)
        ├── Habit.class
        └── HabitServlet.class

```

---

## Prerequisites

Ensure the following tools are installed and configured:

1. **JDK 17 or higher** installed and set up in system environment variables (`JAVA_HOME`).
2. **Apache Tomcat 10.x** installed (e.g., at `C:\tomcat`).

---

## Step-by-Step Implementation & Execution

### 1. Project Setup

Place the project root folder `HabitTracker` inside your Tomcat `webapps` directory:

```text
C:\tomcat\webapps\HabitTracker\

```

### 2. Compilation

Open Command Prompt, navigate to the project directory, and compile the Java source files into `WEB-INF/classes` using UTF-8 encoding and Tomcat's Servlet API libraries:

```cmd
cd C:\tomcat\webapps\HabitTracker
javac -encoding UTF-8 -cp "C:\tomcat\lib\*" -d WEB-INF\classes src\Habit.java src\HabitServlet.java

```

### 3. Starting Apache Tomcat

Navigate to Tomcat's `bin` directory and run the startup script:

* **Windows Command Prompt:**
```cmd
cd C:\tomcat\bin
startup.bat

```


* **macOS / Linux Terminal:**
```bash
cd /path/to/tomcat/bin
./startup.sh

```



### 4. Accessing the Application

Open a web browser and visit:

```text
http://localhost:8080/HabitTracker/habit

```

### 5. Stopping Apache Tomcat

To safely shut down the web server:

* **Windows Command Prompt:**
```cmd
cd C:\tomcat\bin
shutdown.bat

```


* **macOS / Linux Terminal:**
```bash
cd /path/to/tomcat/bin
./shutdown.sh

```



---

## Usage Guide

1. **Viewing Initial Habits:** Upon navigating to `/habit`, default pre-populated habits will display.
2. **Adding a New Habit:** Type a habit name in the input box and click **Add**.
3. **Toggling Status:** Click on any habit name to switch its completed state.
4. **Deleting a Habit:** Click the **Delete** button adjacent to any habit.

---

## Limitations

* **Data Persistence:** Because this project uses an in-memory `ArrayList` rather than a relational database, stored habits persist only while the server is running and will reset when Apache Tomcat restarts.

---

## Future Enhancements

* Integrate relational database storage using **JDBC and MySQL** for persistent data storage across server restarts.
* Implement user login and registration mechanisms.
* Transition output to a RESTful architecture delivering JSON endpoints coupled with a JavaScript dynamic frontend.