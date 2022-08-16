package fr.epita.project.datamodel;

public class MCQAnswer {
    private Integer id;
    private Integer score;
    private String[] answers;
    private Integer question_id;

    public MCQAnswer(Integer id, Integer score, String[] answers, Integer question_id) {
        this.id = id;
        this.score = score;
        this.answers = answers;
        this.question_id = question_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public Integer getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(Integer question_id) {
        this.question_id = question_id;
    }
}
