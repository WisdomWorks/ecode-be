CREATE TABLE [codee].[User](
    userId VARCHAR(70) PRIMARY KEY NOT NULL,
    [name] VARCHAR(70) NOT NULL,
    email VARCHAR(100) NOT NULL,
    [password] VARCHAR(100) NOT NULL,
    [role] VARCHAR(20) NOT NULL,
);
CREATE TABLE [codee].[Course](
    courseId VARCHAR(70) PRIMARY KEY NOT NULL,
    courseName VARCHAR(70) NOT NULL,
    mainTeacher VARCHAR(70) NOT NULL,
    semester VARCHAR(20) NOT NULL,
    dateCreate BIGINT NOT NULL,
    [description] VARCHAR(MAX),
    createBy VARCHAR(70) NOT NULL
);

CREATE TABLE [codee].[Group](
    groupId VARCHAR(70) PRIMARY KEY NOT NULL,
    courseId VARCHAR(70) NOT NULL,
    groupName VARCHAR(70) NOT NULL,
    course VARCHAR(70) NOT NULL,
    dateCreate BIGINT NOT NULL,
    [description] VARCHAR(MAX),
    numberMember INT NOT NULL,
    FOREIGN KEY (courseId) REFERENCES Course(courseId)
);

CREATE TABLE [codee].[Topic](
    topicId VARCHAR(70) PRIMARY KEY NOT NULL, 
    courseId VARCHAR(70) NOT NULL,
    topicName VARCHAR(70) NOT NULL,
    [description] VARCHAR(MAX),
    createBy VARCHAR(70) NOT NULL,
    isPublic BIT NOT NULL,
    FOREIGN KEY (courseId) REFERENCES Course(courseId)
);

CREATE TABLE [codee].[StudentEnrollment](
    studentId VARCHAR(70) NOT NULL,
    courseId VARCHAR(70) NOT NULL,
    dateEnroll BIGINT NOT NULL,
    [description] VARCHAR(MAX),
    PRIMARY KEY (studentId, courseId),
    FOREIGN KEY (studentId) REFERENCES [User](userId),
    FOREIGN KEY (courseId) REFERENCES Course(courseId)
);

CREATE TABLE [codee].[TeacherEnrollment](
    teacherId VARCHAR(70) NOT NULL,
    courseId VARCHAR(70) NOT NULL,
    dateEnroll BIGINT NOT NULL,
    [description] VARCHAR(MAX),
    PRIMARY KEY (teacherId, courseId),
    FOREIGN KEY (teacherId) REFERENCES [User](userId),
    FOREIGN KEY (courseId) REFERENCES Course(courseId)
);

CREATE TABLE [codee].[Material](
    materialId VARCHAR(70) PRIMARY KEY NOT NULL,
    topicId VARCHAR(70) NOT NULL,
    materialName VARCHAR(70) NOT NULL,
    [url] VARCHAR(MAX) NOT NULL,
    createDate BIGINT NOT NULL,
    [description] VARCHAR(MAX),
    isPublic BIT NOT NULL,
    FOREIGN KEY (topicId) REFERENCES [Topic](topicId)
);

CREATE TABLE [codee].[Exercise](
    exerciseId VARCHAR(70) PRIMARY KEY NOT NULL,
    topicId VARCHAR(70) NOT NULL,
    title VARCHAR(70) NOT NULL,
    [description] VARCHAR(MAX),
    [password] VARCHAR(100) NOT NULL,
    dateCreate BIGINT NOT NULL,
    startTime BIGINT NOT NULL,
    dueTo BIGINT NOT NULL,
    [type] INT NOT NULL,
    isPublic BIT NOT NULL,
    FOREIGN KEY (topicId) REFERENCES [Topic](topicId)
);

CREATE TABLE [codee].[Submission](
    exerciseId VARCHAR(70) NOT NULL,
    studentId VARCHAR(70) NOT NULL,
    submission VARCHAR(MAX) NOT NULL,
    score DECIMAL(5,2) NOT NULL,
    dateSubmit BIGINT NOT NULL,
    dateGrade BIGINT NOT NULL,
    Reviewable BIT NOT NULL,
    [description] VARCHAR(MAX),
    PRIMARY KEY (exerciseId, studentId),
    FOREIGN KEY (exerciseId) REFERENCES Exercise(exerciseId),
    FOREIGN KEY (studentId) REFERENCES [User](userId)
);

CREATE TABLE [codee].[MaterialType](
    materialTypeId INT NOT NULL AUTO_INCREMENT,
    materialTypeName VARCHAR(70) NOT NULL
);

CREATE TABLE [codee].[ExerciseType](
    exerciseTypeId INT NOT NULL AUTO_INCREMENT,
    exerciseTypeName VARCHAR(70) NOT NULL
);