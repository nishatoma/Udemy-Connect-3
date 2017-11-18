package com.example.league95.connect3;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int whichPressed = 0;
    // buttons we pressed so far!!
    int[] pressed = {2,2,2,2,2,2,2,2,2};
    //See if game is still active
    boolean isPlaying = true;
    int[][] winningPositions = {{0,1,2},
                                {3,4,5},
                                {6,7,8},
                                {0,3,6},
                                {1,4,7},
                                {2,5,8},
                                {0,4,8},
                                {2,4,6}};


    public void drop(View v)
    {
        ImageView img = (ImageView) v;
        //get index using tag!!
        int index = Integer.parseInt(img.getTag().toString());
        if (pressed[index] == 2 && isPlaying)
        {
            pressed[index] = whichPressed;
            if (whichPressed == 0)
            {
                img.setImageResource(R.drawable.red);
                whichPressed = 1;
            } else
            {
                img.setImageResource(R.drawable.black);
                whichPressed = 0;
            }
            for (int[] winningPosition : winningPositions)
            {
                if (pressed[winningPosition[0]] == pressed[winningPosition[1]]
                 && pressed[winningPosition[1]] == pressed[winningPosition[2]]
                 && pressed[winningPosition[0]] != 2)
                {
                    //Game ends
                    isPlaying = false;
                    //Toast.makeText(this, "Winning", Toast.LENGTH_SHORT).show();
                    //Someone has won!
                    LinearLayout linear = (LinearLayout) findViewById(R.id.playAgain);
                    TextView txt = (TextView) findViewById(R.id.textView);
                    String winner = (whichPressed == 0) ? "black" : "red";
                    txt.setText(winner + " wins!");
                    if (linear.getVisibility() == linear.INVISIBLE)
                    {
                        linear.setVisibility(linear.VISIBLE);
                    }
                } else //no one wins
                {
                    int ct = 0;
                    for (int i = 0; i < pressed.length; i++)
                    {
                        if (pressed[i] != 2)
                        {
                            ct++;
                            if (ct == 9)
                            {
                                LinearLayout linear = (LinearLayout) findViewById(R.id.playAgain);
                                if (linear.getVisibility() == linear.INVISIBLE)
                                {
                                    linear.setVisibility(linear.VISIBLE);
                                }
                                TextView txt = (TextView) findViewById(R.id.textView);
                                //String winner = (whichPressed == 0) ? "black" : "red";
                                txt.setText("Draw!");
                            }
                        }
                    }
                }
            }
        }



    }

    public void play(View v)
    {
        //Initiate game again
        isPlaying = true;
        //initialize the linear layout again to be invisible
        LinearLayout linear = (LinearLayout) findViewById(R.id.playAgain);
        linear.setVisibility(v.INVISIBLE);
        //reset button pressed
        whichPressed = 0;
        // buttons we pressed so far!!
        //cannot do what i tried to do below
        //just update each item
        //pressed = {2,2,2,2,2,2,2,2,2};

        for (int i = 0; i < pressed.length; i++)
        {
            pressed[i] = 2;
        }
        //Also reset grid images
        GridLayout grid = (GridLayout) findViewById(R.id.grid);
        for (int i = 0; i < grid.getChildCount(); i++)
        {
            ((ImageView) grid.getChildAt(i)).setImageResource(0);
        }

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
