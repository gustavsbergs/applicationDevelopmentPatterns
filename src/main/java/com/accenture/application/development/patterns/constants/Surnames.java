package main.java.com.accenture.application.development.patterns.constants;

public class Surnames {

    private String[] Surnames = {"Jackson", "Taylor", "Morris", "Wright", "Torres", "Clark", "Green", "Allen", "Nelson",
            "Martin", "Gray", "Thompson", "Watkins", "Page", "Preston", "Rugor", "Gillory", "Duckstein"};


    public String getName(final Integer index) {
        return Surnames[index];
    }

    public Integer getSize() {
        return Surnames.length;
    }
}
