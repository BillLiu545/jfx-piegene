# jfx-piegene
In this repository is a JavaFX project that simulates chemical composition of a gene using a pie chart. There is functionality to add and remove chemical bases to modify the existing composition.

# How does it work?
When booting the application up, the user sees an empty pie chart with no elements (as chemical bases). There are buttons to add and remove existing chemical bases - if a base does not exist in the pie chart, it is added as a new item in the observable list. If the base already exists in the observable list, the existing base item in the list is modified by incrementing its value property. Similarily, in removing a chemical base, an existing base item in the list is modified by decrementing its value property - if the value is decremented to 0, it is removed. In the menu is a function to reset the pie chart as empty again.
