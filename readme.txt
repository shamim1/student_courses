Public GitHub repo: https://github.com/shamim1/student_courses

1.
Please create some students. At this moment only Id defines if the student is unique or not. It can be enhanced in the future.
We generally want to make sure that no student with the "same first and last name and birth date exists twice in the database".
API POST call: http://{{host}}/v1.1/students
Example API POST call: http://localhost:8080/v1.1/students
Sample Body:
{
    "firstName": "first1",
    "lastName": "last1"
}

2.
Then please create some courses. At this moment only Id defines if the course is unique or not. It can be enhanced in the future.
There can be many factors to define the uniqueness of a course.
Generally "semester and courseCode (and Teacher if needed)" can be used to define the uniqueness of a course for a "semester".
I didn't go as far as creating courses and then creating course_offering tables due to time limitations.
API POST call: http://{{host}}/v1.1/courses
Example API POST call: http://localhost:8080/v1.1/courses
Sample Body:
{
    "courseName": "courseName1",
    "courseCode": "courseCode1",
    "courseDescription": "courseDescription1"
}

3.
Then you can enroll a student in a course.
API POST call: /courses/{courseId}/enroll/{studentId}
Example API POST call: http://localhost:8080/v1.1/courses/1/enroll/1


4.
"Which courses a student has taken" and "Which courses a student has not taken" are implemented using a single
API GET call: /students/{studentId}/courses
Example API GET call: http://localhost:8080/v1.1/students/1/courses

They are related to each other. So, it makes sense to add them together.
Some, APIs calls were not implemented due to time limitations. No input validations were added due to time limitations.




Other notes:
------------

1.
API versioning is implemented. I am starting with 1.1

2.
Using increment by 1 for the SEQUENCE generator. The default is 50 which seems a bit high for me. I probably would prefer 10. This will be considered as enhancement. I did most of it.
I just have to change the value from 1 to 10 if required.


3.
Passwords can be stored as secret. I couldn't do it as I was initially having some problem with my local docker setup.

4.
The spring boot docker image is a "layered" image. It is very beneficial to create docker images in such a way.
We can add a maven docker image to "automate" the build process. It's an enhancement we can do in the future.
All the docker images are based on "alpine linux" and have no vulnerabilities.
I am using Ubuntu 22.04 to deploy the docker images (using "docker desktop") and run my tests. They are working perfectly fine.

5.
I am using PostgreSQL text DataType and then adding CONSTRAINT afterward. It has a lot of benefits this way.

6.
Docker images are with default "latest" tags. If needed, we may push images with different tags for "docker image" version control. I am considering this as an enhancement to do in the future.

7.
ORM mapping was used to simplify the development process in the future. It does have some initial overhead. "dto" classes were not used to save time. "version" column wasn't implemented as the current plan is to create and delete records (not update). It can be easily added if required in the future.
