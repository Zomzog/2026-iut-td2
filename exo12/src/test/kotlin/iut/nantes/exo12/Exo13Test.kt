package iut.nantes.exo12

import assertk.assertThat
import assertk.assertions.isTrue
import assertk.assertions.startsWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.io.File
import javax.sql.DataSource
import kotlin.test.Test

@SpringBootTest
class Exo13Test {
    @Autowired
    lateinit var dataSource: DataSource

    @Test
    fun `database is stored in a file`() {
        val url = dataSource.connection.use { it.metaData.url }

        assertThat(url!!).startsWith("jdbc:h2:file:")
    }

    @Test
    fun `database file is created`() {
        dataSource.connection.use { }

        assertThat(File("data/testdb.mv.db").exists()).isTrue()
    }
}
