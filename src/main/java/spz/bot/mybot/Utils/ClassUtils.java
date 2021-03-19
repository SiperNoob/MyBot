package spz.bot.mybot.Utils;

import com.google.common.reflect.ClassPath;
import spz.bot.mybot.Main;

import java.io.IOException;

public class ClassUtils {
    public static void registerAllComands() throws IOException{

        ClassPath cp = ClassPath.from(ClassUtils.class.getClassLoader());
        cp.getTopLevelClassesRecursive("spz.bot.mybot.Commands").forEach(classInfo -> {
            try {
                Class c = Class.forName(classInfo.getName());
                Object obj = c.newInstance();
                if(obj instanceof CommandExecutor) {
                    CommandExecutor commandExecutor = (CommandExecutor) obj;
                    Main.COMMANDS.put(commandExecutor.name().toLowerCase(), commandExecutor);
                }
            } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
}
