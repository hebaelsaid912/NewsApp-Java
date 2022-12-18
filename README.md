# NewsApp-Java


[![API](https://img.shields.io/badge/API-26%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=26)

NewsApp is an android app, this app for presenting the latest news feeds about Egypt and showing news from BBC news and the next web news. News app allows you to search for all news you need to read more about it. This application using the API from  https://newsapi.org/ 
## Code Installation

You can clone code and run it using :

``
  IDE : Android Studio
  Gradle Version : 7.0.2
  Compile Sdk : 31
  Java: 11
``

## Features
- Present Top Headlines news about Egypt on the top banner.
- List all news From BBC-News and The-Next-News.
- Search for all news you asked to read more.

## App ScreenShots 
 <table>
  <tr>
    <th>Home</th>
    <th>Details</th>
    <th>Details</th>
  </tr>
  <tr>
    <td><img src="https://user-images.githubusercontent.com/72816466/208300494-d2514291-53f1-4d88-aff2-f5cc60deaf3f.jpg" width="350"></td>
    <td><img src="https://user-images.githubusercontent.com/72816466/208300501-a26b621e-e540-4ce5-a222-ae66e72f833f.jpg" width="350"></td>
    <td><img src="https://user-images.githubusercontent.com/72816466/208300505-082c55f4-8ee4-46f5-9fdc-d8139a345867.jpg" width="350"></td>
  </tr>
 
</table> 


## Architecture
The following diagram shows all the modules and how each module interact with one another after. This architecture using a layered software architecture. 

![81968739-a8bec700-95d1-11ea-8682-48fe879c25ff](https://user-images.githubusercontent.com/72816466/201654156-543bf79d-f13c-441f-8f45-6797520ab877.png)

## Tech stack & Open-source libraries
- Minimum SDK level 26
- Java based
- Android Gradle plugin requires Java 11 to run.
- Architecture
    - MVVM Architecture ( DataBinding - ViewModel ).
    - Single Activity 
       - multiple Fragment handled together using Navigation.
- Retrofit2 & Gson - construct the REST APIs.
- LiveData - dispose of observing data when lifecycle state changes.
- RXJava  uses observable sequences to perform asynchronous and event-based programming.
- Glide for image loading
- Scalable Unit 
  - text size.
  - unit size.
- Dependency Injection (dagger-hilt).
