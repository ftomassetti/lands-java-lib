import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Map;

import com.github.lands.PickleSerialization;
import com.github.lands.World;
import net.razorvine.pickle.Unpickler;
import net.razorvine.pickle.objects.ClassDict;

public class Example {

    public static void main(String[] args) throws Exception {
        File file = new File("examples/seed_77.world");

        World world = PickleSerialization.loadWorld(file);
    }
}
