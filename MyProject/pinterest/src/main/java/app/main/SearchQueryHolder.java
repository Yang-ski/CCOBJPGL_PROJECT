package app.main;

public class SearchQueryHolder {
    private static String query = null;

    public static void setQuery(String q) {
        query = q;
    }

    public static String getQuery() {
        return query;
    }

    public static void clear() {
        query = null;
    }
}
