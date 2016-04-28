package edu.augustana.snackers.binaryhero;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ToggleButton;
import android.widget.EditText;

import com.com.example.nelly.binaryhero.R;


public class MainPageActivity extends AppCompatActivity {

    private boolean isBinary;
    ToggleButton baseSwitch;
    Typeface myTypeface;
    public int passWordLevel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baseSwitch = (ToggleButton) findViewById(R.id.toggleButton);
        myTypeface = Typeface.createFromAsset(getAssets(), "Sansation-Regular.ttf");


        baseSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    isBinary = true;
                } else {
                    isBinary = false;
                }
                // true if the switch is in the On position
            }
        });
        if (baseSwitch.isChecked()) {
            isBinary = true;
        } else {
            isBinary = false;
        }

        Button startBtn = (Button) findViewById(R.id.start_btn);

        assert startBtn != null;
        startBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn) {
                Intent intent = new Intent(getApplicationContext(), GameArenaActivity.class);
                Bundle extras = new Bundle();
                EditText passWordText = (EditText) findViewById(R.id.password_Field);
                assert passWordText != null;
                passWordText.setTypeface(myTypeface);

                //Kaylee I pulled, it didn't commit so I made some changes
                if(!isEmpty(passWordText)){
                    for(int i = 0 ; i < LevelsDatabase.passwords.length; i++){
                        if(LevelsDatabase.passwords[i]==Integer.parseInt((passWordText.getText().toString()))){
                            passWordLevel = i + 1;
                        }
                    }
                }

                //passWordLevel is started at 0 if no correct password
                //does this work?  need start level method normal start was at 0
                extras.putInt("PLAYER_LEVEL", passWordLevel);


                extras.putBoolean("GAME_MODE", isBinary);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });


        Button settingBtn = (Button) findViewById(R.id.settings_btn);
        assert settingBtn != null;
        settingBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View btn) {
                Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
        });

        startBtn.setTypeface(myTypeface);
        settingBtn.setTypeface(myTypeface);
        baseSwitch.setTypeface(myTypeface);

    }


        public boolean isEmpty(EditText passwordText){
            if(passwordText.getText().toString().trim().length() > 0) {
                return false;
            }
            return true;

        }
}


