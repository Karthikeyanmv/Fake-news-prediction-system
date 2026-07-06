package dhushyanth.example.demo.controller;

import dhushyanth.example.demo.ml.ModelTrainer;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class PredictController {

    @GetMapping("/")
    public String home() {
        return "Fake News Prediction Backend Running";
    }

    @PostMapping("/predict")
    public String predict(@RequestBody String text) {

        if (text == null || text.trim().length() < 150) {
            return "Enter detailed news text (minimum 150 characters) for accurate prediction.";
        }

        String lower = text.toLowerCase();

        String[] fakeTriggers = {
                "shocking", "secret", "exposed", "revealed", "miracle","Unbelievable","Share before it’s deleted",
                "instantly", "cures", "guaranteed", "you won’t believe","Amazing","amazing!!",
                "Oh my god","ohhh my god!!!","!!","over night",
                "doctors hate", "what happens next", "gone viral",
                "breaking truth", "hidden truth", "one simple trick",
                "scientists prove", "banned video", "before it’s deleted",
                "wake up people", "big pharma", "media won’t tell",
                "everyone is shocked", "must watch", "act now", "don't ignore this",
                "they don't want you to know", "mainstream media hides",
                "government cover up", "the real truth", "suppressed information",
                "exclusive leak", "leaked footage", "watch before it disappears",
                "share immediately", "send this to everyone", "urgent warning",
                "time is running out", "world is stunned", "internet is exploding",
                "people are furious", "nobody expected this", "this changes everything",
                "experts confirm", "officials are panicking", "insiders reveal",
                "anonymous sources say", "top experts agree", "medical experts reveal",
                "miracle treatment", "natural cure", "works instantly",
                "eliminate disease", "100% effective", "proven solution",
                "secret formula", "hidden agenda", "they lied to us",
                "wake up before it's too late", "you have been fooled",
                "social media erupted", "viewers were speechless",
                "people are losing their minds", "fans are in disbelief",
                "in a shocking turn of events", "what happened next shocked everyone",
                "the internet can't believe", "viral sensation", "breaking update",
                "exclusive report", "unbelievable discovery", "stunning revelation",
                "must read", "don't scroll past", "this will change your life",
                "you need to know this", "everyone needs to see this",
                "once in a lifetime", "too good to be true",
                "banned by authorities", "deleted by the media",
                "the truth is finally out", "hidden from the public",
                "do this now", "before it's too late",
                "can't be ignored", "the answer will surprise you",
                "nobody wants you to know", "what they won't tell you",
                "you've been lied to", "dangerous secret", "massive cover up",
                "breaking scandal", "shocking evidence", "explosive report",
                "viral video", "must share", "watch till the end",
                "don't miss this", "this is huge", "mind blowing",
                "jaw dropping", "absolutely unbelievable", "oh my goodness",
                "OMG!!!", "unreal!!", "seriously shocking"
        };

        for (String trigger : fakeTriggers) {
            if (lower.contains(trigger)) {
                return "FAKE NEWS";
            }
        }

        // ML prediction (fallback)
        double[] vector = ModelTrainer.vectorizer.transform(text);
        int prediction = ModelTrainer.model.predict(vector);

        return prediction == 1 ? "REAL NEWS" : "FAKE NEWS";
    }
}
