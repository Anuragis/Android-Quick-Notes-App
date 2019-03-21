package com.example.anuragshinde.grade;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class CreateNoteFragment extends Fragment {

    private EditText title, description;
    private Button create;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_create_note, container, false);

        title = (EditText)view.findViewById(R.id.etTitle);
        description = (EditText)view.findViewById(R.id.etDescription);
        create = (Button) view.findViewById(R.id.create);



        create.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //if(isValid()){
                createNote(title,description);
                Toast.makeText(getActivity(),"Note Created Successfully!!",Toast.LENGTH_LONG).show();
                //}
            }
        });

        return view;
    }

    public void createNote(EditText title, EditText description){
        NotesDatabase notesDb = new NotesDatabase(getActivity());
        SQLiteDatabase db = notesDb.getWritableDatabase();
        ContentValues values = new ContentValues();

        if(validate(title.getText().toString())){
            values.put("title",title.getText().toString());
            values.put("description", description.getText().toString());
            db.insert("Notes", null,values);
        }else {
            customDialog("Error", "Title should not be blank");
        }

    }

 /*   @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }*/
 public boolean validate(String title){
     boolean isValid =true;

     if(null== title || title.isEmpty()){
         isValid = false;
     }
     return isValid;
 }


    public void customDialog(String title, String message){
        final android.support.v7.app.AlertDialog.Builder builderSingle = new android.support.v7.app.AlertDialog.Builder(getActivity());
        builderSingle.setTitle(title);
        builderSingle.setMessage(message);

        builderSingle.setNegativeButton(
                "Close",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builderSingle.show();
    }
}


