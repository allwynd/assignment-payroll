package  com.assignment.payroll.generator.utils;

import static com.assignment.payroll.generator.utils.EmpPayValidatorUtils.DATE_MMM_YYYY_FORMAT;

import java.io.IOException;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
/**
 * 
 * @author Allwyn
 *
 */
public class CustomDateTimeSerializer extends JsonSerializer<DateTime> 
{

  private static DateTimeFormatter formatter = DateTimeFormat.forPattern(DATE_MMM_YYYY_FORMAT);

    @Override
    public void serialize(DateTime value, JsonGenerator gen, 
                          SerializerProvider arg2)
        throws IOException, JsonProcessingException {

        gen.writeString(formatter.print(value));
    }
  
    

}