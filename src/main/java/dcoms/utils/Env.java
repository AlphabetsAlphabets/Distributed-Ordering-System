package dcoms.utils;

import io.github.cdimascio.dotenv.Dotenv;

public class Env {
    public static Dotenv env;

    public static void loadEnv() {
        env = Dotenv.load();
    }
}
