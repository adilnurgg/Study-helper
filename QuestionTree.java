import java.util.*;

public class QuestionTree extends RedBlackTree <Topic> {
    public List<Topic> topicSet= new ArrayList<>();
    public Topic searchTopic1(String name1){
        for(int i = 0;i<topicSet.size();i++){
            if(topicSet.get(i).name.equals(name1)){
                return topicSet.get(i);
            }
        }
        return null;
    }


    /**
     * Check if A Question exists in the tree.
     * @param data specified question
     * @return true if the tree contains the question, false otherwise.
     */
    public boolean contains(Question data) {

        return data.topic.listOfQuestions.contains(data);
    }

    public List<Question> getQuestionList(Topic topic) {
        if (topic != null) {
            return topic.listOfQuestions;
        }
        else{
            return null;
        }

    }
    /**
     * Add Question data to the tree.
     * @param data question need to be inserted.
     * @return true if the question is successfully inserted.
     */
    public Question addQuestion(Question data) {
        if (data.topic.listOfQuestions != null) {
            // We have the list of this data's topic
            data.topic.listOfQuestions.add(data);
            //super.insert(data);
            return data;
        } else {
            //List of the topic does not exist
            Topic tpNode = (Topic) super.insert(data.topic);
            tpNode.listOfQuestions.add(data);
            return data;
        }
    }

    /**
     * Remove the question from the tree
     * @param data the question needs to be removed
     * @return true if question is successfully removed
     */
    public boolean removeQuestion(Question data) {
        if (data.topic.listOfQuestions != null) {
            return data.topic.listOfQuestions.remove(data);
        } else {
            //Question does not exist in the tree
            return false;
        }
    }

    public String listAllQuestions() {
        String retValue = "";

        for (Node<Topic> node : this) {
            Topic Topic = (Topic) node;
            for (Question q : Topic.listOfQuestions) {
                retValue = retValue + q.id + "." + q.question + "\n";
            }
        }

        return retValue;
    }

    public Question getQuestionFromATopic(Topic topic) throws NoSuchElementException {
        Random random = new Random();
        Topic t1 = searchTopic1(topic.name.replaceAll(" ", "_"));
        return t1.listOfQuestions.get(random.nextInt(t1.listOfQuestions.size()));
    }

    public Topic getRandomTopic() {
        int rnd = new Random().nextInt(topicSet.size());

        for(int i = 0;i<topicSet.get(rnd).listOfQuestions.size();i++){
            System.out.println(topicSet.get(rnd).listOfQuestions.get(i).question);
        }
        return topicSet.get(rnd);
    }
    public Set<Topic> searchTopic(String text){
        Set<Topic> topicset = new HashSet<>();
        Node<Topic> root1 = this.root;
        while(root1 != null){
            int c = root1.data.name.substring(0,text.length()).compareTo(text);
            if(c == 0){
                topicset.add(root1.data);
                if(root1.data.name.equals(text)){
                    break;
                }
                root1 = root1.data.name.compareTo(text)>0?root1.leftChild:root1.rightChild;
            }
            else if(c>0){
                root1 = root1.leftChild;
            }
            else{
                root1 = root1.rightChild;
            }
        }
        return topicset;
    }
    public void addTopic(Topic topic){
        insert(topic);
    }

    public void rateQuestion(Question question, String rating) {
        question.difficulty = rating;
    }
    public String toString() {
    return "question tree";
    }
    public void printTree() {
        //System.out.println(this.root.toString());
        for (Node<Topic> node : this) {
            Topic Topic = (Topic) node;
            System.out.println(Topic.data + ":");
            for (Question q : Topic.listOfQuestions) {
                System.out.println("    " + q.id + "." + q.question);
            }
        }
    }

}
