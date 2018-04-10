Internet Programming III
Daniel Kaczynski
Final Project / Android Weather App

Link to my codes Github, was made after the app so it doesn't have changes recorded:
https://github.com/dankaczynski/ITAS274_Android_Project_theWeather_Dan.Kaczynski

Bugs/Broken Features:
Crashes on Startup

Permissions Used:
Internet Access

What I made:
My attempt of a weather app is set up to show data for the one city set in the location variable, it does update through the Yahoo Weather API and requires internet.  
It then takes the Weather Icon images that have numbers in their names and depending on the weather, the ID you grab from the API will be different and it will select a different image from the image folder to display.  
It also grabs the temperature as well as condition of the weather, such as Cloudy or Rainy, and displays the city it is currently showing data for by populating variables with the data after it has been parsed from the JSON
that Yahoo Weather uses in their API.

Errors:
I had some errors I couldn’t work out as it was saying things were pointing to null that from what I could see weren’t so although I tried adding local variables and changing parameters around to fix it but it didn’t end up working.  
I did start from scratch with this and had no starting code from any repositories.

https://i.imgur.com/Yaay2D0.png

Setting the City:
However, if you want to change the city the weather is tracking at the time, you have to change the location variable inside the code on WeatherActivity.Java

service.refreshWeather("Nanaimo, BC");

This variable is handed to the API as well as used in the case of an error to display an error message including the location specified.

A more feature complete version of what I was trying to do(that launches correctly) is available on the Github of the same guy that made the tutorials I used(DigitalPhantom),
however his tutorials don’t implement many of the features included in his github version.  His includes GPS locating, as well as a weekly forecast with settings for celsius or fahrenheit, 
there was no guide for this, and I was making a slightly simpler version of this.

https://i.imgur.com/L4bVl3X.png

https://github.com/DigitalPhantom/PhantomWeatherAndroid






References/ Tutorials Used:

https://github.com/dankaczynski/ITAS274_Android_Project_theWeather_Dan.Kaczynski

https://developer.yahoo.com/weather/

https://www.youtube.com/watch?v=FkT1kwtYSFU

https://www.youtube.com/watch?v=dUKJN_KCK6U

https://www.youtube.com/watch?v=gJ9Ny_J3tcM

https://www.youtube.com/watch?v=wAV2Uqv9KLo

https://developer.android.com/reference/android/widget/ImageView.html

https://developer.android.com/reference/android/os/AsyncTask.html

https://developer.android.com/guide/topics/ui/dialogs.html

https://github.com/DigitalPhantom/PhantomWeatherAndroid






