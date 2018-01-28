package hello;

public class Greeting {

    public String content;
    public String content2;
    
    public Greeting() {
    	
    }
    public Greeting(String content) {
        this.content = content;
    }

    public String getContent() {
        return content + " wao " + content2;
    }

    public void setContent(String str) {
        this.content=str;
    }
    
    
}