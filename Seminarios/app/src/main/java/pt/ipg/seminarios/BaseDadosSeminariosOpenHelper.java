package pt.ipg.seminarios;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by RT on 11/11/2016.
 */

public final class BaseDadosSeminariosOpenHelper extends SQLiteOpenHelper {

    public static final int VERSAO = 1;
    public static final String NOME_BASE_DADOS = "seminarios.db";
    public static final boolean DADOS_FICTICIOS = true;

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use to open or create the database
     * @param name    of the database file, or null for an in-memory database
     * @param factory to use for creating cursor objects, or null for the default
     * @param version number of the database (starting at 1); if the database is older,
     *                {@link #onUpgrade} will be used to upgrade the database; if the database is
     *                newer, {@link #onDowngrade} will be used to downgrade the database
     */
    public BaseDadosSeminariosOpenHelper(Context context) {
        super(context, NOME_BASE_DADOS, null, VERSAO);
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        TabelaOradores tabelaOradores = new TabelaOradores(db);
        tabelaOradores.Cria();

        TabelaSeminarios tabelaSeminarios = new TabelaSeminarios(db);
        tabelaSeminarios.Cria();

        if (DADOS_FICTICIOS) {
            int idOrador = (int) tabelaOradores.Insere(new Orador("Rui Trigo"));
            long idSeminario = tabelaSeminarios.Insere(new Seminario(
                    "Síntese e reconhecimento de voz no Android",
                    idOrador,
                    "Interagir com aplicações através da voz.")
            );

            idOrador = (int) tabelaOradores.Insere(new Orador("Anabela Tavares"));
            idSeminario = tabelaSeminarios.Insere(new Seminario(
                    "Android Wear: Câmara Remota",
                    idOrador,
                    "Interação entre o relógio e a câmara do telemóvel.")
            );
        }
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     * <p>
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
