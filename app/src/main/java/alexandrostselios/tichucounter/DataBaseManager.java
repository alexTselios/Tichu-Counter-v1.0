package alexandrostselios.tichucounter;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class DataBaseManager extends Activity {

    private Context context;
    private String[] writeData = new String[200];
    private static SQLiteDatabase mydatabase;

    public DataBaseManager (SQLiteDatabase mydatabase){
        this.mydatabase=mydatabase;
    }

    /*public DataBaseManager(Intent intent, Save context) throws IOException {
        this.intent=intent;
        this.context=context;
    }

    public DataBaseManager(Intent intent, Load context) throws IOException {
        this.intent=intent;
        this.context=context;
    }*/

    public void createDatabase() {
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Teams(ID INTEGER PRIMARY KEY AUTOINCREMENT,Team1 VARCHAR,Team2 VARCHAR);");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS FinalScore(ID INTEGER PRIMARY KEY AUTOINCREMENT, TeamID INTEGER NOT NULL,Score1 INTEGER, Score2 INTEGER, FOREIGN KEY (ID) REFERENCES Teams (ID));");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS ScoreHistory(ID INTEGER PRIMARY KEY AUTOINCREMENT,TeamID INTEGER NOT NULL,Score1 INTEGER, Score2 INTEGER, FOREIGN KEY (ID) REFERENCES Teams (ID));");
        mydatabase.execSQL("INSERT INTO Teams(Team1,Team2) VALUES('Alexandros','Tselios');");
    }

    public void saveRoundScore(int score1,int score2){
        String index;
        Cursor resultSet = mydatabase.rawQuery("SELECT max(ID) FROM Teams;",null);
        resultSet.moveToFirst();
        index = resultSet.getString(0);
        mydatabase.execSQL("INSERT INTO ScoreHistory(TeamID,Score1,Score2) VALUES("+Integer.parseInt(index)+","+score1+","+score2+");");
    }

    public static void revertScore(){
        String score;
        Cursor resultSet = mydatabase.rawQuery("SELECT count(*) FROM ScoreHistory ORDER BY ID DESC;",null);
        resultSet.moveToFirst();
        if(resultSet.getInt(0)>0){
            resultSet = mydatabase.rawQuery("SELECT * FROM ScoreHistory ORDER BY ID DESC;",null);
            resultSet.moveToPosition(1);
            score = resultSet.getString(2);
            GUI.TextScore1.setText(score);
            score = resultSet.getString(3);
            GUI.TextScore2.setText(score);
        }
    }

    public static void saveFinalScore(int score1,int score2) {
        String index;
        Cursor resultSet = mydatabase.rawQuery("SELECT max(ID) FROM Teams;",null);
        resultSet.moveToFirst();
        index = resultSet.getString(0);
        mydatabase.execSQL("INSERT INTO FinalScore(TeamID,Score1,Score2) VALUES("+Integer.parseInt(index)+","+score1+","+score2+");");
    }

    public void loadScore() {
        String index;
        Cursor resultSet = mydatabase.rawQuery("SELECT count(*) FROM FinalScore ORDER BY ID DESC;",null);
        resultSet.moveToFirst();
        if(resultSet.getInt(0)>0){
            resultSet = mydatabase.rawQuery("SELECT * FROM FinalScore ORDER BY ID DESC;",null);
            resultSet.moveToFirst();
            index = resultSet.getString(2);
            GUI.TextScore1.setText(index);
            index = resultSet.getString(3);
            GUI.TextScore2.setText(index);
        }
    }
}