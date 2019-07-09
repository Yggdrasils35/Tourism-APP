import android.provider.BaseColumns;

public final class SQLiteContract {
    private SQLiteContract() {}

    public static class DiaryEntry implements BaseColumns {
        public static final String TABLE_NAME = "diary";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_CONTEXT = "content";
        public static final String COLUMN_NAME_TIME = "time";
    }
}
