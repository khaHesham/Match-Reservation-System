# EPL Reservation System API

## Overview

This project involves creating an automated ticket reservation system for Egyptian Premier League football matches. It caters to EFA (Egyptian Federation Association) management and fans, offering an automated reservation process and comprehensive match management.

## User Roles

1. **Admin:** Manages authorities and user accounts.
2. **Managers:** Control match details.
3. **Customers (Fans):** Registered users who can reserve tickets.
4. **Guests:** Unregistered or not logged-in users.

## Data Models

- **Users** have essential personal data are classified as (Admin,Managers,Fans,guests)
- **Matches** include home team, away team, venue, date & time, main referee, and two linesmen.
- **Tickets** the reserved ticket for seat in the match
- **Stadium** carry information about user

## ER Diagram
![image](https://github.com/khaHesham/Match-Reservation-System/assets/75990647/509c6c07-b680-4487-a200-165878c0f00e)


## Functionality

### Admin

1. **Approve Users:** Grant authority upon signup.
2. **Remove Users:** Admin can delete accounts.

### Managers

3. **Create Match:** Add new match events.
4. **Edit Match Details:** Modify match information.
5. **Add Stadium:** Include new stadiums.
6. **View Match Details:** Access comprehensive match information.
7. **View Seat Status:** Check vacant/reserved seats.

### Customers

8. **Edit Profile:** Modify personal data.
9. **View Match Details:** Access match info.
10. **Reserve Seat(s):** Book vacant seats, confirm with credit card.
11. **Cancel Reservation:** Permitted up to 3 days before the event.

### Guests

12. **Register Account:** Sign up as a fan or manager.
13. **Sign In:** Log in as an existing user.
14. **View Matches:** Access match details.

## Additional Specifications

1. **Stadium Shape:** VIP lounge defined by rows and seats.
2. **Graphical Reservation:** Clear display of vacant/reserved seats.
3. **Avoid Conflicts:** Prevent simultaneous reservations for the same seat.
4. **Auto Seat Status Update:** Real-time updates during reservation view.
