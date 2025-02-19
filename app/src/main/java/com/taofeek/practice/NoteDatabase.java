package com.taofeek.practice;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Note.class, version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;
    public abstract NoteDao noteDao();
    public static synchronized NoteDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class , "note_database").fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask <Void,Void,Void>{
        private NoteDao noteDao;
        private PopulateDbAsyncTask(NoteDatabase db){
            noteDao = db.noteDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Android Developer","A professional software developer", 4));
            noteDao.insert(new Note("Taofeek","Android developer", 1));
            noteDao.insert(new Note("Engr Tee","A professional app developer", 3));
            return null;
        }
    }
}
