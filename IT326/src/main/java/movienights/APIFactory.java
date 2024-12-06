package main.java.movienights;

public class APIFactory {
    public enum APIType {
        OMDB("omdb");

        public String name;

        APIType(String s) {
            this.name = s.toLowerCase();
        }
    }

    public static API getAPI(APIType a) {
        if (a.equals(APIType.OMDB)) {
            return new OMDbAPI();
        } else {
            return null;
        }
    }
}
