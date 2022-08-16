package fr.epita.project.datamodel;


public class OpenQuestion {

    private Integer id;
    private String answers;

    public OpenQuestion(Integer id, String answers) {
        this.id = id;
        this.answers = answers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswer() {
        return answers;
    }

    public void setAnswer(String answers) {
        this.answers = answers;
    }


    @Override
    public String toString() {
        return "MCQQuestion{" + "id='" + id + '\'' + ", answer='" + answers + '\''  +  '\'' + '}';

    }
}
