INSERT INTO Parent (firstname, lastname) VALUES
('John', 'Doe'),
('Jane', 'Smith'),
('Michael', 'Johnson');

INSERT INTO School (name, hour_price) VALUES
('Happy Kids Kindergarten', 10.53),
('Sunshine Daycare Center', 12.75),
('Rainbow Learning Academy', 9.99);

INSERT INTO Child (firstname, lastname, parent_id, school_id) VALUES
('Emma', 'Doe', 1, 1),
('Noah', 'Smith', 2, 2),
('Olivia', 'Johnson', 3, 3),
('Liam', 'Doe', 1, 2),
('Ava', 'Smith', 2, 3),
('James', 'Johnson', 3, 1);

INSERT INTO Attendance (child_id, entry_date, exit_date) VALUES
(1, '2024-02-20 07:30:00', '2024-02-20 12:15:00'),
(1, '2024-02-21 06:59:59', '2024-02-21 11:59:59'),
(1, '2024-02-22 07:00:01', '2024-02-22 12:00:01'),
(1, '2024-02-23 03:00:00', '2024-02-23 18:00:00'),
(1, '2024-02-24 00:00:00', '2024-02-24 23:59:59'),
(2, '2024-02-20 06:59:00', '2024-02-20 12:01:00'),
(3, '2024-02-20 07:45:00', '2024-02-20 12:30:00'),
(4, '2024-02-20 06:45:00', '2024-02-20 12:30:00'),
(5, '2024-02-20 07:15:00', '2024-02-20 12:45:00'),
(6, '2024-02-20 07:00:00', '2024-02-20 12:00:00');