jpmml-lightgbm支持了转换pmml, 不过转换的没有spark ml格式统一。
所以本项目想柔和jpmml-lightgbm 和 pmml-sparkml项目, 将lightgbm转换的pmml格式统一为sparkml

requires:
synapseml-lightgbm_2.12:0.11.4
spark:3.2.0
pmml-sparkml:2.2.3
