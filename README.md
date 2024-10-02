# Waddl REST API 

A RESTful API example for simple task management application with JAVA/. 
It provides the ability to mange task by sending an REST/HTTP request to the server.

## Feature List
* User can see all current tasks
* User can search task by id
* User can see delete task from list
* User can update task from list
* User can see task status

# Getting started
Go to Waddl homepage using the url:
`http://waddlbackend-env.eba-gmmyh7ye.eu-west-2.elasticbeanstalk.com/api/v1/tasks/health`


## API Endpoints
| Method | Url | Description |
|--------|:-----:| ----------|
| `GET`    | `/api/v1/tasks`    | get all task|
| `GET`    | `/api/v1/tasks/:id` | get detail about a task|
| `POST`   | `/api/v1/tasks `    | add a task|
| `PUT`    | `/api/v1/tasks/:id` | update detail about a task|
| `DELETE` | `/api/v1/tasks/:id` | delete detail about a task|
