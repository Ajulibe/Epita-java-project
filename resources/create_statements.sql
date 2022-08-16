CREATE TABLE IF NOT EXISTS Admin(
  id serial primary key,
  name varchar(255) NOT NULL,
  password varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS Students(
  id serial primary key,
  name varchar(255) UNIQUE NOT NULL
);

CREATE TABLE IF NOT EXISTS Questions(
  id serial primary key,
  question varchar(1024),
  topics varchar(255),
  question_type varchar(255),
  difficulty integer
);

CREATE TABLE IF NOT EXISTS Answers (
  id serial PRIMARY KEY,
  score integer default 0,
  answers text[],
  question_id integer,
  FOREIGN KEY ( id ) REFERENCES students(id),
  FOREIGN KEY ( question_id ) REFERENCES Questions(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS Mcq_questions (
  question_id serial PRIMARY KEY,
  answers varchar(255),
  options text[],
  FOREIGN KEY ( question_id ) REFERENCES Questions(id) ON DELETE CASCADE
);


CREATE TABLE IF NOT EXISTS Aso_questions (
  id serial PRIMARY KEY,
  question_id integer,
  answers varchar(255),
  pair VARCHAR(1024),
  FOREIGN KEY ( question_id ) REFERENCES Questions(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS open_questions (
  question_id integer,
  answers varchar(1024),
  FOREIGN KEY ( question_id ) REFERENCES Questions(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS merged_questions (
  question_id serial PRIMARY KEY,
  answers varchar(1024),
  pair VARCHAR(1024),
  options VARCHAR(1024)
);











