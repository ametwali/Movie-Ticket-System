SWING TIPS AND FORM SET-UP (NICK LOR)

THERE ARE CERTAIN STEPS THAT MUST BE DONE TO MAKE INTELLIJ FORMS WORK PROPERLY:
    - Make sure to make the name the head JPanel to 'mainPanel'
    - Paste in boilerplate constructor code from other forms and modify to your liking
    - add actionlisteners, other view specific functions you need, etc.
    - Make sure the form class extends JFrame

IF YOU WANT A JTABLE WITH HEADERS TO SHOW PROPERLY:
    STEP 1 - CREATE A JTABLE WITHIN A JSCROLLPANE, WITHIN A JPANEL IN INTELLIJ FORM DESIGNER
    STEP 2- SELECT EACH COMPONENT, AND CHECK "CUSTOM CREATE OPTION" IN OPTIONS PANE
    STEP 3 - POPULATE createUIComponents() IN CORRESPONDING JAVA CLASS
    STEP 3A - POPULATE tableModel WITH COLUMNS YOU WANT
    STEP 3B - CONSTRUCT ALL OBJECTS
