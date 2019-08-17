package main.java.com.accenture.application.development.patterns.constants;

public class Names {

    private String[] names = {"Mitch", "Oren", "Jess", "Rex", "Marcus", "Shannon", "Shawn", "Bennett", "Warner", "Nicholas",
            "Trenton", "Antonio", "Robbert", "Denver", "Cary", "Ivan", "Harry", "Sterling", "Long", "Kevin", "Madalene", "Sherly",
            "Alejandrina", "Janene", "Arnette", "Wendolyn", "Providencia", "Sophie", "Johana", "Yee", "Melodi", "Lakeisha", "Patti",
            "Talitha", "Yahaira", "Candie", "Trina", "Karrie", "Dinorah", "Jodi"};


    public String getName(final Integer index) {
        return names[index];
    }

    public Integer getSize() {
        return names.length;
    }
}
