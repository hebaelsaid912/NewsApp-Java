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
![photo_2022-11-07_20-04-04](https://user-images.githubusercontent.com/72816466/201654580-9ce4651b-9fc8-4b26-a9ee-532e6ef541e0.jpg)
![photo_2022-11-07_20-04-05](https://user-images.githubusercontent.com/72816466/201654608-6aca35c7-2b0c-41f5-8b22-d748cd892a05.jpg)
![photo_2022-11-07_20-04-05 (2)](https://user-images.githubusercontent.com/72816466/201654629-f2e4c683-7e89-4c9c-bf60-35660e318533.jpg)
![photo_2022-11-14_14-01-05](https://user-images.githubusercontent.com/72816466/201655386-99b46416-1742-469e-aff3-c31fb8896e3f.jpg)
![photo_2022-11-14_14-01-09](https://user-images.githubusercontent.com/72816466/201655398-a8a9c054-af92-424b-9d8b-ce7d509c7293.jpg)

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
- RXJava  uses observable sequences to perform asynchronous and event-based programming.
- Glide for image loading
- Scalable Unit 
  - text size.
  - unit size.
- Dependency Injection (dagger-hilt).
