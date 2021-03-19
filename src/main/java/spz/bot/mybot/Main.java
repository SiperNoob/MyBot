//main class of this project (here's everything happens)
package spz.bot.mybot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.jetbrains.annotations.NotNull;
import spz.bot.mybot.Listeners.MessageListener;
import spz.bot.mybot.Utils.ClassUtils;
import spz.bot.mybot.Utils.CommandExecutor;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

public class Main {

    private static String bot_token = "ODE4NjEyNDQwNTgwMjI3MTIz.YEamPg.aKRKUis-RF-kxKp3mN-wVSFfbYE"; //token especifico do bot que está sendo feito

    public static String bot_PREFIX = "-"; //acho que é um prefixo para as mensagens que o bot envia

    public static HashMap<String, CommandExecutor> COMMANDS = new HashMap<>();
    public static HashMap<String, CommandExecutor> ALIASES = new HashMap<>();

    public static void main(String[] args) throws LoginException, IOException {
        JDABuilder builder = JDABuilder.createDefault(bot_token) //deixando o bot online
        .setChunkingFilter(ChunkingFilter.ALL)
        .setMemberCachePolicy(MemberCachePolicy.ALL).enableIntents(GatewayIntent.GUILD_MEMBERS);    //diminui o peso computacional



        builder.setActivity(Activity.watching("Loading...")); //acho que é o texto que aparece quando o bot não ta fazendo nada
        builder.addEventListeners(new MessageListener());
        builder.addEventListeners(new ListenerAdapter() {
            @Override
            public void onReady(@NotNull ReadyEvent event) { //tudo que está dentro do onReady só é execultado quando o bot está dentro do servidor com conexões estabelecidas
                //database

                event.getJDA().getPresence().setActivity(Activity.watching("over " + event.getGuildAvailableCount() + " servers.")); //frase que aparece embaixo do bot, mostrando o que tá fazendo

                System.out.println("My bot has loaded successfully!");
            }
        });

            ClassUtils.registerAllComands();
        for(CommandExecutor commandExecutor : COMMANDS.values(){
            if() {

            }
        }


        builder.build();
    }

    //não sei o que essa parte aqui de baixo faz
    //you can find the connection to this void in MessageListener class.
    public static void executeCommand(String prefix, MessageReceivedEvent event) throws IOException {

        String chatMessage = event.getMessage().getContentRaw();
        String[] splitCommand = chatMessage.split(" ");
        String command = splitCommand[0].replaceFirst("[" + prefix + "]", "");
        String commandArgs = event.getMessage().getContentRaw().replace(prefix + command, "");
        commandArgs = commandArgs.replaceFirst(" ", "");
        String[] args = commandArgs.split(" ");

        if (args.length == 1) {
            if (args[0].isEmpty() || args[0].equals(" ") || args[0].equals(prefix + command) || args[0].equals(prefix)) {
                args = new String[0];
            }
        }

        if(COMMANDS.get(command.toLowerCase() != null) {
            if(!COMMANDS.get(command.toLowerCase()).execute(args, event)) {
                event.getChannel().sendMessage("This command is currently disabled").queue();
            } else if (ALIASES.get(command.toLowerCase()) != null) {
                if(ALIASES.get(command.toLowerCase()).execute(args, event)) {
                    event.getChannel().sendMessage("This command is currently disabled").queue();
                }
            }
        }
    }
}
