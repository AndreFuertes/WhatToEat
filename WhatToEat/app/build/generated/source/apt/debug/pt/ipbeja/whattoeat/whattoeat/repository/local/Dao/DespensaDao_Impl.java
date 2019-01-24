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
import pt.ipbeja.whattoeat.whattoeat.repository.local.Entity.Despensa;

@SuppressWarnings("unchecked")
public class DespensaDao_Impl implements DespensaDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfDespensa;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfDespensa;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfDespensa;

  private final SharedSQLiteStatement __preparedStmtOfDeleteDespensa;

  public DespensaDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDespensa = new EntityInsertionAdapter<Despensa>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Despensa`(`id`,`item`,`quantidade`,`validade`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Despensa value) {
        stmt.bindLong(1, value.getId());
        if (value.getItem() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getItem());
        }
        stmt.bindLong(3, value.getQuantidade());
        if (value.getValidade() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getValidade());
        }
      }
    };
    this.__deletionAdapterOfDespensa = new EntityDeletionOrUpdateAdapter<Despensa>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Despensa` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Despensa value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfDespensa = new EntityDeletionOrUpdateAdapter<Despensa>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Despensa` SET `id` = ?,`item` = ?,`quantidade` = ?,`validade` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Despensa value) {
        stmt.bindLong(1, value.getId());
        if (value.getItem() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getItem());
        }
        stmt.bindLong(3, value.getQuantidade());
        if (value.getValidade() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getValidade());
        }
        stmt.bindLong(5, value.getId());
      }
    };
    this.__preparedStmtOfDeleteDespensa = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Despensa WHERE id=?";
        return _query;
      }
    };
  }

  @Override
  public void insertDespensa(Despensa... despensas) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfDespensa.insert(despensas);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteDespensa(Despensa despensa) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfDespensa.handle(despensa);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateDespensa(Despensa despensa) {
    __db.beginTransaction();
    try {
      __updateAdapterOfDespensa.handle(despensa);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteDespensa(long id) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteDespensa.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, id);
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteDespensa.release(_stmt);
    }
  }

  @Override
  public List<Despensa> getDespensa() {
    final String _sql = "SELECT * FROM Despensa";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfItem = _cursor.getColumnIndexOrThrow("item");
      final int _cursorIndexOfQuantidade = _cursor.getColumnIndexOrThrow("quantidade");
      final int _cursorIndexOfValidade = _cursor.getColumnIndexOrThrow("validade");
      final List<Despensa> _result = new ArrayList<Despensa>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Despensa _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpItem;
        _tmpItem = _cursor.getString(_cursorIndexOfItem);
        final int _tmpQuantidade;
        _tmpQuantidade = _cursor.getInt(_cursorIndexOfQuantidade);
        final String _tmpValidade;
        _tmpValidade = _cursor.getString(_cursorIndexOfValidade);
        _item = new Despensa(_tmpId,_tmpItem,_tmpQuantidade,_tmpValidade);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
