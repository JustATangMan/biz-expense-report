# biz-expense-report
A Java Spring Boot web application to calculate a pivot table for tax returns, given an Excel of raw data transactions
# Functionality
* Provides a web application and graphical interface for users to upload, display, and edit raw data
* Utilizes browser cookies to maintain a user's tax season/"work space"
* Aggregates expenses based on category, business, and tax season to create and display a pivot table
* Persists user uploaded data in a MySQL database
# Usage
The application consists of buttons and editable text/number fields. To use:
* Navigate to the home page and select a tax season id
  * This sets a browser cookie for the rest of the session; all data will be taken from this tax season
 <picture>
  <img alt="home" src="https://github.com/JustATangMan/biz-expense-report/blob/main/docs/home.jpg">
 </picture>
* Upload data through the upload page; data must be in an Excel file
 <picture>
  <img alt="upload" src="https://github.com/JustATangMan/biz-expense-report/blob/main/docs/upload.jpg">
 </picture>
* Display the data for a given tax season
 <picture>
  <img alt="display" src="https://github.com/JustATangMan/biz-expense-report/blob/main/docs/display.jpg">
 </picture>
* Edit individual transactions if necessary
 <picture>
  <img alt="edit" src="https://github.com/JustATangMan/biz-expense-report/blob/main/docs/edit.jpg">
 </picture>
* Calculate and display the summary table for a given tax season
 <picture>
  <img alt="summary" src="https://github.com/JustATangMan/biz-expense-report/blob/main/docs/summary.jpg">
 </picture>
# Technologies Used
* Java
  * Spring Boot
  * Mockito
* HTML
  * Thymeleaf
* MySQL
* Maven
