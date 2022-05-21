package me.diademeimi.queercryption.lang;

import edu.stanford.nlp.simple.Sentence;

public class LangProcessor {

    public static Boolean canInjectAdverb(String sentence) {
        Sentence sent = new Sentence(sentence);

        try {
            String posTag = sent.posTag(0);
            if (posTag.startsWith("VB") || posTag.equals("RB")) {
                return true;
            }

        } catch (Exception e) {
        }
        
        return false;
    }

}
