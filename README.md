jpmml-lightgbm 实现了lightgbm模型转pmml <br>
pmml-sparkml 实现了spark ml库转pmml <br>
这两个仓库转的pmml格式不一致，所以本项目是将统合这两个仓库，将lightgbm按照spark-ml库的方式转pmml，从而达到输出格式一致的效果 <br>
<br>
requires: <br>
synapseml-lightgbm_2.12 : 0.11.4 <br>
pmml-sparkml : 2.2.3 <br>
spark_2.12 : 3.2.0 <br>
本项目是基于jpmml-lightgbm项目更改的，调整了部分实现至pmml-sparkml模式， 实现了LightGBMClassificationModel LightGBMRegressionModel分类与回归模型转Pmml<br>


examples:<br>
<br>
PMML pmml = new LightgbmPMMLBuilder(structType , pipelineModel).build();
