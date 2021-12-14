# ENSF 607 - Term Project

**Project:** Veterinary Medicine Web Application

**Group 825**
- Timothy Mok
- Aron Saengchan
- Yong Jun (Jimmy) Zhu

## Project Information

### Overview
In collaboration with the Veterinary School of Medicine, the objective of this project is to create a full stack web application to use in the daily operations of their department. It serves to address three important areas - user management, animal management, add treatment process. This application will be primarily used by administrators and staff that service the animals. As such, an interface that meets the requirements of being both functional and user-friendly needs to be developed.

### Process
The first four out of five sprint for this project have been completed:

1. [Developing epics and user stories](https://uofeng607825.atlassian.net/jira/software/projects/VET/boards/1/roadmap)
2. [Designing wireframes](https://www.figma.com/file/xP4gjKlYZXzneQGNvUFXYK/ENSF-607---Term-Project-Wireframes?node-id=0%3A1)
3. Designing the API
4. Frontend and Backend

The contents of this repository address the deliverables completed in the third sprint and are described in the following sections.

## API Endpoints

API endpoints were developed for each user story that required one. The table below lists the file path of the API package for each associated user story code.

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

Exception handling covering some basic exceptions were also completed. The associated files can be found in `vetapp/src/main/java/group825/vetapp/exceptions`.

## Testing

### Postman 

The Postman API platform was used to test all of the created API endpoints. The Postman collections containing the tested requests are located in the `testing/postman-collections` folder in JSON format.

#### Note

The following Postman collection files includes the API request test to the following endpoints:
- Status - `testing/postman-collections/status.postman_collection.json` 
- Reminders - `testing/postman-collections/reminders.postman_collection.json` 
- Comments - `testing/postman-collections/comments.postman_collection.json` 
- Photos - `testing/postman-collections/photos.postman_collection.json` 

These were setup where each object generates a random UUID pertaining to a specific animal that is created whenever the object is created. As a result, the Postman requests that involve using a specific animal UUID in the path (`GET`, `PUT`, and `DELETE` requests where the UUID is included) do not work for new executions of the requests.

The procedure must be followed where a `GET` request is sent to get all objects for all animals. Select one UUID from the JSON response of all objects, then use the new UUID in place of the current UUID at the end of the URL path.

Note that requests that check if a valid ID was passed in the URL were tested by deleting many characters from the UUID in the path.

There was a noticeable bug with the UUID library where a UUID is successfully created when only a few characters from the UUID is deleted in the path. If half of the characters of the UUID is deleted in the path, (an invalid id is passed) the UUID will not be created and the custom exception response will be returned.

Another bug with the endpoints are the `DELETE` request can be sent multiple times with the same UUID. This is because the ArrayList() has removed the object the first time and the subsequent requests are received successfully. All subsequent requests just delete nothing.

### Example Tests

Some example API endpoint tests that walk through the following user stories can be can be viewed in `testing/example_tests.pdf`. 
- Status (`VET-15`)
- Reminders (`VET-38`)
- Comments (`VET-24` and `VET-45`)
- Photos (`VET-25` and `VET-46`)


## Frontend & Backend

### Frontend
The pages for the front end were completed using the React framework. A page for each user story was developed as required. All pages were routed to their respective paths and some functionalities were added to assist with the general navigation of the application. All the files dedicated to the frontend portion of this project are located in `Front End/`. 

### Backend
The backend of this application was completed by connecting the API endpoints established in the previous sprint to a corresponding query for accessing information in the database. All user stories were addressed in this way and each endpoint call was tested once again using Postman. The files for the backend portion of this project can be found in `Back End/`. 

## Include the jar files for sending emails

Necessary jar files are included in a zipped folder in `Back End/email-files`