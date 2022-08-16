TRUNCATE TABLE merged_questions;
INSERT INTO merged_questions SELECT * FROM mcq_questions ON CONFLICT DO NOTHING;
INSERT INTO merged_questions SELECT * FROM open_questions ON CONFLICT DO NOTHING;
