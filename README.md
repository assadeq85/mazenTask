# mazenTask
Please write automated tests using Selenium WebDriver and using testNG or jUnit for given test scenarios:

Feature: Unlogged customer is able to search for a job.

Scenario: Search for a job matching given criteria Given Customer is on "https://akamaicareers.inflightcloud.com/" When Customer specifies “Find Your Career”: Senior Software Development Engineer in Test And "Filter by country": Poland Then Any job offers are found

Feature: Customer is notified when no offers match given criteria

Scenario: Search for a job matching given criteria Given Customer is on "https://akamaicareers.inflightcloud.com/" When Customer specifies “Find Your Career”: XXX Then Notification about no offers found is displayed: "We found 0 jobs based on your search criteria"

The exercise consists of:

creating a new project in a public repository, like GitHub
choosing tools / libraries needed for writing the tests. Required are java 8+, Selenium WebDriver, and maven. All the rest can be used freely
creating a Test Automation Framework that allows executing the steps needed to perform the tests
writing Automated tests covering provided Test scenarios "
