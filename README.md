
Course Registration
POST http://localhost:8089/api/courses
Content-Type: application/json
{
  "courseName": "Introduction to Spring Boot",
  "courseDescription": "A comprehensive introduction to Spring Boot framework"
}
Instructor Registration
POST http://localhost:8089/api/instructors
Content-Type: application/json
{
  "firstName": "Mahesh",
  "lastName": "Kasala",
  "email": "cvlakshminarayana@gmail.com"
}

Student Registration
POST http://localhost:8089/api/students
Content-Type: application/json
{
  "firstName": "Venkata",
  "lastName": "Chenna",
  "email": "cvlakshminarayana@gmail.com",
  "phoneNumber": "1234567890",
  "address": "Tallapaka"
}
Student Enrollment into course
POST http://localhost:8089/api/enrollments
Content-Type: application/json
{
  "student": {
    "studentID": 1
  },
  "course": {
    "courseID": 1
  },
  "enrollmentDate": "2024-02-07"
}
Student Enrollment Verification
GET http://localhost:8089/api/enrollments/1
