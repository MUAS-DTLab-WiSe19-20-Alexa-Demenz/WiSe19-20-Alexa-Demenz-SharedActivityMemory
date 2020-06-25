package sam_bekannter;

public class PhrasesAndConstants {

    private PhrasesAndConstants() {
        throw new IllegalStateException("Utility class");
    }

    // Card Titel
    public static final String CARD_TITLE = "Shared Activity Memory";

    // Slot Phrases
    public static final String NAME_SLOT = "Name";

    public static final String RESIDENCE_SLOT = "Residence";
    public static final String AGE_SLOT = "Age";
    public static final String RELATION_SLOT = "Relation";
    public static final String HOLIDAY_SLOT = "Holiday";
    public static final String JOB_SLOT = "Job";

    // Key Phrases
    public static final String RELATED_KEY = "RELATED";
    public static final String NAME_KEY = "NAME";
    public static final String COLOR_KEY = "COLOR";
    public static final String AGE_KEY ="AGE";
    public static final String JOB_KEY ="JOB";
    public static final String RESIDENCE_KEY ="RESIDENCE";

    // IS Phrases
    public static final String NAME_IS = "Du heißt ";
    public static final String AGE_IS = "Du bist ";
    public static final String AGE_IS2 = " Jahre alt";
    public static final String JOB_IS = "Du arbeitest als ";
    public static final String RESIDENCE_IS = "Du wohnst in ";
    public static final String MYRELATION_IS = "Deine Beziehung zum Demenzkranken ist ";
    public static final String HOLIDAY_IS = "Du verbringst deinen Urlaub in  ";


    // Saved Phrases
    public static final String JOB_SAVED =  "Dein Beruf wurde gespeichert! Du kannst jetzt weitere Informationen hinzufügen beziehungsweise aktualisieren. Sage dazu zum Beispiel \"Ich mache Urlaub in Dubai\".";
    public static final String HOLIDAY_SAVED =  "Dein Urlaub wurde gespeichert! Du hast jetzt alle deine Daten erfolgreich gespeichert. Falls du Daten ändern willst sage einfach die aktualiserten Informationen. Zum Beispiel \"Ich wohne in München\".";
    public static final String GOOD_BYE ="Vielen Dank, kann ich Dir noch weiter behilflich sein?";
    public static final String AGE_SAVED = "Dein Alter wurde gespeichert! Du kannst jetzt weitere Informationen hinzufügen beziehungsweise aktualisieren. Sage dazu zum Beispiel \"Meine Beziehung zum Angehörigen ist Bruder\".";
    public static final String RESIDENCE_SAVED = "Dein Wohnort wurde gespeichert! Du kannst jetzt weitere Informationen hinzufügen beziehungsweise aktualisieren. Sage dazu zum Beispiel \"Mein Alter ist zwanzig\".";
    public static final String RELATION_SAVED = "Deine Beziehung wurde gespeichert! Du kannst jetzt weitere Informationen hinzufügen beziehungsweise aktualisieren. Sage dazu zum Beispiel \"Ich arbeite als Maurer\".";


    // Unknown Phrases
    public static final String NAME_UNKNOWN ="Ich kenne Deinen Namen noch nicht. Um diesen zu speichern, sage beispielsweise \"Ich heiße Uwe\".";
    public static final String JOB_UNKNOWN ="Ich kenne Deinen Beruf noch nicht. Um diesen zu speichern, sage beispielsweise \"Ich arbeite als Softwareentwickler\".";
    public static final String RESIDENCE_UNKNOWN ="Ich kenne Deinen Wohnort nicht. Um diesen zu speichern, sage beispielsweise \"Mein Wohnort ist München\".";
    public static final String MYRELATION_UNKNOWN ="Ich kenne Deine Beziehung zum Demenzkranken nicht. Um diese zu speichern, sage beispielsweise \"Meine Beziehung zum Erkrankten ist Tochter\".";
    public static final String AGE_UNKNOWN ="Ich kenne Dein aktuelles Alter nicht. Um dieses zu speichern, sage beispielsweise \"Mein Alter ist zehn\".";
    public static final String HOLIDAY_UNKNOWN ="Ich kenne Dein aktuelles Urlaubsdomizil nicht. Um dieses zu speichern, sage beispielsweise \"Ich mache Urlaub in Dubai\"";

    public static final String CANCEL_AND_STOP = "Auf Wiedersehen!";


    public static final String WELCOME = "Hallo ich bin SAM. Ich werde ein kleines Interview mit dir durchführen um deine Daten zu speichern. Bitte sage mir dazu deinen Namen. Sage zum Beispiel \"Ich heiße Peter\".";
    public static final String WELCOME_SICK = "Hallo ich bin SAM. Ich helfe dir Informationen über deine Angehörigen abzufragen. ";
    public static final String EXAMPLE = "Du kannst zum Beispiel sagen \"Wie lange lebt Peter schon?\" oder \"Wo wohnt mein Bruder?\".";
    public static final String NEWNAME = "Ich habe einen neuen Account für dich angelegt. Bitte sage mir jetzt deinen Wohnort. Du kannst zum Beispiel sagen \"Mein Wohnort ist München\".";
    public static final String OLDNAME = "Du kannst deine Informationen hier aktualisieren. Sage dazu zum Beispiel \"Ich wohne in München\" oder \"Mein Alter ist zwölf\".";
    public static final String HELP = "Ich bin sam_bekannter und du kannst mir im Interview folgende Eigenschaften von dir nennen: Name, Alter, Beruf, Wohnort, Beziehung zum Demenzkranken, Aktuelle Beschäftigung." +
            "wenn du nur eine Eigenschaften ändern willst sage mir einfach deine aktualisierten Daten. Zum Beispiel: \"Mein Alter ist dreißig\".";

    public static final String FURTHER_HELP = "Kann ich dir noch weiter behilflich sein?";
    public static final String HISAGE = " Jahre alt.";
    public static final String HISRESIDENCE = " wohnt in ";
    public static final String HISHOLIDAY = " macht gerade Urlaub in ";
    public static final String HISJOB = " arbeitet als ";
    public static final String HISRELATION = " steht in Beziehung zu dir als ";
    public static final String MORE_INFORMATION = " Möchtest du noch etwas wissen?";
    public static final String NAME_NOT_THERE = "Niemand mit diesem Namen hat Informationen abgespeichert";
    public static final String HIS_AGE_UNKNOWN = " hat kein Alter angegeben.";
    public static final String HIS_RESIDENCE_UNKNOWN = " hat keinen Wohnort angegeben.";
    public static final String HIS_JOB_UNKNOWN = " hat keinen Beruf angegeben.";
    public static final String HIS_HOLIDAY_UNKNOWN = " hat keinen Urlaubsort angegeben.";
    public static final String HIS_RELATION_UNKNOWN = " hat keine Beziehung zu Ihnen angegeben.";



    public static final String SAY_NAME_REPROMPT = "Das habe ich nicht verstanden. Bitte sage mir Deinen Namen.";
    public static final String SAY_BIRTHDAY_REPROMPT2 = "Das habe ich nicht verstanden. Bitte sage mir Deinen Geburstag.";
    public static final String SAY_JOB_REPROMPT = "Das habe ich nicht verstanden. Bitte sage mir Deinen Beruf.";
    public static final String SAY_RESIDENCE_REPROMPT = "Das habe ich nicht verstanden. Bitte sage mir Deine Wohnort.";
    public static final String SAY_MYRELATION_REPROMPT = "Das habe ich nicht verstanden. Bitte sage mir Deine Beziehung zum Demenzkranken.";
    public static final String SAY_HOLIDAY_REPROMPT = "Das habe ich nicht verstanden. Bitte sage mir Deine aktuelle Urlaubsregion.";


    public static final String FALLBACK = "Tut mir leid, das weiß ich nicht. Sage einfach Hilfe.";
    public static final String HELP_REPROMPT = "Bitte sag mir Deinen Namen.";


    public static final String BYBY2 = "Auf Wiedersehen.";







}
