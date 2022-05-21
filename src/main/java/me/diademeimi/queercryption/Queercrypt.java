package me.diademeimi.queercryption;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import me.diademeimi.queercryption.lang.LangProcessor;

public class Queercrypt {

    static Random rand = new Random();

    static String[] emoji = new String[]{
        "🥺",
        "🥺",
        "👉👈",
        "😈",
        "😭",
        "😭",
        "🤨",
        "🤨",
        "🤨",
        "😳",
        "😳"
    };

    static String[] bottomKeys = new String[]{
        "a", "a",
        "s", "s", "s",
        "d", "d",
        "f", "f",
        "h",
        "j", "j",
        "k", "k",
        "l", "l", "l",
        ";",
    };

    static List<String> emojiList = Arrays.asList(emoji);

    public static String queercrypt(String input, Integer rounds) {
        for (int i = 0; i < rounds; i++) {
            input = encrypt(input);
        }
        return input;
    }

    static String encrypt(String input) {

        String[] words = input.split(" ");
        ArrayList<String> newWords = new ArrayList<String>();


        for (int i = 0; i < words.length; i++) {

            if (LangProcessor.canInjectAdverb(words[i])) {
                if (rand.nextDouble() > 0.06) {
                    if (canInject(words, i, new ArrayList<>(Arrays.asList("literally", "like")))) {
                        if (rand.nextDouble() > 0.6) {
                            newWords.add("like");
                        } else {
                            newWords.add("literally");
                        }
                    }
                }
            }

            if (rand.nextDouble() < 0.04) {

                if (i - 3 > 0 && !emojiList.containsAll(new ArrayList<>(Arrays.asList(words[i - 1], words[i - 2])))) {
                    newWords.add(emoji[rand.nextInt(emoji.length)]);
                }
            }


            if (rand.nextDouble() < 0.002) {
                newWords.add("like");
            }

            if (rand.nextDouble() < 0.004) {
                StringBuilder sb = new StringBuilder();
                for (int b = 0; b < rand.nextInt(8) + 7; b++) {
                    sb.append(bottomKeys[rand.nextInt(bottomKeys.length)]);
                }
                newWords.add(sb.toString());
            }

            if (rand.nextDouble() < 0.004) {
                StringBuilder sb = new StringBuilder();
                char character;
                if (rand.nextDouble() < 0.5) {
                    character = '!'; 
                } else {
                    character = '?';
                }
                for (int b = 0; b < rand.nextInt(3) + 3; b++) {
                    sb.append(character);
                }
                newWords.add(sb.toString());
            }

            if (words[i].endsWith("?")) {
                if (!words[i].toLowerCase().contains("perchance") && rand.nextDouble() < 0.04) {
                    newWords.add(words[i] + " Perchance.");
                } else {
                    newWords.add(words[i]);
                }

            } else if (rand.nextDouble() < 0.004) {
                // Add word capitalised
                newWords.add(words[i].toUpperCase());
            } else {
                // Add word as normal 
                newWords.add(words[i]);
            }

            if (i == words.length - 1) {
                if (!words[words.length - 1].toLowerCase().startsWith("lmao")) {
                    if (rand.nextDouble() < 0.50) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("lmao");
                        for (int o = 0; o < rand.nextInt(8); o++) {
                            sb.append("o");
                        }
                        newWords.add(sb.toString());
                    }
                }
            }

        }

            return String.join(" ", newWords);

        }

    public static Boolean canInject(String[] words, Integer i, ArrayList<String> toCheck) {
        if (i > 0 && !words[i].equals(" ") && !words[i].equals("")) {

            int offset = 1;
            while (words[i - offset].equals("") || words[i - offset].equals(" ") 
                || emojiList.contains(words[i - offset]) || words[i - offset].matches("([\\!\\?])\\1*")) {
                offset++;
                if (i - offset == 0) {
                    break;
                }
            }

            if (!toCheck.contains(words[i - offset])) {

                if (i + 1 < words.length) {

                    offset = 0;
                    while (words[i + offset].equals("") || words[i + offset].equals(" ") 
                        || emojiList.contains(words[i + offset]) || words[i + offset].matches("([\\!\\?])\\1*")) {
                        offset++;
                        if (i + 1 == words.length) {
                            break;
                        }        
                    }

                    if (!toCheck.contains(words[i + offset])) {

                        return true;
                    }
                }
            }
        }

        return false;

    }
}
