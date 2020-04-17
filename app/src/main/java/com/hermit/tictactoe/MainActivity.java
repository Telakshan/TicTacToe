package com.hermit.tictactoe;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int isActive = 0;
    int[] arr = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    boolean game = true;
    int[][] winningMatrix = {
            {0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},

            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},

            {0, 4, 8},
            {2, 4, 6}
    };


    public void onClick(View view){
        ImageView counter = (ImageView) view;
        counter.getTag();
        counter.setTranslationY(-1500);
        int tag = Integer.parseInt(counter.getTag().toString());

        if(arr[tag] == 2 && game) {
            arr[tag] = isActive;
      /*  if(isActive == 1){
            counter.setImageResource(R.drawable.o);
            isActive = 0;
        }else{
            counter.setImageResource(R.drawable.x);
            isActive = 1;
        }*/
            if (isActive == 0) {
                counter.setImageResource(R.drawable.x);
                isActive = 1;
            } else {
                counter.setImageResource(R.drawable.o);
                isActive = 0;
            }
            counter.animate().translationYBy(1500).setDuration(300);

            for (int[] i : winningMatrix) {

                if (arr[i[0]] == arr[i[1]] && arr[i[1]] == arr[i[2]] && arr[i[0]] != 2) {
                    Log.i("Info: ", "reached here");
                    game = false;
                    String xOrO = "";
                    xOrO = (isActive != 0) ? "X" : "O";
                    Button reset = findViewById(R.id.reset);

                    TextView winner = findViewById(R.id.textView);
                    winner.setText("Player " + xOrO + " has won, would you like to reset?");

                    reset.setVisibility(View.VISIBLE);
                    winner.setVisibility(View.VISIBLE);
                }
            }

        }

            if(!check(arr) && game){
                Button reset = findViewById(R.id.reset);
                TextView winner = findViewById(R.id.textView);
                winner.setText("No one has won. Would you like to reset?");
                reset.setVisibility(View.VISIBLE);
                winner.setVisibility(View.VISIBLE);
            }

        }


    public boolean check(int[] arr){
        for(int i : arr){
            if(i == 2) return true;
        }
        return false;
    }

    public void reset(View view){
        Button reset = findViewById(R.id.reset);
        TextView winner = findViewById(R.id.textView);
        androidx.gridlayout.widget.GridLayout myGrid = findViewById(R.id.gridLayout);

        reset.setVisibility(View.INVISIBLE);
        winner.setVisibility(View.INVISIBLE);

        for(int i=0 ; i<myGrid.getChildCount(); i++){

            ImageView img = (ImageView) myGrid.getChildAt(i);
            img.setImageDrawable(null);
        }
         isActive = 0;
         for(int i=0; i<arr.length; i++){
             arr[i] = 2;
         }
         game = true;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
