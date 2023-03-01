# ensf-614

A few run notes for marker:
- Main.Java instantiates the GUIController which starts the program
- IMPORTANT: The forms were created using IntelliJ's Swing GUI Designer Tool, which only work when run from the IntelliJ IDE. Running our Main.java from another IDE will not work (VSCode for example)
- Database.sql file creates and inserts sample data upon which the Swing GUI application pulls data from
- Constants.java contains the MySQL connection parameters, which default to 'root' and '1234' for username and password
- There is a separate .jar file which has packaged everything up which is submitted in the dropbox which can also be run
