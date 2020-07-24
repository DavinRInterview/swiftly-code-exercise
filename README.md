# About This Project
Hi, I'm Davin Rothschild, and this is the Code Exercise for my Swiftly interview. I'm going to use this readme to provide run instructions and to  give a rundown on the decisions I made in development, including architecture, algorithms, third party libraries, potential further work, and issues.

# How to Run
This code should be ready to install via Android Studio - just pull the code from GitHub (either via downloading the zip or cloning the repo) and open the project in the Android Studio IDE. Once Gradle syncs, you can run the application on your test device or emulator of choice via the IDE run commands. I can provide an APK for install if needed by anyone.

Note, this application has a launch screen with a toggle for local data versus remote (it defaults to local data off). Set that to whichever you want, then click the button to launch the feature screen.

# Process Notes
Quick note about artifacts: there may be a couple files that could/should be ignored because my .gitignore file got messed up at one point

Launch screen and Manager Specials screen: Per conversation with Karen, I confirmed Manager Specials were a feature, rather than separate app. Done to better imitate scenario in which it will exist, and also facilitate screen for testing flags and settings. Can also be extended to support further related screens for testing. On border of YAGNI, but very little cost for good return. I also added a toggle on the launch screen for local debug data, which allowed me to develop UI separate from remote dependencies and verify small changes. I'd likely expand the testing toggles if I had further needs. The launch screen looks messy, but I'm not that worried about it since it's just for testing purposes.

One activity and two fragments: Current Android standard, while debated, is to use fragments rather than not, and simplifies Launch and Specials screen. Also allows for use of specials screen in other configurations (say side-by-side with purchase screen) on tablet, or in other flows, and is very easy to transition if a separate Specials activity is needed (just load fragment).  Once again with YAGNI, but essentially no cost since current Android Studio project creation default is activity and two fragments. Note I progressed with development to support the fragment being in side-by-side orientation.

Architecture: I deliberated on the Architecture (and use of DI) for a bit, and chose to go with MVVM with a repository. Given the ViewModel is right now one-to-one with the view, it's pretty much identical to a presenter, except data flow is only going one way (which is easy in this case because there's no interaction). All the preprocessing logic is in the ViewModel. The repository allowed me to create a Local data source for development, and can be used for data persistence if desired later.

RecyclerView and component views: RecyclerViews are the current Android standard for longer dynamic data displays, and are ideal for memory management with longer list of scrollable items. The individual Manager Special elements are ConstraintLayouts, but the rows are LinearLayouts. It might be possible to optimize this by changing to a different layout format, but it would require some thought on my end.

Third Party Libraries:
* RxJava: Chose Reactive solution due to clean data handling; used RxJava due to familiarity, as well as conversation with Ryan in which he mentions his familiarity and use (so assuming it is likely used in the current application).
* Retrofit: Android standard network handling solution, easy to implement
* Dagger2: Android Standard for DI. Chose to use DI to reduce tight dependency relations, and bring it more inline with the application it would likely become a part of, although given there are so many ways to implement dagger it would likely require changes (I'm currently using a deprecated method, which I'd want to change if I were to move forward).
* Glide: The current Android standard for image loading, more responsive that Picasso and has current support.
* Gson: The standard, although Moshi is used a lot too. I picked over Moshi since Gson is adequate to our needs here, and it's cheaper to implement.
* Mockito: Android standard mocking library for tests, is fairly lightweight and does what I neede dit to.

Algorithm: I clarified with Karen that the expected behavior is "starting at a given item, if the subsequent items don't add up exactly to CanvasUnit, the item should be on a line by itself". So for Canvas size 16 and items 4 4 4 4 5, the 4 4 4 4 all go on a line together and the 5 goes by itself, but if you had 4 4 4 3 5, the initial 4 will be alone and the 4 4 4 3 5 will be on a line together. Given this, a greedy algorithm works well: with a nested loop, iterate forward from the current location - if you add up to CanvasUnit, put those items on a line; if you pass CanvasUnit (or make it to the end of the data set), put just the current one in, then move forward. Worst case is n^2. That's what I've implemented here.
Note: per the feedback from Ryan, I've updated the algorithm to fill up to CanvasUnit. 

Preprocessing data: To properly capitalize off of the RecyclerView's ability to free up memory, the data needs to be grouped as desired for display. Due to the greedy algorithm processing from the start of the data set, without preprocessing the grouping algorithm would need to be rerun from the top for every item removed and readded to the RecyclerView.

Feature Resizing: whooole lotta' math in the adapter, which I'd usually want to avoid, but it really is all display and needs UI-level access. The crux of the "logic" really is that for all view params - image size, font size, margins, etc - I'm sizing down based on whichever of the two dimensions, width or height, is smallest. This allows the elements to be resized such that they're functional, but also not break from the mock's design. Also, to account for potential long names, I've limited the name section to two lines, ellipsized at the end if truncation is needed.

Bad data checks: I added a simple data validation for the width and height. Most potential issues - wrong data types, nulls - are all accounted for either via Gson or Kotlin's null safety behaviors, and empty strings are fine both for the TextViews and Glide image loader, they just won't show. Wrong data on height and width can really mess things up, though, since they are involved in so much logic. Both height and width need to be larger than 0, and width needs to be less than or equal to canvasUnit, so I verify that for every data element, removing any that don't satisfy it.

Localization: I only had a pair of local strings, the header and the alert for failure to fetch. I localized to Korean, but unfortunately I don't speak Korean, so I used Google Translate, which is an abomination and I wouldn't let an app go to production like that. That said, it works for a place-holder. What I've seen done in the past is create a strings file specifically for unfinalized strings, which would definitely be a good tool in cases like there where a professionally translated string hasn't been provided.

Testing: I implemented UTs to cover all of the ViewModel logic happy paths, which should ideally have been the bulk of my logic. The Other large code class is the RecyclerView's Adapter (or rather the Adapter's ViewHolder), but it's a challenge - fundamentally a UI class, and the "logic" is mostly arithmetic for the most part all in the ViewHolder callbacks. I'll continue to think about how I can implement tests there, it's set up for them. Further edge case testing, some UI testing, and end-to-end would all be valuable.

Issues: While the elements resizing dynamically works, sooner or later when things get small enough it won't render well (honestly the small elements were on the edge of this as it was, and might be more so on smaller devices, like watches). Also, people who use different font sizes (particularly bigger ones for accessibility) are not going to get a very pretty experience with this right now.

Potential further effort: There are several things not currently handled here, or not handled the way I'd prefer they were. As of right now there's no data persistence, which might not be a huge deal depending on what the screen is being used for, but could be useful. Tied to that, right now there's no handling for screen rotation, which is definitely something I'd look to handle going forward. I mentioned the testing I'd like to add, and the need for proper copy and localization, but I also did a simple accessibility test on it, and, navigation through the list doesn't work well right now - recyclerviews sometimes struggle with that anyway, but particularly due to the structure here it will navigates horizontally through elements on the line fine, but not down the screen. I would definitely want to fix that if I were to continue with it. I'd also like to change the local data source (if kept in in this format) so it's pulling from the raw file I've got set up, rather than a string in the file itself. It's a small change, but I prioritized focusing other things to get best value from the time spent.
Update: had few minutes, so updated local data to read from raw. Much happier with it.

# License
MIT License

Copyright (c) 2020 DavinRInterview

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
