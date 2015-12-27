package com.duongtran.smskute;

/**
 * Created by thuydao on 09/12/2015.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;



import java.util.ArrayList;
import java.util.List;


public class MyDatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "daothuy";


    // Phiên bản
    private static final int DATABASE_VERSION = 1;


    // Tên cơ sở dữ liệu.
    private static final String DATABASE_NAME = "DB_SMS";


    // Tên bảng: SMS.
    private static final String TABLE_TOPIC = "tblTopic";
    private static final String TABLE_SMS = "tblSMS";

    private static final String COLUMN_TOPIC_ID ="id";
    private static final String COLUMN_TOPIC_NAME ="topicName";

    private static final String COLUMN_SMS_ID ="id";
    private static final String COLUMN_SMS_CONTENT ="content";
    private static final String COLUMN_SMS_LIKED = "liked";
    private static final String COLUMN_SMS_TOPICID = "topicId";

    public MyDatabaseHelper(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Tạo các bảng.
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script tạo bảng.
        String sqlTopic="create table tblTopic ("
                +"id text primary key ,"
                +"topicName text)";
        db.execSQL(sqlTopic);

        String sqlSMS
                ="create table tblSMS ("
                +"id integer primary key autoincrement,"
                +"content text, "
                +"liked boolean,"
                +"topicId text not null constraint topicId references tblTopic(id) on delete cascade)";
        db.execSQL(sqlSMS);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");

        // Hủy (drop) bảng cũ nếu nó đã tồn tại.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOPIC);

        // Và tạo lại.
        onCreate(db);
    }

    // Nếu trong bảng Topic chưa có dữ liệu,
    // Chèn vào mặc định 2 bản ghi.
    public void createDefaultTopicsIfNeed()  {
        int count = this.getTopicsCount();
        if(count == 0 ) {
            Topic topic1 = new Topic("VlT", "Valentine");
            Topic topic2 = new Topic("CH", "Christmas");
            Topic topic3 = new Topic("2010", "20-10");
            Topic topic4 = new Topic("83", "8-3");
            Topic topic5 = new Topic("GN", "Good Night");
            Topic topic6 = new Topic("GR", "Greeting");
            Topic topic7 = new Topic("LO", "Love");
            Topic topic8 = new Topic("NY", "New year");
            Topic topic9 = new Topic("BD", "Birthday");
            Topic topic10 = new Topic("GL", "Good luck");
            Topic topic11 = new Topic("HP", "Happy");
            this.addTopic(topic1);
            this.addTopic(topic2);
            this.addTopic(topic3);
            this.addTopic(topic3);
            this.addTopic(topic4);
            this.addTopic(topic5);
            this.addTopic(topic6);
            this.addTopic(topic7);
            this.addTopic(topic8);
            this.addTopic(topic9);
            this.addTopic(topic10);
            this.addTopic(topic11);
        }
    }

    public int getTopicsCount() {
        Log.i(TAG, "MyDatabaseHelper.getTopicsCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_TOPIC;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }


    public void addTopic(Topic tp) {
        Log.i(TAG, "MyDatabaseHelper.addTopic ... " + tp.getTopicName());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_TOPIC_ID, tp.getId());
        values.put(COLUMN_TOPIC_NAME, tp.getTopicName());

        // Chèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_TOPIC, null, values);

        // Đóng kết nối database.
        db.close();
    }

    // Nếu trong bảng SMS chưa có dữ liệu,
    // Trèn vào mặc định 3 bản ghi.
    public void createDefaultSMSIfNeed()  {
        int count = this.getSMSCount();
        if(count == 0 ) {
            SMS sms19 = new SMS("Chúc bạn có một ngày Valentine thật hạnh phúc bên cạnh người bạn yêu. Và tôi chắc hạnh phúc đó được trạo tặng từ một người yêu bạn chân thành nhất.", "VLT", true);
            SMS sms2 = new SMS("Merry christmas cuộc sống của anh! Chúc em một giáng sinh ấp áp với tiếng cười, niềm vui và hạnh phúc", "CH", false);
            SMS sms3 = new SMS("Happ birthday to you! To day was not sunny day but wish It is day full of happy, smile and lucky with you!", "BD", true);

            SMS sms4 = new SMS("__i_i_i_i_i_i_i_i__\n" +
            "            __[¸.·´¯`·☆·´¯`·.¸]__\n" +
            "                    [.¸.·´¯`04- 5´¯`·.¸.]\n" +
            "            |_______ღ.ღ_______|\n" +
            "            ::..̾.۰.Happy.۰.̾..::\n" +
            "            ::_.۰.Birth Day.۰._::\n" +
            "            ¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯\n" +
            "            Sinh nhật Vui vẻ !", "BD", false);

            SMS sms5 = new SMS("    @=-=-=-=-=-@\n" +
                    "    )) *Chuc*  ))\n" +
                    "   // * mung *//\n" +
                    "  // * nam * //\n" +
                    " // * moi * //\n" +
                    "(( * 2016* ((\n" +
                    "@=-=-=-=-=-+@", "NY", true);
            SMS sms6 = new SMS("Nhân ngày Phụ nữ Việt Nam, anh và con chúc em luôn luôn mạnh khỏe, vui vẻ và... ăn khỏe. Vợ ơi! Cố gắng ăn nhiều vào nhé! Yêu vợ nhiều lắm. Cảm ơn vợ đã vất vả vì bố con anh!", "2010", true);
            SMS sms7 = new SMS("Nhân ngày 8-3 con chúc mẹ nhiều sức khỏe và nụ cười luôn nở trên môi mẹ. Con ở xa mẹ quá, nên không thể chăm sóc cho mẹ được. Mẹ nhớ giữ gìn sức khỏe! Mong cho mọi điều tốt lành sẽ đến với mẹ! Con yêu mẹ!", "83", true);
            SMS sms8 = new SMS("Chúc người ju bé nhỏ của anh ngủ thật ngoan cho ….. cả thế giới đc yên.", "GN", true);
            SMS sms9 = new SMS("Nắng buổi sáng ấm áp và ông mặt trời đang mỉm cười! Em chúc anh buổi sáng tốt lành, một ngày thật tuyệt và làm việc hiệu quả nhé! Bx nhớ Ox.", "GR", true);
            SMS sms10 = new SMS("☆ ·.¸(`'·.¸*¤*¸.·'´)¸.·\n" +
                    "   ».·º`·.LOVE.·´º·.«\n" +
                    " ☆.·'(¸.·'´*¤*`'·.¸)`'·.\n" +
                    "         、、、、、、\n" +
                    "☆·.¸(`'·.¸*¤*¸.·'´)¸.·\n" +
                    "   ».·º`·. YOU.·´º·.«\n" +
                    " ☆.·'(¸.·'´*¤*`'·.¸)`'·.\n" +
                    "         、、、、、、\n" +
                    "☆·.¸(`'·.¸*¤*¸.·'´)¸.·\n" +
                    "   ».·º`· .FOR.·´º·.«\n" +
                    " ☆.·'(¸.·'´*¤*`'·.¸)`'·.\n" +
                    "         、、、、、、\n" +
                    "☆·.¸(`'·.¸*¤*¸.·'´)¸.·\n" +
                    "   ».·º`· .EVER.·´º·.«\n" +
                    " ☆.·'(¸.·'´*¤*`'·.¸)`'·.\n" +
                    "         、、、、、、", "LO", true);
            SMS sms11 = new SMS(" \n" +
                    "      _(^_^)  .   (^.^)_ \n" +
                    "     ,/| |\\, *   ,/| |\\,\n" +
                    "      /  \\ . * .  /  \\\n" +
                    "     ,/. .\\, *   ,/ . \\,\n" +
                    "YOU & ME\n" +
                    "CHUNG MINH CUNG VAO DAI HOC NHA!\n" +
                    "HÃY CỐ LÊN! ", "GL", true);
            SMS sms12 = new SMS("Anh đợi em lâu chưa?\n" +
                    "– hơn 20 năm rồi em à\n" +
                    "Gấu: em yêu anh\n" +
                    "Mình: anh cũng thế\n" +
                    "Gấu: thế là anh cũng yêu em ah?\n" +
                    "Mình: ko anh cũng yêu anh )", "HP", true);
            SMS sms13 = new SMS("Trêu gấu, ai ngờ bị trêu lại\n" +
                    "-Em, biết em khác con bò ở điểm nào không\n" +
                    "-Dạ không biết.\n" +
                    "-Em đi 2 chân còn bò đi 4 chân.\n" +
                    "Gấu vặn lại\n" +
                    "-Còn anh, anh biết anh khác con bò ở điểm nào không.\n" +
                    "-Anh không biết\n" +
                    "-Đúng rồi, vì anh chẳng khác gì con bò cả\n" +
                    "\n", "HP", true);

            SMS sms14 = new SMS("* º *º * º *\n" +
                    "* _Π_*_º_*º*\n" +
                    "/____＿/~＼* º\n" +
                    "｜ ▦▦｜□｜*\n" +
                    "* º *º * º *\n" +
                    "Thay mặt nhân dân của một đất nước có hơn 2 triệu người chết đói cách đây 65 năm xin chúc quý vị ăn Tết thật xa hoa, lãng phí. ※" +
                    "\n", "NY", false);
            SMS sms15 = new SMS("+\"\"-.,_,.-\"\"+.\n" +
                    "; #\"-.Happy_.-\"#\n" +
                    "*'+. New +\"+*\n" +
                    "+-.Year.-+\n" +
                    "\"-._.-\"\n" +
                    "\"-..-*\"-..2016.-*\"-..-\"\n" +
                    "Cung chúc tân niên,\n" +
                    "Sức khỏe vô biên, thành công liên miên,\n" +
                    "hạnh phúc triền miên, túi luôn đầy tiền,\n" +
                    "sung sướng như tiên.Chúc mừng năm mới!\n", "NY", false);
            SMS sms16 = new SMS("      .:!|!:._.:!|!:.\n" +
                    "      \"::: Anh :::\"\n" +
                    "          \"-!|!-\"\n" +
                    "      .:!|!:._.:!|!:.\n" +
                    "       \"::: yeu ::\"\n" +
                    "         \"-!|!-\"\n" +
                    "     .:!|!:._.:!|!:.\n" +
                    "       \":: em .::\"\n" +
                    "         \"-!|!-\"\n" +
                    "   .:!|!:._.:!|!:._.:!|!:.\n" +
                    "     \":: mai mai ! ::\"\n" +
                    "      \"'-!|!-\"\"-!|!-'\"\n" +
                    "Đừng bao giờ rời xa anh em nhé!", "LO", false);
            SMS sms17 = new SMS("* º *º * º *\n" +
                    "* _Π_*_º_*º*\n" +
                    "/____＿/~＼* º\n" +
                    "｜ ▦▦｜□｜*\n" +
                    "* º *º * º *\n" +
                    "Thay mặt nhân dân của một đất nước có hơn 2 triệu người chết đói cách đây 65 năm xin chúc quý vị ăn Tết thật xa hoa, lãng phí. ※" +
                    "\n", "VLT", false);
            SMS sms18 = new SMS("+\"\"-.,_,.-\"\"+.\n" +
                    "; #\"-.Happy_.-\"#\n" +
                    "*'+. New +\"+*\n" +
                    "+-.Year.-+\n" +
                    "\"-._.-\"\n" +
                    "\"-..-*\"-..2016.-*\"-..-\"\n" +
                    "Cung chúc tân niên,\n" +
                    "Sức khỏe vô biên, thành công liên miên,\n" +
                    "hạnh phúc triền miên, túi luôn đầy tiền,\n" +
                    "sung sướng như tiên.Chúc mừng năm mới!\n", "VLT", false);
            SMS sms1 = new SMS("      .:!|!:._.:!|!:.\n" +
                    "      \"::: Anh :::\"\n" +
                    "          \"-!|!-\"\n" +
                    "      .:!|!:._.:!|!:.\n" +
                    "       \"::: yeu ::\"\n" +
                    "         \"-!|!-\"\n" +
                    "     .:!|!:._.:!|!:.\n" +
                    "       \":: em .::\"\n" +
                    "         \"-!|!-\"\n" +
                    "   .:!|!:._.:!|!:._.:!|!:.\n" +
                    "     \":: mai mai ! ::\"\n" +
                    "      \"'-!|!-\"\"-!|!-'\"\n" +
                    "Đừng bao giờ rời xa anh em nhé!", "VLT", false);

            this.addSMS(sms1);
            this.addSMS(sms2);
            this.addSMS(sms3);
            this.addSMS(sms4);
            this.addSMS(sms5);
            this.addSMS(sms6);
            this.addSMS(sms7);
            this.addSMS(sms8);
            this.addSMS(sms9);
            this.addSMS(sms10);
            this.addSMS(sms11);
            this.addSMS(sms13);
            this.addSMS(sms14);
            this.addSMS(sms15);
            this.addSMS(sms16);
            this.addSMS(sms17);
            this.addSMS(sms18);
            this.addSMS(sms19);
        }
    }

    public int getSMSCount() {
        Log.i(TAG, "MyDatabaseHelper.getSMSCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_SMS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }


    public void addSMS(SMS sms) {
        Log.i(TAG, "MyDatabaseHelper.addSMS ... " + sms.getContent());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SMS_CONTENT, sms.getContent());
        values.put(COLUMN_SMS_TOPICID, sms.getTopicId());
        values.put(COLUMN_SMS_LIKED, sms.isLiked());

        // Chèn một dòng dữ liệu vào bảng.
        db.insert(TABLE_SMS, null, values);

        // Đóng kết nối database.
        db.close();
    }

    public List<Topic> getAllTopics() {
        Log.i(TAG, "MyDatabaseHelper.getAllTopics ... " );

        List<Topic> topicList = new ArrayList<Topic>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TOPIC;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                Topic topic = new Topic();
                topic.setId(cursor.getString(0));
                topic.setTopicName(cursor.getString(1));

                // Thêm vào danh sách.
                topicList.add(topic);
            } while (cursor.moveToNext());
        }

        // return note list
        return topicList;
    }

    public List<SMS> getSMS(String topic) {
        Log.i(TAG, "MyDatabaseHelper.getSMS ... " );

        List<SMS> smsList = new ArrayList<SMS>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SMS + " WHERE topicId = '" + topic + "'";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                SMS sms = new SMS();
                sms.setId(Integer.parseInt(cursor.getString(0)));
                sms.setContent(cursor.getString(1));
                sms.setLiked(Boolean.parseBoolean(cursor.getString(2)));
                sms.setTopicId(cursor.getString(3));

                // Thêm vào danh sách.
                smsList.add(sms);
            } while (cursor.moveToNext());
        }

        // return note list
        return smsList;
    }

    ///SMS
    public List<SMS> getAllSMS() {
        Log.i(TAG, "MyDatabaseHelper.getAllSMS ... " );

        List<SMS> SMSList = new ArrayList<SMS>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SMS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                SMS sms = new SMS();
                sms.setId(Integer.parseInt(cursor.getString(0)));

                sms.setContent(cursor.getString(1));
                sms.setLiked(Boolean.parseBoolean(cursor.getString(2)));
                sms.setTopicId(cursor.getString(3));
                // Thêm vào danh sách.
                SMSList.add(sms);
            } while (cursor.moveToNext());
        }

        // return note list
        return SMSList;
    } //end sms

    public List<SMS> getSMSLiked() {
        Log.i(TAG, "MyDatabaseHelper.getSMSLiked ... " );

        List<SMS> smsList = new ArrayList<SMS>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SMS + " WHERE liked = 1";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                SMS sms = new SMS();
                sms.setId(Integer.parseInt(cursor.getString(0)));
                sms.setContent(cursor.getString(1));
                sms.setLiked(Boolean.parseBoolean(cursor.getString(2)));
                sms.setTopicId(cursor.getString(3));

                // Thêm vào danh sách.
                smsList.add(sms);
            } while (cursor.moveToNext());
        }

        // return note list
        return smsList;
    }


//
//
//    public Note getNote(int id) {
//        Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);
//
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_NOTE, new String[] { COLUMN_NOTE_ID,
//                        COLUMN_NOTE_TITLE, COLUMN_NOTE_CONTENT }, COLUMN_NOTE_ID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        Note note = new Note(Integer.parseInt(cursor.getString(0)),
//                cursor.getString(1), cursor.getString(2));
//        // return note
//        return note;
//    }
//
//
//    public List<Note> getAllNotes() {
//        Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );
//
//        List<Note> noteList = new ArrayList<Note>();
//        // Select All Query
//        String selectQuery = "SELECT  * FROM " + TABLE_NOTE;
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor cursor = db.rawQuery(selectQuery, null);
//
//
//        // Duyệt trên con trỏ, và thêm vào danh sách.
//        if (cursor.moveToFirst()) {
//            do {
//                Note note = new Note();
//                note.setNoteId(Integer.parseInt(cursor.getString(0)));
//                note.setNoteTitle(cursor.getString(1));
//                note.setNoteContent(cursor.getString(2));
//
//                // Thêm vào danh sách.
//                noteList.add(note);
//            } while (cursor.moveToNext());
//        }
//
//        // return note list
//        return noteList;
//    }
//
//
    public int updateSMS(SMS sms) {
        Log.i(TAG, "MyDatabaseHelper.updateSMS ... "  + sms.getId());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_SMS_CONTENT, sms.getContent());
        values.put(COLUMN_SMS_LIKED, sms.isLiked());
        values.put(COLUMN_SMS_TOPICID, sms.getTopicId());

        // updating row
        return db.update(TABLE_SMS, values, COLUMN_SMS_ID + " = ?",
                new String[]{String.valueOf(sms.getId())});
    }
//
    public void deleteSMS(SMS sms) {
        Log.i(TAG, "MyDatabaseHelper.updateSMS ... " + sms.getId() );

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SMS, COLUMN_SMS_ID + " = ?",
                new String[] { String.valueOf(sms.getId()) });
        db.close();
    }

}
