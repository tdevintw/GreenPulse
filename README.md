
# GreenPulse

GreenPulse is a Java-based console application designed to help users measure and monitor their carbon footprints. By offering features such as account management, real-time tracking of carbon consumption, and analysis tools, GreenPulse aims to empower individuals to make more sustainable choices. The application provides users with insights into their environmental impact and helps them track their carbon footprint over time.


## 🚀 About Me
Coding enthusiast and full stack developer who loves solving problems and bringing ideas to reality using web development tools..


## Features

-**Login**: access Only for admin 
- **Manage users**: Delete all users and update personal info
- **Undo changes**: Admin can undo changes made in the database 


## Code Overview

The project contains the following key components:

- **Main.java**: Entry point of the application, handles user menus and interactions.
- **User.java**: Represents a user in the system, including personal information and a list of consumptions and reports.
- **Database.java**:  a singleton pattern was implemented to ensure that only one instance will be created out of this class.
- **UserService**:  represente the low modules of the project, userService contains the logic and the calculations , it's like the bride that link the repository and the main(view)
- **UserRepository**:  Thanks to the repository pattern we can abstract the low modules from the databse the repository pattern ensure to hide the databse base from the rest of the project which enhance maintainability and performance , in this context i use userReposiotry to fetch or isnert data from the database . 


## 🛠 Skills
<p>
    <img src="https://skillicons.dev/icons?i=git,idea,java,maven" height="45" alt="html5 logo"  />

</p>
