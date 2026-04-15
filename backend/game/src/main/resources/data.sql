INSERT INTO schools (id, name, email_suffix)
VALUES (1, 'Uranienborg Skole', '@osloskolen.no');

INSERT INTO classrooms (id, name, head_teacher_id, school_id)
VALUES (1, '8A', NULL, 1);

INSERT INTO users (id, username, email, first_name, last_name, password, xp)
VALUES (1, 'alf', 'alf@osloskolen.no', 'Test', 'Teacher', '$2y$10$exUh84ts9h5pXIm.QSqA3uHQ9M5ifNW.ePQs7lBNYDeCV7RZoY5ja', '100');--Password123!

INSERT INTO teachers (id, school_id)
VALUES (1, 1);

UPDATE classrooms
SET head_teacher_id = 1
WHERE id = 1;

INSERT INTO classroom_teachers (classroom_id, teacher_id)
VALUES (1, 1);

INSERT INTO users (id, username, email, first_name, last_name, password, xp)
VALUES (2, 'snorre', 'snorre@osloskolen.no', 'Test', 'Pupil', '$2y$10$exUh84ts9h5pXIm.QSqA3uHQ9M5ifNW.ePQs7lBNYDeCV7RZoY5ja', 100); --Password123!

INSERT INTO pupils (id, classroom_id)
VALUES (2, 1);

ALTER TABLE schools ALTER COLUMN id RESTART WITH 2;
ALTER TABLE classrooms ALTER COLUMN id RESTART WITH 2;
ALTER TABLE users ALTER COLUMN id RESTART WITH 3;
