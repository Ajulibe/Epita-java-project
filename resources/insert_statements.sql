
TRUNCATE public.admin, public.students, public.questions, public.aso_questions, public.open_questions, public.answers, public.Mcq_questions RESTART IDENTITY CASCADE;


INSERT INTO	Admin (name, password) VALUES ('admin', 'admin');
INSERT INTO	Students (name) VALUES ('benjamin');


-- insert questions (but for mcq)
INSERT INTO questions (question, topics, difficulty, question_type) values ('main is a keyword in Java', '{"java","compile"}', 1, 'mcq');
INSERT INTO questions (question, topics, difficulty, question_type) values ('A transient variable can be static', '{"java","compile"}',1 ,'mcq');
INSERT INTO questions (question, topics, difficulty, question_type) values ('The && operator always evaluates both operands', '{"java","compile"}',1,'mcq');
INSERT INTO questions (question, topics, difficulty, question_type) values ('Running a java program with -enableassertions switch will enable assertion for your program','{"java","compile"}', 2,'mcq');
INSERT INTO questions (question, topics, difficulty, question_type) values ('Which of the following classes define the toString() method?', '{"java","compile"}', 2 ,'mcq');
INSERT INTO questions (question, topics, difficulty, question_type) values ('Which of the following are NOT Java keywords?',  '{"code","class"}',2 ,'mcq');
INSERT INTO questions (question, topics, difficulty, question_type) values ('null is a valid keyword in Java', '{"java","compile"}', 1 ,'mcq');
INSERT INTO questions (question, topics, difficulty, question_type) values ('Which of the following is Java keywords?', '{"code","class"}', 1 ,'mcq');
INSERT INTO questions (question, topics, difficulty, question_type) values ('Will this code compile without any errors? public class Test{ public static void main(String[] args){int main = 0;}}','{"code","class"}', 2 ,'mcq');
INSERT INTO questions (question, topics, difficulty, question_type) values ('What will happen when you compile and run the following code?public class Test{public static void main(String[] args){int main = 0;}}', '{"code","class"}', 2 ,'mcq');
INSERT INTO questions (question, topics, difficulty, question_type) values ('Can you declare a static method final?', '{"java","compile"}', 1, 'mcq');
INSERT INTO questions (question, topics, difficulty, question_type) values ('Can you override public constructor declared in a base class to private in a child class?', '{"java","compile"}', 1, 'mcq');
INSERT INTO questions (question, topics, difficulty, question_type) values ('All the methods declared in an interface are implicitly public.', '{"java","public"}', 1, 'mcq');
INSERT INTO questions (question, topics, difficulty, question_type) values ('Will this code compile?public class Test{final int i;}', '{"code","class"}', 1, 'mcq');
INSERT INTO questions (question, topics, difficulty, question_type) values ('Will this code compile?public class Test{static final int i;static{i = 5;}}', '{"code","class"}', 1, 'mcq');
INSERT INTO questions (question, topics, difficulty, question_type) values ('Running a java program with -ea switch will enable assertion in system classes.', '{"java","compile"}', 1, 'mcq');
INSERT INTO questions (question, topics, difficulty, question_type) values ('Will this code compile?public class Test{public void count(){}}class TestChild extends Test{public void count(int i) throws Exception{}}', '{"code","class"}', 2, 'mcq');
INSERT INTO questions (question, topics, difficulty, question_type) values ('What will happen when you compile and run the following code?public class Test{public static void main(String[] args){int i = 2;System.out.println(i << 3);}}', '{"code","class"}', 3, 'mcq');
INSERT INTO questions (question, topics, difficulty, question_type) values ('Will this code compile without error? interface Test {abstract public void someMethod() throws Exception;}', '{"code","class"}', 3, 'mcq');
INSERT INTO questions (question, topics, difficulty, question_type) values ('Which class has defined the equals(Object o) method?', '{"code","class"}', 3, 'mcq');



-- insert questions (but for open)
INSERT INTO questions (question, topics, difficulty, question_type) values ('True or False. main is a keyword in Java', 'java', 1, 'open_question');
INSERT INTO questions (question, topics, difficulty, question_type) values ('True or False. A transient variable can be static', 'variable', 1 ,'open_question');
INSERT INTO questions (question, topics, difficulty, question_type) values ('True or False. The && operator always evaluates both operands', 'operator', 1,'open_question');
INSERT INTO questions (question, topics, difficulty, question_type) values ('True or False. Running a java program with -enableassertions switch will enable assertion for your program','java', 2,'open_question');
INSERT INTO questions (question, topics, difficulty, question_type) values ('Yes or No. Can you declare a static method final?', 'java', 1, 'open_question');
INSERT INTO questions (question, topics, difficulty, question_type) values ('True or False. null is a valid keyword in Java', 'java' , 1 ,'open_question');
INSERT INTO questions (question, topics, difficulty, question_type) values ('Yes or No, Will this code compile without any errors?public class Test{ public static void main(String[] args){ int main = 0;} }', 'code', 2 ,'open_question');

-- insert mcq questions
INSERT INTO Mcq_questions (question_id, answers, options) values (1,'b','{"a). True","b). False"}');
INSERT INTO Mcq_questions (question_id, answers, options) values (2,'a','{"a). True","b). False"}');
INSERT INTO Mcq_questions (question_id, answers, options) values (3,'b','{"a). True","b). False"}');
INSERT INTO Mcq_questions (question_id, answers, options) values (4,'a','{"a). True","b). False,only -ea switch can be used",null}');
INSERT INTO Mcq_questions (question_id, answers, options) values (5,'c of the above','{"a). String","b). Object","c). All of the above"}');
INSERT INTO Mcq_questions (question_id, answers, options) values (6,'a','{"a). extend","b). class","import"}');
INSERT INTO Mcq_questions (question_id, answers, options) values (7,'b','{"a). True","b). False"}');
INSERT INTO Mcq_questions (question_id, answers, options) values (8,'b','{"a). int","b). long"}');
INSERT INTO Mcq_questions (question_id, answers, options) values (9,'a','{"a). Yes","b). No"}');
INSERT INTO Mcq_questions (question_id, answers, options) values (10,'c','{"0",b). null,"c). Compilation Error"}');
INSERT INTO Mcq_questions (question_id, answers, options) values (11,'b','{"a). Yes","b). No"}');
INSERT INTO Mcq_questions (question_id, answers, options) values (12,'b','{"a). Yes","b). No"}');
INSERT INTO Mcq_questions (question_id, answers, options) values (13,'a','{"a). True","b). False"}');
INSERT INTO Mcq_questions (question_id, answers, options) values (14,'b','{"a). Yes","b). No"}');
INSERT INTO Mcq_questions (question_id, answers, options) values (15,'a','{"a). Yes","b). No"}');
INSERT INTO Mcq_questions (question_id, answers, options) values (16,'b','{"a). True","b). False"}');
INSERT INTO Mcq_questions (question_id, answers, options) values (17,'a','{"a). Yes","b). No"}');
INSERT INTO Mcq_questions (question_id, answers, options) values (18,'c','{a). 8,b). 5,c). 16}');
INSERT INTO Mcq_questions (question_id, answers, options) values (19,'a','{"a). Yes","b). c). No"}');
INSERT INTO Mcq_questions (question_id, answers, options) values (20,'c','{"a). String","b). Main","c). Object"}');


-- insert open questions
INSERT INTO open_questions (question_id, answers) values (21,'False');
INSERT INTO open_questions (question_id, answers) values (22,'True');
INSERT INTO open_questions (question_id, answers) values (23,'False');
INSERT INTO open_questions (question_id, answers) values (24,'True');
INSERT INTO open_questions (question_id, answers) values (25,'Yes');
INSERT INTO open_questions (question_id, answers) values (26,'False');
INSERT INTO open_questions (question_id, answers) values (27,'Yes');