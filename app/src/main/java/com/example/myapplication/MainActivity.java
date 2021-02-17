package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.db.MyDbManager;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    AlertDialog.Builder dialogBuilder;
    AlertDialog dialog;
    EditText personal_account, surname_child, name_child, patronymic_child;
    Button add_to, cancellation;
    TableLayout table_children;
    TextView number, fio_child, number_lc, balance;
    //удаляем ребенка
    ImageView mark;

    //БД
    private MyDbManager myDbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_educator);
        setSupportActionBar(toolbar);

        table_children = (TableLayout) findViewById(R.id.table_children);
        balance = (TextView) findViewById(R.id.balance);
        mark = (ImageView) findViewById(R.id.mark);

        //БД
        myDbManager = new MyDbManager(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        myDbManager.openDb();
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table_children);
        tableLayout.removeAllViews();

        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
        TableRow header = (TableRow) inflater.inflate(R.layout.table_row_header, null);
        tableLayout.addView(header);

        List<List<String>> data = myDbManager.getFromDb();
        for (List<String> row: data) {
            TableRow tr = (TableRow) inflater.inflate(R.layout.table_row_plus, null);
            infillRow(tableLayout, tr, row.get(1), row.get(2));
            tableLayout.addView(tr);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nenu_educator, menu);
        return true;

    }

    private void infillRow(TableLayout tableLayout, TableRow tr, String value1, String tv4) {
                        Kid kid = new Kid(
                                surname_child.getText().toString(),
                                name_child.getText().toString(),
                                patronymic_child.getText().toString(),
                                personal_account.getText().toString()
                        );
        number = (TextView) tr.findViewById(R.id.number);

        //нумерации строк при добавлении строки
        int aInt = table_children.getChildCount();
        for (int m = 0; m < aInt; m++) {
            String aString = Integer.toString(m + 1);
            number.setText(aString);
        }

        fio_child = (TextView) tr.findViewById(R.id.fio_child);
        fio_child.setText(kid.getFullName());
        number_lc = (TextView) tr.findViewById(R.id.number_lc);
        number_lc.setText(kid.getNumberLC());

        //переопределяем ImageView, которые появляются со строками
        ImageView markOfTheRow = tr.findViewById(R.id.mark);
        markOfTheRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                String myFio_child;
                TableRow tr = (TableRow)v.getParent();
                TextView reb = (TextView) tr.getChildAt(1);
                myFio_child = reb.getText().toString();
                builder.setMessage("Удалить: " + myFio_child + " ?");
                builder.setCancelable(true);
                builder.setNegativeButton("нет", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        number_lc = (TextView) tr.findViewById(R.id.number_lc);
                        myDbManager.removeFromDb(number_lc.getText().toString());
                        tableLayout.removeView(tr);
                        Toast.makeText(getApplicationContext(), "Ребенок удалён", Toast.LENGTH_SHORT).show();

                        int aInt = tableLayout.getChildCount();
                        for (int m = 1; m < aInt; m++) {
                            TableRow tr = (TableRow)tableLayout.getChildAt(m);
                            TextView number = (TextView) tr.getChildAt(0);
                            number.setText(String.valueOf(m));
                        }

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.add_child) {
            dialogBuilder = new AlertDialog.Builder(this);
            final View add_child = getLayoutInflater().inflate(R.layout.add_child, null);
            personal_account = (EditText) add_child.findViewById(R.id.personal_account);
            surname_child = (EditText) add_child.findViewById(R.id.surname_child);
            name_child = (EditText) add_child.findViewById(R.id.name_child);
            patronymic_child = (EditText) add_child.findViewById(R.id.patronymic_child);
            add_to = (Button) add_child.findViewById(R.id.add_to);
            cancellation = (Button) add_child.findViewById(R.id.cancellation);
            dialogBuilder.setView(add_child);
            dialog = dialogBuilder.create();
            dialog.show();

            add_to.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!TextUtils.isEmpty(personal_account.getText().toString()) && !TextUtils.isEmpty(surname_child.getText().toString()) && !TextUtils.isEmpty(name_child.getText().toString()) &&
                            !TextUtils.isEmpty(patronymic_child.getText().toString())) {
                        TableLayout tableLayout = (TableLayout) findViewById(R.id.table_children);
                        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
                        TableRow tr = (TableRow) inflater.inflate(R.layout.table_row_plus, null);

                        //заполняем строку
                        String tv1 = surname_child.getText().toString();
                        String tv2 = name_child.getText().toString();
                        String tv3 = patronymic_child.getText().toString();
                        String value1 = tv1 + " " + tv2 + " " + tv3;
                        String tv4 = personal_account.getText().toString();
                        infillRow(tableLayout, tr, value1, tv4);
                        //добавляем строку
                        tableLayout.addView(tr);
                        dialog.dismiss();

                        //запись в БД
                        myDbManager.insertToDb(number.getText().toString(), kid);

                        Toast.makeText(getApplicationContext(), "Ребенок добавлен", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), "Заполните все поля", Toast.LENGTH_SHORT).show();
                    }

                }

            });

            cancellation.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }

            });

        } else if (id == R.id.exit) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("Вы действительно хотите выйти?");
            builder.setCancelable(true);
            builder.setNegativeButton("нет", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    //startActivity(intent);
                    System.exit(0);
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

        } else if (id == R.id.sort_child) {


            Toast.makeText(getApplicationContext(), "Отсортировано", Toast.LENGTH_SHORT).show();
        }
        return true;

    }


}
