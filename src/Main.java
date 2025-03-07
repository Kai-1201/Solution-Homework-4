import java.util.HashMap;
import java.util.Map;

class ConfigurationManager {
    private static ConfigurationManager instance;
    private Map<String, String> config;

    private ConfigurationManager() {
        config = new HashMap<>();
        config.put("maxPlayers", "100");
        config.put("defaultLanguage", "en");
        config.put("gameDifficulty", "medium");
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    public String getConfig(String key) {
        return config.get(key);
    }

    public void ConfigurationDemo () {
        config.forEach((key, value) -> System.out.println(key + ": " + value));
    }
}







class LegacyChatService {
    public void sendLegacyMessage(String message) {
        System.out.println("Legacy Chat: " + message);
    }
}


interface ChatService {
    void sendMessage(String message);
}


class ChatServiceAdapter implements ChatService {
    private LegacyChatService legacyChatService;

    public ChatServiceAdapter(LegacyChatService legacyChatService) {
        this.legacyChatService = legacyChatService;
    }

    @Override
    public void sendMessage(String message) {
        legacyChatService.sendLegacyMessage(message);
    }
}

class Main2 {
    public static void main(String[] args) {
        ConfigurationManager configManager = ConfigurationManager.getInstance();
        System.out.println("maxPlayers: " + configManager.getConfig("maxPlayers"));
        System.out.println("defaultLanguage: " + configManager.getConfig("defaultLanguage"));
        System.out.println("gameDifficulty: " + configManager.getConfig("gameDifficulty"));
        System.out.println();
        System.out.println();
        System.out.println();
        LegacyChatService legacyChat = new LegacyChatService();
        ChatService chatService = new ChatServiceAdapter(legacyChat);
        chatService.sendMessage("Hello world!");
    }
}

