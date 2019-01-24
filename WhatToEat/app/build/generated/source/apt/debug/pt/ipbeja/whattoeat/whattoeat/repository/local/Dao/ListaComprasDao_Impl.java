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
import pt.ipbeja.whattoeat.whattoeat.repository.local.Entity.ListaCompras;

@SuppressWarnings("unchecked")
public class ListaComprasDao_Impl implements ListaComprasDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfListaCompras;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfListaCompras;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfListaCompras;

  private final SharedSQLiteStatement __preparedStmtOfDeleteCompra;

  public ListaComprasDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfListaCompras = new EntityInsertionAdapter<ListaCompras>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `ListaCompras`(`id`,`name`,`quantidade`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ListaCompras value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getQuantidade());
      }
    };
    this.__deletionAdapterOfListaCompras = new EntityDeletionOrUpdateAdapter<ListaCompras>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `ListaCompras` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ListaCompras value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfListaCompras = new EntityDeletionOrUpdateAdapter<ListaCompras>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `ListaCompras` SET `id` = ?,`name` = ?,`quantidade` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, ListaCompras value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        stmt.bindLong(3, value.getQuantidade());
        stmt.bindLong(4, value.getId());
      }
    };
    this.__preparedStmtOfDeleteCompra = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM ListaCompras WHERE id=?";
        return _query;
      }
    };
  }

  @Override
  public void insertListaCompras(ListaCompras... listaCompras) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfListaCompras.insert(listaCompras);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteListaCompras(ListaCompras listaCompras) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfListaCompras.handle(listaCompras);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateListaCompras(ListaCompras listaCompras) {
    __db.beginTransaction();
    try {
      __updateAdapterOfListaCompras.handle(listaCompras);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteCompra(long id) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteCompra.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, id);
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteCompra.release(_stmt);
    }
  }

  @Override
  public List<ListaCompras> getListaCompras() {
    final String _sql = "SELECT * FROM ListaCompras";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfName = _cursor.getColumnIndexOrThrow("name");
      final int _cursorIndexOfQuantidade = _cursor.getColumnIndexOrThrow("quantidade");
      final List<ListaCompras> _result = new ArrayList<ListaCompras>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final ListaCompras _item;
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        final String _tmpName;
        _tmpName = _cursor.getString(_cursorIndexOfName);
        final int _tmpQuantidade;
        _tmpQuantidade = _cursor.getInt(_cursorIndexOfQuantidade);
        _item = new ListaCompras(_tmpId,_tmpName,_tmpQuantidade);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
