package com.example;

import java.util.ArrayList;

import io.github.cdimascio.dotenv.Dotenv;
import java.util.List;

public class App {

    public static String[] getChapters(ChatgptApi api) {
        String answer = api.getAnswer(
                "Come up with a plan for a love story and do not include details in this response; Return 5 sentences, each sentence representing one chapter. Do not include the word chapter. Each chapter should be separated with a # symbol.");
        String[] chapters = answer.split("#");
        return chapters;
    }

    public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure()
                .directory("./").load();
        String APP_ID = dotenv.get("APP_ID");

        ChatgptApi api = new ChatgptApi(APP_ID);

        String[] chapters = App.getChapters(api);

        System.out.println("------------------------------");
        System.out.println(" PLAN ");
        System.out.println("------------------------------");
        for (int i = 0 ; i<chapters.length;i++){
            System.out.println("Chapter " + (i+1));
            System.out.println(chapters[i]);
        }
        List<String> content = new ArrayList<String>();
    
        System.out.println("------------------------------");
        System.out.println(" FINAL STORY ");
        System.out.println("------------------------------");
        for (int j = 0; j<chapters.length;j++){
            String question = "Turn this idea into one chapter, but dont include any chapter reference "+chapters[j].strip();
            String answer = api.getAnswer(question);
            content.add(answer);

            System.out.println("CHAPTER "+(j+1));
            System.out.println(answer);

        }


    }
}