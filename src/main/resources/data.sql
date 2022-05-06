-- article 더미 데이터
INSERT INTO article (title, content) VALUES ('aaaaa', '1111');
INSERT INTO article (title, content) VALUES ('bbbbb', '2222');
INSERT INTO article (title, content) VALUES ('ccccc', '3333');
INSERT INTO article (title, content) VALUES ('ddddd', '4444');

-- comment 더미 테이터
---- 1번 게시글의 댓글
INSERT INTO comment (article_id, nickname, body) VALUES (1, 'Seo', '재밌어요~');
INSERT INTO comment (article_id, nickname, body) VALUES (1, 'Kim', '흠...');
INSERT INTO comment (article_id, nickname, body) VALUES (1, 'Lee', '?!?');

---- 2번 게시글의 댓글
INSERT INTO comment (article_id, nickname, body) VALUES (2, 'Seo', '과자~');
INSERT INTO comment (article_id, nickname, body) VALUES (2, 'Kim', '자고 싶다');
INSERT INTO comment (article_id, nickname, body) VALUES (2, 'Lee', '코딩이 뭐죠');

---- 3번 게시글의 댓글
INSERT INTO comment (article_id, nickname, body) VALUES (3, 'Seo', '공부');
INSERT INTO comment (article_id, nickname, body) VALUES (3, 'Kim', '영어...');
INSERT INTO comment (article_id, nickname, body) VALUES (3, 'Lee', 'ㄴkssaaa');

