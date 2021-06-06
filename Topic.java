import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Topic extends RedBlackTree.Node<Topic> implements Comparable<Topic>{

    public String name;
    public List<Question> listOfQuestions;

    public Topic(String name) {

        super();
        this.name = name;
        listOfQuestions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        listOfQuestions.add(question);
    }

    public void removeQuestion(Question question) {
        listOfQuestions.remove(question);
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Topic && ((Topic) o).name.equals(this.name);
    }
    public String toString() {
        return name;
    }
    @Override
    public int compareTo(Topic o) {
        return this.name.compareTo(o.name);
    }
}
