This is a sample app that I made when interviewing for Domain just over a year ago. I had to update a few things to get it to compile with today's toolchain but it's mostly untouched. It's a basic app that pulls data from an API, persists it to storage (SQLite) and displays it in a list.

At the time I was using the experimental Jack processor to help with annotation processing and lambdas - no Kotlin unfortunately! I couldn't use Realm and used a now-abandoned library called Sugar for as an ORM. Unfortunately it had the side-effect of not creating SQL tables when using instant-run, so you'll have to either build from the command-line or turn off instant-run to run it.

=========================================================

# Domain Bondi Properties take-home app

This is my submission for Domain's Android Proficiency Exercise. It calls an API on Domain's servers and displays the information in a list.

It uses an MVP architecture to help enforce single-responsibility and allow for easier testing. Heavily inspired by this [Android Boilerplate project](https://github.com/ribot/android-boilerplate). 

The two-pane layout is shown when the screen-width is >= 600dp.

## Compilation

This application uses the new [Jack Compiler](https://source.android.com/source/jack.html) to take advantage of annotation processing and lambdas. As such, JDK 8 is required.

## Libraries used

- **Android support**: For CardView, RecyclerView and other general support library classes
- **RxJava and RxAndroid**: Allowing reactive programming for retrieving data from the API and database
- **Picasso**: For aynchronous image loading and caching
- **Retrofit + OkHTTP**: For communication with API
- **Butterknife**: For more concise access to Views in a layout
- **Sugarrecord ORM**: For an easy interface with the database. I considered Realm but this was incompatible with the Jack compiler.
- **Eventbus**: For easy communication between elements and notifying parts of the application of events.

I considered using Dagger for dependency injection, but deemed it too much effort for an app of this size.
