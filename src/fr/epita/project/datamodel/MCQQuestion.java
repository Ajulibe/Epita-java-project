package fr.epita.project.datamodel;

import java.util.Arrays;

public class MCQQuestion {
    private Integer id;
    private String answers;
    private String[] options;

    public MCQQuestion(Integer id, String answers, String[] options) {
        this.id = id;
        this.answers = answers;
        this.options = options;
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

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "MCQQuestion{" + "id='" + id + '\'' + ", answer='" + answers + '\'' + ", options='" + Arrays.toString(options) +  '\'' + '}';

    }
}
