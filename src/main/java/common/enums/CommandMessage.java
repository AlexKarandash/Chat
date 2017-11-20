package common.enums;

//текст команды в сообщении
public enum CommandMessage {
    MESSAGE("MESSAGE"), EXIT("EXIT"), LOGIN("LOGIN");

    private String textCommand;
    CommandMessage(String textCommand){
        this.textCommand = textCommand;
    }

    public String getTextCommand(){
        return textCommand;
    }
}
