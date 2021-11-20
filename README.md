# ENSF 607 - Term Project

**Project:** Veterinary Medicine Web Application

**Group 825**
- Timothy Mok
- Aron Saengchan
- Yong Jun Zhu

## Project Information

## Overview
In collaboration with the Veterinary School of Medicine, the objective of this project is to create a full stack web application for their department to use for day-to-day operations. This application serves to address three important areas - animal management, user management, and treatment process. As it will also primarily be used by the staff, an interface will be developed to be user-friendly.

### Process
The first three out of five sprint for this project have been completed:

1. Developing epics and user stories
2. Designing wireframes
3. Designing the API

The contents of this repository address the deliverables completed in the third sprint and are described in the following sections.

## API Endpoints

API endpoints were developed for the user stories that required one. The table below lists the file path of the API package for each associated user story code.

| User Story                                                                | File Path                                                    |
| ------------------------------------------------------------------------- | ------------------------------------------------------------ |
| `VET-36`</br>`VET-42`</br>`VET-43`                                        | `vetapp/src/main/java/group825/vetapp/animal`                |
| `VET-24`</br>`VET-45`                                                     | `vetapp/src/main/java/group825/vetapp/animal/comments`       |
| `VET-25`</br>`VET-46`                                                     | `vetapp/src/main/java/group825/vetapp/animal/photos`         |
| `VET-38`                                                                  | `vetapp/src/main/java/group825/vetapp/animal/reminders`      |
| `VET-33`                                                                  | `vetapp/src/main/java/group825/vetapp/animal/request`        |
| `VET-15`                                                                  | `vetapp/src/main/java/group825/vetapp/animal/status`         |
| `VET-25`                                                                  | `vetapp/src/main/java/group825/vetapp/animal/weight_history` |
| `VET-31`                                                                  | `vetapp/src/main/java/group825/vetapp/treatment/diagnosis`   |
| `VET-28`</br>`VET-32`                                                     | `vetapp/src/main/java/group825/vetapp/treatment/protocol`    |
| `VET-19`</br>`VET-21`</br>`VET-22`</br>`VET-23`</br>`VET-40`</br>`VET-44` | `vetapp/src/main/java/group825/vetapp/user`                  |

Exception handling covering some basic exception was also completed and can be found in `vetapp/src/main/java/group825/vetapp/exceptions`.

## Testing

### Postman 

The Postman API platform was used to test all of the created API endpoints. The Postman request collections JSON files used for testing are located in the `testing/postman-requests` folder.

### Note

Postman collections file "StatusRemindersCommentsPhotos.postman_collection" includes the API request test to the following endpoints;
- `VET-15` (Status)
- `VET-38` (Reminders)
- `VET-45` and `VET-24` (Comments)
- `VET-25` and `VET-46` (Photos)

These were setup where each object generates a random UUID pertaining to a specific animal is created whenever the object is created. As a result, the postman requests that involve using a specific animal UUID in the path (GET, PUT, and DELETE requests where the UUID is included) do not work for new executions of the requests.

The procedure must be followed where a GET request is sent to get all objects for all animals. Select one UUID from the json response of all objects. Then use the new UUID in place of the current UUID at the end of the URL path.

Note that requests that check if a valid id was passed in the path were tested by deleting many characters from the UUID in the path.

### Example Test

A example test that walks through the following user stories can be can be viewed in `testing/example_test.pdf` 
- `VET-15` (Status)
- `VET-38` (Reminders)
- `VET-24` and `VET-45` (Comments)
- `VET-25` and `VET-46`(Photos)