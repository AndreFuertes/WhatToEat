package pt.ipbeja.whattoeat.whattoeat.repository.local;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Callback;
import android.arch.persistence.db.SupportSQLiteOpenHelper.Configuration;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.RoomOpenHelper;
import android.arch.persistence.room.RoomOpenHelper.Delegate;
import android.arch.persistence.room.util.TableInfo;
import android.arch.persistence.room.util.TableInfo.Column;
import android.arch.persistence.room.util.TableInfo.ForeignKey;
import android.arch.persistence.room.util.TableInfo.Index;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;
import pt.ipbeja.whattoeat.whattoeat.repository.local.Dao.DespensaDao;
import pt.ipbeja.whattoeat.whattoeat.repository.local.Dao.DespensaDao_Impl;
import pt.ipbeja.whattoeat.whattoeat.repository.local.Dao.ListaComprasDao;
import pt.ipbeja.whattoeat.whattoeat.repository.local.Dao.ListaComprasDao_Impl;
import pt.ipbeja.whattoeat.whattoeat.repository.local.Dao.PostsDao;
import pt.ipbeja.whattoeat.whattoeat.repository.local.Dao.PostsDao_Impl;

@SuppressWarnings("unchecked")
public class AppDatabase_Impl extends AppDatabase {
  private volatile DespensaDao _despensaDao;

  private volatile ListaComprasDao _listaComprasDao;

  private volatile PostsDao _postsDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Despensa` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `item` TEXT, `quantidade` INTEGER NOT NULL, `validade` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `ListaCompras` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `quantidade` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Posts` (`post_id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT, `description` TEXT, `image` TEXT, `user_id` TEXT, `flag_active` TEXT, `type` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"7371516fad497d30623e8345db76a361\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Despensa`");
        _db.execSQL("DROP TABLE IF EXISTS `ListaCompras`");
        _db.execSQL("DROP TABLE IF EXISTS `Posts`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsDespensa = new HashMap<String, TableInfo.Column>(4);
        _columnsDespensa.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsDespensa.put("item", new TableInfo.Column("item", "TEXT", false, 0));
        _columnsDespensa.put("quantidade", new TableInfo.Column("quantidade", "INTEGER", true, 0));
        _columnsDespensa.put("validade", new TableInfo.Column("validade", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDespensa = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDespensa = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDespensa = new TableInfo("Despensa", _columnsDespensa, _foreignKeysDespensa, _indicesDespensa);
        final TableInfo _existingDespensa = TableInfo.read(_db, "Despensa");
        if (! _infoDespensa.equals(_existingDespensa)) {
          throw new IllegalStateException("Migration didn't properly handle Despensa(pt.ipbeja.whattoeat.whattoeat.repository.local.Entity.Despensa).\n"
                  + " Expected:\n" + _infoDespensa + "\n"
                  + " Found:\n" + _existingDespensa);
        }
        final HashMap<String, TableInfo.Column> _columnsListaCompras = new HashMap<String, TableInfo.Column>(3);
        _columnsListaCompras.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsListaCompras.put("name", new TableInfo.Column("name", "TEXT", false, 0));
        _columnsListaCompras.put("quantidade", new TableInfo.Column("quantidade", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysListaCompras = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesListaCompras = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoListaCompras = new TableInfo("ListaCompras", _columnsListaCompras, _foreignKeysListaCompras, _indicesListaCompras);
        final TableInfo _existingListaCompras = TableInfo.read(_db, "ListaCompras");
        if (! _infoListaCompras.equals(_existingListaCompras)) {
          throw new IllegalStateException("Migration didn't properly handle ListaCompras(pt.ipbeja.whattoeat.whattoeat.repository.local.Entity.ListaCompras).\n"
                  + " Expected:\n" + _infoListaCompras + "\n"
                  + " Found:\n" + _existingListaCompras);
        }
        final HashMap<String, TableInfo.Column> _columnsPosts = new HashMap<String, TableInfo.Column>(7);
        _columnsPosts.put("post_id", new TableInfo.Column("post_id", "INTEGER", true, 1));
        _columnsPosts.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsPosts.put("description", new TableInfo.Column("description", "TEXT", false, 0));
        _columnsPosts.put("image", new TableInfo.Column("image", "TEXT", false, 0));
        _columnsPosts.put("user_id", new TableInfo.Column("user_id", "TEXT", false, 0));
        _columnsPosts.put("flag_active", new TableInfo.Column("flag_active", "TEXT", false, 0));
        _columnsPosts.put("type", new TableInfo.Column("type", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysPosts = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesPosts = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoPosts = new TableInfo("Posts", _columnsPosts, _foreignKeysPosts, _indicesPosts);
        final TableInfo _existingPosts = TableInfo.read(_db, "Posts");
        if (! _infoPosts.equals(_existingPosts)) {
          throw new IllegalStateException("Migration didn't properly handle Posts(pt.ipbeja.whattoeat.whattoeat.repository.local.Entity.Posts).\n"
                  + " Expected:\n" + _infoPosts + "\n"
                  + " Found:\n" + _existingPosts);
        }
      }
    }, "7371516fad497d30623e8345db76a361", "55178536c4e7441c9ba4c0d7876c67de");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Despensa","ListaCompras","Posts");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Despensa`");
      _db.execSQL("DELETE FROM `ListaCompras`");
      _db.execSQL("DELETE FROM `Posts`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public DespensaDao getDespensaDao() {
    if (_despensaDao != null) {
      return _despensaDao;
    } else {
      synchronized(this) {
        if(_despensaDao == null) {
          _despensaDao = new DespensaDao_Impl(this);
        }
        return _despensaDao;
      }
    }
  }

  @Override
  public ListaComprasDao getListaComprasDao() {
    if (_listaComprasDao != null) {
      return _listaComprasDao;
    } else {
      synchronized(this) {
        if(_listaComprasDao == null) {
          _listaComprasDao = new ListaComprasDao_Impl(this);
        }
        return _listaComprasDao;
      }
    }
  }

  @Override
  public PostsDao getPostsDao() {
    if (_postsDao != null) {
      return _postsDao;
    } else {
      synchronized(this) {
        if(_postsDao == null) {
          _postsDao = new PostsDao_Impl(this);
        }
        return _postsDao;
      }
    }
  }
}
