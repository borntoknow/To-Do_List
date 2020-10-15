package com.auth0.todo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import com.auth0.todo.util.ToDoListAdapter;

import android.content.Intent;
import android.view.View;

import com.auth0.todo.identity.AuthAwareActivity;

public class MainActivity extends AuthAwareActivity  {
    private ToDoListAdapter toDoListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.toDoListAdapter = new ToDoListAdapter(this);
        ListView toDoItemsListView = findViewById(R.id.to_do_items);
        toDoItemsListView.setAdapter(toDoListAdapter);
    }

    public void openToDoForm(View view) {
        if (authenticationHandler.hasValidCredentials()) {
            startActivityForResult(new Intent(this, ToDoFormActivity.class), 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String newItem = data.getStringExtra("item");
                this.toDoListAdapter.addItem(newItem);
            }
        }
    }
}