package org.sparkml.lightgbm;

import org.apache.spark.sql.types.StructType;
import org.jpmml.sparkml.ConverterFactory;
import org.jpmml.sparkml.SparkMLEncoder;

public class LightGBMSparkMLEncoder extends SparkMLEncoder {

    public LightGBMSparkMLEncoder(StructType schema, ConverterFactory converterFactory) {
        super(schema, converterFactory);
    }
}
