# Hiking Trails

This was a final project that I created for CSE218 using common data structures. The app was meant to mimic the popular [*AllTrails*](https://www.alltrails.com/) website.

The project itself was very last minute and rushed. At the time of making this project, I found it to be very difficult, and a little buggy. Images and pictures don't load properly. I'll possibly update the files in the future to ensure those issues are fixed, but for now it is what it is.

Below is a description of the containers/data structures I chose to use for the project. The portion was written on 12/14/23.

# 12/14

`UserBag`:
The data structure I chose to hold all the users was a TreeSet since searching will be a lot more crucial than adding or removing and it'll really bring that O(logN) performance along as well.

`TrailBag`:
Initially I had planned to use a HashMap in order to store all of the trails imported, however, when it came to searching, the entire structure of a HashMap would be destroyed. A LinkedList seemed the best since it provided the best insertion and deletion time complexities (O(1)). To account for the constraint, a constant variable was created in order to ensure no more than 50000 trails can be added. The Stream class was used for searching, and no matter what data structure it may be, the performance is always the same.

`ReviewBag`:
ReviewBag uses a TreeSet to manage all of the reviews inserted. Since the data is going too be chronologically managed, the data structures ability to create unique timeframes seemed the best. Users may also want to have the ability to search for a review, so searching will be very efficient.

`HikingHistory`:
HikingHistory also implements the same features as the ReviewBag, except it manages hiking entries. Again, searching is another crucial factor for users, so the performance that a TreeSet provides best fit this situtation.
