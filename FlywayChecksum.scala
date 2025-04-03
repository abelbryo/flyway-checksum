//> using dep "org.flywaydb:flyway-core:11.5.0"
//> using dep "com.monovore::decline:2.5.0"

import cats.implicits.*
import com.monovore.decline.*
import org.flywaydb.core.api.Location
import org.flywaydb.core.internal.resolver.ChecksumCalculator
import org.flywaydb.core.internal.resource.filesystem.FileSystemResource

import java.nio.charset.Charset
import java.nio.file.Path
import java.nio.file.Paths

/* A simple tool to debug the checksum of a flyway SQL migration script */
object FlywayChecksum
    extends CommandApp(
      name = "flyway-checksum",
      header = "Calculate flyway checksum of a file",
      main = {
        val fileOpt  = Opts.argument[String]()
        val quietOpt = Opts.flag("quiet", short = "q", help = "Print only the checksum").orFalse

        (fileOpt, quietOpt).mapN: (file, quiet) =>
          val result = Util.calculateChecksum(file)
          if quiet then println(result)
          else println(s"""|Checksum of $file $result""".stripMargin)
      }
    )

object Util:

  def calculateChecksum(fileNameWithPath: String): Int =
    val resolvedPath: Path = Paths.get(fileNameWithPath).toAbsolutePath.normalize
    val resource           = new FileSystemResource(null, resolvedPath.toString, Charset.defaultCharset(), false)
    ChecksumCalculator.calculate(resource)
