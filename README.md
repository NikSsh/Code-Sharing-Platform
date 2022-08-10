# Code-Sharing-Platform
The Spring boot platform, which has an API and web interface, allows you to share code with friends or teammates

## Description

There are following endpoints:

API: 
- POST /api/code/new with the following body:

{
    "code": "Secret code",
    "time": 5000,
    "views": 5
}

Response: { "id" : "7dc53df5-703e-49b3-8670-b1c468f47f1f" }, by id from response you can get your code

- GET /api/code/2187c46e-03ba-4b3a-828b-963466ea348c 

Response:

{
    "code": "Secret code",
    "date": "2020/05/05 12:01:45",
    "time": 4995,
    "views": 4
}

- GET /api/code/latest, it returns the last 10 uploaded unrestricted code snippets

Response:

[
    {
        "code": "public static void ...",
        "date": "2020/05/05 12:00:43",
        "time": 0,
        "views": 0
    },
    {
        "code": "class Code { ...",
        "date": "2020/05/05 11:59:12",
        "time": 0,
        "views": 0
    }
]

Web interface: 

- GET / , represents home page
    ![main](https://user-images.githubusercontent.com/71446610/183884992-638ce9e7-d1af-4d6a-bb7f-131f18f128df.PNG)

- GET /code/new, allows you to create snippet and set some restrictions
![create](https://user-images.githubusercontent.com/71446610/183885211-327b2d1a-a080-4fee-b116-3777c1e522ba.PNG)

After pressing sumbit button you will get id of saved code snippet in json format

![Снимок](https://user-images.githubusercontent.com/71446610/183885434-d352d99b-9fe8-40ef-96c8-4eb6bc7a65c2.PNG)

- GET /code/yourid, if some of the restrictions have been violated, the page will return a 404 http code (NOT FOUND)
![Concrete](https://user-images.githubusercontent.com/71446610/183885781-dd633177-3559-4986-9a6d-0558ac459076.PNG)

- GET /code/latest, if there are no code snippetes without any restrictions, the page will return a 404 http code (NOT FOUND)

![Latest](https://user-images.githubusercontent.com/71446610/183886235-5a51df94-1d65-404d-a612-49762bceebee.PNG)




