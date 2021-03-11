TITLE:QAM1 — QAM1 TASK 1: JAVA APPLICATION DEVELOPMENT

PURPOSE:Allows users to manage customers, appointments, and scheduling.

Author:Mitchell Blake
Contact Email:mblak87@my.wgu.edu
Application Version:1.0
Date: 3/7/2021
IDE VERSION: Intellij Idea 2020.2.3(EDU)
JDK VERSION: Java SE 11.0.10
JAVAFX VERSION: javafx-sdk-11.0.2
DATABASE DRIVER: v 8.0.22

JAVADOC LOCATION:src/javadox

Directions:
1.) login using username and password from database on the login form; the exit button closes the application.

2.)The user is then directed to a landing form.Customers are loaded in the top table view and appointments are populated
    in the bottom table view after a customer is selected.

    a.)To add either a customer or an appointment just enter information in the appropriate textfields,
    select combo box options, and click the (save update/add new) button.

    b.)to update either a customer or an appointment click on the desired entity in either tableview and click
    the update button under that tableview. After the update button is clicked the textfields are populated with that
    entities information. The cancel update button must be pressed if a user wants full functionality of the form
    without saving an update.

    c.)Appointments can be filtered by month by pressing the appropriate radio button.
    Appointments within a month can be filtered by selecting the appropriate week radio button.Each week is designated
    as starting on sunday. (ie. April 1st falls on a thursday. this is week 1.April 4th falls on a sunday, this is
    week two.)

    d.)Delete buttons delete selected item from associated tableview.


    e.) The generate reports button directs user to a reports form which prints all pages. The exit button on this form
    redirects user to the landing page.

    f.) The exit button on the landing page redirects user to the login form.

DESCRIPTION OF 3RD REPORT FROM PART A3f:
This report generates the amount of customers which were created within the current month.

