import ru.nsu.sergomyaso.multy.core.MulticastAppCore;
import ru.nsu.sergomyaso.multy.core.MulticastSocketFactory;
import ru.nsu.sergomyaso.multy.parser.PropertyNetworkParser;

public class main {
    public static void main(String[] args) {
        PropertyNetworkParser parser = new PropertyNetworkParser();
        MulticastSocketFactory factory = new MulticastSocketFactory();
        MulticastAppCore core = new MulticastAppCore(factory, parser.getNetworkContext("./src/main/resources/config.properties"));
        core.runApp();
    }
}
