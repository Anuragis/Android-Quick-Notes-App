package com.example.anuragshinde.grade;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;


public class ViewNotesFragment extends Fragment {

    private HashMap<Integer, String> positionMap = new HashMap<>();
    private HashMap<String, String> dataMap = new HashMap<>();
    private View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

         view = inflater.inflate(R.layout.fragment_view_notes, container, false);

       // String [] items = {"Do Something", "Do Something2"};

        ListView listView =(ListView) view.findViewById(R.id.notesDisplay);


       String[] items = getNotes();




        ArrayAdapter<String> listViewAdaptor = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                items
        );

        listView.setAdapter(listViewAdaptor);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(view.getContext(), "ListView clicked", Toast.LENGTH_LONG).show();
                customDialog(positionMap.get(position),dataMap.get(positionMap.get(position)));
               // System.out.println();
            }
        });
        return view;
    }

    public String[] getNotes(){
        NotesDatabase database = new NotesDatabase(getActivity());
        SQLiteDatabase db = database.getReadableDatabase();
        String []columns = {"id","title", "description"};
        ArrayList<String> arr =new ArrayList<>();
        Cursor cursor = db.query("Notes", columns, null, null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                arr.add(cursor.getString(cursor.getColumnIndex("title")));
                dataMap.put(cursor.getString(cursor.getColumnIndex("title")), cursor.getString(cursor.getColumnIndex("description")));
            }while(cursor.moveToNext());

        }

        String [] items = new String[arr.size()];

        for (int counter = 0; counter < arr.size(); counter++) {
            items[counter] = arr.get(counter);
            positionMap.put(counter,arr.get(counter));
        }

        return items;
    }


    public void close(){
        Toast.makeText(view.getContext(), "Item Closed", Toast.LENGTH_SHORT).show();
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
                        close();
                    }
                });

        builderSingle.show();
    }
}


