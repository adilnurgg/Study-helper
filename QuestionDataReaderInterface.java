import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.io.FileReader;

public interface QuestionDataReaderInterface {
	QuestionTree read(FileReader reader) throws IOException;
}
