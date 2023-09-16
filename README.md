# biz-expense-report
A Java Spring Boot web application to calculate a pivot table for tax returns, given an Excel of raw data transactions
# Technologies Used
* Java
  * Spring Boot
  * Mockito
* HTML
  * Thymeleaf
* MySQL
* Maven
# Functionality
* Provides a web application and graphical interface for users to upload, display, and edit raw data
* Utilizes browser cookies to maintain a user's tax season/"work space"
* Calculate a summary table from uploaded data for a given tax season
* Persists user uploaded data in a MySQL database
# Usage
The application consists of buttons and editable text/number fields. To use:
* Navigate to the home page and select a tax season id
  * This sets a browser cookie for the rest of the session; all data will be taken from this tax season
  <picture>
  </picture>
* Upload data through the upload page; data must be in an Excel file
  <picture>
  </picture>
* Display the data for a given tax season
  <picture>
  </picture>
* Edit individual transactions if necessary

  <picture>
  </picture>
* Calculate and display the summary table for a given tax season
  <picture>
  </picture>
