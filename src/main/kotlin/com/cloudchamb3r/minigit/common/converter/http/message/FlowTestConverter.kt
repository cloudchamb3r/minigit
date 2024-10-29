import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.sequences.Sequence

class FlowTestSequenceConverter : HttpMessageConverter<Sequence<Any>> {

    override fun getSupportedMediaTypes(): List<MediaType> =
        listOf(MediaType.valueOf("application/x-flow-test"))

    override fun canRead(clazz: Class<*>, mediaType: MediaType?): Boolean =
        Sequence::class.java.isAssignableFrom(clazz) &&
                (mediaType == null || mediaType.includes(MediaType.valueOf("application/x-flow-test")))

    override fun canWrite(clazz: Class<*>, mediaType: MediaType?): Boolean =
        Sequence::class.java.isAssignableFrom(clazz) &&
                (mediaType == null || mediaType.includes(MediaType.valueOf("application/x-flow-test")))

    override fun read(clazz: Class<out Sequence<Any>>, inputMessage: HttpInputMessage): Sequence<Any> {
        val reader = InputStreamReader(inputMessage.body)
        // Implement logic to parse the input (e.g., JSON or another format) into a Sequence
        return sequenceOf(reader.readText().split(","))
    }

    override fun write(
        sequence: Sequence<Any>,
        contentType: MediaType?,
        outputMessage: HttpOutputMessage
    ) {
        val writer = OutputStreamWriter(outputMessage.body)
        val output = sequence.joinToString(",") { it.toString() }
        writer.write(output)
        writer.flush()
    }
}
