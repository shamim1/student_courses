
1.
Please create some students.
API POST call: http://{{host}}/v1.1/students
Example API POST call: http://localhost:8080/v1.1/students
Sample Body:
{
    "firstName": "first1",
    "lastName": "last1"
}

2.
Then please create some courses.
API POST call: http://{{host}}/v1.1/courses
Example API POST call: http://localhost:8080/v1.1/courses
Sample Body:
{
    "courseName": "courseName1",
    "courseCode": "courseCode1",
    "courseDescription": "courseDescription1"
}

3.
Then you can enroll a student to a course.
API POST call: /courses/{courseId}/enroll/{studentId}
Example API POST call: http://localhost:8080/v1.1/courses/1/enroll/1


4.
"Which courses a student has taken" and "Which courses a student has not taken" are implemented using a single
API GET call: /students/{studentId}/courses
Example API GET call: http://localhost:8080/v1.1/students/1/courses

They are related to each other. So, it makes sense to add them together.
Some, APIs calls where not implemented due to time limitation.




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
We can add a maven docker image to "automate" the build process. It's am enhancement we can do in the future.
All the docker images are based on "alpine linux" and has no vulnerabilities.
I am using Ubuntu 22.04 to deploy the docker images (using "docker desktop") and run my tests. They are working perfectly fine.

5.
I am using PostgreSQL text DataType and then adding CONSTRAINT afterward. It has a lot of benefits this way.

6.
Docker images are with default "latest" tags. If needed, we may push images with different tags for "docker image" version control. I am considering this as an enhancement to do in the future.

7.
ORM mapping was used to simplify the development process in the future. It does have some initial overhead. "dto" classes were not used to save time. "version" column wasn't implemented as the current plan is to create and delete records (not update). It can be easily added if required in the future.
