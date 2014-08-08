import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.github.lands.PickleSerialization;
import com.github.lands.World;
import com.github.langgen.Language;
import net.razorvine.pickle.Unpickler;
import net.razorvine.pickle.objects.ClassDict;
import org.python.util.PythonInterpreter;

public class Example {

    public static void main(String[] args) throws Exception {
        List<String> samples = new LinkedList<String>();
        samples.add("Fabio");
        samples.add("Mario");
        samples.add("Fabrizio");
        samples.add("Marco");
        samples.add("Luca");
        samples.add("Giovanni");
        samples.add("Federico");
        samples.add("Alessandro");
        Language language = new Language(samples);
        for (int i=0;i<10;i++){
            System.out.println(" * "+language.name());
        }
    }
}
