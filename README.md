# MyFavoriteSports

![Home Screen](screenshots/home.jpg "Home")
![Detail Screen](screenshots/detail.jpg "Sport Detail")
![Favorite Screen](screenshots/favorite.jpg "Favorite Screen")

What I implemented in this app:
- Continuous Integration Tool: CircleCi 
[![toharifqi](https://circleci.com/gh/toharifqi/MyFavoriteSports.svg?style=svg)](https://circleci.com/gh/toharifqi/MyFavoriteSports)
- Clean Architecture (to seperate presentation, domain, and data layer)
- Reactive programming: using Coroutine Flow
- Dependency Injection (to make loosly coupled codes): Koin library
- JSON Animation: Lottie animation (https://lottiefiles.com)
- Leak memory checker: LeakCanary
- Security: database encryption (using SQLCipher), certificate pinning for server connection (using OkHttp SHA256 certificate pinner), obfuscation (using Proguard)
- API: https://thesportsdb.com

Latest update on branch: capstone_final
