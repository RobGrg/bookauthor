INSERT INTO book (id, book_category, book_name, isbn, status, created_date, last_modified_date) VALUES (1, 'Final', 'blue sky', null, 0, '2021-12-27 18:28:11.909843', '2021-12-27 18:28:11.909843');
INSERT INTO book (id, book_category, book_name, isbn, status, created_date, last_modified_date) VALUES (2, 'Fantasy', 'final sky', null, 0, '2021-12-27 18:28:37.083444', '2021-12-27 18:28:37.083444');
INSERT INTO book (id, book_category, book_name, isbn, status, created_date, last_modified_date) VALUES (3, 'Action', 'game of thrones', '1234567890', 0, '2021-12-27 18:30:07.339969', '2021-12-27 18:30:07.339969');
INSERT INTO book (id, book_category, book_name, isbn, status, created_date, last_modified_date) VALUES (4, 'Thriller', 'run for money', '3216549870', 0, '2021-12-27 18:30:07.356178', '2021-12-27 18:30:07.356178');
INSERT INTO book (id, book_category, book_name, isbn, status, created_date, last_modified_date) VALUES (5, 'Old', 'witcher', '000999888712', 0, '2021-12-27 18:31:10.834720', '2021-12-27 18:31:10.834720');
INSERT INTO book (id, book_category, book_name, isbn, status, created_date, last_modified_date) VALUES (6, 'physics', 'structure or why things dont fall?', null, 0, '2021-12-27 18:31:10.844793', '2021-12-27 18:31:10.844793');

INSERT INTO author (id, author_name, status, created_date, last_modified_date) VALUES (1, 'crooks', 0, '2021-12-27 18:28:11.998143', '2021-12-27 18:28:11.998143');
INSERT INTO author (id, author_name, status, created_date, last_modified_date) VALUES (2, 'rob', 0, '2021-12-27 18:28:37.091765', '2021-12-27 18:28:37.091765');
INSERT INTO author (id, author_name, status, created_date, last_modified_date) VALUES (3, 'leon', 0, '2021-12-27 18:30:07.343058', '2021-12-27 18:30:07.343058');


INSERT INTO book_authors (books_id, authors_id) VALUES (1, 1);
INSERT INTO book_authors (books_id, authors_id) VALUES (2, 2);
INSERT INTO book_authors (books_id, authors_id) VALUES (3, 3);
INSERT INTO book_authors (books_id, authors_id) VALUES (4, 3);
INSERT INTO book_authors (books_id, authors_id) VALUES (5, 3);
INSERT INTO book_authors (books_id, authors_id) VALUES (6, 3);