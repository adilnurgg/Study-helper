import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/**
 * A class to glean the provided file and retrieve Question objects from it. These will be used by
 * the back end programer to design a storage structure.
 */
public class QuestionDataReader implements QuestionDataReaderInterface{

  /**
   * The read method combs the file line by line and retrieves creates Question objects using the
   * data.
   * @param reader the file reader which will help in going through the file.
   * @return a list of Question objects to be used by the back end programmer.
   * @throws IOException if the file cannot be accessed properly.
   */
  @Override public QuestionTree read(FileReader reader) throws IOException {
    QuestionTree qt = new QuestionTree();
    List<Question> questionList = new ArrayList<Question>(); //The final question List to return.
    List<String[]> initial = new ArrayList<>(); //An initial ArrayList of every question before it is converted to a question object.
    String focusLine; //A variable to track which line is being added to the initial list and eventuallt the final question list.
    try {
      BufferedReader read = new BufferedReader(reader);
      while ((focusLine = read.readLine())!=null) {
        String[] toSeparate;
        toSeparate = focusLine.split("\\t");
        initial.add(toSeparate);
      }
      for (int i = 1;i<initial.size();i++) {
        //Question qToAdd = null;
        String question = null;
        String difficulty = null;
        //Topic topic = null;
        String answer = null;
        String tp = initial.get(i)[0];
        Topic topic = new Topic(tp);//The numbers indicate which index represents the values to store.
        question = initial.get(i)[1];
        answer = initial.get(i)[2];
        difficulty = initial.get(i)[4];
        Question qToAdd = new Question(question,answer,difficulty,topic);
        if(qt.topicSet.size() == 0){
          qt.addTopic(topic);
          qt.topicSet.add(topic);
        }
        else if(!qt.contains(topic)) {
          qt.addTopic(topic);
          qt.topicSet.add(topic);
        }
        else{
          topic = qt.searchTopic1(topic.name);
        }
        boolean found = false;
        if (!qToAdd.question.equals(" NULL") && !qToAdd.question.equals(" Question") && !qToAdd.question.equals("NULL") && !qToAdd.question.equals("Question")) {
          for (int j = 0;j < questionList.size();j++) {
            if (qToAdd.question.equals(questionList.get(j).question)) {
              found = true;
            }
          }
          if (found==false) {
            System.out.println(topic.listOfQuestions.size());
            topic.listOfQuestions.add(qToAdd);
            qt.addQuestion(qToAdd);
            questionList.add(qToAdd);
          }
        }
      }
      return qt;
    } catch (IOException e) {
      throw new FileNotFoundException("Could not open file");
    }
  }



}
