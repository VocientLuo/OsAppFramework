# 海外包框架

4层混淆加密功能：
* 第一层：proguard-rules自带混淆；
* 第二层：增加dic.txt超级混淆字典；
* 第三层：XmlClassGuard混淆，对xml文件中嵌套的class进行混淆；
* 第四层：ResChiper混淆

# 使用说明

第一二层混淆，自研源码和第三方sdk在proguard文件中注意添加排除混淆即可。

第三层混淆执行 xmlClassGuardGoogleRelease任务，这是个修改源码的任务，不要在master上执行，可自行开分支执行。
执行后可能部分类会遗漏导致报错，按照报错提示把相应的类名和文件名称改成一致即可，部分package 可能也需要修改。

第三层混淆能运行之后，第四层是侵入式混淆，会直接构建aab包。执行resChiperGoogleRelease即可

最终得到的appName_versionName.aab即为可发布包
