package com.acuo.collateral.learning;

import com.acuo.common.util.ResourceFile;
import org.eclipse.persistence.internal.helper.SerializationHelper;
import org.junit.Rule;
import org.junit.Test;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import static org.spockframework.util.Assert.fail;

public class SchemaValidation {

    @Test
    public void testSchemaValidationCreateMessage() {
        SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        try {
            Schema schema = schemaFactory.newSchema(new StreamSource(this.getClass().getResourceAsStream("/schema/Margin-Call-create.xsd")));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(getClass().getResourceAsStream("/call/Create_Call.xml")));
        } catch (Exception e) {
            fail("sax exception " + e.getMessage());
        }
    }
}
