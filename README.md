# SnitchManager
# Table of contents
- [About](#about)
  - [Technologies](#technologies)
    - [Backend](#tech-backend)
    - [Frontend](#tech-frontend)
  - [Database diagram](#database-diagram)
- [Screenshots](#screenshots)
- [Run project](#run-project)
  - [Backend](#run-backend)
  - [Frontend](#run-frontend)

# About
An application for collecting points for reporting on colleagues, inspired by a meme
## Technologies
### Backend <a name="tech-backend"></a>
- Spring Boot
- Gradle

### Frontend <a name="tech-frontend"></a>
- Vue.js
- Vuex

## Database diagram

![Database](../media/screenshots/database.png?raw=true)

# Screenshots

(click to enlarge)
| | | |
|:-------------------------:|:-------------------------:|:-------------------------:|
| ![Home Page](../media/screenshots/home_page.png?raw=true) | ![Home Page Logged](../media/screenshots/home_logged.png?raw=true) | ![Login Register](../media/screenshots/login_register.png?raw=true) 
| ![Denunciation list - user](../media/screenshots/denunciation_list_user.png?raw=true) | ![Denunciation list - search](../media/screenshots/denunciation_list_search.png?raw=true) | ![Denunciation list - pagination](../media/screenshots/denunciation_list_pagination.png?raw=true) 
| ![Denunciation list - admin](../media/screenshots/denunciation_list_admin.png?raw=true) | ![Types list](../media/screenshots/types_list.png?raw=true) | ![Ranking](../media/screenshots/ranking.png?raw=true) 
| ![Manage roles](../media/screenshots/manage_roles.png?raw=true) | ![Manage roles - popup](../media/screenshots/manage_roles_popup.png?raw=true) |  


# Run project
## Backend  <a name="run-backend"></a>
You'll need:
- Java (at least Java 8, tested with Java 11)
- Gradle
- Any relational database

Steps:
1. Create database
2. Create `application_properties` file inside `snitch-backend\api\src\main\resources` with

```
spring.jpa.hibernate.ddl-auto = update
spring.jpa.show-sql = false
spring.datasource.url = #DB ADDRESS
spring.datasource.password = #DB PASSWORD

#SECURITY
snitch.app.jwtSecret=snitchSecretKey
snitch.app.jwtExpirationMs=86400000

snitch.app.adminEmail= #INITIAL ADMIN EMAIL

#Email configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username= #EMAIL FOR IN-APP EMAIL NOTIFICATION SYSTEM
spring.mail.password= #EMAIL PASSWORD
spring.mail.properties.mail.smtp.auth=true;
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
```

(After first run tables inside database will be created and email with initial admin password will be sent to email given in `snitch.app.adminEmail`)

3. Inside `snitch-backend` directory run command
```
gradle bootRun
```
or use IDE

## Frontend <a name="run-frontend"></a>
You'll need
- node
- npm

Steps:
1. Inside `snitch-frontend\etc` set `VUE_APP_BASE_URL` to match your backend address
2. Inside `snitch-frontend` directory run commands

(Only before first run)
```
npm install
```
To run app
```
npm run serve
```

or use IDE