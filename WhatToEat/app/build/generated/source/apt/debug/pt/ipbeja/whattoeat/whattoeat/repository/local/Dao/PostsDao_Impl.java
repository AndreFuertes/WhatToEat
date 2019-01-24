package pt.ipbeja.whattoeat.whattoeat.repository.local.Dao;

import android.arch.persistence.db.SupportSQLiteStatement;
import android.arch.persistence.room.EntityDeletionOrUpdateAdapter;
import android.arch.persistence.room.EntityInsertionAdapter;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.RoomSQLiteQuery;
import android.arch.persistence.room.SharedSQLiteStatement;
import android.database.Cursor;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;
import pt.ipbeja.whattoeat.whattoeat.repository.local.Entity.Posts;

@SuppressWarnings("unchecked")
public class PostsDao_Impl implements PostsDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfPosts;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfPosts;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfPosts;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllPosts;

  public PostsDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfPosts = new EntityInsertionAdapter<Posts>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Posts`(`post_id`,`title`,`description`,`image`,`user_id`,`flag_active`,`type`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Posts value) {
        stmt.bindLong(1, value.getPost_id());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getImage() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getImage());
        }
        if (value.getUser_id() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getUser_id());
        }
        if (value.getFlag_active() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getFlag_active());
        }
        if (value.getType() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getType());
        }
      }
    };
    this.__deletionAdapterOfPosts = new EntityDeletionOrUpdateAdapter<Posts>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Posts` WHERE `post_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Posts value) {
        stmt.bindLong(1, value.getPost_id());
      }
    };
    this.__updateAdapterOfPosts = new EntityDeletionOrUpdateAdapter<Posts>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Posts` SET `post_id` = ?,`title` = ?,`description` = ?,`image` = ?,`user_id` = ?,`flag_active` = ?,`type` = ? WHERE `post_id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Posts value) {
        stmt.bindLong(1, value.getPost_id());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDescription());
        }
        if (value.getImage() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getImage());
        }
        if (value.getUser_id() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getUser_id());
        }
        if (value.getFlag_active() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getFlag_active());
        }
        if (value.getType() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getType());
        }
        stmt.bindLong(8, value.getPost_id());
      }
    };
    this.__preparedStmtOfDeleteAllPosts = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Posts";
        return _query;
      }
    };
  }

  @Override
  public void addPosts(List<Posts> posts) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfPosts.insert(posts);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertPosts(List<Posts> posts) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfPosts.insert(posts);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deletePosts(Posts posts) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfPosts.handle(posts);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updatePosts(Posts posts) {
    __db.beginTransaction();
    try {
      __updateAdapterOfPosts.handle(posts);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllPosts() {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllPosts.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllPosts.release(_stmt);
    }
  }

  @Override
  public List<Posts> getPosts() {
    final String _sql = "SELECT * FROM Posts";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfPostId = _cursor.getColumnIndexOrThrow("post_id");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
      final int _cursorIndexOfImage = _cursor.getColumnIndexOrThrow("image");
      final int _cursorIndexOfUserId = _cursor.getColumnIndexOrThrow("user_id");
      final int _cursorIndexOfFlagActive = _cursor.getColumnIndexOrThrow("flag_active");
      final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
      final List<Posts> _result = new ArrayList<Posts>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Posts _item;
        final long _tmpPost_id;
        _tmpPost_id = _cursor.getLong(_cursorIndexOfPostId);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        final String _tmpImage;
        _tmpImage = _cursor.getString(_cursorIndexOfImage);
        final String _tmpUser_id;
        _tmpUser_id = _cursor.getString(_cursorIndexOfUserId);
        final String _tmpFlag_active;
        _tmpFlag_active = _cursor.getString(_cursorIndexOfFlagActive);
        final String _tmpType;
        _tmpType = _cursor.getString(_cursorIndexOfType);
        _item = new Posts(_tmpPost_id,_tmpTitle,_tmpDescription,_tmpImage,_tmpUser_id,_tmpFlag_active,_tmpType);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Posts getPost(long id) {
    final String _sql = "SELECT * FROM posts WHERE post_id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfPostId = _cursor.getColumnIndexOrThrow("post_id");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfDescription = _cursor.getColumnIndexOrThrow("description");
      final int _cursorIndexOfImage = _cursor.getColumnIndexOrThrow("image");
      final int _cursorIndexOfUserId = _cursor.getColumnIndexOrThrow("user_id");
      final int _cursorIndexOfFlagActive = _cursor.getColumnIndexOrThrow("flag_active");
      final int _cursorIndexOfType = _cursor.getColumnIndexOrThrow("type");
      final Posts _result;
      if(_cursor.moveToFirst()) {
        final long _tmpPost_id;
        _tmpPost_id = _cursor.getLong(_cursorIndexOfPostId);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpDescription;
        _tmpDescription = _cursor.getString(_cursorIndexOfDescription);
        final String _tmpImage;
        _tmpImage = _cursor.getString(_cursorIndexOfImage);
        final String _tmpUser_id;
        _tmpUser_id = _cursor.getString(_cursorIndexOfUserId);
        final String _tmpFlag_active;
        _tmpFlag_active = _cursor.getString(_cursorIndexOfFlagActive);
        final String _tmpType;
        _tmpType = _cursor.getString(_cursorIndexOfType);
        _result = new Posts(_tmpPost_id,_tmpTitle,_tmpDescription,_tmpImage,_tmpUser_id,_tmpFlag_active,_tmpType);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
