package org.sparkml.lightgbm;

import com.microsoft.azure.synapse.ml.lightgbm.LightGBMRegressionModel;
import com.microsoft.azure.synapse.ml.lightgbm.booster.LightGBMBooster;
import org.dmg.pmml.Model;
import org.jpmml.converter.Schema;
import org.jpmml.lightgbm.GBDT;
import org.jpmml.lightgbm.HasLightGBMOptions;
import org.jpmml.lightgbm.LightGBMUtil;
import org.jpmml.sparkml.RegressionModelConverter;
import org.jpmml.sparkml.SparkMLEncoder;
import org.jpmml.sparkml.model.HasTreeOptions;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class LightGBMRegressionModelConverter extends RegressionModelConverter<LightGBMRegressionModel>
        implements HasTreeOptions {
    public LightGBMRegressionModelConverter(LightGBMRegressionModel model) {
        super(model);
    }

    private GBDT gbdt = null;

    @Override
    public Model encodeModel(Schema schema) {
        LightGBMRegressionModel model = getModel();
        LightGBMBooster booster = model.getModel();

        if (gbdt == null) {
            gbdt = loadGBDT(booster);
        }


        Map<String, Object> options = new LinkedHashMap<>();
        options.put(HasLightGBMOptions.OPTION_COMPACT, true);
        options.put(HasLightGBMOptions.OPTION_NUM_ITERATION, null);
        Boolean nanAsMissing = (Boolean) options.get(HasLightGBMOptions.OPTION_NAN_AS_MISSING);

        return gbdt.encodeModel(options, schema);


        //todo 应用 nanAsMissing

//        return BoosterUtil.encodeBinaryClassificationBooster(booster, schema);
    }


    @Override
    public Schema encodeSchema(SparkMLEncoder encoder) {
        LightGBMSparkMLEncoder encoderV2 = (LightGBMSparkMLEncoder) encoder;
        LightGBMRegressionModel model = getModel();
        LightGBMBooster booster = model.getModel();
        if (gbdt == null) {
            gbdt = loadGBDT(booster);
        }

        return gbdt.encodeSchema(null, null, encoderV2);
    }


    public GBDT loadGBDT(LightGBMBooster booster) {
        byte[] bytes = booster.modelStr().get().getBytes();
        GBDT gbdt;
        try (InputStream is = new ByteArrayInputStream(bytes)) {
            gbdt = LightGBMUtil.loadGBDT(is);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        return gbdt;
    }


}