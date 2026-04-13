-- Alte Daten löschen, um Konflikte beim Neustart zu vermeiden
DELETE FROM users;
DELETE FROM media;

-- 1 Admin und 3 User einfügen (Passwort für alle: Passwort123!)
INSERT INTO users (first_name, last_name, email, username, password, tel_number, role) VALUES
('Super', 'Admin', 'admin@bibliothek.ch', 'admin', '$2a$10$KooNYy5.HnF.9QLOxnlRaeH6PCCpsUrToxHlEY/rnFKM9/flEowhS', '0791234567', 'ADMIN'),
('Hans', 'Muster', 'hans@bibliothek.ch', 'hansm', '$2a$10$KooNYy5.HnF.9QLOxnlRaeH6PCCpsUrToxHlEY/rnFKM9/flEowhS', '0791112233', 'USER'),
('Petra', 'Meier', 'petra@bibliothek.ch', 'petram', '$2a$10$KooNYy5.HnF.9QLOxnlRaeH6PCCpsUrToxHlEY/rnFKM9/flEowhS', '0794445566', 'USER'),
('Lars', 'Kaufmann', 'lars@bibliothek.ch', 'larsk', '$2a$10$KooNYy5.HnF.9QLOxnlRaeH6PCCpsUrToxHlEY/rnFKM9/flEowhS', '0797778899', 'USER');

-- 5 Medien einfügen
INSERT INTO media (type, title, author, isbn) VALUES
('BOOK', 'Clean Code', 'Robert C. Martin', '9780132350884'),
('BOOK', 'Effective Java', 'Joshua Bloch', '9780134685991'),
('BOOK', 'Head First Java', 'Kathy Sierra', '9780596009205'),
('BOOK', 'The Clean Coder', 'Robert C. Martin', '9780137081073'),
('BOOK', 'Spring in Action', 'Craig Walls', '9781617294945');