package fr.epita.project.datamodel;

import java.util.Arrays;

public class Question {

    private Integer id;
    private String question;
    private Integer difficulty;
    private String[] topics;

    private String question_type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Question(int id, String question, Integer difficulty, String[] topics, String question_type) {
        this.id = id;
        this.question = question;
        this.difficulty = difficulty;
        this.topics = topics;
        this.question_type = question_type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(String question_type) {
        this.question_type = question_type;
    }

    public Integer getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Integer difficulty) {
        this.difficulty = difficulty;
    }

    public String[] getTopics() {
        return topics;
    }

    public void setTopics(String[] topics) {
        this.topics = topics;
    }

    @Override
    public String toString() {
        return "Question{" + "question='" + question + '\'' + ", difficulty='" + difficulty + '\'' + ", topics='" + Arrays.toString(topics) + ", question_type='" + question_type + '\'' + '}';
    }


}
