drop table if exists articles;

create table articles (
	article_id int NOT NULL AUTO_INCREMENT,
	category varchar(255) NOT NULL,
	title varchar(255) NOT NULL,
	PRIMARY KEY (article_id)
);

INSERT INTO ARTICLES (title, category) VALUES ('Spring REST Security using Hibernate', 'Spring');
INSERT INTO ARTICLES (title, category) VALUES ('Cloud Native Java', 'Spring');
INSERT INTO ARTICLES (title, category) VALUES ('12 Factor Principles', 'Cloud Native');
INSERT INTO ARTICLES (title, category) VALUES ('Beyond 12 Factor Principles', 'Cloud Native');
