# Student House Rental Management System

A student house rental management system for university students in Canada and landlords who want to rent their spaces to students. This application will let landlords enter information about their housing spaces that are available for rent. It allows the landlord to change the status of the listed place if it has been rented. It will let the landlords remove a place from the listing. The students can use this application to view all the listings that are currently available.     

I am interested in this project because I have observed many of my friends struggle while finding housing off-campus or while finding housing in a new city where they might move for a short period of time for internships or jobs. Through this application, I want to connect the landlords and the students to make the process of finding and renting spaces easier. 

## User stories

The following are some of the *user stories* reagrding how my application can be used by someone to produce specific outcomes-:
- As a user, I want to be able to add a house to my housing list.
- As a user, I want to be able to mark a house as rented in my housing list.
- As a user, I want to remove a house from my housing list.
- As a user, I want to be able to view the list of houses that are available for rent in my housing list.
- As a user, I want to be able to save my list of houses to the file.
- As a user, I want to be able to load my list of houses from the file.


# Instructions for Grader

- You can generate the first required event related to adding Xs to a Y by clicking on the "Add House" button from the buttons panel 
or clicking "Add House ^U" option from the menu of User in the menu bar. Clicking on this button or option will pop
a new window to collect all the information necessary to add a new house to the housing list and then process the information to add a new house in the housing list. 
- You can generate the second required event related to adding Xs to a Y by clicking the "UserNumber" button followed by clicking the "Remove House" button.
You will get a new window to enter your registration number once you click the "UserNumber" button. After entering a valid Registration Number, click the "Remove House"
button. Clicking this button will remove the house from the housing list if it exists in the list otherwise it will show you an error message.
- You can locate my visual component by clicking on the "Add House" button followed by entering all the required information in the correct format. As you click the "OK" button after correctly filling the information,
an image of a house with plus sign will pop up which will automatically close in 5 seconds. This image indicates that the house has been added to the housing list.
- You can save the state of my application by clicking on the "Save Houses" button in the buttons panel.
- You can reload the state of my application by clicking on the "Load Houses" button in the buttons panel

# Phase 4: Task 2

The following is a representative sample of the events that occur when the program runs -:

Fri Dec 02 14:24:45 PST 2022

House Created!

Fri Dec 02 14:24:45 PST 2022

House Added to system!

Fri Dec 02 14:24:53 PST 2022

House Removed from system!

# Phase 4: Task 3

The UML Diagram can be found below the README.md file.

Some refactoring steps that I would take if had more time include-:
- Split the Gui class into separate classes by creating a separate class for each of the buttons rather than including 
all these classes within the Gui class as nested classes. Creating separate classes would promote cohesion and the 
single responsibility principle.
- In the Display class, I would create more methods to promote the single responsibility principle. 
I would create a separate method to create the table instead of including it in the constructor. I would also 
split the convertHouseList method to promote single responsibility principle by creating a separate method to get the 
details of each house by passing the house as a parameter into the new method.
- I would also create abstract classes to prevent code duplication in the Gui and the nested classes 

