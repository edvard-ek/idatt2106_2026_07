INSERT INTO schools (id, name, email_suffix)
VALUES (1, 'Uranienborg Skole', '@osloskolen.no');

INSERT INTO classrooms (id, name, head_teacher_id, school_id)
VALUES (1, '8A', NULL, 1);

INSERT INTO users (id, username, email, first_name, last_name, password)
VALUES (1, 'alf', 'alf@osloskolen.no', 'Test', 'Teacher', '$2y$10$exUh84ts9h5pXIm.QSqA3uHQ9M5ifNW.ePQs7lBNYDeCV7RZoY5ja');--Password123!

INSERT INTO teachers (id, school_id)
VALUES (1, 1);

UPDATE classrooms
SET head_teacher_id = 1
WHERE id = 1;

INSERT INTO classroom_teachers (classroom_id, teacher_id)
VALUES (1, 1);

INSERT INTO users (id, username, email, first_name, last_name, password)
VALUES (2, 'snorre', 'snorre@osloskolen.no', 'Test', 'Pupil', '$2y$10$exUh84ts9h5pXIm.QSqA3uHQ9M5ifNW.ePQs7lBNYDeCV7RZoY5ja'); --Password123!

INSERT INTO pupils (id, classroom_id)
VALUES (2, 1);

-- Avatar items test data 
INSERT INTO avatar_items (id, name, slot)
VALUES (1, 'Boy', 'GENDER');

INSERT INTO avatar_items (id, name, slot)
VALUES (2, 'Girl', 'GENDER');

INSERT INTO avatar_items (id, name, slot)
VALUES (3, 'Blue Eyes', 'EYE_COLOR');

INSERT INTO avatar_items (id, name, slot)
VALUES (4, 'Brown Eyes', 'EYE_COLOR');

INSERT INTO avatar_items (id, name, slot)
VALUES (5, 'Light Skin', 'SKIN_COLOR');

INSERT INTO avatar_items (id, name, slot)
VALUES (6, 'Dark Skin', 'SKIN_COLOR');

INSERT INTO avatar_items (id, name, slot)
VALUES (7, 'Black Hair', 'HAIR_COLOR');

INSERT INTO avatar_items (id, name, slot)
VALUES (8, 'Blonde Hair', 'HAIR_COLOR');

INSERT INTO avatar_items (id, name, slot)
VALUES (9, 'Short Hair', 'HAIR_STYLE');

INSERT INTO avatar_items (id, name, slot)
VALUES (10, 'Long Hair', 'HAIR_STYLE');

INSERT INTO avatar_items (id, name, slot)
VALUES (11, 'Red Hoodie', 'UPPER_BODY_CLOTHING');

INSERT INTO avatar_items (id, name, slot)
VALUES (12, 'Blue Sweater', 'UPPER_BODY_CLOTHING');

INSERT INTO avatar_items (id, name, slot)
VALUES (13, 'Blue Pants', 'LOWER_BODY_CLOTHING');

INSERT INTO avatar_items (id, name, slot)
VALUES (14, 'Black Pants', 'LOWER_BODY_CLOTHING');

INSERT INTO avatar_items (id, name, slot)
VALUES (15, 'No Hat', 'HAT');

INSERT INTO avatar_items (id, name, slot)
VALUES (16, 'Red Cap', 'HAT');

ALTER TABLE schools ALTER COLUMN id RESTART WITH 2;
ALTER TABLE classrooms ALTER COLUMN id RESTART WITH 2;
ALTER TABLE users ALTER COLUMN id RESTART WITH 3;
ALTER TABLE avatar_items ALTER COLUMN id RESTART WITH 17;
