package com.example.www.sdoku;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    Button Select[];
    Button B[][];
    String a;

    int[][] sdoku_easy = {{0,1,0,9,0,0,0,8,7},{0,0,0,2,0,0,0,0,6},{0,0,0,0,0,3,2,1,0},{0,0,1,0,4,5,0,0,0},{0,0,2,1,0,8,9,0,0},{0,0,0,3,2,0,6,0,0},{0,9,3,8,0,0,0,0,0},{7,0,0,0,0,1,0,0,0},{5,8,0,0,0,6,0,9,0}},
            sdoku_normal = {{0,8,0,2,0,9,6,0,0}, {0,0,0,0,0,0,9,0,1}, {1,9,6,0,8,0,0,0,0}, {0,0,4,6,0,2,0,5,0}, {0,0,0,0,9,0,0,0,0}, {0,2,0,1,0,5,8,0,0}, {0,0,0,0,3,0,5,1,4}, {3,0,7,0,0,0,0,0,0}, {0,0,1,5,0,8,0,6,0}},
            sdoku_hard = {{2,0,0,4,0,0,0,0,6},{0,0,1,0,0,0,5,0,0},{0,3,0,0,0,7,2,0,8},{7,0,0,8,1,0,0,3,9},{3,9,0,0,7,0,0,0,0},{4,0,0,3,0,0,0,0,2},{0,2,0,0,0,8,0,0,1},{0,0,4,0,9,5,0,6,0},{0,0,3,0,0,1,0,2,0}},
            sdoku_hell = {{8,0,0,0,0,0,0,0,0},{0,0,3,6,0,0,0,0,0},{0,7,0,0,9,0,2,0,0},{0,5,0,0,0,7,0,0,0},{0,0,0,0,4,5,7,0,0},{0,0,0,1,0,0,0,3,0},{0,0,1,0,0,0,0,6,8},{0,0,8,5,0,0,0,1,0},{0,9,0,0,0,0,4,0,0}};

    //각 셀에 입력된 자료가 오답(오답이면 버튼 컬러가 빨갛게 변해서 배열명 isRed)인지를 판별
    Boolean[][] isRed = {{false, false, false, false, false, false, false, false, false}, {false, false, false, false, false, false, false, false, false}, {false, false, false, false, false, false, false, false, false}, {false, false, false, false, false, false, false, false, false}, {false, false, false, false, false, false, false, false, false}, {false, false, false, false, false, false, false, false, false}, {false, false, false, false, false, false, false, false, false}, {false, false, false, false, false, false, false, false, false}, {false, false, false, false, false, false, false, false, false}};


    int x = 10, y = 10, level = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        B = new Button[9][9];
        Select = new Button[10];

        int[] SelectID = {R.id.Select0, R.id.Select1, R.id.Select2, R.id.Select3, R.id.Select4, R.id.Select5, R.id.Select6, R.id.Select7, R.id.Select8, R.id.Select9};

        for (int i = 0; i < 10; i++) {
            Select[i] = (Button) findViewById(SelectID[i]);
        }

        for (int i = 0; i < 10; i++) {
            Select[i].setClickable(false);
            Select[i].setTextColor(Color.rgb(50, 50, 50));
        }

        int[][] BID = {{R.id.B00, R.id.B01, R.id.B02, R.id.B03, R.id.B04, R.id.B05, R.id.B06, R.id.B07, R.id.B08}, {R.id.B10, R.id.B11, R.id.B12, R.id.B13, R.id.B14, R.id.B15, R.id.B16, R.id.B17, R.id.B18,},
                {R.id.B20, R.id.B21, R.id.B22, R.id.B23, R.id.B24, R.id.B25, R.id.B26, R.id.B27, R.id.B28}, {R.id.B30, R.id.B31, R.id.B32, R.id.B33, R.id.B34, R.id.B35, R.id.B36, R.id.B37, R.id.B38},
                {R.id.B40, R.id.B41, R.id.B42, R.id.B43, R.id.B44, R.id.B45, R.id.B46, R.id.B47, R.id.B48}, {R.id.B50, R.id.B51, R.id.B52, R.id.B53, R.id.B54, R.id.B55, R.id.B56, R.id.B57, R.id.B58},
                {R.id.B60, R.id.B61, R.id.B62, R.id.B63, R.id.B64, R.id.B65, R.id.B66, R.id.B67, R.id.B68}, {R.id.B70, R.id.B71, R.id.B72, R.id.B73, R.id.B74, R.id.B75, R.id.B76, R.id.B77, R.id.B78},
                {R.id.B80, R.id.B81, R.id.B82, R.id.B83, R.id.B84, R.id.B85, R.id.B86, R.id.B87, R.id.B88}};

        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                B[i][j] = (Button)findViewById(BID[i][j]);
            }
        }
        initialize();
        Resetcolor();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.difficulty, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.reset) {
            Resetcolor();
            initialize();
        } else if (item.getItemId() == R.id.easy) {
            Resetcolor();
            level = 1;
            initialize();
        } else if (item.getItemId() == R.id.normal) {
            Resetcolor();
            level = 2;
            initialize();
        } else if (item.getItemId() == R.id.hard) {
            Resetcolor();
            level = 3;
            initialize();
        } else if (item.getItemId() == R.id.hell) {
            Resetcolor();
            level = 4;
            initialize();
        }
        return super.onOptionsItemSelected(item);
    }
    
    //배열 위치 알아내는 코드 도저히 생각이 안나서 그냥 각각의 버튼 클릭을 처리하는 과정을 다 만들었습니다. 그냥 쭉 내리셔도 됩니다. 죄송합니다 ㅠㅠ
    public void b00(View v) {
        Resetcolor();
        if (x == 0 && y == 0) {
            x = 10;
            y = 10;
        } else {
            x = 0;
            y = 0;
            Changecolor();
        }
    }

    public void b01(View v) {
        Resetcolor();
        if (x == 0 && y == 1) {
            x = 10;
            y = 10;
        } else {
            x = 0;
            y = 1;
            Changecolor();
        }
    }

    public void b02(View v) {
        Resetcolor();
        if (x == 0 && y == 2) {
            x = 10;
            y = 10;
        } else {
            x = 0;
            y = 2;
            Changecolor();
        }
    }

    public void b03(View v) {
        Resetcolor();
        if (x == 0 && y == 3) {
            x = 10;
            y = 10;
        } else {
            x = 0;
            y = 3;
            Changecolor();
        }
    }

    public void b04(View v) {
        Resetcolor();
        if (x == 0 && y == 4) {
            x = 10;
            y = 10;
        } else {
            x = 0;
            y = 4;
            Changecolor();
        }
    }

    public void b05(View v) {
        Resetcolor();
        if (x == 0 && y == 5) {
            x = 10;
            y = 10;
        } else {
            x = 0;
            y = 5;
            Changecolor();
        }
    }

    public void b06(View v) {
        Resetcolor();
        if (x == 0 && y == 6) {
            x = 10;
            y = 10;
        } else {
            x = 0;
            y = 6;
            Changecolor();
        }
    }

    public void b07(View v) {
        Resetcolor();
        if (x == 0 && y == 7) {
            x = 10;
            y = 10;
        } else {
            x = 0;
            y = 7;
            Changecolor();
        }
    }

    public void b08(View v) {
        Resetcolor();
        if (x == 0 && y == 8) {
            x = 10;
            y = 10;
        } else {
            x = 0;
            y = 8;
            Changecolor();
        }
    }

    public void b10(View v) {
        Resetcolor();
        if (x == 1 && y == 0) {
            x = 10;
            y = 10;
        } else {
            x = 1;
            y = 0;
            Changecolor();
        }
    }

    public void b11(View v) {
        Resetcolor();
        if (x == 1 && y == 1) {
            x = 10;
            y = 10;
        } else {
            x = 1;
            y = 1;
            Changecolor();
        }
    }

    public void b12(View v) {
        Resetcolor();
        if (x == 1 && y == 2) {
            x = 10;
            y = 10;
        } else {
            x = 1;
            y = 2;
            Changecolor();
        }
    }

    public void b13(View v) {
        Resetcolor();
        if (x == 1 && y == 3) {
            x = 10;
            y = 10;
        } else {
            x = 1;
            y = 3;
            Changecolor();
        }
    }

    public void b14(View v) {
        Resetcolor();
        if (x == 1 && y == 4) {
            x = 10;
            y = 10;
        } else {
            x = 1;
            y = 4;
            Changecolor();
        }
    }

    public void b15(View v) {
        Resetcolor();
        if (x == 1 && y == 5) {
            x = 10;
            y = 10;
        } else {
            x = 1;
            y = 5;
            Changecolor();
        }
    }

    public void b16(View v) {
        Resetcolor();
        if (x == 1 && y == 6) {
            x = 10;
            y = 10;
        } else {
            x = 1;
            y = 6;
            Changecolor();
        }
    }

    public void b17(View v) {
        Resetcolor();
        if (x == 1 && y == 7) {
            x = 10;
            y = 10;
        } else {
            x = 1;
            y = 7;
            Changecolor();
        }
    }

    public void b18(View v) {
        Resetcolor();
        if (x == 1 && y == 8) {
            x = 10;
            y = 10;
        } else {
            x = 1;
            y = 8;
            Changecolor();
        }
    }

    public void b20(View v) {
        Resetcolor();
        if (x == 2 && y == 0) {
            x = 10;
            y = 10;
        } else {
            x = 2;
            y = 0;
            Changecolor();
        }
    }

    public void b21(View v) {
        Resetcolor();
        if (x == 2 && y == 1) {
            x = 10;
            y = 10;
        } else {
            x = 2;
            y = 1;
            Changecolor();
        }
    }

    public void b22(View v) {
        Resetcolor();
        if (x == 2 && y == 2) {
            x = 10;
            y = 10;
        } else {
            x = 2;
            y = 2;
            Changecolor();
        }
    }

    public void b23(View v) {
        Resetcolor();
        if (x == 2 && y == 3) {
            x = 10;
            y = 10;
        } else {
            x = 2;
            y = 3;
            Changecolor();
        }
    }

    public void b24(View v) {
        Resetcolor();
        if (x == 2 && y == 4) {
            x = 10;
            y = 10;
        } else {
            x = 2;
            y = 4;
            Changecolor();
        }
    }

    public void b25(View v) {
        Resetcolor();
        if (x == 2 && y == 5) {
            x = 10;
            y = 10;
        } else {
            x = 2;
            y = 5;
            Changecolor();
        }
    }

    public void b26(View v) {
        Resetcolor();
        if (x == 2 && y == 6) {
            x = 10;
            y = 10;
        } else {
            x = 2;
            y = 6;
            Changecolor();
        }
    }

    public void b27(View v) {
        Resetcolor();
        if (x == 2 && y == 7) {
            x = 10;
            y = 10;
        } else {
            x = 2;
            y = 7;
            Changecolor();
        }
    }

    public void b28(View v) {
        Resetcolor();
        if (x == 2 && y == 8) {
            x = 10;
            y = 10;
        } else {
            x = 2;
            y = 8;
            Changecolor();
        }
    }

    public void b30(View v) {
        Resetcolor();
        if (x == 3 && y == 0) {
            x = 10;
            y = 10;
        } else {
            x = 3;
            y = 0;
            Changecolor();
        }
    }

    public void b31(View v) {
        Resetcolor();
        if (x == 3 && y == 1) {
            x = 10;
            y = 10;
        } else {
            x = 3;
            y = 1;
            Changecolor();
        }
    }

    public void b32(View v) {
        Resetcolor();
        if (x == 3 && y == 2) {
            x = 10;
            y = 10;
        } else {
            x = 3;
            y = 2;
            Changecolor();
        }
    }

    public void b33(View v) {
        Resetcolor();
        if (x == 3 && y == 3) {
            x = 10;
            y = 10;
        } else {
            x = 3;
            y = 3;
            Changecolor();
        }
    }

    public void b34(View v) {
        Resetcolor();
        if (x == 3 && y == 4) {
            x = 10;
            y = 10;
        } else {
            x = 3;
            y = 4;
            Changecolor();
        }
    }

    public void b35(View v) {
        Resetcolor();
        if (x == 3 && y == 5) {
            x = 10;
            y = 10;
        } else {
            x = 3;
            y = 5;
            Changecolor();
        }
    }

    public void b36(View v) {
        Resetcolor();
        if (x == 3 && y == 6) {
            x = 10;
            y = 10;
        } else {
            x = 3;
            y = 6;
            Changecolor();
        }
    }

    public void b37(View v) {
        Resetcolor();
        if (x == 3 && y == 7) {
            x = 10;
            y = 10;
        } else {
            x = 3;
            y = 7;
            Changecolor();
        }
    }

    public void b38(View v) {
        Resetcolor();
        if (x == 3 && y == 8) {
            x = 10;
            y = 10;
        } else {
            x = 3;
            y = 8;
            Changecolor();
        }
    }

    public void b40(View v) {
        Resetcolor();
        if (x == 4 && y == 0) {
            x = 10;
            y = 10;
        } else {
            x = 4;
            y = 0;
            Changecolor();
        }
    }

    public void b41(View v) {
        Resetcolor();
        if (x == 4 && y == 1) {
            x = 10;
            y = 10;
        } else {
            x = 4;
            y = 1;
            Changecolor();
        }
    }

    public void b42(View v) {
        Resetcolor();
        if (x == 4 && y == 2) {
            x = 10;
            y = 10;
        } else {
            x = 4;
            y = 2;
            Changecolor();
        }
    }

    public void b43(View v) {
        Resetcolor();
        if (x == 4 && y == 3) {
            x = 10;
            y = 10;
        } else {
            x = 4;
            y = 3;
            Changecolor();
        }
    }

    public void b44(View v) {
        Resetcolor();
        if (x == 4 && y == 4) {
            x = 10;
            y = 10;
        } else {
            x = 4;
            y = 4;
            Changecolor();
        }
    }

    public void b45(View v) {
        Resetcolor();
        if (x == 4 && y == 5) {
            x = 10;
            y = 10;
        } else {
            x = 4;
            y = 5;
            Changecolor();
        }
    }

    public void b46(View v) {
        Resetcolor();
        if (x == 4 && y == 6) {
            x = 10;
            y = 10;
        } else {
            x = 4;
            y = 6;
            Changecolor();
        }
    }

    public void b47(View v) {
        Resetcolor();
        if (x == 4 && y == 7) {
            x = 10;
            y = 10;
        } else {
            x = 4;
            y = 7;
            Changecolor();
        }
    }

    public void b48(View v) {
        Resetcolor();
        if (x == 4 && y == 8) {
            x = 10;
            y = 10;
        } else {
            x = 4;
            y = 8;
            Changecolor();
        }
    }

    public void b50(View v) {
        Resetcolor();
        if (x == 5 && y == 0) {
            x = 10;
            y = 10;
        } else {
            x = 5;
            y = 0;
            Changecolor();
        }
    }

    public void b51(View v) {
        Resetcolor();
        if (x == 5 && y == 1) {
            x = 10;
            y = 10;
        } else {
            x = 5;
            y = 1;
            Changecolor();
        }
    }

    public void b52(View v) {
        Resetcolor();
        if (x == 5 && y == 2) {
            x = 10;
            y = 10;
        } else {
            x = 5;
            y = 2;
            Changecolor();
        }
    }

    public void b53(View v) {
        Resetcolor();
        if (x == 5 && y == 3) {
            x = 10;
            y = 10;
        } else {
            x = 5;
            y = 3;
            Changecolor();
        }
    }

    public void b54(View v) {
        Resetcolor();
        if (x == 5 && y == 4) {
            x = 10;
            y = 10;
        } else {
            x = 5;
            y = 4;
            Changecolor();
        }
    }

    public void b55(View v) {
        Resetcolor();
        if (x == 5 && y == 5) {
            x = 10;
            y = 10;
        } else {
            x = 5;
            y = 5;
            Changecolor();
        }
    }

    public void b56(View v) {
        Resetcolor();
        if (x == 5 && y == 6) {
            x = 10;
            y = 10;
        } else {
            x = 5;
            y = 6;
            Changecolor();
        }
    }

    public void b57(View v) {
        Resetcolor();
        if (x == 5 && y == 7) {
            x = 10;
            y = 10;
        } else {
            x = 5;
            y = 7;
            Changecolor();
        }
    }

    public void b58(View v) {
        Resetcolor();
        if (x == 5 && y == 8) {
            x = 10;
            y = 10;
        } else {
            x = 5;
            y = 8;
            Changecolor();
        }
    }

    public void b60(View v) {
        Resetcolor();
        if (x == 6 && y == 0) {
            x = 10;
            y = 10;
        } else {
            x = 6;
            y = 0;
            Changecolor();
        }
    }

    public void b61(View v) {
        Resetcolor();
        if (x == 6 && y == 1) {
            x = 10;
            y = 10;
        } else {
            x = 6;
            y = 1;
            Changecolor();
        }
    }

    public void b62(View v) {
        Resetcolor();
        if (x == 6 && y == 2) {
            x = 10;
            y = 10;
        } else {
            x = 6;
            y = 2;
            Changecolor();
        }
    }

    public void b63(View v) {
        Resetcolor();
        if (x == 6 && y == 3) {
            x = 10;
            y = 10;
        } else {
            x = 6;
            y = 3;
            Changecolor();
        }
    }

    public void b64(View v) {
        Resetcolor();
        if (x == 6 && y == 4) {
            x = 10;
            y = 10;
        } else {
            x = 6;
            y = 4;
            Changecolor();
        }
    }

    public void b65(View v) {
        Resetcolor();
        if (x == 6 && y == 5) {
            x = 10;
            y = 10;
        } else {
            x = 6;
            y = 5;
            Changecolor();
        }
    }

    public void b66(View v) {
        Resetcolor();
        if (x == 6 && y == 6) {
            x = 10;
            y = 10;
        } else {
            x = 6;
            y = 6;
            Changecolor();
        }
    }

    public void b67(View v) {
        Resetcolor();
        if (x == 6 && y == 7) {
            x = 10;
            y = 10;
        } else {
            x = 6;
            y = 7;
            Changecolor();
        }
    }

    public void b68(View v) {
        Resetcolor();
        if (x == 6 && y == 8) {
            x = 10;
            y = 10;
        } else {
            x = 6;
            y = 8;
            Changecolor();
        }
    }

    public void b70(View v) {
        Resetcolor();
        if (x == 7 && y == 0) {
            x = 10;
            y = 10;
        } else {
            x = 7;
            y = 0;
            Changecolor();
        }
    }

    public void b71(View v) {
        Resetcolor();
        if (x == 7 && y == 1) {
            x = 10;
            y = 10;
        } else {
            x = 7;
            y = 1;
            Changecolor();
        }
    }

    public void b72(View v) {
        Resetcolor();
        if (x == 7 && y == 2) {
            x = 10;
            y = 10;
        } else {
            x = 7;
            y = 2;
            Changecolor();
        }
    }

    public void b73(View v) {
        Resetcolor();
        if (x == 7 && y == 3) {
            x = 10;
            y = 10;
        } else {
            x = 7;
            y = 3;
            Changecolor();
        }
    }

    public void b74(View v) {
        Resetcolor();
        if (x == 7 && y == 4) {
            x = 10;
            y = 10;
        } else {
            x = 7;
            y = 4;
            Changecolor();
        }
    }

    public void b75(View v) {
        Resetcolor();
        if (x == 7 && y == 5) {
            x = 10;
            y = 10;
        } else {
            x = 7;
            y = 5;
            Changecolor();
        }
    }

    public void b76(View v) {
        Resetcolor();
        if (x == 7 && y == 6) {
            x = 10;
            y = 10;
        } else {
            x = 7;
            y = 6;
            Changecolor();
        }
    }

    public void b77(View v) {
        Resetcolor();
        if (x == 7 && y == 7) {
            x = 10;
            y = 10;
        } else {
            x = 7;
            y = 7;
            Changecolor();
        }
    }

    public void b78(View v) {
        Resetcolor();
        if (x == 7 && y == 8) {
            x = 10;
            y = 10;
        } else {
            x = 7;
            y = 8;
            Changecolor();
        }
    }

    public void b80(View v) {
        Resetcolor();
        if (x == 8 && y == 0) {
            x = 10;
            y = 10;
        } else {
            x = 8;
            y = 0;
            Changecolor();
        }
    }

    public void b81(View v) {
        Resetcolor();
        if (x == 8 && y == 1) {
            x = 10;
            y = 10;
        } else {
            x = 8;
            y = 1;
            Changecolor();
        }
    }

    public void b82(View v) {
        Resetcolor();
        if (x == 8 && y == 2) {
            x = 10;
            y = 10;
        } else {
            x = 8;
            y = 2;
            Changecolor();
        }
    }

    public void b83(View v) {
        Resetcolor();
        if (x == 8 && y == 3) {
            x = 10;
            y = 10;
        } else {
            x = 8;
            y = 3;
            Changecolor();
        }
    }

    public void b84(View v) {
        Resetcolor();
        if (x == 8 && y == 4) {
            x = 10;
            y = 10;
        } else {
            x = 8;
            y = 4;
            Changecolor();
        }
    }

    public void b85(View v) {
        Resetcolor();
        if (x == 8 && y == 5) {
            x = 10;
            y = 10;
        } else {
            x = 8;
            y = 5;
            Changecolor();
        }
    }

    public void b86(View v) {
        Resetcolor();
        if (x == 8 && y == 6) {
            x = 10;
            y = 10;
        } else {
            x = 8;
            y = 6;
            Changecolor();
        }
    }

    public void b87(View v) {
        Resetcolor();
        if (x == 8 && y == 7) {
            x = 10;
            y = 10;
        } else {
            x = 8;
            y = 7;
            Changecolor();
        }
    }

    public void b88(View v) {
        Resetcolor();
        if (x == 8 && y == 8) {
            x = 10;
            y = 10;
        } else {
            x = 8;
            y = 8;
            Changecolor();
        }
    }

    //숫자 입력부

    public void s0(View v) {
        B[x][y].setText(" ");
        B[x][y].setBackgroundColor(Color.rgb(255, 200, 200));
        isRed[x][y] = false;
        RedCheck();
        Resetcolor();
        x = 10;
        y = 10;
    }

    public void s1(View v) {
        B[x][y].setText("1");
        CellCheck(x, y);
        afterCellCheck();
        WInCheck();
        RedCheck();
        Resetcolor();
    }

    public void s2(View v) {
        B[x][y].setText("2");
        CellCheck(x, y);
        afterCellCheck();
        WInCheck();
        RedCheck();
        Resetcolor();
    }

    public void s3(View v) {
        B[x][y].setText("3");
        CellCheck(x, y);
        afterCellCheck();
        WInCheck();
        RedCheck();
        Resetcolor();
    }

    public void s4(View v) {
        B[x][y].setText("4");
        CellCheck(x, y);
        afterCellCheck();
        WInCheck();
        RedCheck();
        Resetcolor();
    }

    public void s5(View v) {
        B[x][y].setText("5");
        CellCheck(x, y);
        afterCellCheck();
        WInCheck();
        RedCheck();
        Resetcolor();
    }

    public void s6(View v) {
        B[x][y].setText("6");
        CellCheck(x, y);
        afterCellCheck();
        WInCheck();
        RedCheck();
        Resetcolor();
    }

    public void s7(View v) {
        B[x][y].setText("7");
        CellCheck(x, y);
        afterCellCheck();
        WInCheck();
        RedCheck();
        Resetcolor();
    }

    public void s8(View v) {
        B[x][y].setText("8");
        CellCheck(x, y);
        afterCellCheck();
        WInCheck();
        RedCheck();
        Resetcolor();
    }

    public void s9(View v) {
        B[x][y].setText("9");
        CellCheck(x, y);
        afterCellCheck();
        WInCheck();
        RedCheck();
        Resetcolor();
    }

    // 메서드 영역
    public void Changecolor() {
        for (int i = 0; i < x; i++) {
            B[i][y].setBackgroundColor(Color.rgb(222, 222, 222));
            if (isRed[i][y]) {
                CellCheck(i, y);
                if (!isRed[i][y]) {
                    B[i][y].setBackgroundColor(Color.rgb(200, 200, 200));
                }
            }
        }
        for (int i = 8; i >= x; i--) {
            B[i][y].setBackgroundColor(Color.rgb(222, 222, 222));
            if (isRed[i][y]) {
                CellCheck(i, y);
                if (!isRed[i][y]) {
                    B[i][y].setBackgroundColor(Color.rgb(200, 200, 200));
                }
            }
        }
        for (int i = 0; i < y; i++) {
            B[x][i].setBackgroundColor(Color.rgb(222, 222, 222));
            if (isRed[x][i]) {
                CellCheck(x, i);
                if (!isRed[x][i]) {
                    B[x][i].setBackgroundColor(Color.rgb(200, 200, 200));
                }
            }
        }
        for (int i = 8; i >= y; i--) {
            B[x][i].setBackgroundColor(Color.rgb(222, 222, 222));
            if (isRed[x][i]) {
                CellCheck(x, i);
                if (!isRed[x][i]) {
                    B[x][i].setBackgroundColor(Color.rgb(200, 200, 200));
                }
            }
        }

        B[x][y].setBackgroundColor(Color.rgb(255, 200, 200));
        if (isRed[x][y]) {
            CellCheck(x, y);
        }

        for (int i = 0; i < 10; i++) {
            Select[i].setClickable(true);
            Select[i].setTextColor(Color.rgb(255, 64, 129));
        }
    }

    public void Resetcolor() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                B[i][j].setBackgroundColor(Color.rgb(255, 255, 255));
                if (isRed[i][j]) {
                    CellCheck(i, j);
                }
            }
        }
        for (int i = 0; i < 10; i++) {
            Select[i].setClickable(false);
            Select[i].setTextColor(Color.rgb(50, 50, 50));
        }
    }

    //스도쿠 셀에 오류가 있는 부분이 있는지를 찾는 메소드입니다. 다른 반복문에서도 인수를 받아 사용하기 위해 매개변수로 x_axis와 y_axis를 가지고 있습니다. 사실상 핵심 영역입니다.
    public void CellCheck(int x_axis, int y_axis) {
        a = B[x_axis][y_axis].getText().toString();

        for (int i = 0; i < x_axis; i++) {
            String b = B[i][y_axis].getText().toString();
            if (a.equals(b)) {
                B[x_axis][y_axis].setBackgroundColor(Color.rgb(255, 0, 0));
                isRed[x_axis][y_axis] = true;
                break;
            } else if ((i == x_axis - 1) || (i == x_axis)) {
                isRed[x_axis][y_axis] = false;
            }
        }
        if (x_axis == 0) {
            isRed[x_axis][y_axis] = false;
        }
        if (!isRed[x_axis][y_axis]) {
            for (int i = 8; i > x_axis; i--) {
                String b = B[i][y_axis].getText().toString();
                if (a.equals(b)) {
                    B[x_axis][y_axis].setBackgroundColor(Color.rgb(255, 0, 0));
                    isRed[x_axis][y_axis] = true;
                    break;
                } else if ((i == x_axis + 1) || (i == x_axis)) {
                    isRed[x_axis][y_axis] = false;
                }
            }
            if (x_axis == 8) {
                isRed[x_axis][y_axis] = false;
            }
        }
        if (!isRed[x_axis][y_axis]) {
            for (int i = 0; i < y_axis; i++) {
                String b = B[x_axis][i].getText().toString();
                if (a.equals(b)) {
                    B[x_axis][y_axis].setBackgroundColor(Color.rgb(255, 0, 0));
                    isRed[x_axis][y_axis] = true;
                    break;
                } else if ((i == y_axis - 1) || (i == y_axis)) {
                    isRed[x_axis][y_axis] = false;
                }
            }
            if (y_axis == 0) {
                isRed[x_axis][y_axis] = false;
            }
        }

        if (!isRed[x_axis][y_axis]) {
            for (int i = 8; i > y_axis; i--) {
                String b = B[x_axis][i].getText().toString();
                if (a.equals(b)) {
                    B[x_axis][y_axis].setBackgroundColor(Color.rgb(255, 0, 0));
                    isRed[x_axis][y_axis] = true;
                    break;
                } else if ((i == y_axis - 1) || (i == y_axis)) {
                    isRed[x_axis][y_axis] = false;
                }
                if (y_axis == 8) {
                    isRed[x_axis][y_axis] = false;
                }
            }
        }
        //셀 가로세로 끝, 내부탐색
        if (!isRed[x_axis][y_axis]) {

            if ((x_axis >= 0) && (x_axis <= 2) && (y_axis >= 0) && (y_axis <= 2)) {
                for (int i = 0; i <= 2; i++) {
                    for (int j = 0; j <= 2; j++) {
                        if ((i == x_axis) && (j == y_axis)) {
                            continue;
                        } else {
                            String b = B[i][j].getText().toString();
                            if (a.equals(b)) {
                                B[x_axis][y_axis].setBackgroundColor(Color.rgb(255, 0, 0));
                                isRed[x_axis][y_axis] = true;
                                break;
                            }
                        }
                        if (isRed[x_axis][y_axis]) break;
                        else if (i == 2) isRed[x_axis][y_axis] = false;
                    }
                }
            } else if ((x_axis >= 0) && (x_axis <= 2) && (y_axis >= 3) && (y_axis <= 5)) {
                for (int i = 0; i <= 2; i++) {
                    for (int j = 3; j <= 5; j++) {
                        if ((i == x_axis) && (j == y_axis)) {
                            continue;
                        } else {
                            String b = B[i][j].getText().toString();
                            if (a.equals(b)) {
                                B[x_axis][y_axis].setBackgroundColor(Color.rgb(255, 0, 0));
                                isRed[x_axis][y_axis] = true;
                                break;
                            }
                        }
                        if (isRed[x_axis][y_axis]) break;
                        else if (i == 2) isRed[x_axis][y_axis] = false;
                    }
                }
            } else if ((x_axis >= 0) && (x_axis <= 2) && (y_axis >= 6) && (y_axis <= 8)) {
                for (int i = 0; i <= 2; i++) {
                    for (int j = 6; j <= 8; j++) {
                        if ((i == x_axis) && (j == y_axis)) {
                            continue;
                        } else {
                            String b = B[i][j].getText().toString();
                            if (a.equals(b)) {
                                B[x_axis][y_axis].setBackgroundColor(Color.rgb(255, 0, 0));
                                isRed[x_axis][y_axis] = true;
                                break;
                            }
                        }
                        if (isRed[x_axis][y_axis]) break;
                        else if (i == 2) isRed[x_axis][y_axis] = false;
                    }
                }
            } else if ((x_axis >= 3) && (x_axis <= 5) && (y_axis >= 0) && (y_axis <= 2)) {
                for (int i = 3; i <= 5; i++) {
                    for (int j = 0; j <= 2; j++) {
                        if ((i == x_axis) && (j == y_axis)) {
                            continue;
                        } else {
                            String b = B[i][j].getText().toString();
                            if (a.equals(b)) {
                                B[x_axis][y_axis].setBackgroundColor(Color.rgb(255, 0, 0));
                                isRed[x_axis][y_axis] = true;
                                break;
                            }
                        }
                        if (isRed[x_axis][y_axis]) break;
                        else if (i == 5) isRed[x_axis][y_axis] = false;
                    }
                }
            } else if ((x_axis >= 3) && (x_axis <= 5) && (y_axis >= 3) && (y_axis <= 5)) {
                for (int i = 3; i <= 5; i++) {
                    for (int j = 3; j <= 5; j++) {
                        if ((i == x_axis) && (j == y_axis)) {
                            continue;
                        } else {
                            String b = B[i][j].getText().toString();
                            if (a.equals(b)) {
                                B[x_axis][y_axis].setBackgroundColor(Color.rgb(255, 0, 0));
                                isRed[x_axis][y_axis] = true;
                                break;
                            }
                        }
                        if (isRed[x_axis][y_axis]) break;
                        else if (i == 5) isRed[x_axis][y_axis] = false;
                    }
                }
            } else if ((x_axis >= 3) && (x_axis <= 5) && (y_axis >= 6) && (y_axis <= 8)) {
                for (int i = 3; i <= 5; i++) {
                    for (int j = 6; j <= 8; j++) {
                        if ((i == x_axis) && (j == y_axis)) {
                            continue;
                        } else {
                            String b = B[i][j].getText().toString();
                            if (a.equals(b)) {
                                B[x_axis][y_axis].setBackgroundColor(Color.rgb(255, 0, 0));
                                isRed[x_axis][y_axis] = true;
                                break;
                            }
                        }
                        if (isRed[x_axis][y_axis]) break;
                        else if (i == 5) isRed[x_axis][y_axis] = false;
                    }

                }
            } else if ((x_axis >= 6) && (x_axis <= 8) && (y_axis >= 0) && (y_axis <= 2)) {
                for (int i = 6; i <= 8; i++) {
                    for (int j = 0; j <= 2; j++) {
                        if ((i == x_axis) && (j == y_axis)) {
                            continue;
                        } else {
                            String b = B[i][j].getText().toString();
                            if (a.equals(b)) {
                                B[x_axis][y_axis].setBackgroundColor(Color.rgb(255, 0, 0));
                                isRed[x_axis][y_axis] = true;
                                break;
                            }
                        }
                        if (isRed[x_axis][y_axis]) break;
                        else if (i == 8) isRed[x_axis][y_axis] = false;
                    }
                }
            } else if ((x_axis >= 6) && (x_axis <= 8) && (y_axis >= 3) && (y_axis <= 5)) {
                for (int i = 6; i <= 8; i++) {
                    for (int j = 3; j <= 5; j++) {
                        if ((i == x_axis) && (j == y_axis)) {
                            continue;
                        } else {
                            String b = B[i][j].getText().toString();
                            if (a.equals(b)) {
                                B[x_axis][y_axis].setBackgroundColor(Color.rgb(255, 0, 0));
                                isRed[x_axis][y_axis] = true;
                                break;
                            }
                        }
                        if (isRed[x_axis][y_axis]) break;
                        else if (i == 8) isRed[x_axis][y_axis] = false;
                    }
                }
            } else if ((x_axis >= 6) && (x_axis <= 8) && (y_axis >= 6) && (y_axis <= 8)) {
                for (int i = 6; i <= 8; i++) {
                    for (int j = 6; j <= 8; j++) {
                        if ((i == x_axis) && (j == y_axis)) {
                            continue;
                        } else {
                            String b = B[i][j].getText().toString();
                            if (a.equals(b)) {
                                B[x_axis][y_axis].setBackgroundColor(Color.rgb(255, 0, 0));
                                isRed[x_axis][y_axis] = true;
                                break;
                            }
                        }
                        if (isRed[x_axis][y_axis]) {
                            break;
                        } else if (i == 8) isRed[x_axis][y_axis] = false;
                    }
                }
            }
        }
    }

    public void WInCheck() { //게임 끝났는지 유무를 검출
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                String zero = B[i][j].getText().toString();
                if ((isRed[i][j]) || (zero.equals(" "))) {
                    i = 9;
                    break;
                } else if ((i == 8) && (j == 8)) {
                    Toast.makeText(getApplicationContext(), "당신의 승리입니다.", LENGTH_SHORT).show();
                    initialize();
                }
            }
        }
    }

    public void RedCheck() { //입력받은 수 외의 다른 수의 오류를 검출
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!isRed[i][j]) {
                    if (B[i][j].getText().toString().equals(" ")) {
                        continue;
                    } else CellCheck(i, j);

                    if (!isRed[i][j]) {
                        B[i][j].setBackgroundColor(Color.rgb(255, 255, 255));
                    }
                    else {
                        if(B[i][j].isClickable()){
                            B[i][j].setBackgroundColor(Color.rgb(255, 0, 0));
                        }
                        else{
                            isRed[i][j] = false;
                        }
                    }
                }
            }
        }
    }


    public void initialize(){

        x = 10;
        y = 10;

        if(level == 1){
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    B[i][j].setText(" ");
                    B[i][j].setClickable(true);
                    B[i][j].setTextColor(Color.rgb(0, 0, 0));
                    B[i][j].setBackgroundColor(Color.rgb(255, 255, 255));
                    if (sdoku_easy[i][j] != 0) {
                        B[i][j].setText("" + sdoku_easy[i][j]);
                        B[i][j].setTextColor(Color.rgb(0, 0, 255));
                        B[i][j].setClickable(false);
                    }
                    isRed[i][j] = false;
                }
            }
        }
        else if(level == 2){
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    B[i][j].setText(" ");
                    B[i][j].setClickable(true);
                    B[i][j].setTextColor(Color.rgb(0, 0, 0));
                    B[i][j].setBackgroundColor(Color.rgb(255, 255, 255));
                    if (sdoku_normal[i][j] != 0) {
                        B[i][j].setText("" + sdoku_normal[i][j]);
                        B[i][j].setTextColor(Color.rgb(0, 0, 255));
                        B[i][j].setClickable(false);
                    }
                    isRed[i][j] = false;
                }
            }
        }
        else if(level == 3){
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                B[i][j].setText(" ");
                B[i][j].setClickable(true);
                B[i][j].setTextColor(Color.rgb(0, 0, 0));
                    B[i][j].setBackgroundColor(Color.rgb(255, 255, 255));
                if (sdoku_hard[i][j] != 0) {
                    B[i][j].setText("" + sdoku_hard[i][j]);
                    B[i][j].setTextColor(Color.rgb(0, 0, 255));
                    B[i][j].setClickable(false);
                    }
                    isRed[i][j] = false;
                }
            }
        }
        else{
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                B[i][j].setText(" ");
                B[i][j].setClickable(true);
                B[i][j].setTextColor(Color.rgb(0, 0, 0));
                B[i][j].setBackgroundColor(Color.rgb(255, 255, 255));
                if (sdoku_hell[i][j] != 0) {
                    B[i][j].setText("" + sdoku_hell[i][j]);
                    B[i][j].setTextColor(Color.rgb(0, 0, 255));
                    B[i][j].setClickable(false);
                    }
                    isRed[i][j] = false;
                }
            }
        }
    }
    public void afterCellCheck(){
        if (!isRed[x][y]) {
            B[x][y].setBackgroundColor(Color.rgb(255, 200, 200));
        }
        x = 10;
        y = 10;
    }
}