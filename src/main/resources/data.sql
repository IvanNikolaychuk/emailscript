-- Data for teacher
insert into teacherlist_houdai(ID, name, japLevel, japJessLevel, studyMajor, university, occupation, teachExpLevel)
VALUES (1, 'Akihiko Sanada', 3, 3,  'Math', 'Tokyo University', 'Tokyo', 2);

insert into teacherlist_houdai(ID, name, japLevel, japJessLevel, studyMajor, university, occupation, teachExpLevel)
VALUES (2, 'Atsushi Nakadsima', 2, 2,  'Literature', 'Tokyo University', 'Tokyo', 3);

insert into teacherlist_houdai(ID, name, japLevel, japJessLevel, studyMajor, university, occupation, teachExpLevel)
VALUES (3, 'Daiki Aomine', 1, 1,  'History', 'Tokyo University', 'Tokyo', 1);

-- Data for favorite teachers of students
insert into studentfavorite VALUES (1, 1);
insert into studentfavorite VALUES (2, 1);
insert into studentfavorite VALUES (3, 2);
insert into studentfavorite VALUES (4, 3);
insert into studentfavorite VALUES (5, 3);
insert into studentfavorite VALUES (6, 3);

-- Data for teachers schedule
INSERT INTO teacherlist_schedule_houdai_favoriteteacherid VALUES (1498918315179, 1498918315179, 1);
INSERT INTO teacherlist_schedule_houdai_favoriteteacherid VALUES (1499004776394, 1499004776394, 2);
INSERT INTO teacherlist_schedule_houdai_favoriteteacherid VALUES (1499091176394, 1499091176394, 2);
INSERT INTO teacherlist_schedule_houdai_favoriteteacherid VALUES (1499177576394, 1499177576394, 3);
INSERT INTO teacherlist_schedule_houdai_favoriteteacherid VALUES (1499350376394, 1499350376394, 1);
INSERT INTO teacherlist_schedule_houdai_favoriteteacherid VALUES (1499263976394, 1499263976394, 3);
INSERT INTO teacherlist_schedule_houdai_favoriteteacherid VALUES (1500127976394, 1500127976394, 2);
INSERT INTO teacherlist_schedule_houdai_favoriteteacherid VALUES (1499523176394, 1499523176394, 1);
INSERT INTO teacherlist_schedule_houdai_favoriteteacherid VALUES (1499609576394, 1499609576394, 2);
INSERT INTO teacherlist_schedule_houdai_favoriteteacherid VALUES (1499782376394, 1499782376394, 1);

-- Rank for teachers
INSERT INTO teacherlist_houdai_rank VALUES (1, 'Rank', 'A');
INSERT INTO teacherlist_houdai_rank VALUES (2, 'Rank', 'B');
INSERT INTO teacherlist_houdai_rank VALUES (3, 'Rank', 'A');

-- Students
INSERT INTO apply_student_list VALUES (1, '愛万', 'アイバン', 'NikolaychukIvan@ya.ru');
INSERT INTO apply_student_list VALUES (2, '慈英久', 'ジェーク', 'some-email1@gmail.com');
INSERT INTO apply_student_list VALUES (3, '舞久', 'マイク', 'some-email2@gmail.com');

-- Teachers scores
INSERT INTO teacher_evaluate VALUES (1, 1, 1498227361136, 1498227361136, 3);
INSERT INTO teacher_evaluate VALUES (1, 2, 1497363361137, 1497363361137, 5);
INSERT INTO teacher_evaluate VALUES (2, 3, 1496844961137, 1496844961137, 4);
INSERT INTO teacher_evaluate VALUES (2, 1, 1496844961133, 1496844961137, 5);
INSERT INTO teacher_evaluate VALUES (3, 2, 1497881761136, 1497881761136, 3);
INSERT INTO teacher_evaluate VALUES (3, 1, 1497363361137, 1497363361137, 4);

