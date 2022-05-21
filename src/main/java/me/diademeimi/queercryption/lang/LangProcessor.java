package me.diademeimi.queercryption.lang;

import edu.stanford.nlp.simple.Sentence;

public class LangProcessor {

    public static Boolean isVerb(String sentence) {
        Sentence sent = new Sentence(sentence);

        try {

            if (sent.posTag(0).startsWith("VB")) {
                return true;
            }

        } catch (Exception e) {
        }
        
        return false;
    }

}
