package com.tgcity.network.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import com.tgcity.network.greendao.model.CollegeDetailsCollegeContrastSchoolDto;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;
import org.greenrobot.greendao.internal.DaoConfig;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * DAO for table "COLLEGE_DETAILS_COLLEGE_CONTRAST_SCHOOL_DTO".
*/
public class CollegeDetailsCollegeContrastSchoolDtoDao extends AbstractDao<CollegeDetailsCollegeContrastSchoolDto, Long> {

    public static final String TABLENAME = "COLLEGE_DETAILS_COLLEGE_CONTRAST_SCHOOL_DTO";

    /**
     * Properties of entity CollegeDetailsCollegeContrastSchoolDto.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property SchoolName = new Property(1, String.class, "schoolName", false, "SCHOOL_NAME");
        public final static Property SchoolId = new Property(2, int.class, "schoolId", false, "SCHOOL_ID");
        public final static Property Ranking = new Property(3, String.class, "ranking", false, "RANKING");
        public final static Property SchoolProvince = new Property(4, String.class, "schoolProvince", false, "SCHOOL_PROVINCE");
        public final static Property Time = new Property(5, long.class, "time", false, "TIME");
        public final static Property IsCheck = new Property(6, int.class, "isCheck", false, "IS_CHECK");
    }


    public CollegeDetailsCollegeContrastSchoolDtoDao(DaoConfig config) {
        super(config);
    }
    
    public CollegeDetailsCollegeContrastSchoolDtoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COLLEGE_DETAILS_COLLEGE_CONTRAST_SCHOOL_DTO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"SCHOOL_NAME\" TEXT," + // 1: schoolName
                "\"SCHOOL_ID\" INTEGER NOT NULL ," + // 2: schoolId
                "\"RANKING\" TEXT," + // 3: ranking
                "\"SCHOOL_PROVINCE\" TEXT," + // 4: schoolProvince
                "\"TIME\" INTEGER NOT NULL ," + // 5: time
                "\"IS_CHECK\" INTEGER NOT NULL );"); // 6: isCheck
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COLLEGE_DETAILS_COLLEGE_CONTRAST_SCHOOL_DTO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, CollegeDetailsCollegeContrastSchoolDto entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String schoolName = entity.getSchoolName();
        if (schoolName != null) {
            stmt.bindString(2, schoolName);
        }
        stmt.bindLong(3, entity.getSchoolId());
 
        String ranking = entity.getRanking();
        if (ranking != null) {
            stmt.bindString(4, ranking);
        }
 
        String schoolProvince = entity.getSchoolProvince();
        if (schoolProvince != null) {
            stmt.bindString(5, schoolProvince);
        }
        stmt.bindLong(6, entity.getTime());
        stmt.bindLong(7, entity.getIsCheck());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, CollegeDetailsCollegeContrastSchoolDto entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String schoolName = entity.getSchoolName();
        if (schoolName != null) {
            stmt.bindString(2, schoolName);
        }
        stmt.bindLong(3, entity.getSchoolId());
 
        String ranking = entity.getRanking();
        if (ranking != null) {
            stmt.bindString(4, ranking);
        }
 
        String schoolProvince = entity.getSchoolProvince();
        if (schoolProvince != null) {
            stmt.bindString(5, schoolProvince);
        }
        stmt.bindLong(6, entity.getTime());
        stmt.bindLong(7, entity.getIsCheck());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public CollegeDetailsCollegeContrastSchoolDto readEntity(Cursor cursor, int offset) {
        CollegeDetailsCollegeContrastSchoolDto entity = new CollegeDetailsCollegeContrastSchoolDto( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // schoolName
            cursor.getInt(offset + 2), // schoolId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // ranking
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // schoolProvince
            cursor.getLong(offset + 5), // time
            cursor.getInt(offset + 6) // isCheck
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, CollegeDetailsCollegeContrastSchoolDto entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setSchoolName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setSchoolId(cursor.getInt(offset + 2));
        entity.setRanking(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setSchoolProvince(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setTime(cursor.getLong(offset + 5));
        entity.setIsCheck(cursor.getInt(offset + 6));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(CollegeDetailsCollegeContrastSchoolDto entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(CollegeDetailsCollegeContrastSchoolDto entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(CollegeDetailsCollegeContrastSchoolDto entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
