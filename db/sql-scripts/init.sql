

-- -- Need to add check about if the database exists. I will do it later. Now, I am assuming
-- -- there is no university_courses database as the base alpine image don't have that database.
-- -- This is important as the API will connect with this database.
-- CREATE DATABASE university_courses;


DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = 'public' AND
        table_name = 'student') THEN

        -- Create Sequence
        CREATE SEQUENCE IF NOT EXISTS student_seq start with 1 increment by 1;

        -- Create table
        CREATE TABLE student (
            id bigint NOT NULL,
            first_name text DEFAULT 'UNKNOWN' NOT NULL,
            last_name text DEFAULT 'UNKNOWN' NOT NULL,
            created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
            created_by text DEFAULT 'User1' NOT NULL,            
            primary key (id)
        );

        ALTER TABLE student ADD CONSTRAINT
            student_first_name_len CHECK (length(first_name) < 256);
        ALTER TABLE student ADD CONSTRAINT
            student_last_name_len CHECK (length(last_name) < 256);
        ALTER TABLE student ADD CONSTRAINT
            student_created_by_len CHECK (length(created_by) < 256);
    END IF;


    IF NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = 'public' AND
        table_name = 'course') THEN

        -- Create Sequence
        CREATE SEQUENCE IF NOT EXISTS course_seq start with 1 increment by 1;

        -- Create table
        CREATE TABLE course (
            id bigint NOT NULL,
            course_name text DEFAULT 'UNKNOWN' NOT NULL,
            course_code text DEFAULT 'UNKNOWN' NOT NULL,
            course_description text DEFAULT 'UNKNOWN' NOT NULL,
            created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
            created_by text DEFAULT 'User1' NOT NULL,            
            primary key (id)
        );

        ALTER TABLE course ADD CONSTRAINT
            course_course_name_len CHECK (length(course_name) < 256);
        ALTER TABLE course ADD CONSTRAINT
            course_course_code_len CHECK (length(course_code) < 101);
        ALTER TABLE course ADD CONSTRAINT
            course_course_description_len CHECK (length(course_description) < 4001);            
        ALTER TABLE course ADD CONSTRAINT
            course_created_by_len CHECK (length(created_by) < 256);
    END IF;


    IF NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_schema = 'public' AND
        table_name = 'enrollment') THEN

        -- Create Sequence
        CREATE SEQUENCE IF NOT EXISTS enrollment_seq start with 1 increment by 1;

        -- Create table
        CREATE TABLE enrollment (
            id bigint NOT NULL,
            student_id INT REFERENCES student(id),
            course_id INT REFERENCES course(id),
            created_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
            created_by text DEFAULT 'User1' NOT NULL,            
            primary key (id)
        );
           
        ALTER TABLE enrollment ADD CONSTRAINT
            enrollment_created_by_len CHECK (length(created_by) < 256);
    END IF;


END $$;


