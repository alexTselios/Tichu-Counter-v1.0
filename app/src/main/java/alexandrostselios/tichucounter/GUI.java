package alexandrostselios.tichucounter;

import android.annotation.SuppressLint;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuItem;

public class GUI extends AppCompatActivity {
    private TextView roundScore = null;

    private Button tichu1 = null;
    private Button grandTichu1 = null;
    private Button tichu2 = null;
    private Button grandTichu2 = null;

    private EditText TextScore1 = null;
    private EditText currentScore1 = null;
    private EditText TextScore2 = null;
    private EditText currentScore2 = null;

    private CheckBox tichuCheck1 = null;
    private CheckBox grandTichuCheck1 = null;
    private CheckBox tichuCheck2 = null;
    private CheckBox grandTichuCheck2 = null;

    private int teamTichu1 = 0;
    private int teamGrandTichu1 = 0;
    private int score1 = 0;
    private int teamTichu2 = 0;
    private int teamGrandTichu2 = 0;
    private int score2 = 0;

    private TichuCounter tichuCounter=null;
    private boolean win = false;
    private int error=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createButtons();
        createEditText();
        createCheckBox();
        playGame();
    }

    @SuppressLint("ResourceType")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.layout.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_new_game:
                clearScore();
                return true;
            case R.id.menu_save_game:
                Toast.makeText(GUI.this, "Save Game is Selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_load_game:
                Toast.makeText(GUI.this, "Load Game is Selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menu_about:
                Toast.makeText(GUI.this, "Version: 3.0", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createButtons(){
        roundScore = (TextView) findViewById(R.id.roundPoints);
        roundScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tichuCounter.checkScore(currentScore1,currentScore2);
                setScore();
            }
        });

        tichu1 = (Button) findViewById(R.id.tichu1);
        tichu1.setBackgroundResource(android.R.drawable.btn_default);
        tichu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(teamTichu1==0){
                    teamTichu1 = 1;
                    tichu1.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
                }else{
                    teamTichu1 = 0;
                    tichu1.getBackground().clearColorFilter();
                }
            }
        });

        grandTichu1 = (Button) findViewById(R.id.grandTichu1);
        grandTichu1.setBackgroundResource(android.R.drawable.btn_default);
        grandTichu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(teamGrandTichu1==0){
                    teamGrandTichu1 = 1;
                    grandTichu1.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
                }else{
                    teamGrandTichu1 = 0;
                    grandTichu1.getBackground().clearColorFilter();
                }
            }
        });

        tichu2 = (Button) findViewById(R.id.tichu2);
        tichu2.setBackgroundResource(android.R.drawable.btn_default);
        tichu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(teamTichu2==0){
                    teamTichu2 = 1;
                    tichu2.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
                }
                else{
                    teamTichu2 = 0;
                    tichu2.getBackground().clearColorFilter();
                }
            }
        });

        grandTichu2 = (Button) findViewById(R.id.grandTichu2);
        grandTichu2.setBackgroundResource(android.R.drawable.btn_default);
        grandTichu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(teamGrandTichu2==0){
                    teamGrandTichu2 = 1;
                    grandTichu2.getBackground().setColorFilter(0xFFFF0000, PorterDuff.Mode.MULTIPLY);
                }else{
                    teamGrandTichu2 = 0;
                    grandTichu2.getBackground().clearColorFilter();
                }
            }
        });
    }

    private void createEditText() {
        currentScore1 = (EditText) findViewById(R.id.currentScore1);
        currentScore2 = (EditText) findViewById(R.id.currentScore2);
        TextScore1 =  findViewById(R.id.score1EditText);
        TextScore2 =  findViewById(R.id.score2EditText);
    }

    private void createCheckBox(){
        tichuCheck1 = (CheckBox) findViewById(R.id.tichuCheck1);
        grandTichuCheck1 = (CheckBox) findViewById(R.id.grandTichuCheck1);
        tichuCheck2 = (CheckBox) findViewById(R.id.tichuCheck2);
        grandTichuCheck2 = (CheckBox) findViewById(R.id.grandTichuCheck2);
    }

    private void playGame(){
        tichuCounter = new TichuCounter(GUI.this);
        tichuCounter.isWinner();
    }

    public void setScore() {
        tichuCounter.checkTichuStatus(teamTichu1,tichuCheck1,grandTichu1,teamGrandTichu1,grandTichuCheck1,teamTichu2,tichuCheck2,grandTichu2,teamGrandTichu2,grandTichuCheck2);
        if(error==0){
            TextScore1.setText(String.valueOf(tichuCounter.getScoreTeam1()));
            TextScore2.setText(String.valueOf(tichuCounter.getScoreTeam2()));
            clear();
        }else {
            error = 0;
        }
    }

    private void clearScore() {
        score1=0;
        score2=0;

        TextScore1.setText(String.valueOf(""));
        TextScore2.setText(String.valueOf(""));

        teamTichu1=0;
        teamGrandTichu1=0;
        tichuCheck1.setChecked(false);
        grandTichuCheck1.setChecked(false);

        teamTichu2=0;
        teamGrandTichu2=0;
        tichuCheck2.setChecked(false);
        grandTichuCheck2.setChecked(false);

        tichu1.getBackground().clearColorFilter();
        tichu2.getBackground().clearColorFilter();
        grandTichu1.getBackground().clearColorFilter();
        grandTichu2.getBackground().clearColorFilter();
        currentScore1.setText(String.valueOf(""));
        currentScore2.setText(String.valueOf(""));
        playGame();
    }

    private void clear(){
        teamTichu1=0;
        teamGrandTichu1=0;
        tichuCheck1.setChecked(false);
        grandTichuCheck1.setChecked(false);

        teamTichu2=0;
        teamGrandTichu2=0;
        tichuCheck2.setChecked(false);
        grandTichuCheck2.setChecked(false);

        tichu1.getBackground().clearColorFilter();
        tichu2.getBackground().clearColorFilter();
        grandTichu1.getBackground().clearColorFilter();
        grandTichu2.getBackground().clearColorFilter();
        currentScore1.setText(String.valueOf(""));
        currentScore2.setText(String.valueOf(""));
    }
}