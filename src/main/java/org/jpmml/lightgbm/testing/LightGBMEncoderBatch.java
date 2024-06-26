package org.jpmml.lightgbm.testing;///*
// * Copyright (c) 2020 Villu Ruusmann
// *
// * This file is part of JPMML-LightGBM
// *
// * JPMML-LightGBM is free software: you can redistribute it and/or modify
// * it under the terms of the GNU Affero General Public License as published by
// * the Free Software Foundation, either version 3 of the License, or
// * (at your option) any later version.
// *
// * JPMML-LightGBM is distributed in the hope that it will be useful,
// * but WITHOUT ANY WARRANTY; without even the implied warranty of
// * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// * GNU Affero General Public License for more details.
// *
// * You should have received a copy of the GNU Affero General Public License
// * along with JPMML-LightGBM.  If not, see <http://www.gnu.org/licenses/>.
// */
//package org.jpmml.lightgbm.testing;
//
//import java.io.InputStream;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.function.Predicate;
//
//import com.google.common.base.Equivalence;
//import org.dmg.pmml.PMML;
//import org.jpmml.converter.testing.ModelEncoderBatch;
//import org.jpmml.converter.testing.OptionsUtil;
//import org.jpmml.evaluator.ResultField;
//import org.jpmml.lightgbm.GBDT;
//import org.jpmml.lightgbm.HasLightGBMOptions;
//import org.jpmml.lightgbm.LightGBMUtil;
//import org.jpmml.lightgbm.ObjectiveFunction;
//
//abstract
//public class LightGBMEncoderBatch extends ModelEncoderBatch {
//
//	public LightGBMEncoderBatch(String algorithm, String dataset, Predicate<ResultField> columnFilter, Equivalence<Object> equivalence){
//		super(algorithm, dataset, columnFilter, equivalence);
//	}
//
//	@Override
//	abstract
//	public LightGBMEncoderBatchTest getArchiveBatchTest();
//
//	@Override
//	public List<Map<String, Object>> getOptionsMatrix(){
//		String dataset = getDataset();
//
//		Integer numIteration = null;
//
//		int index = dataset.indexOf('@');
//		if(index > -1){
//			numIteration = new Integer(dataset.substring(index + 1));
//		}
//
//		Map<String, Object> options = new LinkedHashMap<>();
//		options.put(HasLightGBMOptions.OPTION_COMPACT, new Boolean[]{false, true});
//		options.put(HasLightGBMOptions.OPTION_NAN_AS_MISSING, true);
//		options.put(HasLightGBMOptions.OPTION_NUM_ITERATION, numIteration);
//
//		return OptionsUtil.generateOptionsMatrix(options);
//	}
//
//	public ObjectiveFunction getObjectiveFunction(){
//		return null;
//	}
//
//	public String getModelTxtPath(){
//		return "/lgbm/" + (getAlgorithm() + truncate(getDataset())) + ".txt";
//	}
//
//	@Override
//	public PMML getPMML() throws Exception {
//		GBDT gbdt;
//
//		try(InputStream is = open(getModelTxtPath())){
//			gbdt = LightGBMUtil.loadGBDT(is);
//		}
//
//		ObjectiveFunction objectiveFunction = getObjectiveFunction();
//		if(objectiveFunction != null){
//			gbdt.setObjectiveFunction(objectiveFunction);
//		}
//
//		Map<String, ?> options = getOptions();
//
//		PMML pmml = gbdt.encodePMML(options, null, null);
//
//		validatePMML(pmml);
//
//		return pmml;
//	}
//
//	@Override
//	public String getInputCsvPath(){
//		return "/csv/" + truncate(getDataset()) + ".csv";
//	}
//
//	@Override
//	public String getOutputCsvPath(){
//		return super.getOutputCsvPath();
//	}
//}