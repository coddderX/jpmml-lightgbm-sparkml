jpmml-lightgbm实现了lightgbm模型转pmml
spark-ml 实现了spark ml库转pmml
这两个仓库转的pmml格式不一致，所以本项目是将统合这两个仓库，将lightgbm按照spark-ml库的方式转pmml，从而达到输出格式一致的效果

requires:
synapseml-lightgbm_2.12:0.11.4
pmml-sparkml:2.2.3

本项目是基于jpmml-lightgbm项目更改的，调整了部分实现至pmml-sparkml模式， 实现了LightGBMClassificationModel LightGBMRegressionModel分类与回归模型转Pmml