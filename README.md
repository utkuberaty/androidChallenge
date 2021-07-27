# androidChallenge

Sample Android meditation project created to show Android development skills. 

In this project is created in MVVM design architecture using with Kotlin. There are 3 fragment(ProfileFragment, HomeFragment, MusicDetailFrament) and 1 root activtiy(MainActivity). All fragments are controlling with Android Navigation component.

# Screens
## Profile Screen

Profile has 2 Input and 1 continue button. For validation;
### Username 
* Has minimum 2 character rule

### Password 
* Using regex for validate minimum 6 characters with at least 1 uppercase character, 1 number

## Home Screen

Home Screen has a nested scroll view, 2 recyclerView and banner. Banner is hideable according to response. One of the recyclerView using for listing meditations in horizontal. Other recyclerView using for listing stories in vertical. If one the contents is tapped it navigates Media detail screen.  

## Media Detail Screen

Media Detail screen is shows meditation's or story's text, title and date. And media detail screen has a media player to play sounds.

## What I Used 
* MVVM design architecture
* Koin for DI
* Coil for fetching images 
* Paper for saving data 
* Retrofit
* Kotlinx Serialization
* Navigation Component
* Coroutine
