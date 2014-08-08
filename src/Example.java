import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.github.lands.PickleSerialization;
import com.github.lands.World;
import com.github.langgen.Language;
import com.github.langgen.LanguageSamples;
import com.github.langgen.SamplesBasedLanguageFactory;
import net.razorvine.pickle.Unpickler;
import net.razorvine.pickle.objects.ClassDict;
import org.python.util.PythonInterpreter;

public class Example {

    public static void main(String[] args) throws Exception {
        Language language = SamplesBasedLanguageFactory.getRandomLanguage();
        for (int i=0;i<20;i++){
            System.out.println(" * "+language.name());
        }
    }
}
