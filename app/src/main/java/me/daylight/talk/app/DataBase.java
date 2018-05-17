package me.daylight.talk.app;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import me.daylight.talk.bean.ChatMessage;
import me.daylight.talk.bean.FriendList;
import me.daylight.talk.bean.MessageList;
import me.daylight.talk.bean.User;
import me.daylight.talk.dao.ChatMessageDao;
import me.daylight.talk.dao.FriendDao;
import me.daylight.talk.dao.MessageListDao;
import me.daylight.talk.dao.UserDao;

@Database(entities = {User.class, MessageList.class, FriendList.class, ChatMessage.class},version = 1,exportSchema = false)
public abstract class DataBase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract MessageListDao messageListDao();
    public abstract FriendDao friendDao();
    public abstract ChatMessageDao chatMessageDao();
    private static DataBase INSTANCE;
    public static DataBase getDataBase(Context context){
        if (INSTANCE==null){
            synchronized (DataBase.class){
                if (INSTANCE==null)
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                            DataBase.class,"database")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .addMigrations(MIGRATION)
                            .build();
            }
        }
        return INSTANCE;
    }
    private static final Migration MIGRATION = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
//            database.execSQL("CREATE TABLE `recent_table` (\n" +
//                    "    `id`          INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
//                    "    `account`      TEXT    REFERENCES `user_table` (`phoneNum`) ON DELETE NO ACTION\n" +
//                    "                                                         ON UPDATE NO ACTION,\n" +
//                    "    `neighbor`     TEXT    REFERENCES `user_table` (`phoneNum`) ON DELETE NO ACTION\n" +
//                    "                                                         ON UPDATE NO ACTION,\n" +
//                    "    `lastMsg`        TEXT,\n" +
//                    "    `time`        INTEGER,\n" +
//                    "    `uniqueId` TEXT\n" +
//                    ")");
//            database.execSQL("CREATE INDEX `index_recent_table_account` on `recent_table`(`account`)");
//            database.execSQL("CREATE INDEX `index_recent_table_neighbor` on `recent_table`(`neighbor`)");
//            database.execSQL("CREATE UNIQUE INDEX `index_recent_table_uniqueId` on `recent_table`(`uniqueId`)");
//            database.execSQL("ALTER TABLE `messagelist` ADD COLUMN `updatetime` INTEGER NOT NULL DEFAULT 0");
        }
    };
}
