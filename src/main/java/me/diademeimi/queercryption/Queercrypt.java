package me.diademeimi.queercryption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Queercrypt {

    static String[] emoji = new String[]{"🥺", "👉👈", "😈", "😭", "🤨", "😳"};

    public static String queercrypt(String input, Integer rounds) {
        for (int i = 0; i < rounds; i++) {
            input = encrypt(input);
        }
        return input;
    }

    public static String encrypt(String input) {
        Random rand = new Random();

        String[] words = input.split(" ");
        ArrayList<String> newWords = new ArrayList<String>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
        
            }

            if (rand.nextDouble() < 0.33) {
                newWords.add(emoji[rand.nextInt(emoji.length)]);
            }

            if (i > 0 && !words[i].equals(" ") && !words[i].equals("")) {
                int offset = 1;

                while (words[i - offset].equals("") || words[i - offset].equals(" ")) {
                    offset++;
                }
                if (!words[i - offset].equalsIgnoreCase("literally") && !Arrays.asList(emoji).contains(words[i - offset])) {
                    if (i + 1 < words.length) {
                        offset = 0;

                        while (words[i + offset].equals("") || words[i + offset].equals(" ")) {
                            offset++;
                        }
                        if (!words[i + offset].equalsIgnoreCase("literally") && !Arrays.asList(emoji).contains(words[i - offset])) {
                            if (rand.nextDouble() < 0.10) {
                                newWords.add("LITERALLY");
                            }            
                        }
                    }
                }
            }

            newWords.add(words[i]);

            if (i == words.length - 1) {
                if (!words[words.length - 1].toLowerCase().startsWith("lmao")) {
                    if (rand.nextDouble() < 0.50) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("lmao");
                        for (int o = 0; o < rand.nextInt(5); o++) {
                            sb.append("o");
                        }
                        newWords.add(sb.toString());
                    }
                }
            }


        }

            return String.join(" ", newWords);

        }

    public static String inject(String input, Integer position, String toInject) {
        return input.substring(0, position) + toInject + input.substring(position);
    }

}
