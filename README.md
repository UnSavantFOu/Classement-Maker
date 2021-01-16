
# Classement Maker
## Presentation
This program is a national rank calculator based on french fencing points formula. It uses jsoup web scraping. It takes 27 inputs like the inscription url or the names column to run. The program will do calculs during around 20 seconds before exporting the updated national rank.

## Advantages
The main goal of this program was to calculate the french national fencing rankings just after the competition end (because the French federation took around 1.5 week to update the ranking). Furthermore, it’s fully programmable : if the column  with names changes from the first place to the second, when we use the program we just have to change the input. Finally, It’s quite accurate because there is only a 3% error between reality and calculation (due to the fact that some athletes have their masked identity).

## Disadvantages
Firstly, the program calculate during around 20s non-stop, and it could be improve by giving in advance the current ranking, so he parses the site before the competition. Secondly, even though it is programmable, it is like even very difficult to use : there are 27 inputs, so we have to enter them in the right order.  Finally, it’s scraping, so if the site changes, the program will be unusable (this is the case at this time, the site has changed).

## Conclusion
It was just a program that allowed me to discover web-scraping with java. To be able to reuse it one day, I will have to re-develop a program that parses a pdf this time.
