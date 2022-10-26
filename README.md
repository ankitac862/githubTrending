# Github Trending Repo

Android App that lists all the daily repositories that are trending in Github


# API

Since there is no official API for Trending Repositories (it is one of the internal GitHub API’s), I have decided to use https://gh-trending-api.herokuapp.com

# Introduction

The app consists of single fragment (MainFragment) that displays the trending repositories from Github

### Functionality

#### Phase 1

Fetch the trending repo from API using retrofit and store in RoomDb with refresh timestamp

Provides Offline support using Room DB once data is fetched 

Provides Swipe to Refresh that force  to refreshes repositories from API in case of success and from RoomDb in case of API fail

if user click on item it's position is maintained

#### Phase 2

Search repositories by username

### Architecture Used

MVVM with LiveData

### Libraries

- Android Support Library
- Android Architecture Components
- Swipe Refresh Layout
- Facebook Shimmer Library for shimmer loading effect
- Retrofit and OkHttp for API
- Room for offline storage
- Kotline coroutines
- Koin for dependency injection
- Glide for image loading


