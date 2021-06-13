# guevara-kumucodingchallenge

Itunes-API Application

## Room Persistence
I've implemented the single source of truth to update the UI. After the API request it will add the data to the DB using 
```ResultsListDao.insertSearchResults```
Then it will get the results from the DB using 
```ResultsListDao.getSearchResults```
which is in the LiveData that will be observed and populate the RV.


## Design Pattern
I used MVVM, upon graduating and getting my first job. I know that Android development is the best for me so when an Android job offered me I accepted it.
Upon working, I didn't know about MVVM + Dagger etc. At first it was hard. But using it and understanding it gave me the comfort and speed in creating applications.
Studying MVVM taught me that seperation of view and view model is important and reducing the risk of outdated views. Also LiveData is best suited for MVVM and Room which is best in
persisting data.

## From the developer
Hi Kumu Philippines! This is my coding challenge I hope I made the right application and got into your expectations. I've just made a simple app and followed the instructions. 
Hope I did good. Thank you!
